package hu.dlaszlo.natura2remek.csv;

import hu.dlaszlo.natura2remek.csv.naturacsv.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Natura CSV fájl betöltése, validálása - megvalósítás
 */
@Singleton
public class NaturaCsvServiceImpl implements NaturaCsvService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(NaturaCsvServiceImpl.class);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd.");

    @Override
    public NaturaCsvPackage loadCSV(String fileName)
    {
        NaturaCsvPackage naturaCsvPackage = null;

        LOGGER.info("CSV fájl betöltése: {}", fileName);
        Path p = Paths.get(fileName);
        try (Reader in = Files.newBufferedReader(p, Charset.defaultCharset()))
        {
            List<CSVRecord> recordList = CSVFormat.newFormat('|')
                    .withAllowMissingColumnNames()
                    .withTrim()
                    .withEscape('\\')
                    .withQuoteMode(QuoteMode.NONE)
                    .parse(in).getRecords();

            LOGGER.info("{} db. rekord", recordList.size());

            naturaCsvPackage = new NaturaCsvPackage(1, recordList.size());
            FileFormat csvFormat = parseFormatum(
                    recordList.subList(0, Math.min(recordList.size(), 2)));
            naturaCsvPackage.setCsvFormat(csvFormat);

            InvoiceHeader invoiceHeader = null;

            if (recordList.size() > 2)
            {
                for (int i = 2; i < recordList.size(); i++)
                {
                    CSVRecord record = recordList.get(i);
                    String type = record.size() > 0 ? record.get(0) : null;
                    switch (record.get(0))
                    {
                        case InvoiceHeader.TYPE:
                        {
                            invoiceHeader = parseFejlec(record);
                            naturaCsvPackage.getInvoiceHeaders().add(invoiceHeader);
                            break;
                        }
                        case Customer.TYPE:
                        {
                            Validate.isTrue(invoiceHeader != null);
                            Validate.isTrue(invoiceHeader.getCustomer() == null);
                            invoiceHeader.setCustomer(parsePartner(record));
                            break;
                        }
                        case InvoiceItem.TYPE:
                        {
                            Validate.isTrue(invoiceHeader != null);
                            invoiceHeader.getInvoiceItems().add(parseTetel(record));
                            break;
                        }
                        default:
                        {
                            throw new IllegalArgumentException("Érvénytelen típus: " + type);
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        Set<CsvError> errors = validate(naturaCsvPackage);
        naturaCsvPackage.setErrors(errors);

        if (!errors.isEmpty())
        {
            for (CsvError csvError : errors)
            {
                LOGGER.error(csvError.toString());
            }
        }

        return naturaCsvPackage;
    }

    private CsvError getMessage(ConstraintViolation<?> violation)
    {
        AbstractEntity naturaEntity = (AbstractEntity) violation.getLeafBean();
        CsvError csvError = new CsvError(
                naturaEntity.getRecordFrom(), naturaEntity.getRecordTo(),
                naturaEntity.getTipus(), violation.getPropertyPath().toString(), violation.getMessage()
        );
        return csvError;
    }

    private Set<CsvError> validate(NaturaCsvPackage naturaCsvPackage)
    {
        LOGGER.info("Validálás");
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<?>> errors = new LinkedHashSet<>();

        errors.addAll(validator.validate(naturaCsvPackage));
        if (naturaCsvPackage.getCsvFormat() != null)
        {
            errors.addAll(validator.validate(naturaCsvPackage.getCsvFormat()));
        }

        for (InvoiceHeader invoiceHeader : naturaCsvPackage.getInvoiceHeaders())
        {
            errors.addAll(validator.validate(invoiceHeader));
            if (invoiceHeader.getCustomer() != null)
            {
                errors.addAll(validator.validate(invoiceHeader.getCustomer()));
            }
            for (InvoiceItem invoiceItem : invoiceHeader.getInvoiceItems())
            {
                errors.addAll(validator.validate(invoiceItem));
            }
        }

        Set<CsvError> messages = new LinkedHashSet<>();
        messages.addAll(errors.stream().map(this::getMessage).collect(Collectors.toList()));
        LOGGER.info("{} db. hiba", messages.size());
        return messages;
    }

    private FileFormat parseFormatum(List<CSVRecord> header)
    {
        FileFormat csvFormat = new FileFormat(1, header.size());
        if (header.size() > 0 && header.get(0).size() > 1)
        {
            csvFormat.setDescription1(header.get(0).get(0));
            csvFormat.setVersion(header.get(0).get(1));
        }
        if (header.size() > 1 && header.get(1).size() > 0)
        {
            csvFormat.setDescription2(header.get(1).get(0));
        }
        return csvFormat;
    }

    private InvoiceHeader parseFejlec(CSVRecord record)
    {
        Validate.isTrue(InvoiceHeader.TYPE.equals(record.get(0)));
        InvoiceHeader invoiceHeader = new InvoiceHeader((int) record.getRecordNumber(),
                                                        (int) record.getRecordNumber());
        int i = 0;
        invoiceHeader.setTipus(parseString(record.get(i++)));
        invoiceHeader.setBizonylatszam(parseString(record.get(i++)));
        invoiceHeader.setSzamlaTipus(parseString(record.get(i++)));
        invoiceHeader.setKiallitoCegNev(parseString(record.get(i++)));
        invoiceHeader.setKiallitoCegAdat(parseString(record.get(i++)));
        invoiceHeader.setVevoKod(parseLong(record.get(i++)));
        invoiceHeader.setVevoNev(parseString(record.get(i++)));
        invoiceHeader.setVevoEgyebAdat(parseString(record.get(i++)));
        invoiceHeader.setFizetesiModKod(parseLong(record.get(i++)));
        invoiceHeader.setFizetesiMod(parseString(record.get(i++)));
        invoiceHeader.setKiallitasNap(parseDate(record.get(i++)));
        invoiceHeader.setTeljesitesNap(parseDate(record.get(i++)));
        invoiceHeader.setFizetesiHatarido(parseDate(record.get(i++)));
        invoiceHeader.setKiegyenlitesNapja(parseDate(record.get(i++)));
        invoiceHeader.setPenznem(parseString(record.get(i++)));
        invoiceHeader.setArfolyam(parseBigDecimal(record.get(i++)));
        invoiceHeader.setNettoVegosszeg(parseBigDecimal(record.get(i++)));
        invoiceHeader.setAfaVegosszeg(parseBigDecimal(record.get(i++)));
        invoiceHeader.setBruttoVegosszeg(parseBigDecimal(record.get(i++)));
        invoiceHeader.setKiegyenlitettBruttoOsszeg(parseBigDecimal(record.get(i++)));
        invoiceHeader.setMegjegyzes1(parseString(record.get(i++)));
        invoiceHeader.setMegjegyzes2(parseString(record.get(i++)));
        invoiceHeader.setStornoVagyHelyesbSzamlaszam(parseString(record.get(i++)));
        invoiceHeader.setStornoVagyHelyesbitoTipus(parseString(record.get(i++)));
        invoiceHeader.setSzamlacsoportKod(parseLong(record.get(i++)));
        invoiceHeader.setSzamlacsoportMegnevezes(parseString(record.get(i++)));
        invoiceHeader.setSzallitolevelBizonylatszam(parseString(record.get(i++)));
        Validate.isTrue(i == record.size());
        return invoiceHeader;
    }

    private Customer parsePartner(CSVRecord record)
    {
        Validate.isTrue(Customer.TYPE.equals(record.get(0)));
        Customer customer = new Customer((int) record.getRecordNumber(),
                                         (int) record.getRecordNumber());
        int i = 0;
        customer.setTipus(parseString(record.get(i++)));
        customer.setUgyfelKod(parseLong(record.get(i++)));
        customer.setNev(parseString(record.get(i++)));
        customer.setOrszag(parseString(record.get(i++)));
        customer.setIrszam(parseString(record.get(i++)));
        customer.setVaros(parseString(record.get(i++)));
        customer.setCim(parseString(record.get(i++)));
        customer.setSzallitasNev(parseString(record.get(i++)));
        customer.setSzallitasOrszag(parseString(record.get(i++)));
        customer.setSzallitasIrszam(parseString(record.get(i++)));
        customer.setSzallitasVaros(parseString(record.get(i++)));
        customer.setSzallitasCim(parseString(record.get(i++)));
        customer.setKapcsolattartoNev(parseString(record.get(i++)));
        customer.setTelefonszam(parseString(record.get(i++)));
        customer.setFax(parseString(record.get(i++)));
        customer.setEmail(parseString(record.get(i++)));
        customer.setAdoszam(parseString(record.get(i++)));
        customer.setKozossegiAdoszam(parseString(record.get(i++)));
        customer.setBankszamlaszam(parseString(record.get(i++)));
        customer.setHonlapCim(parseString(record.get(i++)));
        customer.setKlubkartya(parseString(record.get(i++)));
        customer.setCsoportKod1(parseLong(record.get(i++)));
        customer.setCsoport1(parseString(record.get(i++)));
        customer.setCsoportKod2(parseLong(record.get(i++)));
        customer.setCsoport2(parseString(record.get(i++)));
        customer.setCsoportKod3(parseLong(record.get(i++)));
        customer.setCsoport3(parseString(record.get(i++)));
        Validate.isTrue(i == record.size());
        return customer;
    }

    private InvoiceItem parseTetel(CSVRecord record)
    {
        Validate.isTrue(InvoiceItem.TYPE.equals(record.get(0)));
        InvoiceItem invoiceItem = new InvoiceItem((int) record.getRecordNumber(),
                                                  (int) record.getRecordNumber());
        int i = 0;
        invoiceItem.setTipus(parseString(record.get(i++)));
        invoiceItem.setTermekKod(parseLong(record.get(i++)));
        invoiceItem.setTermekMegnevezes(parseString(record.get(i++)));
        invoiceItem.setTermekSzolgaltatasKod(parseString(record.get(i++)));
        invoiceItem.setCikkszam(parseString(record.get(i++)));
        invoiceItem.setVtszSzjTeszor(parseString(record.get(i++)));
        invoiceItem.setMennyiseg(parseBigDecimal(record.get(i++)));
        invoiceItem.setMennyisegiEgyseg(parseString(record.get(i++)));
        invoiceItem.setNettoAr(parseBigDecimal(record.get(i++)));
        invoiceItem.setBruttoAr(parseBigDecimal(record.get(i++)));
        invoiceItem.setKedvezmeny(parseBigDecimal(record.get(i++)));
        invoiceItem.setNettoEgysegAr(parseBigDecimal(record.get(i++)));
        invoiceItem.setBruttoEgysegAr(parseBigDecimal(record.get(i++)));
        invoiceItem.setAfaKulcsKod(parseLong(record.get(i++)));
        invoiceItem.setAfaKulcsRovid(parseString(record.get(i++)));
        invoiceItem.setAfaKulcsLeiras(parseString(record.get(i++)));
        invoiceItem.setAfaKulcs(parseBigDecimal(record.get(i++)));
        invoiceItem.setNettoOsszesenAr(parseBigDecimal(record.get(i++)));
        invoiceItem.setAfaOsszesenAr(parseBigDecimal(record.get(i++)));
        invoiceItem.setBruttoOsszesenAr(parseBigDecimal(record.get(i++)));
        invoiceItem.setMegjegyzes(parseString(record.get(i++)));
        invoiceItem.setRaktarhelyKod(parseLong(record.get(i++)));
        invoiceItem.setRaktarhely(parseString(record.get(i++)));
        Validate.isTrue(i == record.size());
        return invoiceItem;
    }


    private String parseString(String s)
    {
        return StringUtils.trimToNull(s);
    }

    private Long parseLong(String s)
    {
        return StringUtils.isBlank(s) ? null : Long.valueOf(s);
    }

    private LocalDate parseDate(String d)
    {
        return StringUtils.isBlank(d) ? null : LocalDate.parse(d, DATE_FORMATTER);
    }

    private BigDecimal parseBigDecimal(String s)
    {
        BigDecimal b = null;
        if (StringUtils.isNotBlank(s))
        {
            b = new BigDecimal(s.replace(',', '.'));
        }
        return b;
    }

}
