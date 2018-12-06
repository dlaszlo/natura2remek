package hu.dlaszlo.natura2remek.csv.remekcsv;

import hu.dlaszlo.natura2remek.csv.AbstractEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

/**
 * "REMEK" főkönyvbe importálható CSV fájl formátuma (sor leírás)
 */
public class RemekCsvRow extends AbstractEntity implements Serializable
{
    private static final long serialVersionUID = -8023842473453494857L;

    /**
     * 01.(A) Számlaszám (20)
     */
    @NotEmpty
    @Length(max = 20)
    private String szamlaszam;

    /**
     * 02.(B) Kiállítás dátuma (10)
     */
    @NotEmpty
    @Pattern(regexp = "20[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}")
    @Length(min = 10, max = 10)
    private String kiallitasDatuma;

    /**
     * 03.(C) Teljesítés kelte (10)
     */
    @NotEmpty
    @Pattern(regexp = "20[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}")
    @Length(min = 10, max = 10)
    private String teljesitesKelte;

    /**
     * 04.(D) Fiz.határid (10)
     */
    @NotEmpty
    @Pattern(regexp = "20[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}")
    @Length(min = 10, max = 10)
    private String fizetesiHatarido;

    /**
     * 05.(E) Fiz.mód (20)
     */
    @NotEmpty
    @Length(max = 20)
    private String fizetesiMod;

    /**
     * 06.(F) ÁFA% (20)
     */
    @NotEmpty
    @Pattern(regexp = "[0-9]+(,[0-9]{1,4})?")
    @Length(max = 20)
    private String afaSzazalek;

    /**
     * 07.(G) Nettó (20)
     */
    @NotEmpty
    @Pattern(regexp = "-{0,1}[0-9]+(,[0-9]{1,4})?")
    @Length(max = 20)
    private String netto;

    /**
     * 08.(H) ÁFA (20)
     */
    @NotEmpty
    @Pattern(regexp = "-{0,1}[0-9]+(,[0-9]{1,4})?")
    @Length(max = 20)
    private String afa;

    /**
     * 09.(I) Bruttó (20)
     */
    @NotEmpty
    @Pattern(regexp = "-{0,1}[0-9]+(,[0-9]{1,4})?")
    @Length(max = 20)
    private String brutto;

    /**
     * 10.(J) Megnevezés (40)
     */
    @NotEmpty
    @Length(max = 40)
    private String megnevezes;

    /**
     * 11.(K) Vevőkód (6)
     */
    @NotEmpty
    @Pattern(regexp = "4[0-9]{5}")
    @Length(min = 6, max = 6)
    private String vevokod;

    /**
     * 12.(L) Vevő név (60)
     */
    @NotEmpty
    @Length(max = 60)
    private String vevonev;

    /**
     * 13.(M) Ir.szám (10)
     */
    @Length(max = 10)
    private String irszam;

    /**
     * 14.(N) Város (40)
     */
    @Length(max = 40)
    private String varos;

    /**
     * 15.(O) Utca,házszám,stb. (40)
     */
    @Length(max = 40)
    private String utcaHazszam;

    /**
     * 16.(P) Vevő adószáma (20)
     */
    @Length(max = 20)
    private String vevoAdoszam;

    /**
     * 17.(Q) Opcionális könyvelési (kontírozási) paraméter (20)
     */
    @NotEmpty
    @Length(max = 20)
    private String kontirozasiParameter1;

    /**
     * 18.(R) Opcionális könyvelési (kontírozási) paraméter (20)
     */
    @Length(max = 20)
    private String kontirozasiParameter2;

    /**
     * 19.(S) Opcionális könyvelési (kontírozási) paraméter (20)
     */
    @Length(max = 20)
    private String kontirozasiParameter3;

    /**
     * 20.(T) Opcionális könyvelési (kontírozási) paraméter (20)
     */
    @Length(max = 20)
    private String kontirozasiParameter4;

    /**
     * 21.(U) Opcionális könyvelési (kontírozási) paraméter (20)
     */
    @Length(max = 20)
    private String kontirozasiParameter5;

    /**
     * 22.(V) Opcionális könyvelési (kontírozási) paraméter (20)
     */
    @Length(max = 20)
    private String kontirozasiParameter6;

    /**
     * 23.(W) Opcionális: Devizanem (3)
     */
    @Length(max = 3)
    private String devizaNem;

    /**
     * 24.(X) Opcionális: Nettó deviza (20)
     */
    @Length(max = 20)
    private String nettoDeviza;

    /**
     * 25.(Y) Opcionális: ÁFA deviza (20)
     */
    @Length(max = 20)
    private String afaDeviza;

    /**
     * 26.(Z) Opcionális: Bruttó deviza (20)
     */
    @Length(max = 20)
    private String bruttoDeviza;

    /**
     * 27.(AA) Opcionális: Vevő címe, ha nincs mezőkre bontva
     */
    private String vevoCimeEgyben;

    /**
     * 28.(AB) Opcionális: Vevő EU adószáma
     */
    private String vevoEUAdoszam;

    @Override
    public String getTipus()
    {
        return "REMEK";
    }

    /**
     * Konstruktor
     *
     * @param recordFrom Sortól
     * @param recordTo Sorig
     */
    public RemekCsvRow(int recordFrom, int recordTo)
    {
        super(recordFrom, recordTo);
    }

    /**
     * @see RemekCsvRow#szamlaszam
     */
    public String getSzamlaszam()
    {
        return szamlaszam;
    }

    /**
     * @see RemekCsvRow#szamlaszam
     */
    public void setSzamlaszam(String szamlaszam)
    {
        this.szamlaszam = szamlaszam;
    }

    /**
     * @see RemekCsvRow#kiallitasDatuma
     */
    public String getKiallitasDatuma()
    {
        return kiallitasDatuma;
    }

    /**
     * @see RemekCsvRow#kiallitasDatuma
     */
    public void setKiallitasDatuma(String kiallitasDatuma)
    {
        this.kiallitasDatuma = kiallitasDatuma;
    }

    /**
     * @see RemekCsvRow#teljesitesKelte
     */
    public String getTeljesitesKelte()
    {
        return teljesitesKelte;
    }

    /**
     * @see RemekCsvRow#teljesitesKelte
     */
    public void setTeljesitesKelte(String teljesitesKelte)
    {
        this.teljesitesKelte = teljesitesKelte;
    }

    /**
     * @see RemekCsvRow#fizetesiHatarido
     */
    public String getFizetesiHatarido()
    {
        return fizetesiHatarido;
    }

    /**
     * @see RemekCsvRow#fizetesiHatarido
     */
    public void setFizetesiHatarido(String fizetesiHatarido)
    {
        this.fizetesiHatarido = fizetesiHatarido;
    }

    /**
     * @see RemekCsvRow#fizetesiMod
     */
    public String getFizetesiMod()
    {
        return fizetesiMod;
    }

    /**
     * @see RemekCsvRow#fizetesiMod
     */
    public void setFizetesiMod(String fizetesiMod)
    {
        this.fizetesiMod = fizetesiMod;
    }

    /**
     * @see RemekCsvRow#afaSzazalek
     */
    public String getAfaSzazalek()
    {
        return afaSzazalek;
    }

    /**
     * @see RemekCsvRow#afaSzazalek
     */
    public void setAfaSzazalek(String afaSzazalek)
    {
        this.afaSzazalek = afaSzazalek;
    }

    /**
     * @see RemekCsvRow#netto
     */
    public String getNetto()
    {
        return netto;
    }

    /**
     * @see RemekCsvRow#netto
     */
    public void setNetto(String netto)
    {
        this.netto = netto;
    }

    /**
     * @see RemekCsvRow#afa
     */
    public String getAfa()
    {
        return afa;
    }

    /**
     * @see RemekCsvRow#afa
     */
    public void setAfa(String afa)
    {
        this.afa = afa;
    }

    /**
     * @see RemekCsvRow#brutto
     */
    public String getBrutto()
    {
        return brutto;
    }

    /**
     * @see RemekCsvRow#brutto
     */
    public void setBrutto(String brutto)
    {
        this.brutto = brutto;
    }

    /**
     * @see RemekCsvRow#megnevezes
     */
    public String getMegnevezes()
    {
        return megnevezes;
    }

    /**
     * @see RemekCsvRow#megnevezes
     */
    public void setMegnevezes(String megnevezes)
    {
        this.megnevezes = megnevezes;
    }

    /**
     * @see RemekCsvRow#vevokod
     */
    public String getVevokod()
    {
        return vevokod;
    }

    /**
     * @see RemekCsvRow#vevokod
     */
    public void setVevokod(String vevokod)
    {
        this.vevokod = vevokod;
    }

    /**
     * @see RemekCsvRow#vevonev
     */
    public String getVevonev()
    {
        return vevonev;
    }

    /**
     * @see RemekCsvRow#vevonev
     */
    public void setVevonev(String vevonev)
    {
        this.vevonev = vevonev;
    }

    /**
     * @see RemekCsvRow#irszam
     */
    public String getIrszam()
    {
        return irszam;
    }

    /**
     * @see RemekCsvRow#irszam
     */
    public void setIrszam(String irszam)
    {
        this.irszam = irszam;
    }

    /**
     * @see RemekCsvRow#varos
     */
    public String getVaros()
    {
        return varos;
    }

    /**
     * @see RemekCsvRow#varos
     */
    public void setVaros(String varos)
    {
        this.varos = varos;
    }

    /**
     * @see RemekCsvRow#utcaHazszam
     */
    public String getUtcaHazszam()
    {
        return utcaHazszam;
    }

    /**
     * @see RemekCsvRow#utcaHazszam
     */
    public void setUtcaHazszam(String utcaHazszam)
    {
        this.utcaHazszam = utcaHazszam;
    }

    /**
     * @see RemekCsvRow#vevoAdoszam
     */
    public String getVevoAdoszam()
    {
        return vevoAdoszam;
    }

    /**
     * @see RemekCsvRow#vevoAdoszam
     */
    public void setVevoAdoszam(String vevoAdoszam)
    {
        this.vevoAdoszam = vevoAdoszam;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter1
     */
    public String getKontirozasiParameter1()
    {
        return kontirozasiParameter1;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter1
     */
    public void setKontirozasiParameter1(String kontirozasiParameter1)
    {
        this.kontirozasiParameter1 = kontirozasiParameter1;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter2
     */
    public String getKontirozasiParameter2()
    {
        return kontirozasiParameter2;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter2
     */
    public void setKontirozasiParameter2(String kontirozasiParameter2)
    {
        this.kontirozasiParameter2 = kontirozasiParameter2;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter3
     */
    public String getKontirozasiParameter3()
    {
        return kontirozasiParameter3;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter3
     */
    public void setKontirozasiParameter3(String kontirozasiParameter3)
    {
        this.kontirozasiParameter3 = kontirozasiParameter3;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter4
     */
    public String getKontirozasiParameter4()
    {
        return kontirozasiParameter4;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter4
     */
    public void setKontirozasiParameter4(String kontirozasiParameter4)
    {
        this.kontirozasiParameter4 = kontirozasiParameter4;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter5
     */
    public String getKontirozasiParameter5()
    {
        return kontirozasiParameter5;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter5
     */
    public void setKontirozasiParameter5(String kontirozasiParameter5)
    {
        this.kontirozasiParameter5 = kontirozasiParameter5;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter6
     */
    public String getKontirozasiParameter6()
    {
        return kontirozasiParameter6;
    }

    /**
     * @see RemekCsvRow#kontirozasiParameter6
     */
    public void setKontirozasiParameter6(String kontirozasiParameter6)
    {
        this.kontirozasiParameter6 = kontirozasiParameter6;
    }

    /**
     * @see RemekCsvRow#devizaNem
     */
    public String getDevizaNem()
    {
        return devizaNem;
    }

    /**
     * @see RemekCsvRow#devizaNem
     */
    public void setDevizaNem(String devizaNem)
    {
        this.devizaNem = devizaNem;
    }

    /**
     * @see RemekCsvRow#nettoDeviza
     */
    public String getNettoDeviza()
    {
        return nettoDeviza;
    }

    /**
     * @see RemekCsvRow#nettoDeviza
     */
    public void setNettoDeviza(String nettoDeviza)
    {
        this.nettoDeviza = nettoDeviza;
    }

    /**
     * @see RemekCsvRow#afaDeviza
     */
    public String getAfaDeviza()
    {
        return afaDeviza;
    }

    /**
     * @see RemekCsvRow#afaDeviza
     */
    public void setAfaDeviza(String afaDeviza)
    {
        this.afaDeviza = afaDeviza;
    }

    /**
     * @see RemekCsvRow#bruttoDeviza
     */
    public String getBruttoDeviza()
    {
        return bruttoDeviza;
    }

    /**
     * @see RemekCsvRow#bruttoDeviza
     */
    public void setBruttoDeviza(String bruttoDeviza)
    {
        this.bruttoDeviza = bruttoDeviza;
    }

    /**
     * @see RemekCsvRow#vevoCimeEgyben
     */
    public String getVevoCimeEgyben()
    {
        return vevoCimeEgyben;
    }

    /**
     * @see RemekCsvRow#vevoCimeEgyben
     */
    public void setVevoCimeEgyben(String vevoCimeEgyben)
    {
        this.vevoCimeEgyben = vevoCimeEgyben;
    }

    /**
     * @see RemekCsvRow#vevoEUAdoszam
     */
    public String getVevoEUAdoszam()
    {
        return vevoEUAdoszam;
    }

    /**
     * @see RemekCsvRow#vevoEUAdoszam
     */
    public void setVevoEUAdoszam(String vevoEUAdoszam)
    {
        this.vevoEUAdoszam = vevoEUAdoszam;
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
        RemekCsvRow that = (RemekCsvRow) o;
        return Objects.equals(szamlaszam, that.szamlaszam) &&
                Objects.equals(kiallitasDatuma, that.kiallitasDatuma) &&
                Objects.equals(teljesitesKelte, that.teljesitesKelte) &&
                Objects.equals(fizetesiHatarido, that.fizetesiHatarido) &&
                Objects.equals(fizetesiMod, that.fizetesiMod) &&
                Objects.equals(afaSzazalek, that.afaSzazalek) &&
                Objects.equals(netto, that.netto) &&
                Objects.equals(afa, that.afa) &&
                Objects.equals(brutto, that.brutto) &&
                Objects.equals(megnevezes, that.megnevezes) &&
                Objects.equals(vevokod, that.vevokod) &&
                Objects.equals(vevonev, that.vevonev) &&
                Objects.equals(irszam, that.irszam) &&
                Objects.equals(varos, that.varos) &&
                Objects.equals(utcaHazszam, that.utcaHazszam) &&
                Objects.equals(vevoAdoszam, that.vevoAdoszam) &&
                Objects.equals(kontirozasiParameter1, that.kontirozasiParameter1) &&
                Objects.equals(kontirozasiParameter2, that.kontirozasiParameter2) &&
                Objects.equals(kontirozasiParameter3, that.kontirozasiParameter3) &&
                Objects.equals(kontirozasiParameter4, that.kontirozasiParameter4) &&
                Objects.equals(kontirozasiParameter5, that.kontirozasiParameter5) &&
                Objects.equals(kontirozasiParameter6, that.kontirozasiParameter6) &&
                Objects.equals(devizaNem, that.devizaNem) &&
                Objects.equals(nettoDeviza, that.nettoDeviza) &&
                Objects.equals(afaDeviza, that.afaDeviza) &&
                Objects.equals(bruttoDeviza, that.bruttoDeviza) &&
                Objects.equals(vevoCimeEgyben, that.vevoCimeEgyben) &&
                Objects.equals(vevoEUAdoszam, that.vevoEUAdoszam);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), szamlaszam, kiallitasDatuma, teljesitesKelte, fizetesiHatarido, fizetesiMod,
                            afaSzazalek, netto, afa, brutto, megnevezes, vevokod, vevonev, irszam, varos, utcaHazszam, vevoAdoszam,
                            kontirozasiParameter1, kontirozasiParameter2, kontirozasiParameter3, kontirozasiParameter4, kontirozasiParameter5,
                            kontirozasiParameter6, devizaNem, nettoDeviza, afaDeviza, bruttoDeviza, vevoCimeEgyben, vevoEUAdoszam);
    }

    @Override
    public String toString()
    {
        return "RemekCsvRow{" +
                "szamlaszam='" + szamlaszam + '\'' +
                ", kiallitasDatuma='" + kiallitasDatuma + '\'' +
                ", teljesitesKelte='" + teljesitesKelte + '\'' +
                ", fizetesiHatarido='" + fizetesiHatarido + '\'' +
                ", fizetesiMod='" + fizetesiMod + '\'' +
                ", afaSzazalek='" + afaSzazalek + '\'' +
                ", netto='" + netto + '\'' +
                ", afa='" + afa + '\'' +
                ", brutto='" + brutto + '\'' +
                ", megnevezes='" + megnevezes + '\'' +
                ", vevokod='" + vevokod + '\'' +
                ", vevonev='" + vevonev + '\'' +
                ", irszam='" + irszam + '\'' +
                ", varos='" + varos + '\'' +
                ", utcaHazszam='" + utcaHazszam + '\'' +
                ", vevoAdoszam='" + vevoAdoszam + '\'' +
                ", kontirozasiParameter1='" + kontirozasiParameter1 + '\'' +
                ", kontirozasiParameter2='" + kontirozasiParameter2 + '\'' +
                ", kontirozasiParameter3='" + kontirozasiParameter3 + '\'' +
                ", kontirozasiParameter4='" + kontirozasiParameter4 + '\'' +
                ", kontirozasiParameter5='" + kontirozasiParameter5 + '\'' +
                ", kontirozasiParameter6='" + kontirozasiParameter6 + '\'' +
                ", devizaNem='" + devizaNem + '\'' +
                ", nettoDeviza='" + nettoDeviza + '\'' +
                ", afaDeviza='" + afaDeviza + '\'' +
                ", bruttoDeviza='" + bruttoDeviza + '\'' +
                ", vevoCimeEgyben='" + vevoCimeEgyben + '\'' +
                ", vevoEUAdoszam='" + vevoEUAdoszam + '\'' +
                '}';
    }
}
