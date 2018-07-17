package hu.dlaszlo.natura2remek.guice;

import com.google.inject.AbstractModule;
import hu.dlaszlo.natura2remek.config.ConfigService;
import hu.dlaszlo.natura2remek.config.ConfigServiceImpl;
import hu.dlaszlo.natura2remek.csv.NaturaCsvService;
import hu.dlaszlo.natura2remek.csv.NaturaCsvServiceImpl;
import hu.dlaszlo.natura2remek.csv.RemekCsvService;
import hu.dlaszlo.natura2remek.csv.RemekCsvServiceImpl;
import hu.dlaszlo.natura2remek.guice.provider.FXMLLoaderProvider;
import hu.dlaszlo.natura2remek.guice.store.Store;
import hu.dlaszlo.natura2remek.guice.store.StoreImpl;
import javafx.fxml.FXMLLoader;

/**
 * Guice modul
 */
public class GuiceModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(FXMLLoader.class).toProvider(FXMLLoaderProvider.class);
        bind(NaturaCsvService.class).to(NaturaCsvServiceImpl.class);
        bind(RemekCsvService.class).to(RemekCsvServiceImpl.class);
        bind(Store.class).to(StoreImpl.class);
        bind(ConfigService.class).to(ConfigServiceImpl.class);
    }
}
