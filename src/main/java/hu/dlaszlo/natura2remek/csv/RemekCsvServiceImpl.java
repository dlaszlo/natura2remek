package hu.dlaszlo.natura2remek.csv;

import hu.dlaszlo.natura2remek.config.ConfigService;
import hu.dlaszlo.natura2remek.csv.naturacsv.Customer;
import hu.dlaszlo.natura2remek.csv.naturacsv.InvoiceHeader;
import hu.dlaszlo.natura2remek.csv.naturacsv.InvoiceItem;
import hu.dlaszlo.natura2remek.csv.naturacsv.NaturaCsvPackage;
import hu.dlaszlo.natura2remek.csv.remekcsv.RemekCsvPackage;
import hu.dlaszlo.natura2remek.csv.remekcsv.RemekCsvRow;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Remek CSV fájl konvertálása, validálása, mentése - megvalósítás
 */
@Service
public class RemekCsvServiceImpl implements RemekCsvService
{

    private static final Logger LOGGER
            = LoggerFactory.getLogger(RemekCsvServiceImpl.class);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    @Autowired
    private ConfigService configService;

    @Override
    public RemekCsvPackage convert(NaturaCsvPackage naturaCsvPackage)
    {
        LOGGER.info("Natura CSV -> Remek CSV konvertálás");
        Validate.isTrue(naturaCsvPackage.getErrors().isEmpty());

        List<RemekCsvRow> remekCsvRowList = new ArrayList<>();

        int record = 1;

        for (InvoiceHeader invoiceHeader : naturaCsvPackage.getInvoiceHeaders())
        {
            Customer customer = invoiceHeader.getCustomer();
            if (customer == null)
            {
                customer = new Customer(0, 0);
                customer.setUgyfelKod(0L);
            }

            for (InvoiceItem invoiceItem : invoiceHeader.getInvoiceItems())
            {
                RemekCsvRow remekCsvRow = new RemekCsvRow(record, record);

                remekCsvRow.setSzamlaszam(invoiceHeader.getBizonylatszam());
                remekCsvRow.setKiallitasDatuma(getDate(invoiceHeader.getKiallitasNap()));
                remekCsvRow.setTeljesitesKelte(getDate(invoiceHeader.getTeljesitesNap()));
                remekCsvRow.setFizetesiHatarido(getDate(invoiceHeader.getFizetesiHatarido()));
                remekCsvRow.setFizetesiMod(getString(invoiceHeader.getFizetesiMod(), 20));
                remekCsvRow.setAfaSzazalek(getBigDecimal(invoiceItem.getAfaKulcs()));
                remekCsvRow.setNetto(getBigDecimal(invoiceItem.getNettoOsszesenAr()));
                remekCsvRow.setAfa(getBigDecimal(invoiceItem.getAfaOsszesenAr()));
                remekCsvRow.setBrutto(getBigDecimal(invoiceItem.getBruttoOsszesenAr()));
                remekCsvRow.setMegnevezes(getString(invoiceItem.getTermekMegnevezes(), 40));
                remekCsvRow.setVevokod(getLong(customer.getUgyfelKod() + 400000L));
                remekCsvRow.setVevonev(getString(customer.getNev(), 60));
                remekCsvRow.setIrszam(getString(customer.getIrszam(), 10));
                remekCsvRow.setVaros(getString(customer.getVaros(), 40));
                remekCsvRow.setUtcaHazszam(getString(customer.getCim(), 40));
                remekCsvRow.setVevoAdoszam(getString(customer.getAdoszam(), 20));
                String fokonyviSzam = null;
                if (StringUtils.isNotBlank(invoiceItem.getCikkszam()))
                {
                    fokonyviSzam = configService.getFokonyviSzamok().get(invoiceItem.getCikkszam().trim());
                }

                remekCsvRow.setKontirozasiParameter1(fokonyviSzam == null ? "999" : fokonyviSzam);
                remekCsvRow.setKontirozasiParameter2("");
                remekCsvRow.setKontirozasiParameter3("");
                remekCsvRow.setKontirozasiParameter4("");
                remekCsvRow.setKontirozasiParameter5("");
                remekCsvRow.setKontirozasiParameter6("");
                if (StringUtils.isNotBlank(invoiceHeader.getPenznem()) &&
                        !"HUF".equals(invoiceHeader.getPenznem()))
                {
                    remekCsvRow.setDevizaNem(invoiceHeader.getPenznem());
                    remekCsvRow.setNettoDeviza(getBigDecimal(invoiceItem.getNettoOsszesenAr()));
                    remekCsvRow.setAfaDeviza(getBigDecimal(invoiceItem.getAfaOsszesenAr()));
                    remekCsvRow.setBruttoDeviza(getBigDecimal(invoiceItem.getBruttoOsszesenAr()));
                }
                else
                {
                    remekCsvRow.setDevizaNem("");
                    remekCsvRow.setNettoDeviza("");
                    remekCsvRow.setAfaDeviza("");
                    remekCsvRow.setBruttoDeviza("");
                }
                remekCsvRow.setVevoCimeEgyben("");
                remekCsvRow.setVevoEUAdoszam(getString(customer.getKozossegiAdoszam(), 20));

                remekCsvRowList.add(remekCsvRow);
                record++;
            }
        }

        LOGGER.info("{} db. rekord", remekCsvRowList.size());

        RemekCsvPackage remekCsvPackage = new RemekCsvPackage(1, Math.max(remekCsvRowList.size(), 1));
        remekCsvPackage.setRows(remekCsvRowList);

        Set<CsvError> errors = validate(remekCsvPackage);
        remekCsvPackage.setErrors(errors);

        if (!errors.isEmpty())
        {
            for (CsvError csvError : errors)
            {
                LOGGER.error(csvError.toString());
            }
        }

        return remekCsvPackage;
    }

    private CsvError getMessage(ConstraintViolation<?> violation)
    {
        AbstractEntity remekEntity = (AbstractEntity) violation.getLeafBean();
        CsvError csvError = new CsvError(
                remekEntity.getRecordFrom(), remekEntity.getRecordTo(),
                remekEntity.getTipus(), violation.getPropertyPath().toString(), violation.getMessage()
        );
        return csvError;
    }

    private Set<CsvError> validate(RemekCsvPackage remekCsvPackage)
    {
        LOGGER.info("Validálás");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<?>> errors = new LinkedHashSet<>();
        errors.addAll(validator.validate(remekCsvPackage));
        for (RemekCsvRow remekCsvRow : remekCsvPackage.getRows())
        {
            errors.addAll(validator.validate(remekCsvRow));
        }

        Set<CsvError> messages = new LinkedHashSet<>();
        messages.addAll(errors.stream().map(this::getMessage).collect(Collectors.toList()));
        LOGGER.info("{} db. hiba", messages.size());
        return messages;
    }

    private String getDate(LocalDate date)
    {
        return date == null ? "" : DATE_FORMATTER.format(date);
    }

    private String getString(String s, int maxLen)
    {
        return s == null ? "" : StringUtils.left(StringUtils.replace(s.trim(), ";", ""), maxLen);
    }

    private String getBigDecimal(BigDecimal d)
    {
        String ret;
        if (d != null)
        {
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setDecimalSeparator(',');
            decimalFormatSymbols.setMinusSign('-');
            DecimalFormat decimalFormat = new DecimalFormat("0.0000", decimalFormatSymbols);
            decimalFormat.setGroupingUsed(false);
            ret = decimalFormat.format(d);
        }
        else
        {
            ret = "";
        }
        return ret;
    }

    private String getLong(Long l)
    {
        return l == null ? "" : l.toString();
    }

    @Override
    public void saveCSV(String filename, RemekCsvPackage remekCsvPackage)
    {
        LOGGER.info("Remek CSV mentése: {}", filename);

        Path p = Paths.get(filename);

        try (BufferedWriter out = Files.newBufferedWriter(p, Charset.forName("ISO-8859-2")))
        {

            try (CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.newFormat(';').withEscape(null).withQuote(null).withRecordSeparator("\r\n")))
            {

                String[] header = new String[]{
                        "szamlaszam",
                        "kiallitasDatuma",
                        "teljesitesKelte",
                        "fizetesiHatarido",
                        "fizetesiMod",
                        "afaSzazalek",
                        "netto",
                        "afa",
                        "brutto",
                        "megnevezes",
                        "vevokod",
                        "vevonev",
                        "irszam",
                        "varos",
                        "utcaHazszam",
                        "vevoAdoszam",
                        "kontirozasiParameter1",
                        "kontirozasiParameter2",
                        "kontirozasiParameter3",
                        "kontirozasiParameter4",
                        "kontirozasiParameter5",
                        "kontirozasiParameter6",
                        "devizaNem",
                        "nettoDeviza",
                        "afaDeviza",
                        "bruttoDeviza",
                        "vevoCimeEgyben",
                        "vevoEUAdoszam"
                };

                csvPrinter.printRecord(Arrays.asList(header));

                for (RemekCsvRow remekCsvRow : remekCsvPackage.getRows())
                {
                    String[] row = new String[]{
                            remekCsvRow.getSzamlaszam(),
                            remekCsvRow.getKiallitasDatuma(),
                            remekCsvRow.getTeljesitesKelte(),
                            remekCsvRow.getFizetesiHatarido(),
                            remekCsvRow.getFizetesiMod(),
                            remekCsvRow.getAfaSzazalek(),
                            remekCsvRow.getNetto(),
                            remekCsvRow.getAfa(),
                            remekCsvRow.getBrutto(),
                            remekCsvRow.getMegnevezes(),
                            remekCsvRow.getVevokod(),
                            remekCsvRow.getVevonev(),
                            remekCsvRow.getIrszam(),
                            remekCsvRow.getVaros(),
                            remekCsvRow.getUtcaHazszam(),
                            remekCsvRow.getVevoAdoszam(),
                            remekCsvRow.getKontirozasiParameter1(),
                            remekCsvRow.getKontirozasiParameter2(),
                            remekCsvRow.getKontirozasiParameter3(),
                            remekCsvRow.getKontirozasiParameter4(),
                            remekCsvRow.getKontirozasiParameter5(),
                            remekCsvRow.getKontirozasiParameter6(),
                            remekCsvRow.getDevizaNem(),
                            remekCsvRow.getNettoDeviza(),
                            remekCsvRow.getAfaDeviza(),
                            remekCsvRow.getBruttoDeviza(),
                            remekCsvRow.getVevoCimeEgyben(),
                            remekCsvRow.getVevoEUAdoszam()
                    };

                    csvPrinter.printRecord(Arrays.asList(row));
                }

                csvPrinter.flush();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        LOGGER.info("A Remek CSV mentése végetért. {} db rekord.", remekCsvPackage.getRows().size());

    }
}
