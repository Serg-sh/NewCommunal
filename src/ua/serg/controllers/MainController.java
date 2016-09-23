package ua.serg.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import ua.serg.impl.*;
import ua.serg.objects.*;
import ua.serg.utils.CalcUtils;
import ua.serg.utils.DBUtils;
import ua.serg.utils.DialogManager;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
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

    //Tab Calc -> tab Electric
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
    @FXML
    private TableColumn<Electric, Date> ctHistoryElDate;
    @FXML
    private TableColumn<Electric, String> ctHistoryElPeriod;
    @FXML
    private TableColumn<Electric, Integer> ctHistoryElMetr;
    @FXML
    private TableColumn<Electric, Integer> ctHistoryElToUse;
    @FXML
    private TableColumn<Electric, BigDecimal> ctHistoryElSum;
    @FXML
    private TableColumn<Electric, BigDecimal> ctHistoryElSumPerM;

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
    @FXML
    private TableColumn<Wather, Date> ctHistoryWaterDate;
    @FXML
    private TableColumn<Wather, String> ctHistoryWaterPeriod;
    @FXML
    private TableColumn<Wather, Integer> ctHistoryWaterMetr;
    @FXML
    private TableColumn<Wather, Integer> ctHistoryWaterToUse;
    @FXML
    private TableColumn<Wather, BigDecimal> ctHistoryWaterSum;
    @FXML
    private TableColumn<Wather, BigDecimal> ctHistoryWaterSumPerM;

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
    @FXML
    private TableColumn<Gas, Date> ctHistoryGasDate;
    @FXML
    private TableColumn<Gas, String> ctHistoryGasPeriod;
    @FXML
    private TableColumn<Gas, BigDecimal> ctHistoryGasSum;
    @FXML
    private TableColumn<Gas, BigDecimal> ctHistoryGasSumPerM;

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
    private CustomTextField tfAreaRoom; /*изменить на NumberTextField в Main.fxml тоже*/
    @FXML
    private TableView tableHistoryDwelling;
    @FXML
    private Button btnUpdateDbDwelling;
    @FXML
    private TableColumn<Dwelling, Date> ctHistoryDwellingDate;
    @FXML
    private TableColumn<Dwelling, String> ctHistoryDwellingPeriod;
    @FXML
    private TableColumn<Dwelling, BigDecimal> ctHistoryDwellingSum;
    @FXML
    private TableColumn<Dwelling, BigDecimal> ctHistoryDwellingSumPerM;

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
    @FXML
    private TableColumn<Elevator, Date> ctHistoryElevatorDate;
    @FXML
    private TableColumn<Elevator, String> ctHistoryElevatorPeriod;
    @FXML
    private TableColumn<Elevator, BigDecimal> ctHistoryElevatorSum;
    @FXML
    private TableColumn<Elevator, BigDecimal> ctHistoryElevatorSumPerM;

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
    @FXML
    private TableColumn<Garbage, Date> ctHistoryGarbageDate;
    @FXML
    private TableColumn<Garbage, String> ctHistoryGarbagePeriod;
    @FXML
    private TableColumn<Garbage, BigDecimal> ctHistoryGarbageSum;
    @FXML
    private TableColumn<Garbage, BigDecimal> ctHistoryGarbageSumPerM;

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
    @FXML
    private TableColumn<Healting, Date> ctHistoryHeatingDate;
    @FXML
    private TableColumn<Healting, String> ctHistoryHeatingPeriod;
    @FXML
    private TableColumn<Healting, BigDecimal> ctHistoryHeatingSum;
    @FXML
    private TableColumn<Healting, BigDecimal> ctHistoryHeatingSumPerM;


    private CollectionTarifs listTarif = new CollectionTarifs();
    private CollectionPays listPay = new CollectionPays();
    private CollectionServiceMetr listElectric = new CollectionServiceMetr();
    private CollectionServiceMetr listWater = new CollectionServiceMetr();
    private CollectionService listHeating = new CollectionService();
    private CollectionService listGas = new CollectionService();
    private CollectionService listDwelling = new CollectionService();
    private CollectionService listElevator = new CollectionService();
    private CollectionService listGarbage = new CollectionService();


    @FXML
    private void initialize() {


//  таблица тарифов
        columnTableTarifsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTableTarifsTarif.setCellValueFactory(new PropertyValueFactory<>("cost"));
        columnTableTarifsDate.setCellValueFactory(new PropertyValueFactory<>("dateChangeOfTarif"));

//   таблица платежей
        columnTableAllDate.setCellValueFactory(new PropertyValueFactory<>("dateOfPay"));
        columnTableAllSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        columnTableAllComment.setCellValueFactory(new PropertyValueFactory<>("comment"));

//   таблица газ
        ctHistoryGasDate.setCellValueFactory(new PropertyValueFactory<>("datePay"));
        ctHistoryGasPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
        ctHistoryGasSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        ctHistoryGasSumPerM.setCellValueFactory(new PropertyValueFactory<>("sumPerMonth"));

//   Таблица отопление
        ctHistoryHeatingDate.setCellValueFactory(new PropertyValueFactory<>("datePay"));
        ctHistoryHeatingPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
        ctHistoryHeatingSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        ctHistoryHeatingSumPerM.setCellValueFactory(new PropertyValueFactory<>("sumPerMonth"));

//        Таблица квартплата
        ctHistoryDwellingDate.setCellValueFactory(new PropertyValueFactory<>("datePay"));
        ctHistoryDwellingPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
        ctHistoryDwellingSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        ctHistoryDwellingSumPerM.setCellValueFactory(new PropertyValueFactory<>("sumPerMonth"));

//        Таблица лифт
        ctHistoryElevatorDate.setCellValueFactory(new PropertyValueFactory<>("datePay"));
        ctHistoryElevatorPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
        ctHistoryElevatorSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        ctHistoryElevatorSumPerM.setCellValueFactory(new PropertyValueFactory<>("sumPerMonth"));

//        Таблица мусор
        ctHistoryGarbageDate.setCellValueFactory(new PropertyValueFactory<>("datePay"));
        ctHistoryGarbagePeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
        ctHistoryGarbageSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        ctHistoryGarbageSumPerM.setCellValueFactory(new PropertyValueFactory<>("sumPerMonth"));

//        Таблица Электроэнергия
        ctHistoryElDate.setCellValueFactory(new PropertyValueFactory<>("datePay"));
        ctHistoryElPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
        ctHistoryElMetr.setCellValueFactory(new PropertyValueFactory<>("metrReadingsEnd"));
        ctHistoryElToUse.setCellValueFactory(new PropertyValueFactory<>("toUse"));
        ctHistoryElSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        ctHistoryElSumPerM.setCellValueFactory(new PropertyValueFactory<>("sumPerMonth"));

//        Таблица вода
        ctHistoryWaterDate.setCellValueFactory(new PropertyValueFactory<>("datePay"));
        ctHistoryWaterPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
        ctHistoryWaterMetr.setCellValueFactory(new PropertyValueFactory<>("metrReadingsEnd"));
        ctHistoryWaterToUse.setCellValueFactory(new PropertyValueFactory<>("toUse"));
        ctHistoryWaterSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        ctHistoryWaterSumPerM.setCellValueFactory(new PropertyValueFactory<>("sumPerMonth"));


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
        setupClearButtonField(tfAreaRoom);

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
        tfOldMetrReadingsEl.setText(String.valueOf(DBUtils.getStartMetrReadings("Electric")));
        tfOldMetrReadingsWater.setText(String.valueOf(DBUtils.getStartMetrReadings("Wather")));



        listTarif.clear();
        listTarif.add(DBUtils.getResultsListTarif());
        tableTarifs.setItems(listTarif.getTarifsList());

        listPay.clear();
        listPay.add(DBUtils.getResultsListPay());
        tableAll.setItems(listPay.getPaysList());

        listGas.clear();
        listGas.add(DBUtils.getResultsListService("SELECT * FROM Gas ORDER BY date DESC", new Gas()));
        tableHistoryGas.setItems(listGas.getServiceList());

        listHeating.clear();
        listHeating.add(DBUtils.getResultsListService("SELECT * FROM Healting ORDER BY date DESC", new Healting()));
        tableHistoryHeating.setItems(listHeating.getServiceList());

        listDwelling.clear();
        listDwelling.add(DBUtils.getResultsListService("SELECT * FROM Dwelling ORDER BY date DESC", new Dwelling()));
        tableHistoryDwelling.setItems(listDwelling.getServiceList());

        listElevator.clear();
        listElevator.add(DBUtils.getResultsListService("SELECT * FROM Elevator ORDER BY date DESC", new Elevator()));
        tableHistoryElevator.setItems(listElevator.getServiceList());

        listGarbage.clear();
        listGarbage.add(DBUtils.getResultsListService("SELECT * FROM Garbage ORDER BY date DESC", new Garbage()));
        tableHistoryGarbage.setItems(listGarbage.getServiceList());

        listElectric.clear();
        listElectric.add(DBUtils.getResultsListServiceMetr("SELECT * FROM Electric ORDER BY date DESC", new Electric()));
        tableHistoryEl.setItems(listElectric.getServiceList());

        listWater.clear();
        listWater.add(DBUtils.getResultsListServiceMetr("SELECT * FROM Wather ORDER BY date DESC", new Wather()));
        tableHistoryWater.setItems(listWater.getServiceList());

        setLabelTarifs();

//        DBUtils.closeConnection();

    }

    private void setLabelTarifs() {
        labelTarifBefore100El.setText("Тариф до 100 кВатт: " + DBUtils.getTarifLastDate("Электроэнергия до 100 кВатт") + " грн.");
        labelTarifBefore600El.setText("Тариф до 600 кВатт: " + DBUtils.getTarifLastDate("Электроэнергия до 600 кВатт") + " грн.");
        labelTarifWater.setText("Тариф: " + DBUtils.getTarifLastDate("Водоснабжение") + "  грн./кб.м.");
        labelTarifGas.setText("Тариф: " + DBUtils.getTarifLastDate("Газоснабжение") + "  грн./кб.м.");
        labelTarifDwelling.setText("Тариф: " + DBUtils.getTarifLastDate("Квартплата") + " грн./кв.м.");
        labelTarifElevator.setText("Тариф: " + DBUtils.getTarifLastDate("Лифт") + " грн./чел.");
        labelTarifGarbage.setText("Тариф: " + DBUtils.getTarifLastDate("Вывоз мусора") + " грн./чел.");
    }

    private boolean equalsDate(LocalDate startDate, LocalDate endDate) {
        boolean res = false;
        if (endDate == null || startDate == null) {
            DialogManager.showInfoDialog("Внимание!", "Укажите корректные даты");
            res = true;
        }
        if (startDate.isAfter(endDate)) {
            DialogManager.showErrorDialog("Неверные даты!", "Конечная дата меньше начальной!");
            res = true;
        }
        return res;
    }

    private boolean equals0toUse(Integer toUse) {
        if (toUse < 0) {
            DialogManager.showErrorDialog("Неверные показания счетчика!", "Введите корректные показания счетчика.");
            return true;
        }
        return false;
    }

//    Обработка онажатия энтер - электричество расчет
    public void enterPassedElectric(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER){
            actionElectric();
        }
    }
    public void actPayPeriodEl (ActionEvent actionEvent){
        if (dpEndPayPeriodWater.getValue() == null || dpStartPayPeriodWater.getValue() == null){

        }else {
            actionElectric();
        }
    }
    private void actionElectric() {
        LocalDate startDate= dpStartPayPeriodEl.getValue();
        LocalDate endDate = dpEndPayPeriodEl.getValue();
        Integer startMetr;
        Integer endMetr;
        Integer toUse;
        try {
            if (equalsDate(startDate, endDate)){
                dpEndPayPeriodEl.setValue(LocalDate.now());
                return;
            }
            startMetr = Integer.parseInt(tfOldMetrReadingsEl.getText());
            endMetr = Integer.parseInt(tfNewMetrReadingsEl.getText());
            toUse = endMetr - startMetr;
            if (equals0toUse(toUse)) return;
            labelToUseEl.setText("Потребленно " + toUse + " кВатт");
            labelSumEl.setText("Сумма к оплате " + CalcUtils.calcElectric(startDate, endDate, startMetr, endMetr) + " грн.");

        } catch (NumberFormatException e){
//            DialogManager.showInfoDialog("Внимание!", "Введите даты периода оплаты");
        }
    }

    public void enterPassedWater(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER){
            actionWater();
        }
    }
    public void actPayPeriodWater (ActionEvent actionEvent){
        if (dpEndPayPeriodWater.getValue() == null || dpStartPayPeriodWater.getValue() == null){

        }else {
            actionWater();
        }
    }
    private void actionWater() {
        LocalDate startDate = dpStartPayPeriodWater.getValue();
        LocalDate endDate = dpEndPayPeriodWater.getValue();
        Integer startMetr;
        Integer endMetr;
        Integer toUse;
        try {
            if (equalsDate(startDate, endDate)){
                dpEndPayPeriodWater.setValue(LocalDate.now());
                return;
            }
            startMetr = Integer.parseInt(tfOldMetrReadingsWater.getText());
            endMetr = Integer.parseInt(tfNewMetrReadingsWater.getText());
            toUse = endMetr - startMetr;
            if (equals0toUse(toUse)) return;
            labelToUseWater.setText("Потребленно " + toUse + " кб.м");
            labelSumWater.setText("Сумма к оплате " + CalcUtils.calcWater(startMetr, endMetr) + " грн.");

        } catch (NumberFormatException e){
//            DialogManager.showInfoDialog("Внимание!", "Введите даты периода оплаты");
        }
    }

    public void enterPassedDwelling(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER){
            actionDwelling();
        }
    }
    public void actCountPeopleDwelling(ActionEvent actionEvent) {
        actionDwelling();
    }
    private void actionDwelling() {
        LocalDate startDate = dpStartPayPeriodDwelling.getValue();
        LocalDate endDate = dpEndPayPeriodDwelling.getValue();
        try {
            if (equalsDate(startDate, endDate)){
                dpEndPayPeriodDwelling.setValue(LocalDate.now());
                return;
            }
            Integer countPeople = (Integer) cbCountPeopleDwelling.getValue();
            Double area = new Double(tfAreaRoom.getText());
            labelSumDwelling.setText("Сумма к оплате " + CalcUtils.calcDwelling(startDate, endDate, countPeople, area) + " грн.");
        } catch (NumberFormatException e){
//            DialogManager.showInfoDialog("Внимание!", "Введите даты периода оплаты");
        }
    }

    public void enterPassedGas(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER){
            actionGas();
        }
    }
    public void actCountPeopleGas(ActionEvent actionEvent) {
        actionGas();
    }
    private void actionGas() {
        LocalDate startDate = dpStartPayPeriodGas.getValue();
        LocalDate endDate = dpEndPayPeriodGas.getValue();
        try {
            if (equalsDate(startDate, endDate)){
                dpEndPayPeriodGas.setValue(LocalDate.now());
                return;
            }
            Integer countPeople = (Integer) cbCountPeopleGaz.getValue();
            labelSumGas.setText("Сумма к оплате " + CalcUtils.calcGas(startDate, endDate, countPeople) + " грн.");
        } catch (NumberFormatException e){
//            DialogManager.showInfoDialog("Внимание!", "Введите даты периода оплаты");
        }
    }

    public void enterPassedElevator(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER){
            actionElevator();
        }
    }
    public void actCountPeopleElevator(ActionEvent actionEvent) {
        actionElevator();
    }
    private void actionElevator() {
        LocalDate startDate = dpStartPayPeriodElevator.getValue();
        LocalDate endDate = dpEndPayPeriodElevator.getValue();
        try {
            if (equalsDate(startDate, endDate)){
                dpEndPayPeriodElevator.setValue(LocalDate.now());
                return;
            }
            Integer countPeople = (Integer) cbCountPeopleElevator.getValue();
            labelSumElevator.setText("Сумма к оплате " + CalcUtils.calcElevator(startDate, endDate, countPeople) + " грн.");
        } catch (NumberFormatException e){
//            DialogManager.showInfoDialog("Внимание!", "Введите даты периода оплаты");
        }
    }

    public void enterPassedGarbage(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER){
            actionGarbage();
        }
    }
    public void actCountPeopleGarbage(ActionEvent actionEvent) {
        actionGarbage();
    }
    private void actionGarbage() {
        LocalDate startDate = dpStartPayPeriodGarbage.getValue();
        LocalDate endDate = dpEndPayPeriodGarbage.getValue();
        try {
            if (equalsDate(startDate, endDate)){
                dpEndPayPeriodGarbage.setValue(LocalDate.now());
                return;
            }
            Integer countPeople = (Integer) cbCountPeopleGarbage.getValue();
            labelSumGarbage.setText("Сумма к оплате " + CalcUtils.calcGarbage(startDate, endDate, countPeople) + " грн.");
        } catch (NumberFormatException e){
//            DialogManager.showInfoDialog("Внимание!", "Введите даты периода оплаты");
        }
    }



    public void btnActionElectric(ActionEvent actionEvent) {


        System.out.println(Period.between(dpStartPayPeriodEl.getValue(), dpEndPayPeriodEl.getValue()).getMonths()+1);





    }



}
