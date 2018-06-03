package hu.dlaszlo.natura2remek.guice.store;

import hu.dlaszlo.natura2remek.csv.naturacsv.NaturaCsvPackage;
import hu.dlaszlo.natura2remek.csv.remekcsv.RemekCsvPackage;

/**
 * Tároló, a kontrollerek ezen az osztályon keresztűl osztják meg egymás között az adatokat
 */
public interface Store
{
    /**
     * @return Natura CSV csomag
     */
    NaturaCsvPackage getNaturaCsvPackage();

    /**
     * @param naturaCsvPackage Natura CSV csomag
     */
    void setNaturaCsvPackage(NaturaCsvPackage naturaCsvPackage);

    /**
     * @return Remek CSV csomag
     */
    RemekCsvPackage getRemekCsvPackage();

    /**
     * @param remekCsvPackage Remek CSV csomag
     */
    void setRemekCsvPackage(RemekCsvPackage remekCsvPackage);

}
