package hu.dlaszlo.natura2remek.gui.controller;

import com.google.inject.Inject;
import hu.dlaszlo.natura2remek.csv.RemekCsvService;
import hu.dlaszlo.natura2remek.csv.naturacsv.Customer;
import hu.dlaszlo.natura2remek.csv.naturacsv.InvoiceHeader;
import hu.dlaszlo.natura2remek.csv.naturacsv.InvoiceItem;
import hu.dlaszlo.natura2remek.csv.remekcsv.RemekCsvPackage;
import hu.dlaszlo.natura2remek.gui.utils.FormatUtils;
import hu.dlaszlo.natura2remek.guice.store.Store;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Natura CSV fájlok megtekintése
 */
public class ViewNaturaCsvController extends AbstractController
{
    @Inject
    private Store store;

    @Inject
    private RemekCsvService remekCsvService;

    private StringProperty infoLine = new SimpleStringProperty("");

    @FXML
    private Label infoLbl;

    @FXML
    private TableView<InvoiceHeader> invoiceHeadersTbl;

    @FXML
    private TableView<InvoiceItem> invoiceItemsTbl;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tab1;

    @FXML
    private Tab tab2;

    @FXML
    private TextField nettoOsszesenTxt;

    @FXML
    private TextField afaOsszesenTxt;

    @FXML
    private TextField bruttoOsszesenTxt;

    @FXML
    private TableColumn<InvoiceHeader, String> bizonylatszamColumn0;

    @FXML
    private TableColumn<InvoiceHeader, String> szamlaTipusColumn0;

    @FXML
    private TableColumn<InvoiceHeader, Long> vevoKodColumn0;

    @FXML
    private TableColumn<InvoiceHeader, String> vevoNevColumn0;

    @FXML
    private TableColumn<InvoiceHeader, LocalDate> fizetesiModColumn0;

    @FXML
    private TableColumn<InvoiceHeader, LocalDate> kiallitasNapColumn0;

    @FXML
    private TableColumn<InvoiceHeader, LocalDate> teljesitesNapColumn0;

    @FXML
    private TableColumn<InvoiceHeader, LocalDate> fizetesiHataridoColumn0;

    @FXML
    private TableColumn<InvoiceHeader, LocalDate> kiegyenlitesNapjaColumn0;

    @FXML
    private TableColumn<InvoiceHeader, String> penznemColumn0;

    @FXML
    private TableColumn<InvoiceHeader, BigDecimal> arfolyamColumn0;

    @FXML
    private TableColumn<InvoiceHeader, BigDecimal> nettoVegosszegColumn0;

    @FXML
    private TableColumn<InvoiceHeader, BigDecimal> afaVegosszegColumn0;

    @FXML
    private TableColumn<InvoiceHeader, BigDecimal> bruttoVegosszegColumn0;

    @FXML
    private TableColumn<InvoiceHeader, BigDecimal> kiegyenlitettBruttoOsszegColumn0;

    @FXML
    private TableColumn<InvoiceHeader, String> stornoVagyHelyesbSzamlaszamColumn0;

    @FXML
    private TableColumn<InvoiceHeader, String> stornoVagyHelyesbitoTipusColumn0;

    @FXML
    private TableColumn<InvoiceHeader, Long> szamlacsoportKodColumn0;

    @FXML
    private TableColumn<InvoiceHeader, String> szamlacsoportMegnevezesColumn0;

    @FXML
    private TableColumn<InvoiceHeader, String> szallitolevelBizonylatszamColumn0;

    @FXML
    private TextField ugyfelKodTxt;

    @FXML
    private TextField nevTxt;

    @FXML
    private TextField orszagTxt;

    @FXML
    private TextField irszamTxt;

    @FXML
    private TextField varosTxt;

    @FXML
    private TextField cimTxt;

    @FXML
    private TextField telefonszamTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField adoszamTxt;

    @FXML
    private TextField kozossegiAdoszamTxt;

    @FXML
    private TextField bankszamlaszamTxt;

    @FXML
    TableColumn<InvoiceItem, Long> termekKodColumn1;

    @FXML
    TableColumn<InvoiceItem, String> termekMegnevezesColumn1;

    @FXML
    TableColumn<InvoiceItem, String> termekSzolgaltatasKodColumn1;

    @FXML
    TableColumn<InvoiceItem, String> cikkszamColumn1;

    @FXML
    TableColumn<InvoiceItem, String> vtszSzjTeszorColumn1;

    @FXML
    TableColumn<InvoiceItem, BigDecimal> mennyisegColumn1;

    @FXML
    TableColumn<InvoiceItem, String> mennyisegiEgysegColumn1;

    @FXML
    TableColumn<InvoiceItem, BigDecimal> nettoArColumn1;

    @FXML
    TableColumn<InvoiceItem, BigDecimal> bruttoArColumn1;

    @FXML
    TableColumn<InvoiceItem, BigDecimal> kedvezmenyColumn1;

    @FXML
    TableColumn<InvoiceItem, BigDecimal> nettoEgysegArColumn1;

    @FXML
    TableColumn<InvoiceItem, BigDecimal> bruttoEgysegArColumn1;

    @FXML
    TableColumn<InvoiceItem, BigDecimal> afaKulcsColumn1;

    @FXML
    TableColumn<InvoiceItem, BigDecimal> nettoOsszesenArColumn1;

    @FXML
    TableColumn<InvoiceItem, BigDecimal> afaOsszesenArColumn1;

    @FXML
    TableColumn<InvoiceItem, BigDecimal> bruttoOsszesenArColumn1;

    @FXML
    private void nextPage()
    {
        RemekCsvPackage remekCsvPackage = remekCsvService.convert(store.getNaturaCsvPackage());
        store.setRemekCsvPackage(remekCsvPackage);
        if (remekCsvPackage.getErrors().isEmpty())
        {
            setPage("viewRemekCsv");
        }
        else
        {
            setPage("remekCsvErrors");
        }
    }

    @FXML
    private void prevPage()
    {
        setPage("loadNaturaCsv");
    }

    @FXML
    private void initialize()
    {
        invoiceHeadersTbl.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
            {
                tabPane.getTabs().remove(tab2);
                tabPane.getTabs().add(tab2);
                tabPane.getSelectionModel().select(tab2);

                InvoiceHeader invoiceHeader = invoiceHeadersTbl.getSelectionModel().getSelectedItem();
                invoiceItemsTbl.getItems().setAll(invoiceHeader.getInvoiceItems());
                Customer customer = invoiceHeader.getCustomer();
                if (customer == null)
                {
                    customer = new Customer(0, 0);
                }
                ugyfelKodTxt.setText(customer.getUgyfelKod() == null ? "" : customer.getUgyfelKod().toString());
                nevTxt.setText(StringUtils.defaultIfBlank(customer.getNev(), ""));
                orszagTxt.setText(StringUtils.defaultIfBlank(customer.getOrszag(), ""));
                irszamTxt.setText(StringUtils.defaultIfBlank(customer.getIrszam(), ""));
                varosTxt.setText(StringUtils.defaultIfBlank(customer.getVaros(), ""));
                cimTxt.setText(StringUtils.defaultIfBlank(customer.getCim(), ""));
                telefonszamTxt.setText(StringUtils.defaultIfBlank(customer.getTelefonszam(), ""));
                emailTxt.setText(StringUtils.defaultIfBlank(customer.getEmail(), ""));
                adoszamTxt.setText(StringUtils.defaultIfBlank(customer.getAdoszam(), ""));
                kozossegiAdoszamTxt.setText(StringUtils.defaultIfBlank(customer.getKozossegiAdoszam(), ""));
                bankszamlaszamTxt.setText(StringUtils.defaultIfBlank(customer.getBankszamlaszam(), ""));

                tab2.setText(invoiceHeader.getBizonylatszam());
            }
        });

        arfolyamColumn0.setCellFactory(getBigDecimalCellFactory());
        nettoVegosszegColumn0.setCellFactory(getBigDecimalCellFactory());
        afaVegosszegColumn0.setCellFactory(getBigDecimalCellFactory());
        bruttoVegosszegColumn0.setCellFactory(getBigDecimalCellFactory());
        kiegyenlitettBruttoOsszegColumn0.setCellFactory(getBigDecimalCellFactory());

        kiallitasNapColumn0.setCellFactory(getLocalDateCellFactory());
        teljesitesNapColumn0.setCellFactory(getLocalDateCellFactory());
        fizetesiHataridoColumn0.setCellFactory(getLocalDateCellFactory());
        kiegyenlitesNapjaColumn0.setCellFactory(getLocalDateCellFactory());

        bizonylatszamColumn0.setCellValueFactory(new PropertyValueFactory("bizonylatszam"));
        szamlaTipusColumn0.setCellValueFactory(new PropertyValueFactory("szamlaTipus"));
        vevoKodColumn0.setCellValueFactory(new PropertyValueFactory("vevoKod"));
        vevoNevColumn0.setCellValueFactory(new PropertyValueFactory("vevoNev"));
        fizetesiModColumn0.setCellValueFactory(new PropertyValueFactory("fizetesiMod"));
        kiallitasNapColumn0.setCellValueFactory(new PropertyValueFactory("kiallitasNap"));
        teljesitesNapColumn0.setCellValueFactory(new PropertyValueFactory("teljesitesNap"));
        fizetesiHataridoColumn0.setCellValueFactory(new PropertyValueFactory("fizetesiHatarido"));
        kiegyenlitesNapjaColumn0.setCellValueFactory(new PropertyValueFactory("kiegyenlitesNapja"));
        penznemColumn0.setCellValueFactory(new PropertyValueFactory("penznem"));
        arfolyamColumn0.setCellValueFactory(new PropertyValueFactory("arfolyam"));
        nettoVegosszegColumn0.setCellValueFactory(new PropertyValueFactory("nettoVegosszeg"));
        afaVegosszegColumn0.setCellValueFactory(new PropertyValueFactory("afaVegosszeg"));
        bruttoVegosszegColumn0.setCellValueFactory(new PropertyValueFactory("bruttoVegosszeg"));
        kiegyenlitettBruttoOsszegColumn0.setCellValueFactory(new PropertyValueFactory("kiegyenlitettBruttoOsszeg"));
        stornoVagyHelyesbSzamlaszamColumn0.setCellValueFactory(new PropertyValueFactory("stornoVagyHelyesbSzamlaszam"));
        stornoVagyHelyesbitoTipusColumn0.setCellValueFactory(new PropertyValueFactory("stornoVagyHelyesbitoTipus"));
        szamlacsoportKodColumn0.setCellValueFactory(new PropertyValueFactory("szamlacsoportKod"));
        szamlacsoportMegnevezesColumn0.setCellValueFactory(new PropertyValueFactory("szamlacsoportMegnevezes"));
        szallitolevelBizonylatszamColumn0.setCellValueFactory(new PropertyValueFactory("szallitolevelBizonylatszam"));

        invoiceHeadersTbl.getItems().setAll(store.getNaturaCsvPackage().getInvoiceHeaders());

        BigDecimal totalNetto = store.getNaturaCsvPackage().getInvoiceHeaders().stream()
                .filter(h -> "HUF".equals(h.getPenznem()))
                .map(InvoiceHeader::getNettoVegosszeg).reduce(BigDecimal.ZERO, BigDecimal::add);
        nettoOsszesenTxt.setText(FormatUtils.formatAmount(totalNetto) + " Ft.");
        nettoOsszesenTxt.setAlignment(Pos.BASELINE_RIGHT);

        BigDecimal totalAFA = store.getNaturaCsvPackage().getInvoiceHeaders().stream()
                .filter(h -> "HUF".equals(h.getPenznem()))
                .map(InvoiceHeader::getAfaVegosszeg).reduce(BigDecimal.ZERO, BigDecimal::add);
        afaOsszesenTxt.setText(FormatUtils.formatAmount(totalAFA) + " Ft.");
        afaOsszesenTxt.setAlignment(Pos.BASELINE_RIGHT);

        BigDecimal totalBrutto = store.getNaturaCsvPackage().getInvoiceHeaders().stream()
                .filter(h -> "HUF".equals(h.getPenznem()))
                .map(InvoiceHeader::getBruttoVegosszeg).reduce(BigDecimal.ZERO, BigDecimal::add);
        bruttoOsszesenTxt.setText(FormatUtils.formatAmount(totalBrutto) + " Ft.");
        bruttoOsszesenTxt.setAlignment(Pos.BASELINE_RIGHT);

        mennyisegColumn1.setCellFactory(getBigDecimalCellFactory());
        nettoArColumn1.setCellFactory(getBigDecimalCellFactory());
        bruttoArColumn1.setCellFactory(getBigDecimalCellFactory());
        kedvezmenyColumn1.setCellFactory(getBigDecimalCellFactory());
        nettoEgysegArColumn1.setCellFactory(getBigDecimalCellFactory());
        bruttoEgysegArColumn1.setCellFactory(getBigDecimalCellFactory());
        afaKulcsColumn1.setCellFactory(getBigDecimalCellFactory());
        nettoOsszesenArColumn1.setCellFactory(getBigDecimalCellFactory());
        afaOsszesenArColumn1.setCellFactory(getBigDecimalCellFactory());
        bruttoOsszesenArColumn1.setCellFactory(getBigDecimalCellFactory());

        termekKodColumn1.setCellValueFactory(new PropertyValueFactory("termekKod"));
        termekMegnevezesColumn1.setCellValueFactory(new PropertyValueFactory("termekMegnevezes"));
        termekSzolgaltatasKodColumn1.setCellValueFactory(new PropertyValueFactory("termekSzolgaltatasKod"));
        cikkszamColumn1.setCellValueFactory(new PropertyValueFactory("cikkszam"));
        vtszSzjTeszorColumn1.setCellValueFactory(new PropertyValueFactory("vtszSzjTeszor"));
        mennyisegColumn1.setCellValueFactory(new PropertyValueFactory("mennyiseg"));
        mennyisegiEgysegColumn1.setCellValueFactory(new PropertyValueFactory("mennyisegiEgyseg"));
        nettoArColumn1.setCellValueFactory(new PropertyValueFactory("nettoAr"));
        bruttoArColumn1.setCellValueFactory(new PropertyValueFactory("bruttoAr"));
        kedvezmenyColumn1.setCellValueFactory(new PropertyValueFactory("kedvezmeny"));
        nettoEgysegArColumn1.setCellValueFactory(new PropertyValueFactory("nettoEgysegAr"));
        bruttoEgysegArColumn1.setCellValueFactory(new PropertyValueFactory("bruttoEgysegAr"));
        afaKulcsColumn1.setCellValueFactory(new PropertyValueFactory("afaKulcs"));
        nettoOsszesenArColumn1.setCellValueFactory(new PropertyValueFactory("nettoOsszesenAr"));
        afaOsszesenArColumn1.setCellValueFactory(new PropertyValueFactory("afaOsszesenAr"));
        bruttoOsszesenArColumn1.setCellValueFactory(new PropertyValueFactory("bruttoOsszesenAr"));

        infoLbl.textProperty().bind(infoLine);

        tabPane.getTabs().remove(tab2);
    }

    private <T> javafx.util.Callback<TableColumn<T, BigDecimal>, TableCell<T, BigDecimal>> getBigDecimalCellFactory()
    {
        return param -> {
            TableCell<T, BigDecimal> cell = new TableCell<T, BigDecimal>()
            {
                @Override
                protected void updateItem(BigDecimal item, boolean empty)
                {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? "" : FormatUtils.formatAmount(item));
                }
            };
            cell.setStyle("-fx-alignment: CENTER-RIGHT;");
            return cell;
        };
    }

    private javafx.util.Callback<TableColumn<InvoiceHeader, LocalDate>, TableCell<InvoiceHeader, LocalDate>> getLocalDateCellFactory()
    {
        return param -> {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.");
            TableCell<InvoiceHeader, LocalDate> cell = new TableCell<InvoiceHeader, LocalDate>()
            {
                @Override
                protected void updateItem(LocalDate item, boolean empty)
                {
                    super.updateItem(item, empty);
                    if (empty || item == null)
                    {
                        setText("");
                    }
                    else
                    {
                        setText(dateFormatter.format(item));
                    }
                }
            };
            return cell;
        };
    }

    @Override
    public String getTitle()
    {
        return "2. lépés: Natura CSV fájl megtekintése";
    }

}
