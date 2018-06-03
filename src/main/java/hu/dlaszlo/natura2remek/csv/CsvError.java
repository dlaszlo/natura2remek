package hu.dlaszlo.natura2remek.csv;

import java.io.Serializable;
import java.util.Objects;

/**
 * Adathiba leírása
 */
public class CsvError implements Serializable
{
    private static final long serialVersionUID = 6547338175089199049L;

    /**
     * Rekordtól
     */
    private Integer recordFrom;

    /**
     * Rekordig
     */
    private Integer recordTo;

    /**
     * Típus
     */
    private String tipus;

    /**
     * Mező
     */
    private String mezo;

    /**
     * Üzenet
     */
    private String uzenet;

    /**
     * Konstruktor
     */
    public CsvError()
    {
    }

    public CsvError(Integer recordFrom, Integer recordTo, String tipus, String mezo, String uzenet)
    {
        this.recordFrom = recordFrom;
        this.recordTo = recordTo;
        this.tipus = tipus;
        this.mezo = mezo;
        this.uzenet = uzenet;
    }

    /**
     * @see CsvError#recordFrom
     */
    public Integer getRecordFrom()
    {
        return recordFrom;
    }

    /**
     * @see CsvError#recordFrom
     */
    public void setRecordFrom(Integer recordFrom)
    {
        this.recordFrom = recordFrom;
    }

    /**
     * @see CsvError#recordTo
     */
    public Integer getRecordTo()
    {
        return recordTo;
    }

    /**
     * @see CsvError#recordTo
     */
    public void setRecordTo(Integer recordTo)
    {
        this.recordTo = recordTo;
    }

    /**
     * @see CsvError#tipus
     */
    public String getTipus()
    {
        return tipus;
    }

    /**
     * @see CsvError#tipus
     */
    public void setTipus(String tipus)
    {
        this.tipus = tipus;
    }

    /**
     * @see CsvError#mezo
     */
    public String getMezo()
    {
        return mezo;
    }

    /**
     * @see CsvError#mezo
     */
    public void setMezo(String mezo)
    {
        this.mezo = mezo;
    }

    /**
     * @see CsvError#uzenet
     */
    public String getUzenet()
    {
        return uzenet;
    }

    /**
     * @see CsvError#uzenet
     */
    public void setUzenet(String uzenet)
    {
        this.uzenet = uzenet;
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
        CsvError csvError = (CsvError) o;
        return Objects.equals(recordFrom, csvError.recordFrom) &&
                Objects.equals(recordTo, csvError.recordTo) &&
                Objects.equals(tipus, csvError.tipus) &&
                Objects.equals(mezo, csvError.mezo) &&
                Objects.equals(uzenet, csvError.uzenet);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recordFrom, recordTo, tipus, mezo, uzenet);
    }

    @Override
    public String toString()
    {
        return "rekord: " + getRecordFrom()
                + "-" + getRecordTo()
                + ", típus: " + getTipus()
                + ", mező: " + getMezo()
                + " - " + getUzenet();
    }
}
