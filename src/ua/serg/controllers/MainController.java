package ua.serg.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import ua.serg.impl.CollectionPays;
import ua.serg.impl.CollectionTarifs;
import ua.serg.objects.Pay;
import ua.serg.objects.Tarif;
import ua.serg.utils.DBUtils;
import ua.serg.utils.DialogManager;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class MainController {

    private final ObservableList<Integer> countPeople = FXCollections.observableArrayList(1, 2, 3, 4, 5);
    private final ObservableList<String> tarifName = FXCollections.observableArrayList(
            "Электроэнергия до 100 кВатт",
            "Электроэнергия до 600 кВатт",
            "Водоснабжение",
            "Газоснабжение",
            "Теплоэнергия",
            "Квартплата",
            "Лифт",
            "Вывоз мусора");
    // Tab Tarifs
    @FXML
    private CustomTextField tfNewTarif;  /*изменить на NumberTextField в Main.fxml тоже*/
    @FXML
    private Button btnChangeTarif;
    @FXML
    private DatePicker dpNewTarif;
    @FXML
    private ComboBox cbNewTarif;
    @FXML
    private TableView tableTarifs;
    @FXML
    private TableView tableAll;
    @FXML
    private Label labelLastDatePay;
    @FXML
    private Label labelLastSumPay;
    @FXML
    private Label labelLastDateDebts;
    @FXML
    private Label labelLastSumDebts;
    @FXML
    private TableColumn<Tarif, String> columnTableTarifsName;
    @FXML
    private TableColumn<Tarif, BigDecimal> columnTableTarifsTarif;
    @FXML
    private TableColumn<Tarif, Date> columnTableTarifsDate;
    @FXML
    private TableColumn<Pay, Date> columnTableAllDate;
    @FXML
    private TableColumn<Pay, BigDecimal> columnTableAllSum;
    @FXML
    private TableColumn<Pay, String> columnTableAllComment;
    //Tab Calc -> tab El
    @FXML
    private CustomTextField tfNewMetrReadingsEl; /*изменить на NumberTextField в Main.fxml тоже*/
    @FXML
    private CustomTextField tfOldMetrReadingsEl; /*изменить на NumberTextField в Main.fxml тоже*/
    @FXML
    private DatePicker dpStartPayPeriodEl;
    @FXML
    private DatePicker dpEndPayPeriodEl;
    @FXML
    private Label labelTarifBefore100El;
    @FXML
    private Label labelTarifBefore600El;
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
    private CustomTextField tfNewMetrReadingsWater; /*изменить на NumberTextField в Main.fxml тоже*/
    @FXML
    private CustomTextField tfOldMetrReadingsWater; /*изменить на NumberTextField в Main.fxml тоже*/
    @FXML
    private DatePicker dpStartPayPeriodWater;
    @FXML
    private DatePicker dpEndPayPeriodWater;
    @FXML
    private Label labelTarifWater;
    @FXML
    private Label labelToUseWater;
    @FXML
    private Label labelSumWater;
    @FXML
    private TableView tableHistoryWater;
    @FXML
    private Button btnUpdateDbWater;
    // Tab Calc -> tab Gas
    @FXML
    private DatePicker dpStartPayPeriodGas;
    @FXML
    private DatePicker dpEndPayPeriodGas;
    @FXML
    private Label labelTarifGas;
    @FXML
    private ComboBox cbCountPeopleGaz;
    @FXML
    private Label labelToUseGas;
    @FXML
    private Label labelSumGas;
    @FXML
    private TableView tableHistoryGas;
    @FXML
    private Button btnUpdateDbGas;
    // Tab Calc -> tab Dwelling
    @FXML
    private DatePicker dpStartPayPeriodDwelling;
    @FXML
    private DatePicker dpEndPayPeriodDwelling;
    @FXML
    private Label labelTarifDwelling;
    @FXML
    private ComboBox cbCountPeopleDwelling;
    @FXML
    private Label labelSumDwelling;
    @FXML
    private TableView tableHistoryDwelling;
    @FXML
    private Button btnUpdateDbDwelling;
    // Tab Calc -> tab Elevator
    @FXML
    private DatePicker dpStartPayPeriodElevator;
    @FXML
    private DatePicker dpEndPayPeriodElevator;
    @FXML
    private Label labelTarifElevator;
    @FXML
    private ComboBox cbCountPeopleElevator;
    @FXML
    private Label labelSumElevator;
    @FXML
    private TableView tableHistoryElevator;
    @FXML
    private Button btnUpdateDbElevator;
    // Tab Calc -> tab Garbage
    @FXML
    private DatePicker dpStartPayPeriodGarbage;
    @FXML
    private DatePicker dpEndPayPeriodGarbage;
    @FXML
    private Label labelTarifGarbage;
    @FXML
    private ComboBox cbCountPeopleGarbage;
    @FXML
    private Label labelSumGarbage;
    @FXML
    private TableView tableHistoryGarbage;
    @FXML
    private Button btnUpdateDbGarbage;
    // Tab Calc -> tab Heating
    @FXML
    private DatePicker dpStartPayPeriodHeating;
    @FXML
    private DatePicker dpEndPayPeriodHeating;
    @FXML
    private TableView tablePayPerionSumHeating;
    @FXML
    private Label labelSumHeating;
    @FXML
    private TableView tableHistoryHeating;
    @FXML
    private Button btnUpdateDbHeating;

    private CollectionTarifs listTarif = new CollectionTarifs();
    private CollectionPays listPay = new CollectionPays();


    @FXML
    private void initialize() {
//  таблица тарифов
        columnTableTarifsName.setCellValueFactory(new PropertyValueFactory<Tarif, String>("name"));
        columnTableTarifsTarif.setCellValueFactory(new PropertyValueFactory<Tarif, BigDecimal>("cost"));
        columnTableTarifsDate.setCellValueFactory(new PropertyValueFactory<Tarif, Date>("dateChangeOfTarif"));

//  таблица платежей
        columnTableAllDate.setCellValueFactory(new PropertyValueFactory<Pay, Date>("dateOfPay"));
        columnTableAllSum.setCellValueFactory(new PropertyValueFactory<Pay, BigDecimal>("sum"));
        columnTableAllComment.setCellValueFactory(new PropertyValueFactory<Pay, String>("comment"));


        fillData();
        setClearFields(); /*установка самоочищающегос поля*/
        setCountPeopleAndTarifName(); /*усттановка кол-ва пропис. и названия тарифов */
        setPayPeriodDate();
    }

    private void setClearFields() {
        setupClearButtonField(tfNewTarif);
        setupClearButtonField(tfNewMetrReadingsEl);
        setupClearButtonField(tfOldMetrReadingsEl);
        setupClearButtonField(tfOldMetrReadingsWater);
        setupClearButtonField(tfNewMetrReadingsWater);

    }

    private void setCountPeopleAndTarifName() {
        cbNewTarif.setItems(tarifName);

        cbCountPeopleGaz.setItems(countPeople);
        cbCountPeopleGaz.setValue(countPeople.get(1));
        cbCountPeopleDwelling.setItems(countPeople);
        cbCountPeopleDwelling.setValue(countPeople.get(1));
        cbCountPeopleElevator.setItems(countPeople);
        cbCountPeopleElevator.setValue(countPeople.get(1));
        cbCountPeopleGarbage.setItems(countPeople);
        cbCountPeopleGarbage.setValue(countPeople.get(1));


    }

    private void setPayPeriodDate() {
        dpNewTarif.setValue(LocalDate.now());
        dpStartPayPeriodEl.setValue(setFirstMonthDey());
        dpEndPayPeriodEl.setValue(LocalDate.now());
        dpStartPayPeriodWater.setValue(setFirstMonthDey());
        dpEndPayPeriodWater.setValue(LocalDate.now());
        dpStartPayPeriodGas.setValue(setFirstMonthDey());
        dpEndPayPeriodGas.setValue(LocalDate.now());
        dpStartPayPeriodDwelling.setValue(setFirstMonthDey());
        dpEndPayPeriodDwelling.setValue(LocalDate.now());
        dpStartPayPeriodElevator.setValue(setFirstMonthDey());
        dpEndPayPeriodElevator.setValue(LocalDate.now());
        dpStartPayPeriodGarbage.setValue(setFirstMonthDey());
        dpEndPayPeriodGarbage.setValue(LocalDate.now());
        dpStartPayPeriodHeating.setValue(setFirstMonthDey());
        dpEndPayPeriodHeating.setValue(LocalDate.now());
    }

    private LocalDate setFirstMonthDey() {
        return LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1);
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

    //Кнопка изменить
    public void actionBtnChange(ActionEvent actionEvent) {
        DBUtils.openConnection();

        if (tableTarifs.getSelectionModel().isEmpty()) {
            DialogManager.showErrorDialog("Ошибка!", "Выберите тариф для изменения");
            return;
        }

        String name = ((Tarif) tableTarifs.getSelectionModel().getSelectedItem()).getName();
        try {

            BigDecimal cost = new BigDecimal(tfNewTarif.getText()).setScale(3, BigDecimal.ROUND_HALF_UP);
            LocalDate dateChangeTarif = dpNewTarif.getValue();

            String sqlQuery = "INSERT INTO Tarifs (name_tarif_id, date, price) " +
                    "VALUES ((SELECT id FROM spr_name_tarifs WHERE name=" + "'" + name + "'" + ")," + "'" + dateChangeTarif + "'" + "," + cost + ")";

            DBUtils.updateDB(sqlQuery);
            fillData();
        } catch (NumberFormatException | NullPointerException e) {
            DialogManager.showErrorDialog("Ошибка!", "Введите корректную стоимость!\nРазделитель дробной части точка.");
        }
    }

    private void fillData() {
        DBUtils.openConnection();

        listTarif.clear();
        listTarif.add(DBUtils.getResultsListTarif());
        tableTarifs.setItems(listTarif.getTarifsList());

        listPay.clear();
        listPay.add(DBUtils.getResultsListPay());
        tableAll.setItems(listPay.getPaysList());

//        DBUtils.closeConnection();

    }
}
