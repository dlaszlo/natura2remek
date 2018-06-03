package hu.dlaszlo.natura2remek.csv.naturacsv;

import hu.dlaszlo.natura2remek.csv.AbstractEntity;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

/**
 * Fájlformátum adatok
 */
public class FileFormat extends AbstractEntity implements Serializable
{

    private static final long serialVersionUID = 6991640000558463508L;

    /**
     * 1. sor: //CSV SZERKEZET VERZIÓ
     */
    @NotEmpty
    private String description1;

    /**
     * 2. sor //A SZERKEZET PONTOS LEÍRÁSA A NATURASOFT PROGRAM SÚGÓJÁBAN OLVASHATÓ!
     */
    @NotEmpty
    private String description2;

    /**
     * CSV fájl verzió. Értéke: 1.00
     */
    @NotEmpty
    private String version;

    @Override
    public String getTipus()
    {
        return "VERZIÓ";
    }

    /**
     * Konstruktor
     *
     * @param lineFrom CSV rekordtól
     * @param lineTo   CSV rekordig
     */
    public FileFormat(int lineFrom, int lineTo)
    {
        super(lineFrom, lineTo);
    }

    @AssertTrue(message = "Érvénytelen Natura CSV fájl verzió")
    public boolean isVersionValid()
    {
        return "//CSV SZERKEZET VERZIÓ".equals(description1)
                && "//A SZERKEZET PONTOS LEÍRÁSA A NATURASOFT PROGRAM SÚGÓJÁBAN OLVASHATÓ!".equals(description2)
                && "1.00".equals(version);
    }

    /**
     * @see FileFormat#description1
     */
    public String getDescription1()
    {
        return description1;
    }

    /**
     * @see FileFormat#description1
     */
    public void setDescription1(String description1)
    {
        this.description1 = description1;
    }

    /**
     * @see FileFormat#description2
     */
    public String getDescription2()
    {
        return description2;
    }

    /**
     * @see FileFormat#description2
     */
    public void setDescription2(String description2)
    {
        this.description2 = description2;
    }

    /**
     * @see FileFormat#version
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * @see FileFormat#version
     */
    public void setVersion(String version)
    {
        this.version = version;
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
        FileFormat that = (FileFormat) o;
        return Objects.equals(description1, that.description1) &&
                Objects.equals(description2, that.description2) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(description1, description2, version);
    }

    @Override
    public String toString()
    {
        return "FileFormat{" +
                "description1='" + description1 + '\'' +
                ", description2='" + description2 + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

}
