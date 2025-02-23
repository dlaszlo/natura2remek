package hu.dlaszlo.natura2remek.csv.naturacsv;

import hu.dlaszlo.natura2remek.csv.AbstractEntity;
import hu.dlaszlo.natura2remek.csv.CsvError;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Natura CSV formátumú adatok
 */
public class NaturaCsvPackage extends AbstractEntity implements Serializable
{
    private static final long serialVersionUID = 9025250011502985683L;

    /**
     * Fájl formátum
     */
    @NotNull
    private FileFormat csvFormat;

    /**
     * Számla fejléc
     */
    @NotEmpty
    private List<InvoiceHeader> invoiceHeaders = new ArrayList<>();

    /**
     * Betöltés hibák
     */
    private Set<CsvError> errors;

    @Override
    public String getTipus()
    {
        return "CSV fájl";
    }

    /**
     * Konstruktor
     *
     * @param lineFrom CSV rekordtól
     * @param lineTo   CSV rekordig
     */
    public NaturaCsvPackage(int lineFrom, int lineTo)
    {
        super(lineFrom, lineTo);
    }

    /**
     * @see NaturaCsvPackage#csvFormat
     */
    public FileFormat getCsvFormat()
    {
        return csvFormat;
    }

    /**
     * @see NaturaCsvPackage#csvFormat
     */
    public void setCsvFormat(FileFormat csvFormat)
    {
        this.csvFormat = csvFormat;
    }

    /**
     * @see NaturaCsvPackage#invoiceHeaders
     */
    public List<InvoiceHeader> getInvoiceHeaders()
    {
        return invoiceHeaders;
    }

    /**
     * @see NaturaCsvPackage#invoiceHeaders
     */
    public void setInvoiceHeaders(List<InvoiceHeader> invoiceHeaders)
    {
        this.invoiceHeaders = invoiceHeaders;
    }

    /**
     * @see NaturaCsvPackage#errors
     */
    public Set<CsvError> getErrors()
    {
        return errors;
    }

    /**
     * @see NaturaCsvPackage#errors
     */
    public void setErrors(Set<CsvError> errors)
    {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        if (!super.equals(o))
        {
            return false;
        }
        NaturaCsvPackage that = (NaturaCsvPackage) o;
        return Objects.equals(csvFormat, that.csvFormat) &&
                Objects.equals(invoiceHeaders, that.invoiceHeaders) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), csvFormat, invoiceHeaders, errors);
    }

    @Override
    public String toString()
    {
        return "NaturaCsvPackage{" +
                "csvFormat=" + csvFormat +
                ", invoiceHeaders=" + invoiceHeaders +
                ", errors=" + errors +
                '}';
    }
}
