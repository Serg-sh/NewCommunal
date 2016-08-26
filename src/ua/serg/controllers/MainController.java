package ua.serg.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;


import java.lang.reflect.Method;

public class MainController {

    // Tab Tarifs
    @FXML
    private CustomTextField tfNewTarif;  /*изменить на NumberTextFild в Main.fxml тоже*/
    @FXML
    private Button btnChangeTarif;
    @FXML
    private DatePicker dpNewTarif;
    @FXML
    private MenuButton mbNewTarif;
    @FXML
    private TableView tableTarifs;
    @FXML
    private TableView tableAll;



    //Tab Calc -> tab El
    @FXML
    private CustomTextField tfNewMetrReadings; /*изменить на NumberTextFild в Main.fxml тоже*/
    @FXML
    private CustomTextField tfOldMetrReadings; /*изменить на NumberTextFild в Main.fxml тоже*/
    @FXML
    private DatePicker dpStartPayPeriodEl;
    @FXML
    private DatePicker dpEndPayPeriodEl;
    @FXML
    private Label labelTarifBefore100El;
    @FXML
    private Label labelTarifBefore500El;
    @FXML
    private Label labelToUseEl;
    @FXML
    private Label labelSumEl;
    @FXML
    private TableView tableHistoryEl;
    @FXML
    private Button btnUpdateDbEl;


    // Tab Calc -> tab Water




    @FXML
    private void initialize(){
        setupClearButtonField(tfNewTarif);
        setupClearButtonField(tfNewMetrReadings);
        setupClearButtonField(tfOldMetrReadings);


    }
//удаление данных в текстовом поле
    private void setupClearButtonField(CustomTextField customTextField) {
        try {
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
