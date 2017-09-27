package ua.serg.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ua.serg.Main;
import ua.serg.impl.CollectionDetalPays;
import ua.serg.impl.CollectionPays;
import ua.serg.objects.DetaliziedPay;
import ua.serg.utils.DBUtils;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by shpak on 21.02.2017.
 */
public class DetaliziedController {

    public DetaliziedController() {
    }

    static String dateOfPay;
    @FXML
    private Button btnDetalizationClose;
    @FXML
    private TableView tableRes;
    @FXML
    private TableColumn ctDetalizationNameTarif;
    @FXML
    private TableColumn ctDetalizationDatePay;
    @FXML
    private TableColumn ctDetalizationPeriod;
    @FXML
    private TableColumn ctDetalizationSum;
    @FXML
    private TableColumn ctDetalizationSumPerMonth;
    @FXML
    private TextArea detalisationTextArea;
    @FXML
    private Button btnDetalizationSave;

    private CollectionDetalPays listDetaliziedPays = new CollectionDetalPays();
    private CollectionPays listPay = new CollectionPays();

    public void setListPay(CollectionPays listPay) {
        this.listPay = listPay;
    }

    public void setDateOfPay(String dateOfPay) {
        this.dateOfPay = dateOfPay;
    }

    @FXML
    private void initialize() {
//        Таблица детализации
        ctDetalizationNameTarif.setCellValueFactory(new PropertyValueFactory<>("nameTarif"));
        ctDetalizationDatePay.setCellValueFactory(new PropertyValueFactory<>("dateOfPay"));
        ctDetalizationPeriod.setCellValueFactory(new PropertyValueFactory<>("periodOfPay"));
        ctDetalizationSum.setCellValueFactory(new PropertyValueFactory<>("sumOfPay"));
        ctDetalizationSumPerMonth.setCellValueFactory(new PropertyValueFactory<>("sumPerMonth"));

        fillData();
    }

    private void fillData() {
        listDetaliziedPays.clear();
        listDetaliziedPays.add(DBUtils.getResultsListDetaliziedPays(dateOfPay));
        tableRes.setItems(listDetaliziedPays.getPaysList());
    }

    // закрываем окно
    public void actionBtnClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    //записать в базу
    public void updateDB(ActionEvent actionEvent) {
        String comment = detalisationTextArea.getText();
        String sqlQery = "UPDATE Pay SET comment = '" + comment + "' WHERE date_pay = '" + dateOfPay + "'";
        DBUtils.updateDB(sqlQery);
        listPay.clear();
        listPay.add(DBUtils.getResultsListPay()); //Обновление данных в tableAll
    }
}
