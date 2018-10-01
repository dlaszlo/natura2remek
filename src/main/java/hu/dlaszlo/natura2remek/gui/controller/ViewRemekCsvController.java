package hu.dlaszlo.natura2remek.gui.controller;

import com.google.inject.Inject;
import hu.dlaszlo.natura2remek.config.ConfigService;
import hu.dlaszlo.natura2remek.csv.RemekCsvService;
import hu.dlaszlo.natura2remek.csv.remekcsv.RemekCsvRow;
import hu.dlaszlo.natura2remek.gui.utils.FormatUtils;
import hu.dlaszlo.natura2remek.guice.store.Store;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Natura CSV fájlok megtekintése
 */
public class ViewRemekCsvController extends AbstractController
{
    @Inject
    private Store store;

    @Inject
    private RemekCsvService remekCsvService;

    @Inject
    private ConfigService configService;

    private StringProperty infoLine = new SimpleStringProperty("");

    @FXML
    private Label infoLbl;

    @FXML
    private TextField nettoOsszesenTxt;

    @FXML
    private TextField afaOsszesenTxt;

    @FXML
    private TextField bruttoOsszesenTxt;

    @FXML
    private TableView<RemekCsvRow> csvTbl;

    @FXML
    private TableColumn<RemekCsvRow, String> szamlaszamColumn;


    @FXML
    private TableColumn<RemekCsvRow, String> kiallitasDatumaColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> teljesitesKelteColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> fizetesiHataridoColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> fizetesiModColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> afaSzazalekColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> nettoColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> afaColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> bruttoColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> megnevezesColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> vevokodColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> vevonevColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> irszamColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> varosColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> utcaHazszamColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> vevoAdoszamColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> kontirozasiParameter1Column;

    @FXML
    private TableColumn<RemekCsvRow, String> kontirozasiParameter2Column;

    @FXML
    private TableColumn<RemekCsvRow, String> kontirozasiParameter3Column;

    @FXML
    private TableColumn<RemekCsvRow, String> kontirozasiParameter4Column;

    @FXML
    private TableColumn<RemekCsvRow, String> kontirozasiParameter5Column;

    @FXML
    private TableColumn<RemekCsvRow, String> kontirozasiParameter6Column;

    @FXML
    private TableColumn<RemekCsvRow, String> devizaNemColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> nettoDevizaColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> afaDevizaColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> bruttoDevizaColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> vevoCimeEgybenColumn;

    @FXML
    private TableColumn<RemekCsvRow, String> vevoEUAdoszamColumn;

    @FXML
    private void nextPage()
    {
        FileChooser fileChooser = getFileChooser();
        File f = fileChooser.showSaveDialog(new Stage());
        remekCsvService.saveCSV(f.getAbsolutePath(), store.getRemekCsvPackage());
        Platform.exit();
    }

    private FileChooser getFileChooser()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Remek CSV fájl mentése");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        fileChooser.setInitialFileName(dateFormatter.format(LocalDate.now()) + "_remek.csv");
        if (StringUtils.isNotBlank(configService.getRemekDir()))
        {
            Path p = Paths.get(configService.getRemekDir());
            if (Files.exists(p) && Files.isDirectory(p))
            {
                fileChooser.setInitialDirectory(new File(configService.getRemekDir()));
            }
        }
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        return fileChooser;
    }

    @FXML
    private void prevPage()
    {
        setPage("viewNaturaCsv");
    }

    @FXML
    private void initialize()
    {
        szamlaszamColumn.setCellValueFactory(new PropertyValueFactory("szamlaszam"));
        kiallitasDatumaColumn.setCellValueFactory(new PropertyValueFactory("kiallitasDatuma"));
        teljesitesKelteColumn.setCellValueFactory(new PropertyValueFactory("teljesitesKelte"));
        fizetesiHataridoColumn.setCellValueFactory(new PropertyValueFactory("fizetesiHatarido"));
        fizetesiModColumn.setCellValueFactory(new PropertyValueFactory("fizetesiMod"));
        afaSzazalekColumn.setCellValueFactory(new PropertyValueFactory("afaSzazalek"));
        nettoColumn.setCellValueFactory(new PropertyValueFactory("netto"));
        afaColumn.setCellValueFactory(new PropertyValueFactory("afa"));
        bruttoColumn.setCellValueFactory(new PropertyValueFactory("brutto"));
        megnevezesColumn.setCellValueFactory(new PropertyValueFactory("megnevezes"));
        vevokodColumn.setCellValueFactory(new PropertyValueFactory("vevokod"));
        vevonevColumn.setCellValueFactory(new PropertyValueFactory("vevonev"));
        irszamColumn.setCellValueFactory(new PropertyValueFactory("irszam"));
        varosColumn.setCellValueFactory(new PropertyValueFactory("varos"));
        utcaHazszamColumn.setCellValueFactory(new PropertyValueFactory("utcaHazszam"));
        vevoAdoszamColumn.setCellValueFactory(new PropertyValueFactory("vevoAdoszam"));
        kontirozasiParameter1Column.setCellValueFactory(new PropertyValueFactory("kontirozasiParameter1"));
        kontirozasiParameter2Column.setCellValueFactory(new PropertyValueFactory("kontirozasiParameter2"));
        kontirozasiParameter3Column.setCellValueFactory(new PropertyValueFactory("kontirozasiParameter3"));
        kontirozasiParameter4Column.setCellValueFactory(new PropertyValueFactory("kontirozasiParameter4"));
        kontirozasiParameter5Column.setCellValueFactory(new PropertyValueFactory("kontirozasiParameter5"));
        kontirozasiParameter6Column.setCellValueFactory(new PropertyValueFactory("kontirozasiParameter6"));
        devizaNemColumn.setCellValueFactory(new PropertyValueFactory("devizaNem"));
        nettoDevizaColumn.setCellValueFactory(new PropertyValueFactory("nettoDeviza"));
        afaDevizaColumn.setCellValueFactory(new PropertyValueFactory("afaDeviza"));
        bruttoDevizaColumn.setCellValueFactory(new PropertyValueFactory("bruttoDeviza"));
        vevoCimeEgybenColumn.setCellValueFactory(new PropertyValueFactory("vevoCimeEgyben"));
        vevoEUAdoszamColumn.setCellValueFactory(new PropertyValueFactory("vevoEUAdoszam"));

        csvTbl.getItems().setAll(store.getRemekCsvPackage().getRows());

        BigDecimal totalNetto = store.getRemekCsvPackage().getRows().stream()
                .filter(r -> "HUF".equals(r.getDevizaNem()) || StringUtils.isBlank(r.getDevizaNem()))
                .map(RemekCsvRow::getNetto).map(FormatUtils::parseCsvBigDecimal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        nettoOsszesenTxt.setText(FormatUtils.formatAmount(totalNetto) + " Ft.");
        nettoOsszesenTxt.setAlignment(Pos.BASELINE_RIGHT);

        BigDecimal totalAFA = store.getRemekCsvPackage().getRows().stream()
                .filter(r -> "HUF".equals(r.getDevizaNem()) || StringUtils.isBlank(r.getDevizaNem()))
                .map(RemekCsvRow::getAfa).map(FormatUtils::parseCsvBigDecimal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        afaOsszesenTxt.setText(FormatUtils.formatAmount(totalAFA) + " Ft.");
        afaOsszesenTxt.setAlignment(Pos.BASELINE_RIGHT);

        BigDecimal totalBrutto = store.getRemekCsvPackage().getRows().stream()
                .filter(r -> "HUF".equals(r.getDevizaNem()) || StringUtils.isBlank(r.getDevizaNem()))
                .map(RemekCsvRow::getBrutto).map(FormatUtils::parseCsvBigDecimal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        bruttoOsszesenTxt.setText(FormatUtils.formatAmount(totalBrutto) + " Ft.");
        bruttoOsszesenTxt.setAlignment(Pos.BASELINE_RIGHT);

        infoLbl.textProperty().bind(infoLine);
    }


    @Override
    public String getTitle()
    {
        return "3. lépés: Remek CSV fájl megtekintése, mentése";
    }

}
