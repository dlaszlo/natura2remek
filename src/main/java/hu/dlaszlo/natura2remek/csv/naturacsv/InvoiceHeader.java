package hu.dlaszlo.natura2remek.csv.naturacsv;

import hu.dlaszlo.natura2remek.csv.AbstractEntity;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Fejléc adatok
 */
public class InvoiceHeader extends AbstractEntity implements Serializable
{
    private static final long serialVersionUID = 6879635201731372426L;

    public static final String TYPE = "FEJ";

    /**
     * 01: A számla fő adatait tartalmazó sorok FEJ felirattal kezdődnek.
     */
    @NotEmpty
    @Length(min = 3, max = 3)
    @Pattern(regexp = "FEJ")
    private String tipus;

    /**
     * 02: Számla bizonylatszáma (max. 13 kar.)
     */
    @NotEmpty
    @Length(max = 13)
    private String bizonylatszam;

    /**
     * 03: Számla típusa (1 kar.): G - gépi A4-es számla, E - egyszerűsített blokkméretű bruttós számla, K - kézi számla
     */
    @NotEmpty
    @Length(min = 1, max = 1)
    @Pattern(regexp = "G|E|K")
    private String szamlaTipus;

    /**
     * 04: Számla kiállító cég neve (max. 100 kar.)
     */
    @NotEmpty
    @Length(max = 100)
    private String kiallitoCegNev;

    /**
     * 05: Számla kiállító cég adatai (korlátlan kar.): számlán a név alatti rész, ami tetszőlegesen van kitöltve,
     * bármilyen sorrendben, bármilyen adatot tartalmazhat, pl. cím, adószám, bankszámlaszám, telefonszám, stb.
     */
    private String kiallitoCegAdat;

    /**
     * 06: Vevő NATURASOFT programban lévő egyedi ügyfélkódja (egész szám)
     */
    private Long vevoKod;

    /**
     * 07: Vevő számlán feltüntetett neve (max. 100 kar.)
     */
    @NotEmpty
    @Length(max = 100)
    private String vevoNev;

    /**
     * 08: Vevőn számlán feltüntetett egyéb adatai (korlátlan kar.): számlán a vevő név alatti rész, ami tetszőlegesen van kitöltve,
     * bármilyen sorrendben, bármilyen adatot tartalmazhat, pl. cím, adószám, bankszámlaszám, telefonszám, stb.
     */
    private String vevoEgyebAdat;

    /**
     * 09: Fizetési mód NATURASOFT programban lévő egyedi kódja (egész szám)
     */
    private Long fizetesiModKod;

    /**
     * 10: Fizetési mód neve (max. 20 kar.)
     */
    @NotEmpty
    @Length(max = 20)
    private String fizetesiMod;

    /**
     * 11: Számla kiállítás napja (ÉÉÉÉ.HH.NN. formátumban)
     */
    @NotNull
    private LocalDate kiallitasNap;

    /**
     * 12: Számla teljesítés napja (ÉÉÉÉ.HH.NN. formátumban)
     */
    @NotNull
    private LocalDate teljesitesNap;

    /**
     * 13: Fizetési határidő napja (ÉÉÉÉ.HH.NN. formátumban)
     */
    @NotNull
    private LocalDate fizetesiHatarido;

    /**
     * 14. Számla kiegyenlítés napja (ÉÉÉÉ.HH.NN. formátumban)
     */
    private LocalDate kiegyenlitesNapja;

    /**
     * 15: Számla pénzneme (max. 3 kar.): A forint egységesen HUF néven szerepel.
     */
    @NotEmpty
    @Length(max = 3)
    private String penznem;

    /**
     * 16: Számla árfolyama (fixen 4 tizedes, tizedesvesszővel elválasztva)
     */
    @NotNull
    private BigDecimal arfolyam;

    /**
     * 17: Számla nettó végösszege a számla pénznemében (forintos pénznem esetén egész szám, egyébként max. 2 tizedes, tizedesvesszővel elválasztva)
     */
    @NotNull
    private BigDecimal nettoVegosszeg;

    /**
     * 18: Számla ÁFA végösszege a számla pénznemében (forintos pénznem esetén egész szám, egyébként max. 2 tizedes, tizedesvesszővel elválasztva)
     */
    @NotNull
    private BigDecimal afaVegosszeg;

    /**
     * 19: Számla bruttó végösszege a számla pénznemében (forintos pénznem esetén egész szám, egyébként max. 2 tizedes, tizedesvesszővel elválasztva)
     */
    @NotNull
    private BigDecimal bruttoVegosszeg;

    /**
     * 20: Számlázó programban eddig kiegyenlítettnek jelölt bruttó összeg a számla pénznemében (forintos pénznem esetén egész szám,
     * egyébként max. 2 tizedes, tizedesvesszővel elválasztva)
     */
    private BigDecimal kiegyenlitettBruttoOsszeg;

    /**
     * 21: Számlán alkalmazott tetszőleges megjegyzés a tételek felsorolása felett (korlátlan kar.)
     */
    private String megjegyzes1;

    /**
     * 22: Számlán alkalmazott tetszőleges megjegyzés a tételek felsorolása alatt (korlátlan kar.)
     */
    private String megjegyzes2;

    /**
     * 23: Stornózott vagy helyesbített számla bizonylatszáma (max. 13 kar.)
     */
    @Length(max = 13)
    private String stornoVagyHelyesbSzamlaszam;

    /**
     * 24: Stornózás/helyesbítés jelölés (1 kar.): S - stornó számla, H - helyesbítő számla. Az előző mező tartalmazza,
     * hogy pontosan melyik számlát stornózták/helyesbítették.
     */
    @Length(max = 1)
    @Pattern(regexp = "S|H")
    private String stornoVagyHelyesbitoTipus;

    /**
     * 25: Számlacsoport NATURASOFT programban lévő egyedi kódja (egész szám)
     */
    private Long szamlacsoportKod;

    /**
     * 26: Számlacsoport megnevezése (max. 30 kar.)
     */
    @Length(max = 30)
    private String szamlacsoportMegnevezes;

    /**
     * 27: Szállítólevél bizonylatszáma, ha a számla(!) alapján készült szállítólevél (max. 13 kar.)
     */
    @Length(max = 13)
    private String szallitolevelBizonylatszam;

    /**
     * Ügyfél adat
     */
    private Customer customer;

    /**
     * Tételek
     */
    @NotEmpty
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    /**
     * Konstruktor
     *
     * @param lineFrom CSV rekordtól
     * @param lineTo   CSV rekordig
     */
    public InvoiceHeader(int lineFrom, int lineTo)
    {
        super(lineFrom, lineTo);
    }

    /**
     * @see InvoiceHeader#tipus
     */
    public String getTipus()
    {
        return tipus;
    }

    /**
     * @see InvoiceHeader#tipus
     */
    public void setTipus(String tipus)
    {
        this.tipus = tipus;
    }

    /**
     * @see InvoiceHeader#bizonylatszam
     */
    public String getBizonylatszam()
    {
        return bizonylatszam;
    }

    /**
     * @see InvoiceHeader#bizonylatszam
     */
    public void setBizonylatszam(String bizonylatszam)
    {
        this.bizonylatszam = bizonylatszam;
    }

    /**
     * @see InvoiceHeader#szamlaTipus
     */
    public String getSzamlaTipus()
    {
        return szamlaTipus;
    }

    /**
     * @see InvoiceHeader#szamlaTipus
     */
    public void setSzamlaTipus(String szamlaTipus)
    {
        this.szamlaTipus = szamlaTipus;
    }

    /**
     * @see InvoiceHeader#kiallitoCegNev
     */
    public String getKiallitoCegNev()
    {
        return kiallitoCegNev;
    }

    /**
     * @see InvoiceHeader#kiallitoCegNev
     */
    public void setKiallitoCegNev(String kiallitoCegNev)
    {
        this.kiallitoCegNev = kiallitoCegNev;
    }

    /**
     * @see InvoiceHeader#kiallitoCegAdat
     */
    public String getKiallitoCegAdat()
    {
        return kiallitoCegAdat;
    }

    /**
     * @see InvoiceHeader#kiallitoCegAdat
     */
    public void setKiallitoCegAdat(String kiallitoCegAdat)
    {
        this.kiallitoCegAdat = kiallitoCegAdat;
    }

    /**
     * @see InvoiceHeader#vevoKod
     */
    public Long getVevoKod()
    {
        return vevoKod;
    }

    /**
     * @see InvoiceHeader#vevoKod
     */
    public void setVevoKod(Long vevoKod)
    {
        this.vevoKod = vevoKod;
    }

    /**
     * @see InvoiceHeader#vevoNev
     */
    public String getVevoNev()
    {
        return vevoNev;
    }

    /**
     * @see InvoiceHeader#vevoNev
     */
    public void setVevoNev(String vevoNev)
    {
        this.vevoNev = vevoNev;
    }

    /**
     * @see InvoiceHeader#vevoEgyebAdat
     */
    public String getVevoEgyebAdat()
    {
        return vevoEgyebAdat;
    }

    /**
     * @see InvoiceHeader#vevoEgyebAdat
     */
    public void setVevoEgyebAdat(String vevoEgyebAdat)
    {
        this.vevoEgyebAdat = vevoEgyebAdat;
    }

    /**
     * @see InvoiceHeader#fizetesiModKod
     */
    public Long getFizetesiModKod()
    {
        return fizetesiModKod;
    }

    /**
     * @see InvoiceHeader#fizetesiModKod
     */
    public void setFizetesiModKod(Long fizetesiModKod)
    {
        this.fizetesiModKod = fizetesiModKod;
    }

    /**
     * @see InvoiceHeader#fizetesiMod
     */
    public String getFizetesiMod()
    {
        return fizetesiMod;
    }

    /**
     * @see InvoiceHeader#fizetesiMod
     */
    public void setFizetesiMod(String fizetesiMod)
    {
        this.fizetesiMod = fizetesiMod;
    }

    /**
     * @see InvoiceHeader#kiallitasNap
     */
    public LocalDate getKiallitasNap()
    {
        return kiallitasNap;
    }

    /**
     * @see InvoiceHeader#kiallitasNap
     */
    public void setKiallitasNap(LocalDate kiallitasNap)
    {
        this.kiallitasNap = kiallitasNap;
    }

    /**
     * @see InvoiceHeader#teljesitesNap
     */
    public LocalDate getTeljesitesNap()
    {
        return teljesitesNap;
    }

    /**
     * @see InvoiceHeader#teljesitesNap
     */
    public void setTeljesitesNap(LocalDate teljesitesNap)
    {
        this.teljesitesNap = teljesitesNap;
    }

    /**
     * @see InvoiceHeader#fizetesiHatarido
     */
    public LocalDate getFizetesiHatarido()
    {
        return fizetesiHatarido;
    }

    /**
     * @see InvoiceHeader#fizetesiHatarido
     */
    public void setFizetesiHatarido(LocalDate fizetesiHatarido)
    {
        this.fizetesiHatarido = fizetesiHatarido;
    }

    /**
     * @see InvoiceHeader#kiegyenlitesNapja
     */
    public LocalDate getKiegyenlitesNapja()
    {
        return kiegyenlitesNapja;
    }

    /**
     * @see InvoiceHeader#kiegyenlitesNapja
     */
    public void setKiegyenlitesNapja(LocalDate kiegyenlitesNapja)
    {
        this.kiegyenlitesNapja = kiegyenlitesNapja;
    }

    /**
     * @see InvoiceHeader#penznem
     */
    public String getPenznem()
    {
        return penznem;
    }

    /**
     * @see InvoiceHeader#penznem
     */
    public void setPenznem(String penznem)
    {
        this.penznem = penznem;
    }

    /**
     * @see InvoiceHeader#arfolyam
     */
    public BigDecimal getArfolyam()
    {
        return arfolyam;
    }

    /**
     * @see InvoiceHeader#arfolyam
     */
    public void setArfolyam(BigDecimal arfolyam)
    {
        this.arfolyam = arfolyam;
    }

    /**
     * @see InvoiceHeader#nettoVegosszeg
     */
    public BigDecimal getNettoVegosszeg()
    {
        return nettoVegosszeg;
    }

    /**
     * @see InvoiceHeader#nettoVegosszeg
     */
    public void setNettoVegosszeg(BigDecimal nettoVegosszeg)
    {
        this.nettoVegosszeg = nettoVegosszeg;
    }

    /**
     * @see InvoiceHeader#afaVegosszeg
     */
    public BigDecimal getAfaVegosszeg()
    {
        return afaVegosszeg;
    }

    /**
     * @see InvoiceHeader#afaVegosszeg
     */
    public void setAfaVegosszeg(BigDecimal afaVegosszeg)
    {
        this.afaVegosszeg = afaVegosszeg;
    }

    /**
     * @see InvoiceHeader#bruttoVegosszeg
     */
    public BigDecimal getBruttoVegosszeg()
    {
        return bruttoVegosszeg;
    }

    /**
     * @see InvoiceHeader#bruttoVegosszeg
     */
    public void setBruttoVegosszeg(BigDecimal bruttoVegosszeg)
    {
        this.bruttoVegosszeg = bruttoVegosszeg;
    }

    /**
     * @see InvoiceHeader#kiegyenlitettBruttoOsszeg
     */
    public BigDecimal getKiegyenlitettBruttoOsszeg()
    {
        return kiegyenlitettBruttoOsszeg;
    }

    /**
     * @see InvoiceHeader#kiegyenlitettBruttoOsszeg
     */
    public void setKiegyenlitettBruttoOsszeg(BigDecimal kiegyenlitettBruttoOsszeg)
    {
        this.kiegyenlitettBruttoOsszeg = kiegyenlitettBruttoOsszeg;
    }

    /**
     * @see InvoiceHeader#megjegyzes1
     */
    public String getMegjegyzes1()
    {
        return megjegyzes1;
    }

    /**
     * @see InvoiceHeader#megjegyzes1
     */
    public void setMegjegyzes1(String megjegyzes1)
    {
        this.megjegyzes1 = megjegyzes1;
    }

    /**
     * @see InvoiceHeader#megjegyzes2
     */
    public String getMegjegyzes2()
    {
        return megjegyzes2;
    }

    /**
     * @see InvoiceHeader#megjegyzes2
     */
    public void setMegjegyzes2(String megjegyzes2)
    {
        this.megjegyzes2 = megjegyzes2;
    }

    /**
     * @see InvoiceHeader#stornoVagyHelyesbSzamlaszam
     */
    public String getStornoVagyHelyesbSzamlaszam()
    {
        return stornoVagyHelyesbSzamlaszam;
    }

    /**
     * @see InvoiceHeader#stornoVagyHelyesbSzamlaszam
     */
    public void setStornoVagyHelyesbSzamlaszam(String stornoVagyHelyesbSzamlaszam)
    {
        this.stornoVagyHelyesbSzamlaszam = stornoVagyHelyesbSzamlaszam;
    }

    /**
     * @see InvoiceHeader#stornoVagyHelyesbitoTipus
     */
    public String getStornoVagyHelyesbitoTipus()
    {
        return stornoVagyHelyesbitoTipus;
    }

    /**
     * @see InvoiceHeader#stornoVagyHelyesbitoTipus
     */
    public void setStornoVagyHelyesbitoTipus(String stornoVagyHelyesbitoTipus)
    {
        this.stornoVagyHelyesbitoTipus = stornoVagyHelyesbitoTipus;
    }

    /**
     * @see InvoiceHeader#szamlacsoportKod
     */
    public Long getSzamlacsoportKod()
    {
        return szamlacsoportKod;
    }

    /**
     * @see InvoiceHeader#szamlacsoportKod
     */
    public void setSzamlacsoportKod(Long szamlacsoportKod)
    {
        this.szamlacsoportKod = szamlacsoportKod;
    }

    /**
     * @see InvoiceHeader#szamlacsoportMegnevezes
     */
    public String getSzamlacsoportMegnevezes()
    {
        return szamlacsoportMegnevezes;
    }

    /**
     * @see InvoiceHeader#szamlacsoportMegnevezes
     */
    public void setSzamlacsoportMegnevezes(String szamlacsoportMegnevezes)
    {
        this.szamlacsoportMegnevezes = szamlacsoportMegnevezes;
    }

    /**
     * @see InvoiceHeader#szallitolevelBizonylatszam
     */
    public String getSzallitolevelBizonylatszam()
    {
        return szallitolevelBizonylatszam;
    }

    /**
     * @see InvoiceHeader#szallitolevelBizonylatszam
     */
    public void setSzallitolevelBizonylatszam(String szallitolevelBizonylatszam)
    {
        this.szallitolevelBizonylatszam = szallitolevelBizonylatszam;
    }

    /**
     * @see InvoiceHeader#customer
     */
    public Customer getCustomer()
    {
        return customer;
    }

    /**
     * @see InvoiceHeader#customer
     */
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    /**
     * @see InvoiceHeader#invoiceItems
     */
    public List<InvoiceItem> getInvoiceItems()
    {
        return invoiceItems;
    }

    /**
     * @see InvoiceHeader#invoiceItems
     */
    public void setInvoiceItems(List<InvoiceItem> invoiceItems)
    {
        this.invoiceItems = invoiceItems;
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
        InvoiceHeader invoiceHeader = (InvoiceHeader) o;
        return Objects.equals(tipus, invoiceHeader.tipus) &&
                Objects.equals(bizonylatszam, invoiceHeader.bizonylatszam) &&
                Objects.equals(szamlaTipus, invoiceHeader.szamlaTipus) &&
                Objects.equals(kiallitoCegNev, invoiceHeader.kiallitoCegNev) &&
                Objects.equals(kiallitoCegAdat, invoiceHeader.kiallitoCegAdat) &&
                Objects.equals(vevoKod, invoiceHeader.vevoKod) &&
                Objects.equals(vevoNev, invoiceHeader.vevoNev) &&
                Objects.equals(vevoEgyebAdat, invoiceHeader.vevoEgyebAdat) &&
                Objects.equals(fizetesiModKod, invoiceHeader.fizetesiModKod) &&
                Objects.equals(fizetesiMod, invoiceHeader.fizetesiMod) &&
                Objects.equals(kiallitasNap, invoiceHeader.kiallitasNap) &&
                Objects.equals(teljesitesNap, invoiceHeader.teljesitesNap) &&
                Objects.equals(fizetesiHatarido, invoiceHeader.fizetesiHatarido) &&
                Objects.equals(kiegyenlitesNapja, invoiceHeader.kiegyenlitesNapja) &&
                Objects.equals(penznem, invoiceHeader.penznem) &&
                Objects.equals(arfolyam, invoiceHeader.arfolyam) &&
                Objects.equals(nettoVegosszeg, invoiceHeader.nettoVegosszeg) &&
                Objects.equals(afaVegosszeg, invoiceHeader.afaVegosszeg) &&
                Objects.equals(bruttoVegosszeg, invoiceHeader.bruttoVegosszeg) &&
                Objects.equals(kiegyenlitettBruttoOsszeg, invoiceHeader.kiegyenlitettBruttoOsszeg) &&
                Objects.equals(megjegyzes1, invoiceHeader.megjegyzes1) &&
                Objects.equals(megjegyzes2, invoiceHeader.megjegyzes2) &&
                Objects.equals(stornoVagyHelyesbSzamlaszam, invoiceHeader.stornoVagyHelyesbSzamlaszam) &&
                Objects.equals(stornoVagyHelyesbitoTipus, invoiceHeader.stornoVagyHelyesbitoTipus) &&
                Objects.equals(szamlacsoportKod, invoiceHeader.szamlacsoportKod) &&
                Objects.equals(szamlacsoportMegnevezes, invoiceHeader.szamlacsoportMegnevezes) &&
                Objects.equals(szallitolevelBizonylatszam, invoiceHeader.szallitolevelBizonylatszam) &&
                Objects.equals(customer, invoiceHeader.customer) &&
                Objects.equals(invoiceItems, invoiceHeader.invoiceItems);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(tipus, bizonylatszam, szamlaTipus, kiallitoCegNev, kiallitoCegAdat, vevoKod, vevoNev, vevoEgyebAdat,
                            fizetesiModKod, fizetesiMod, kiallitasNap, teljesitesNap, fizetesiHatarido, kiegyenlitesNapja, penznem, arfolyam,
                            nettoVegosszeg, afaVegosszeg, bruttoVegosszeg, kiegyenlitettBruttoOsszeg, megjegyzes1, megjegyzes2,
                            stornoVagyHelyesbSzamlaszam, stornoVagyHelyesbitoTipus, szamlacsoportKod, szamlacsoportMegnevezes, szallitolevelBizonylatszam,
                            customer, invoiceItems);
    }
}
