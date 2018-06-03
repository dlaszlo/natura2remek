package hu.dlaszlo.natura2remek;

import hu.dlaszlo.natura2remek.csv.NaturaCsvService;
import hu.dlaszlo.natura2remek.csv.NaturaCsvServiceImpl;
import hu.dlaszlo.natura2remek.csv.RemekCsvService;
import hu.dlaszlo.natura2remek.csv.RemekCsvServiceImpl;
import hu.dlaszlo.natura2remek.csv.naturacsv.NaturaCsvPackage;
import hu.dlaszlo.natura2remek.csv.remekcsv.RemekCsvPackage;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Parancssoros indító
 */
public class Natura2Remek {

    private static final Logger LOGGER = LoggerFactory.getLogger(Natura2Remek.class);

    private static void checkInputFile(String inputFile)
    {
        Path p = Paths.get(inputFile);
        if (!Files.exists(p) || !Files.isRegularFile(p) || !Files.isReadable(p))
        {
            throw new RuntimeException("A fájl nem létezik, vagy nem olvasható: " + inputFile);
        }
    }

    private static void checkOutputFile(String outputFile)
    {
        Path p = Paths.get(outputFile);
        if (Files.exists(p))
        {
            throw new RuntimeException("A fájl már létezik: " + outputFile);
        }
    }

    private static Options getOptions() {
        Options options = new Options();

        Option inputFile = Option.builder("i")
                .longOpt("input").required()
                .hasArg().numberOfArgs(1).argName("natura_file")
                .desc("Naturasoft főkönyv feladás állomány CSV állomány")
                .build();
        options.addOption(inputFile);

        Option outputFile = Option.builder("o")
                .longOpt("output").required()
                .hasArg().numberOfArgs(1).argName("remek_file")
                .desc("Remek főkönyv feladás állomány")
                .build();
        options.addOption(outputFile);
        return options;
    }

    public static void main(String[] args)
    {
        CommandLineParser parser = new DefaultParser();

        Options options = getOptions();

        try
        {
            CommandLine commandLine = parser.parse(options, args);

            String inputFile = commandLine.getOptionValue("i");
            checkInputFile(inputFile);

            String outputFile = commandLine.getOptionValue("o");
            checkOutputFile(outputFile);

            NaturaCsvService naturaCsvService = new NaturaCsvServiceImpl();
            NaturaCsvPackage naturaCsvPackage = naturaCsvService.loadCSV(inputFile);
            if (!naturaCsvPackage.getErrors().isEmpty())
            {
                throw new RuntimeException("Az adathibák miatt a konvertálás nem folytatható.");
            }

            RemekCsvService remekCsvService = new RemekCsvServiceImpl();
            RemekCsvPackage remekCsvPackage = remekCsvService.convert(naturaCsvPackage);
            if (!remekCsvPackage.getErrors().isEmpty())
            {
                throw new RuntimeException("Az adathibák miatt a konvertálás nem folytatható.");
            }

            remekCsvService.saveCSV(outputFile, remekCsvPackage);
        }
        catch (ParseException e)
        {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp(Natura2Remek.class.getSimpleName(), options,true );
        }
        catch (Exception e)
        {
            LOGGER.error("Hiba történt", e);
        }

    }
}
