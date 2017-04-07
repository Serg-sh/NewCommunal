package ua.serg.controllers;

import com.sun.javafx.scene.control.TableColumnComparatorBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ua.serg.impl.AbstrsctPay;

/**
 * Created by shpak on 21.02.2017.
 */
public class DetaliziedController {

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

    @FXML
    private void initialize(){
    }

    // закрываем окно
    public void actionBtnClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }
}
