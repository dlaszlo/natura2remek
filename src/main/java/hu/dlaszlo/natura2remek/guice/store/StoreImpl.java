package hu.dlaszlo.natura2remek.guice.store;

import hu.dlaszlo.natura2remek.csv.naturacsv.NaturaCsvPackage;
import hu.dlaszlo.natura2remek.csv.remekcsv.RemekCsvPackage;

import javax.inject.Singleton;

/**
 * Tároló megvalósítás
 */
@Singleton
public class StoreImpl implements Store
{

    private NaturaCsvPackage naturaNaturaCsvPackage;

    private RemekCsvPackage remekCsvPackage;

    @Override
    public NaturaCsvPackage getNaturaCsvPackage()
    {
        return naturaNaturaCsvPackage;
    }

    @Override
    public void setNaturaCsvPackage(NaturaCsvPackage naturaCsvPackage)
    {
        this.naturaNaturaCsvPackage = naturaCsvPackage;
    }

    @Override
    public RemekCsvPackage getRemekCsvPackage()
    {
        return remekCsvPackage;
    }

    @Override
    public void setRemekCsvPackage(RemekCsvPackage remekCsvPackage)
    {
        this.remekCsvPackage = remekCsvPackage;
    }
}
