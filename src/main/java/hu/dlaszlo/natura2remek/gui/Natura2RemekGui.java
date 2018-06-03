package hu.dlaszlo.natura2remek.gui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hu.dlaszlo.natura2remek.gui.controller.AbstractController;
import hu.dlaszlo.natura2remek.guice.GuiceModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;

/**
 * Natura CSV - Remek CSV konvertáló program
 */
public class Natura2RemekGui extends Application
{
    Injector injector = Guice.createInjector(new GuiceModule());

    private Stage stage = null;

    private Map<String, Scene> pageMap = new LinkedHashMap<>();
    private Map<String, AbstractController> controllerMap = new LinkedHashMap<>();

    /**
     * Oldal váltás. Ha az oldal még nem volt betöltve, akkor betölti a program
     *
     * @param pageName Az oldal neve (az fxml fájl neve kiterjesztés, és elérési út nélkül).
     */
    public void setPage(String pageName)
    {
        try
        {
            Scene scene = pageMap.get(pageName);
            AbstractController controller = controllerMap.get(pageName);
            if (scene == null)
            {
                FXMLLoader loader = injector.getInstance(FXMLLoader.class);
                Parent parent = loader.load(getClass().getResourceAsStream("/scenes/" + pageName + ".fxml"));
                scene = new Scene(parent);
                controller = loader.getController();
                controller.setOnSetPage(this::setPage);
                pageMap.put(pageName, scene);
                controllerMap.put(pageName, controller);
            }
            stage.setScene(scene);

            setTitle(controller);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void setTitle(AbstractController controller)
    {
        StringBuilder sb = new StringBuilder("Főkönyvi feladás");
        if (StringUtils.isNotBlank(controller.getTitle()))
        {
            sb.append(" - ").append(controller.getTitle().trim());
        }
        stage.setTitle(sb.toString());
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.stage = primaryStage;
        stage.setWidth(1024);
        stage.setHeight(768);

        setPage("loadNaturaCsv");

        primaryStage.show();

    }

    @Override
    public void stop() throws Exception
    {
        super.stop();
    }
}
