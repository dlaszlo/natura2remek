package hu.dlaszlo.natura2remek.csv;

import hu.dlaszlo.natura2remek.csv.naturacsv.NaturaCsvPackage;
import hu.dlaszlo.natura2remek.csv.remekcsv.RemekCsvPackage;

/**
 * Natura CSV adatok -> Remek CSV adatok konvertálását, validálását, mentését végző szolgáltatás
 */
public interface RemekCsvService
{

    /**
     * Natura CSV adatok konvertálása Remek CSV adatokra
     *
     * @param naturaCsvPackage Natura CSV adatok
     * @return Remek CSV adatok
     */
    RemekCsvPackage convert(NaturaCsvPackage naturaCsvPackage);

    /**
     * Remek CSV adatok elmentése
     *
     * @param filename Fájlnév
     * @param remekCsvPackage Remek CSV adatok
     */
    void saveCSV(String filename, RemekCsvPackage remekCsvPackage);
}
