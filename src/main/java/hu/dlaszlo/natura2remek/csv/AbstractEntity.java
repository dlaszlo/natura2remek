package hu.dlaszlo.natura2remek.csv;

import java.util.Objects;

/**
 * A betöltendő/kimentendő adatokat tároló osztályok ősosztálya
 */
public abstract class AbstractEntity
{
    /**
     * CSV fájl sortól
     */
    protected int recordFrom;

    /**
     * CSV fájl sorig
     */
    protected int recordTo;

    /**
     * Konstruktor
     *
     * @param recordFrom CSV rekordtól
     * @param recordTo CSV rekordtól
     */
    public AbstractEntity(int recordFrom, int recordTo)
    {
        this.recordFrom = recordFrom;
        this.recordTo = recordTo;
    }

    /**
     * @see AbstractEntity#recordFrom
     */
    public int getRecordFrom()
    {
        return recordFrom;
    }

    /**
     * @see AbstractEntity#recordFrom
     */
    public void setRecordFrom(int recordFrom)
    {
        this.recordFrom = recordFrom;
    }

    /**
     * @see AbstractEntity#recordTo
     */
    public int getRecordTo()
    {
        return recordTo;
    }

    /**
     * @see AbstractEntity#recordTo
     */
    public void setRecordTo(int recordTo)
    {
        this.recordTo = recordTo;
    }

    /**
     * Típus
     */
    public abstract String getTipus();

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
        AbstractEntity that = (AbstractEntity) o;
        return recordFrom == that.recordFrom &&
                recordTo == that.recordTo;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recordFrom, recordTo);
    }

    @Override
    public String toString()
    {
        return "AbstractEntity{" +
                "recordFrom=" + recordFrom +
                ", recordTo=" + recordTo +
                '}';
    }
}


