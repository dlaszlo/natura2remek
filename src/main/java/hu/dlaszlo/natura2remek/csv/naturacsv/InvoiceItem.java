package hu.dlaszlo.natura2remek.csv.naturacsv;

import hu.dlaszlo.natura2remek.csv.AbstractEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Tétel adatok
 */
public class InvoiceItem extends AbstractEntity implements Serializable
{
    private static final long serialVersionUID = -4851901603051631116L;

    public static final String TYPE = "TÉTEL";

    /**
     * 01: A számla tételeire vonatkozó adatok TÉTEL felirattal kezdődnek.
     */
    @NotEmpty
    @Length(min = 5, max = 5)
    @Pattern(regexp = "TÉTEL")
    private String tipus;

    /**
     * 02: Termék/szolgáltatás NATURASOFT programban lévő egyedi kódja (egész szám)
     */
    @NotNull
    private Long termekKod;

    /**
     * 03: Termék/szolgáltatás elnevezése  (max. 100 kar.)
     */
    @NotEmpty
    @Length(max = 100)
    private String termekMegnevezes;

    /**
     * 04: Termék/szolgáltatás termékkódja/vonalkódja (max. 20 kar.)
     */
    @Length(max = 20)
    private String termekSzolgaltatasKod;

    /**
     * 05: Termék/szolgáltatás cikkszáma (max. 20 kar.)
     */
    @Length(max = 20)
    private String cikkszam;

    /**
     * 06: Termék/szolgáltatás VTSZ/SZJ/Teszor száma (max. 15 kar.)
     */
    @Length(max = 15)
    private String vtszSzjTeszor;

    /**
     * 07: Kiszámlázott mennyiség (max. 6 tizedes, tizedesvesszővel elválasztva)
     */
    @NotNull
    private BigDecimal mennyiseg;

    /**
     * 08: Termék/szolgáltatás mennyiségi egysége (max. 5 kar.)
     */
    @NotEmpty
    @Length(max = 5)
    private String mennyisegiEgyseg;

    /**
     * 09: Termék/szolgáltatás nettó eredeti egységára kedvezmény nélkül a számla pénznemében (max. 4 tizedes, tizedesvesszővel elválasztva)
     */
    @NotNull
    private BigDecimal nettoAr;

    /**
     * 10: Termék/szolgáltatás bruttó eredeti egységára kedvezmény nélkül a számla pénznemében (max. 4 tizedes, tizedesvesszővel elválasztva)
     */
    @NotNull
    private BigDecimal bruttoAr;

    /**
     * 11: Alkalmazott kedvezmény (fix 2 tizedes, tizedesvesszővel elválasztva): mínuszos előjel esetén felárat jelent.
     */
    private BigDecimal kedvezmeny;

    /**
     * 12: Termék/szolgáltatás kedvezményes nettó egységára a számla pénznemében (max. 4 tizedes, tizedesvesszővel elválasztva)
     */
    @NotNull
    private BigDecimal nettoEgysegAr;

    /**
     * 13: Termék/szolgáltatás kedvezményes bruttó egységára a számla pénznemében (max. 4 tizedes, tizedesvesszővel elválasztva)
     */
    @NotNull
    private BigDecimal bruttoEgysegAr;

    /**
     * 14: Alkalmazott ÁFA kulcs NATURASOFT programban lévő egyedi kódja (egész szám)
     */
    @NotNull
    private Long afaKulcsKod;

    /**
     * 15: Alkalmazott ÁFA kulcs rövidítése (max. 3 kar.)
     */
    @NotEmpty
    @Length(max = 3)
    private String afaKulcsRovid;

    /**
     * 16: Alkalmazott ÁFA kulcs elnevezése (max. 20 kar.)
     */
    @NotEmpty
    @Length(max = 20)
    private String afaKulcsLeiras;

    /**
     * 17: Alkalmazott ÁFA kulcs százaléka (fix 2 tizedes, tizedesvesszővel elválasztva)
     */
    @NotNull
    private BigDecimal afaKulcs;

    /**
     * 18: Termék/szolgáltatás nettó összesen ára a számla pénznemében (max. 2 tizedes, tizedesvesszővel elválasztva): az egységár és a mennyiség szorzata
     */
    @NotNull
    private BigDecimal nettoOsszesenAr;

    /** 19: Áfa összesen */
    @NotNull
    private BigDecimal afaOsszesenAr;

    /**
     * 20: Termék/szolgáltatás bruttó összesen ára a számla pénznemében (max. 2 tizedes, tizedesvesszővel elválasztva): az egységár és a mennyiség szorzata
     */
    @NotNull
    private BigDecimal bruttoOsszesenAr;

    /**
     * 21: Számlatételhez fűzött tetszőleges megjegyzés (korlátlan kar.)
     */
    private String megjegyzes;

    /**
     * 22: Raktárhely NATURASOFT programban lévő egyedi kódja (egész szám)
     */
    private Long raktarhelyKod;

    /**
     * 23: Raktárhely elnevezése (max. 30 kar.)
     */
    @Length(max = 30)
    private String raktarhely;

    /**
     * Konstruktor
     *
     * @param lineFrom CSV sortól
     * @param lineTo   CSV sorig
     */
    public InvoiceItem(int lineFrom, int lineTo)
    {
        super(lineFrom, lineTo);
    }

    /**
     * @see InvoiceItem#tipus
     */
    public String getTipus()
    {
        return tipus;
    }

    /**
     * @see InvoiceItem#tipus
     */
    public void setTipus(String tipus)
    {
        this.tipus = tipus;
    }

    /**
     * @see InvoiceItem#termekKod
     */
    public Long getTermekKod()
    {
        return termekKod;
    }

    /**
     * @see InvoiceItem#termekKod
     */
    public void setTermekKod(Long termekKod)
    {
        this.termekKod = termekKod;
    }

    /**
     * @see InvoiceItem#termekMegnevezes
     */
    public String getTermekMegnevezes()
    {
        return termekMegnevezes;
    }

    /**
     * @see InvoiceItem#termekMegnevezes
     */
    public void setTermekMegnevezes(String termekMegnevezes)
    {
        this.termekMegnevezes = termekMegnevezes;
    }

    /**
     * @see InvoiceItem#termekSzolgaltatasKod
     */
    public String getTermekSzolgaltatasKod()
    {
        return termekSzolgaltatasKod;
    }

    /**
     * @see InvoiceItem#termekSzolgaltatasKod
     */
    public void setTermekSzolgaltatasKod(String termekSzolgaltatasKod)
    {
        this.termekSzolgaltatasKod = termekSzolgaltatasKod;
    }

    /**
     * @see InvoiceItem#cikkszam
     */
    public String getCikkszam()
    {
        return cikkszam;
    }

    /**
     * @see InvoiceItem#cikkszam
     */
    public void setCikkszam(String cikkszam)
    {
        this.cikkszam = cikkszam;
    }

    /**
     * @see InvoiceItem#vtszSzjTeszor
     */
    public String getVtszSzjTeszor()
    {
        return vtszSzjTeszor;
    }

    /**
     * @see InvoiceItem#vtszSzjTeszor
     */
    public void setVtszSzjTeszor(String vtszSzjTeszor)
    {
        this.vtszSzjTeszor = vtszSzjTeszor;
    }

    /**
     * @see InvoiceItem#mennyiseg
     */
    public BigDecimal getMennyiseg()
    {
        return mennyiseg;
    }

    /**
     * @see InvoiceItem#mennyiseg
     */
    public void setMennyiseg(BigDecimal mennyiseg)
    {
        this.mennyiseg = mennyiseg;
    }

    /**
     * @see InvoiceItem#mennyisegiEgyseg
     */
    public String getMennyisegiEgyseg()
    {
        return mennyisegiEgyseg;
    }

    /**
     * @see InvoiceItem#mennyisegiEgyseg
     */
    public void setMennyisegiEgyseg(String mennyisegiEgyseg)
    {
        this.mennyisegiEgyseg = mennyisegiEgyseg;
    }

    /**
     * @see InvoiceItem#nettoAr
     */
    public BigDecimal getNettoAr()
    {
        return nettoAr;
    }

    /**
     * @see InvoiceItem#nettoAr
     */
    public void setNettoAr(BigDecimal nettoAr)
    {
        this.nettoAr = nettoAr;
    }

    /**
     * @see InvoiceItem#bruttoAr
     */
    public BigDecimal getBruttoAr()
    {
        return bruttoAr;
    }

    /**
     * @see InvoiceItem#bruttoAr
     */
    public void setBruttoAr(BigDecimal bruttoAr)
    {
        this.bruttoAr = bruttoAr;
    }

    /**
     * @see InvoiceItem#kedvezmeny
     */
    public BigDecimal getKedvezmeny()
    {
        return kedvezmeny;
    }

    /**
     * @see InvoiceItem#kedvezmeny
     */
    public void setKedvezmeny(BigDecimal kedvezmeny)
    {
        this.kedvezmeny = kedvezmeny;
    }

    /**
     * @see InvoiceItem#nettoEgysegAr
     */
    public BigDecimal getNettoEgysegAr()
    {
        return nettoEgysegAr;
    }

    /**
     * @see InvoiceItem#nettoEgysegAr
     */
    public void setNettoEgysegAr(BigDecimal nettoEgysegAr)
    {
        this.nettoEgysegAr = nettoEgysegAr;
    }

    /**
     * @see InvoiceItem#bruttoEgysegAr
     */
    public BigDecimal getBruttoEgysegAr()
    {
        return bruttoEgysegAr;
    }

    /**
     * @see InvoiceItem#bruttoEgysegAr
     */
    public void setBruttoEgysegAr(BigDecimal bruttoEgysegAr)
    {
        this.bruttoEgysegAr = bruttoEgysegAr;
    }

    /**
     * @see InvoiceItem#afaKulcsKod
     */
    public Long getAfaKulcsKod()
    {
        return afaKulcsKod;
    }

    /**
     * @see InvoiceItem#afaKulcsKod
     */
    public void setAfaKulcsKod(Long afaKulcsKod)
    {
        this.afaKulcsKod = afaKulcsKod;
    }

    /**
     * @see InvoiceItem#afaKulcsRovid
     */
    public String getAfaKulcsRovid()
    {
        return afaKulcsRovid;
    }

    /**
     * @see InvoiceItem#afaKulcsRovid
     */
    public void setAfaKulcsRovid(String afaKulcsRovid)
    {
        this.afaKulcsRovid = afaKulcsRovid;
    }

    /**
     * @see InvoiceItem#afaKulcsLeiras
     */
    public String getAfaKulcsLeiras()
    {
        return afaKulcsLeiras;
    }

    /**
     * @see InvoiceItem#afaKulcsLeiras
     */
    public void setAfaKulcsLeiras(String afaKulcsLeiras)
    {
        this.afaKulcsLeiras = afaKulcsLeiras;
    }

    /**
     * @see InvoiceItem#afaKulcs
     */
    public BigDecimal getAfaKulcs()
    {
        return afaKulcs;
    }

    /**
     * @see InvoiceItem#afaKulcs
     */
    public void setAfaKulcs(BigDecimal afaKulcs)
    {
        this.afaKulcs = afaKulcs;
    }

    /**
     * @see InvoiceItem#nettoOsszesenAr
     */
    public BigDecimal getNettoOsszesenAr()
    {
        return nettoOsszesenAr;
    }

    /**
     * @see InvoiceItem#nettoOsszesenAr
     */
    public void setNettoOsszesenAr(BigDecimal nettoOsszesenAr)
    {
        this.nettoOsszesenAr = nettoOsszesenAr;
    }

    /**
     * @see InvoiceItem#afaOsszesenAr
     */
    public BigDecimal getAfaOsszesenAr()
    {
        return afaOsszesenAr;
    }

    /**
     * @see InvoiceItem#afaOsszesenAr
     */
    public void setAfaOsszesenAr(BigDecimal afaOsszesenAr)
    {
        this.afaOsszesenAr = afaOsszesenAr;
    }

    /**
     * @see InvoiceItem#bruttoOsszesenAr
     */
    public BigDecimal getBruttoOsszesenAr()
    {
        return bruttoOsszesenAr;
    }

    /**
     * @see InvoiceItem#bruttoOsszesenAr
     */
    public void setBruttoOsszesenAr(BigDecimal bruttoOsszesenAr)
    {
        this.bruttoOsszesenAr = bruttoOsszesenAr;
    }

    /**
     * @see InvoiceItem#megjegyzes
     */
    public String getMegjegyzes()
    {
        return megjegyzes;
    }

    /**
     * @see InvoiceItem#megjegyzes
     */
    public void setMegjegyzes(String megjegyzes)
    {
        this.megjegyzes = megjegyzes;
    }

    /**
     * @see InvoiceItem#raktarhelyKod
     */
    public Long getRaktarhelyKod()
    {
        return raktarhelyKod;
    }

    /**
     * @see InvoiceItem#raktarhelyKod
     */
    public void setRaktarhelyKod(Long raktarhelyKod)
    {
        this.raktarhelyKod = raktarhelyKod;
    }

    /**
     * @see InvoiceItem#raktarhely
     */
    public String getRaktarhely()
    {
        return raktarhely;
    }

    /**
     * @see InvoiceItem#raktarhely
     */
    public void setRaktarhely(String raktarhely)
    {
        this.raktarhely = raktarhely;
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
        InvoiceItem invoiceItem = (InvoiceItem) o;
        return Objects.equals(tipus, invoiceItem.tipus) &&
                Objects.equals(termekKod, invoiceItem.termekKod) &&
                Objects.equals(termekMegnevezes, invoiceItem.termekMegnevezes) &&
                Objects.equals(termekSzolgaltatasKod, invoiceItem.termekSzolgaltatasKod) &&
                Objects.equals(cikkszam, invoiceItem.cikkszam) &&
                Objects.equals(vtszSzjTeszor, invoiceItem.vtszSzjTeszor) &&
                Objects.equals(mennyiseg, invoiceItem.mennyiseg) &&
                Objects.equals(mennyisegiEgyseg, invoiceItem.mennyisegiEgyseg) &&
                Objects.equals(nettoAr, invoiceItem.nettoAr) &&
                Objects.equals(bruttoAr, invoiceItem.bruttoAr) &&
                Objects.equals(kedvezmeny, invoiceItem.kedvezmeny) &&
                Objects.equals(nettoEgysegAr, invoiceItem.nettoEgysegAr) &&
                Objects.equals(bruttoEgysegAr, invoiceItem.bruttoEgysegAr) &&
                Objects.equals(afaKulcsKod, invoiceItem.afaKulcsKod) &&
                Objects.equals(afaKulcsRovid, invoiceItem.afaKulcsRovid) &&
                Objects.equals(afaKulcsLeiras, invoiceItem.afaKulcsLeiras) &&
                Objects.equals(afaKulcs, invoiceItem.afaKulcs) &&
                Objects.equals(nettoOsszesenAr, invoiceItem.nettoOsszesenAr) &&
                Objects.equals(afaOsszesenAr, invoiceItem.afaOsszesenAr) &&
                Objects.equals(bruttoOsszesenAr, invoiceItem.bruttoOsszesenAr) &&
                Objects.equals(megjegyzes, invoiceItem.megjegyzes) &&
                Objects.equals(raktarhelyKod, invoiceItem.raktarhelyKod) &&
                Objects.equals(raktarhely, invoiceItem.raktarhely);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(tipus, termekKod, termekMegnevezes, termekSzolgaltatasKod, cikkszam, vtszSzjTeszor, mennyiseg, mennyisegiEgyseg, nettoAr, bruttoAr, kedvezmeny,
                            nettoEgysegAr, bruttoEgysegAr, afaKulcsKod, afaKulcsRovid, afaKulcsLeiras, afaKulcs,
                            nettoOsszesenAr, afaOsszesenAr, bruttoOsszesenAr, megjegyzes, raktarhelyKod, raktarhely);
    }

    @Override
    public String toString()
    {
        return "InvoiceItem{" +
                "tipus='" + tipus + '\'' +
                ", termekKod=" + termekKod +
                ", termekMegnevezes='" + termekMegnevezes + '\'' +
                ", termekSzolgaltatasKod='" + termekSzolgaltatasKod + '\'' +
                ", cikkszam='" + cikkszam + '\'' +
                ", vtszSzjTeszor='" + vtszSzjTeszor + '\'' +
                ", mennyiseg=" + mennyiseg +
                ", mennyisegiEgyseg='" + mennyisegiEgyseg + '\'' +
                ", nettoAr=" + nettoAr +
                ", bruttoAr=" + bruttoAr +
                ", kedvezmeny=" + kedvezmeny +
                ", nettoEgysegAr=" + nettoEgysegAr +
                ", bruttoEgysegAr=" + bruttoEgysegAr +
                ", afaKulcsKod=" + afaKulcsKod +
                ", afaKulcsRovid='" + afaKulcsRovid + '\'' +
                ", afaKulcsLeiras='" + afaKulcsLeiras + '\'' +
                ", afaKulcs=" + afaKulcs +
                ", nettoOsszesenAr=" + nettoOsszesenAr +
                ", afaOsszesenAr=" + afaOsszesenAr +
                ", bruttoOsszesenAr=" + bruttoOsszesenAr +
                ", megjegyzes='" + megjegyzes + '\'' +
                ", raktarhelyKod=" + raktarhelyKod +
                ", raktarhely='" + raktarhely + '\'' +
                '}';
    }

}
