package hu.dlaszlo.natura2remek.guice.provider;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import javafx.fxml.FXMLLoader;

/**
 *  FXMLLoader létrehozó
 */
public class FXMLLoaderProvider implements Provider<FXMLLoader>
{

    @Inject
    private Injector injector;

    @Override
    public FXMLLoader get()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(p -> injector.getInstance(p));
        return loader;
    }

}
