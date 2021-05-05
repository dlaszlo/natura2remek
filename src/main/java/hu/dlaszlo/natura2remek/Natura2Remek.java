package hu.dlaszlo.natura2remek;

import hu.dlaszlo.natura2remek.config.ConfigService;
import hu.dlaszlo.natura2remek.csv.NaturaCsvService;
import hu.dlaszlo.natura2remek.csv.RemekCsvService;
import hu.dlaszlo.natura2remek.csv.naturacsv.NaturaCsvPackage;
import hu.dlaszlo.natura2remek.csv.remekcsv.RemekCsvPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Parancssoros indító
 */
@SpringBootApplication
public class Natura2Remek implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Natura2Remek.class);

    @Autowired
    private NaturaCsvService naturaCsvService;

    @Autowired
    private RemekCsvService remekCsvService;

    @Autowired
    private ConfigService configService;

    private static void checkInputFile(String inputFile) {
        Path p = Paths.get(inputFile);
        if (!Files.exists(p) || !Files.isRegularFile(p) || !Files.isReadable(p)) {
            throw new RuntimeException("A fájl nem létezik, vagy nem olvasható: " + inputFile);
        }
    }

    private static void checkOutputFile(String outputFile) {
        Path p = Paths.get(outputFile);
        if (Files.exists(p)) {
            throw new RuntimeException("A fájl már létezik: " + outputFile);
        }
    }

    @Override
    public void run(String... args) {
        try {
            String datePrefix = LocalDate.now(ZoneId.systemDefault())
                    .minusMonths(1)
                    .format(DateTimeFormatter.ofPattern("yyyy_MM_"));

            String inputFile = Paths.get(configService.getNaturaDir(), datePrefix + "feladas.csv").toAbsolutePath().toString();
            checkInputFile(inputFile);

            String outputFile = Paths.get(configService.getRemekDir(), datePrefix + "remek.csv").toAbsolutePath().toString();
            checkOutputFile(outputFile);

            NaturaCsvPackage naturaCsvPackage = naturaCsvService.loadCSV(inputFile);
            if (!naturaCsvPackage.getErrors().isEmpty()) {
                throw new RuntimeException("Az adathibák miatt a konvertálás nem folytatható.");
            }

            RemekCsvPackage remekCsvPackage = remekCsvService.convert(naturaCsvPackage);
            if (!remekCsvPackage.getErrors().isEmpty()) {
                throw new RuntimeException("Az adathibák miatt a konvertálás nem folytatható.");
            }

            remekCsvService.saveCSV(outputFile, remekCsvPackage);
        } catch (Exception e) {
            LOGGER.error("Hiba történt", e);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Natura2Remek.class, args);
    }

}
