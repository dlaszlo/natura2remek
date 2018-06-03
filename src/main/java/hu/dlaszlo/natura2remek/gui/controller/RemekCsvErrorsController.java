package hu.dlaszlo.natura2remek.gui.controller;

import com.google.inject.Inject;
import hu.dlaszlo.natura2remek.csv.CsvError;
import hu.dlaszlo.natura2remek.csv.remekcsv.RemekCsvPackage;
import hu.dlaszlo.natura2remek.guice.store.Store;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Natura CSV fájlok betöltése
 */
public class RemekCsvErrorsController extends AbstractController
{
    @Inject
    private Store store;

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
    private Button exitBtn;

    @FXML
    private Button prevBtn;


    @FXML
    private void nextPage()
    {
        Platform.exit();
    }

    @FXML
    private void prevPage()
    {
        setPage("viewNaturaCsv");
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

        RemekCsvPackage remekCsvPackage = store.getRemekCsvPackage();
        errorTbl.getItems().setAll(remekCsvPackage.getErrors());

    }

    @Override
    public String getTitle()
    {
        return "Remek CSV hibák megtekintése";
    }

}
