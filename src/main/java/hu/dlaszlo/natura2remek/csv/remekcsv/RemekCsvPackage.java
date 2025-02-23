package hu.dlaszlo.natura2remek.csv.remekcsv;

import hu.dlaszlo.natura2remek.csv.AbstractEntity;
import hu.dlaszlo.natura2remek.csv.CsvError;
import hu.dlaszlo.natura2remek.csv.naturacsv.FileFormat;
import hu.dlaszlo.natura2remek.csv.naturacsv.InvoiceHeader;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Remek CSV formátumú adatok
 */
public class RemekCsvPackage extends AbstractEntity implements Serializable
{

    private static final long serialVersionUID = 3645010695393936059L;

    /**
     * Számla fejléc
     */
    @NotEmpty
    private List<RemekCsvRow> rows = new ArrayList<>();

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
    public RemekCsvPackage(int lineFrom, int lineTo)
    {
        super(lineFrom, lineTo);
    }

    /**
     * @see RemekCsvPackage#rows
     */
    public List<RemekCsvRow> getRows()
    {
        return rows;
    }

    /**
     * @see RemekCsvPackage#rows
     */
    public void setRows(List<RemekCsvRow> rows)
    {
        this.rows = rows;
    }

    /**
     * @see RemekCsvPackage#errors
     */
    public Set<CsvError> getErrors()
    {
        return errors;
    }

    /**
     * @see RemekCsvPackage#errors
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
        RemekCsvPackage that = (RemekCsvPackage) o;
        return Objects.equals(rows, that.rows) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), rows, errors);
    }

    @Override
    public String toString()
    {
        return "RemekCsvPackage{" +
                "rows=" + rows +
                ", errors=" + errors +
                '}';
    }

}
