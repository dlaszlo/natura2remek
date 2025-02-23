package hu.dlaszlo.natura2remek.csv.naturacsv;

import hu.dlaszlo.natura2remek.csv.AbstractEntity;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

/**
 * Customer adatok
 */
public class Customer extends AbstractEntity implements Serializable
{
    private static final long serialVersionUID = 5902554980829270128L;

    public static final String TYPE = "PARTNER";

    /**
     * 01: A vevő NATURASOFT partnernyilvántartásban látható aktuális adatait tartalmazó sorok PARTNER felirattal kezdődnek. Ezt követően a mezők sorrendje a következő:
     */
    @NotEmpty
    @Length(min = 7, max = 7)
    @Pattern(regexp = "PARTNER")
    private String tipus;

    /**
     * 02: Vevő NATURASOFT programban lévő egyedi ügyfélkódja (egész szám)
     */
    private Long ugyfelKod;

    /**
     * 03: Vevő neve (max. 100 kar.)
     */
    @NotEmpty
    @Length(max = 100)
    private String nev;

    /**
     * 04: Ország (max. 40 kar.)
     */
    @Length(max = 40)
    private String orszag;

    /**
     * 05: Irányítószám (max. 10 kar.)
     */
    @Length(max = 10)
    private String irszam;

    /**
     * 06: Város (max. 40 kar.)
     */
    @Length(max = 40)
    private String varos;

    /**
     * 07: Cím (max. 100 kar.)
     */
    @Length(max = 100)
    private String cim;

    /**
     * 08: Postázási/szállítási név (max. 100 kar.)
     */
    @Length(max = 100)
    private String szallitasNev;

    /**
     * 09: Postázási/szállítás ország (max. 40 kar.)
     */
    @Length(max = 40)
    private String szallitasOrszag;

    /**
     * 10: Postázási/szállítás irányítószám (max. 10 kar.)
     */
    @Length(max = 10)
    private String szallitasIrszam;

    /**
     * 11: Postázási/szállítás város (max. 40 kar.)
     */
    @Length(max = 40)
    private String szallitasVaros;

    /**
     * 12: Postázási/szállítás cím (max. 100 kar.)
     */
    @Length(max = 100)
    private String szallitasCim;

    /**
     * 13: Kapcsolattartó neve (max. 100 kar.)
     */
    @Length(max = 100)
    private String kapcsolattartoNev;

    /**
     * 14: Telefonszám (max. 50 kar.)
     */
    @Length(max = 50)
    private String telefonszam;

    /**
     * 15: FAX (max. 50 kar.)
     */
    @Length(max = 50)
    private String fax;

    /**
     * 16: E-mail cím (max. 100 kar.)
     */
    @Length(max = 100)
    private String email;

    /**
     * 17: Adószám (max. 20 kar.)
     */
    @Length(max = 20)
    private String adoszam;

    /**
     * 18: Közösségi adószám (max. 20 kar.)
     */
    @Length(max = 20)
    private String kozossegiAdoszam;

    /**
     * 19: Bankszámlaszám (max. 40 kar.)
     */
    @Length(max = 40)
    private String bankszamlaszam;

    /**
     * 20: Honlap címe (max. 50 kar.)
     */
    @Length(max = 50)
    private String honlapCim;

    /**
     * 21: Klubkártya/törzsvásárlói kártya/egyéb egyedi azonosító (max. 16 kar.)
     */
    @Length(max = 16)
    private String klubkartya;

    /**
     * 22: 1-es ügyfélcsoport NATURASOFT programban lévő egyedi kódja (egész szám)
     */
    private Long csoportKod1;

    /**
     * 23: 1-es ügyfélcsoport megnevezése (max. 30 kar.)
     */
    @Length(max = 30)
    private String csoport1;

    /**
     * 24: 2-es ügyfélcsoport NATURASOFT programban lévő egyedi kódja (egész szám)
     */
    private Long csoportKod2;

    /**
     * 25: 2-es ügyfélcsoport megnevezése (max. 30 kar.)
     */
    @Length(max = 30)
    private String csoport2;

    /**
     * 26: 3-as ügyfélcsoport NATURASOFT programban lévő egyedi kódja (egész szám)
     */
    private Long csoportKod3;

    /**
     * 27: 3-as ügyfélcsoport megnevezése (max. 30 kar.)
     */
    @Length(max = 30)
    private String csoport3;

    /**
     * Konstruktor
     *
     * @param lineFrom CSV sortól
     * @param lineTo   CSV sorig
     */
    public Customer(int lineFrom, int lineTo)
    {
        super(lineFrom, lineTo);
    }

    /**
     * @see Customer#tipus
     */
    public String getTipus()
    {
        return tipus;
    }

    /**
     * @see Customer#tipus
     */
    public void setTipus(String tipus)
    {
        this.tipus = tipus;
    }

    /**
     * @see Customer#ugyfelKod
     */
    public Long getUgyfelKod()
    {
        return ugyfelKod;
    }

    /**
     * @see Customer#ugyfelKod
     */
    public void setUgyfelKod(Long ugyfelKod)
    {
        this.ugyfelKod = ugyfelKod;
    }

    /**
     * @see Customer#nev
     */
    public String getNev()
    {
        return nev;
    }

    /**
     * @see Customer#nev
     */
    public void setNev(String nev)
    {
        this.nev = nev;
    }

    /**
     * @see Customer#orszag
     */
    public String getOrszag()
    {
        return orszag;
    }

    /**
     * @see Customer#orszag
     */
    public void setOrszag(String orszag)
    {
        this.orszag = orszag;
    }

    /**
     * @see Customer#irszam
     */
    public String getIrszam()
    {
        return irszam;
    }

    /**
     * @see Customer#irszam
     */
    public void setIrszam(String irszam)
    {
        this.irszam = irszam;
    }

    /**
     * @see Customer#varos
     */
    public String getVaros()
    {
        return varos;
    }

    /**
     * @see Customer#varos
     */
    public void setVaros(String varos)
    {
        this.varos = varos;
    }

    /**
     * @see Customer#cim
     */
    public String getCim()
    {
        return cim;
    }

    /**
     * @see Customer#cim
     */
    public void setCim(String cim)
    {
        this.cim = cim;
    }

    /**
     * @see Customer#szallitasNev
     */
    public String getSzallitasNev()
    {
        return szallitasNev;
    }

    /**
     * @see Customer#szallitasNev
     */
    public void setSzallitasNev(String szallitasNev)
    {
        this.szallitasNev = szallitasNev;
    }

    /**
     * @see Customer#szallitasOrszag
     */
    public String getSzallitasOrszag()
    {
        return szallitasOrszag;
    }

    /**
     * @see Customer#szallitasOrszag
     */
    public void setSzallitasOrszag(String szallitasOrszag)
    {
        this.szallitasOrszag = szallitasOrszag;
    }

    /**
     * @see Customer#szallitasIrszam
     */
    public String getSzallitasIrszam()
    {
        return szallitasIrszam;
    }

    /**
     * @see Customer#szallitasIrszam
     */
    public void setSzallitasIrszam(String szallitasIrszam)
    {
        this.szallitasIrszam = szallitasIrszam;
    }

    /**
     * @see Customer#szallitasVaros
     */
    public String getSzallitasVaros()
    {
        return szallitasVaros;
    }

    /**
     * @see Customer#szallitasVaros
     */
    public void setSzallitasVaros(String szallitasVaros)
    {
        this.szallitasVaros = szallitasVaros;
    }

    /**
     * @see Customer#szallitasCim
     */
    public String getSzallitasCim()
    {
        return szallitasCim;
    }

    /**
     * @see Customer#szallitasCim
     */
    public void setSzallitasCim(String szallitasCim)
    {
        this.szallitasCim = szallitasCim;
    }

    /**
     * @see Customer#kapcsolattartoNev
     */
    public String getKapcsolattartoNev()
    {
        return kapcsolattartoNev;
    }

    /**
     * @see Customer#kapcsolattartoNev
     */
    public void setKapcsolattartoNev(String kapcsolattartoNev)
    {
        this.kapcsolattartoNev = kapcsolattartoNev;
    }

    /**
     * @see Customer#telefonszam
     */
    public String getTelefonszam()
    {
        return telefonszam;
    }

    /**
     * @see Customer#telefonszam
     */
    public void setTelefonszam(String telefonszam)
    {
        this.telefonszam = telefonszam;
    }

    /**
     * @see Customer#fax
     */
    public String getFax()
    {
        return fax;
    }

    /**
     * @see Customer#fax
     */
    public void setFax(String fax)
    {
        this.fax = fax;
    }

    /**
     * @see Customer#email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @see Customer#email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @see Customer#adoszam
     */
    public String getAdoszam()
    {
        return adoszam;
    }

    /**
     * @see Customer#adoszam
     */
    public void setAdoszam(String adoszam)
    {
        this.adoszam = adoszam;
    }

    /**
     * @see Customer#kozossegiAdoszam
     */
    public String getKozossegiAdoszam()
    {
        return kozossegiAdoszam;
    }

    /**
     * @see Customer#kozossegiAdoszam
     */
    public void setKozossegiAdoszam(String kozossegiAdoszam)
    {
        this.kozossegiAdoszam = kozossegiAdoszam;
    }

    /**
     * @see Customer#bankszamlaszam
     */
    public String getBankszamlaszam()
    {
        return bankszamlaszam;
    }

    /**
     * @see Customer#bankszamlaszam
     */
    public void setBankszamlaszam(String bankszamlaszam)
    {
        this.bankszamlaszam = bankszamlaszam;
    }

    /**
     * @see Customer#honlapCim
     */
    public String getHonlapCim()
    {
        return honlapCim;
    }

    /**
     * @see Customer#honlapCim
     */
    public void setHonlapCim(String honlapCim)
    {
        this.honlapCim = honlapCim;
    }

    /**
     * @see Customer#klubkartya
     */
    public String getKlubkartya()
    {
        return klubkartya;
    }

    /**
     * @see Customer#klubkartya
     */
    public void setKlubkartya(String klubkartya)
    {
        this.klubkartya = klubkartya;
    }

    /**
     * @see Customer#csoportKod1
     */
    public Long getCsoportKod1()
    {
        return csoportKod1;
    }

    /**
     * @see Customer#csoportKod1
     */
    public void setCsoportKod1(Long csoportKod1)
    {
        this.csoportKod1 = csoportKod1;
    }

    /**
     * @see Customer#csoport1
     */
    public String getCsoport1()
    {
        return csoport1;
    }

    /**
     * @see Customer#csoport1
     */
    public void setCsoport1(String csoport1)
    {
        this.csoport1 = csoport1;
    }

    /**
     * @see Customer#csoportKod2
     */
    public Long getCsoportKod2()
    {
        return csoportKod2;
    }

    /**
     * @see Customer#csoportKod2
     */
    public void setCsoportKod2(Long csoportKod2)
    {
        this.csoportKod2 = csoportKod2;
    }

    /**
     * @see Customer#csoport2
     */
    public String getCsoport2()
    {
        return csoport2;
    }

    /**
     * @see Customer#csoport2
     */
    public void setCsoport2(String csoport2)
    {
        this.csoport2 = csoport2;
    }

    /**
     * @see Customer#csoportKod3
     */
    public Long getCsoportKod3()
    {
        return csoportKod3;
    }

    /**
     * @see Customer#csoportKod3
     */
    public void setCsoportKod3(Long csoportKod3)
    {
        this.csoportKod3 = csoportKod3;
    }

    /**
     * @see Customer#csoport3
     */
    public String getCsoport3()
    {
        return csoport3;
    }

    /**
     * @see Customer#csoport3
     */
    public void setCsoport3(String csoport3)
    {
        this.csoport3 = csoport3;
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
        Customer customer = (Customer) o;
        return Objects.equals(tipus, customer.tipus) &&
                Objects.equals(ugyfelKod, customer.ugyfelKod) &&
                Objects.equals(nev, customer.nev) &&
                Objects.equals(orszag, customer.orszag) &&
                Objects.equals(irszam, customer.irszam) &&
                Objects.equals(varos, customer.varos) &&
                Objects.equals(cim, customer.cim) &&
                Objects.equals(szallitasNev, customer.szallitasNev) &&
                Objects.equals(szallitasOrszag, customer.szallitasOrszag) &&
                Objects.equals(szallitasIrszam, customer.szallitasIrszam) &&
                Objects.equals(szallitasVaros, customer.szallitasVaros) &&
                Objects.equals(szallitasCim, customer.szallitasCim) &&
                Objects.equals(kapcsolattartoNev, customer.kapcsolattartoNev) &&
                Objects.equals(telefonszam, customer.telefonszam) &&
                Objects.equals(fax, customer.fax) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(adoszam, customer.adoszam) &&
                Objects.equals(kozossegiAdoszam, customer.kozossegiAdoszam) &&
                Objects.equals(bankszamlaszam, customer.bankszamlaszam) &&
                Objects.equals(honlapCim, customer.honlapCim) &&
                Objects.equals(klubkartya, customer.klubkartya) &&
                Objects.equals(csoportKod1, customer.csoportKod1) &&
                Objects.equals(csoport1, customer.csoport1) &&
                Objects.equals(csoportKod2, customer.csoportKod2) &&
                Objects.equals(csoport2, customer.csoport2) &&
                Objects.equals(csoportKod3, customer.csoportKod3) &&
                Objects.equals(csoport3, customer.csoport3);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(tipus, ugyfelKod, nev, orszag, irszam, varos, cim, szallitasNev, szallitasOrszag, szallitasIrszam, szallitasVaros, szallitasCim,
                            kapcsolattartoNev, telefonszam, fax, email, adoszam, kozossegiAdoszam, bankszamlaszam, honlapCim, klubkartya,
                            csoportKod1, csoport1, csoportKod2, csoport2, csoportKod3, csoport3);
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "tipus='" + tipus + '\'' +
                ", ugyfelKod=" + ugyfelKod +
                ", nev='" + nev + '\'' +
                ", orszag='" + orszag + '\'' +
                ", irszam='" + irszam + '\'' +
                ", varos='" + varos + '\'' +
                ", cim='" + cim + '\'' +
                ", szallitasNev='" + szallitasNev + '\'' +
                ", szallitasOrszag='" + szallitasOrszag + '\'' +
                ", szallitasIrszam='" + szallitasIrszam + '\'' +
                ", szallitasVaros='" + szallitasVaros + '\'' +
                ", szallitasCim='" + szallitasCim + '\'' +
                ", kapcsolattartoNev='" + kapcsolattartoNev + '\'' +
                ", telefonszam='" + telefonszam + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", adoszam='" + adoszam + '\'' +
                ", kozossegiAdoszam='" + kozossegiAdoszam + '\'' +
                ", bankszamlaszam='" + bankszamlaszam + '\'' +
                ", honlapCim='" + honlapCim + '\'' +
                ", klubkartya='" + klubkartya + '\'' +
                ", csoportKod1=" + csoportKod1 +
                ", csoport1='" + csoport1 + '\'' +
                ", csoportKod2=" + csoportKod2 +
                ", csoport2='" + csoport2 + '\'' +
                ", csoportKod3=" + csoportKod3 +
                ", csoport3='" + csoport3 + '\'' +
                '}';
    }

}
