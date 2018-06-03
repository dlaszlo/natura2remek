package hu.dlaszlo.natura2remek.csv;

import hu.dlaszlo.natura2remek.csv.naturacsv.NaturaCsvPackage;

/**
 * Natura CSV fájl betöltését, validálását végző szolgáltatás
 */
public interface NaturaCsvService
{
    /**
     * Natura CSV fájl betöltése
     *
     * @param fileName Fájlnév
     * @return Betöltött CSV fájl
     */
    NaturaCsvPackage loadCSV(String fileName);
}
