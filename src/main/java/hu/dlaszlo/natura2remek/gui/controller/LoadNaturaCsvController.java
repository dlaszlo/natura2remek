package hu.dlaszlo.natura2remek.gui.controller;

import com.google.inject.Inject;
import hu.dlaszlo.natura2remek.config.ConfigService;
import hu.dlaszlo.natura2remek.guice.store.Store;
import hu.dlaszlo.natura2remek.csv.NaturaCsvService;
import hu.dlaszlo.natura2remek.csv.CsvError;
import hu.dlaszlo.natura2remek.csv.naturacsv.NaturaCsvPackage;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Natura CSV fájlok betöltése
 */
public class LoadNaturaCsvController extends AbstractController
{
    @Inject
    private NaturaCsvService naturaCsvService;

    @Inject
    private Store store;

    @Inject
    private ConfigService configService;

    private StringProperty infoLine = new SimpleStringProperty("");

    @FXML
    private Label infoLbl;

    @FXML
    private Label fileLbl;

    @FXML
    private TableView<CsvError> errorTbl;

    @FXML
    private TableColumn<CsvError, String> recordColumn;

    @FXML
    private TableColumn<CsvError, String> typeColumn;

    @FXML
    private TableColumn<CsvError, String> fieldColumn;

    @FXML
    private TableColumn<CsvError, String> messageColumn;

    @FXML
    private Button nextPageBtn;

    @FXML
    private Button loadNaturaCsvBtn;

    @FXML
    private void loadCsvFile()
    {
        FileChooser fileChooser = getFileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null)
        {
            try
            {
                NaturaCsvPackage naturaCsvPackage = naturaCsvService.loadCSV(file.getAbsolutePath());
                Platform.runLater(() -> {
                    fileLbl.setText(file.getAbsolutePath());
                    if (!naturaCsvPackage.getErrors().isEmpty())
                    {
                        infoLine.set("A konvertálás nem folytatódhat a hibák miatt.");
                        showErrors(naturaCsvPackage);
                        store.setNaturaCsvPackage(null);
                        nextPageBtn.setDisable(true);
                    }
                    else
                    {
                        infoLine.set("A CSV fájl betöltése rendben megtörtént.");
                        clearErrors();
                        store.setNaturaCsvPackage(naturaCsvPackage);
                        nextPageBtn.setDisable(false);
                        nextPage();
                    }
                });
            }
            catch (RuntimeException e)
            {
                loadNaturaCsvBtn.setDisable(false);
                showErrors(null);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Hiba történt!");
                alert.setContentText("Kérem, hogy ellenőrizze a naplót.");
                alert.showAndWait();
            }
        }
    }

    private FileChooser getFileChooser()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Natura CSV fájl megnyitása");
        if (StringUtils.isNotBlank(configService.getNaturaDir()))
        {
            Path p = Paths.get(configService.getNaturaDir());
            if (Files.exists(p) && Files.isDirectory(p))
            {
                fileChooser.setInitialDirectory(new File(configService.getNaturaDir()));
            }
        }
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        return fileChooser;
    }

    @FXML
    private void nextPage()
    {
        setPage("viewNaturaCsv");
    }

    private void showErrors(NaturaCsvPackage naturaCsvPackage)
    {
        errorTbl.setManaged(true);
        errorTbl.setVisible(true);
        if (naturaCsvPackage != null)
        {
            errorTbl.getItems().setAll(naturaCsvPackage.getErrors());
        }
        else
        {
            errorTbl.getItems().setAll((List<CsvError>)null);
        }
    }

    private void clearErrors()
    {
        errorTbl.setManaged(false);
        errorTbl.setVisible(false);
        errorTbl.getItems().clear();
        nextPageBtn.setDisable(false);
    }

    @FXML
    @SuppressWarnings("Duplicates")
    private void initialize()
    {
        recordColumn.setCellValueFactory(cell -> {
            CsvError csvError = cell.getValue();
            StringBuilder sb = new StringBuilder();
            sb.append(csvError.getRecordFrom());
            if (csvError.getRecordTo() != null
                    && !csvError.getRecordTo().equals(csvError.getRecordFrom()))
            {
                sb.append("-").append(csvError.getRecordTo());
            }
            return new SimpleStringProperty(sb.toString());
        });
        typeColumn.setCellValueFactory(new PropertyValueFactory("tipus"));
        fieldColumn.setCellValueFactory(new PropertyValueFactory("mezo"));
        messageColumn.setCellValueFactory(new PropertyValueFactory("uzenet"));

        infoLbl.textProperty().bind(infoLine);
    }

    @Override
    public String getTitle()
    {
        return "1. lépés: Natura CSV fájl betöltése";
    }
}
