package ua.serg.utils;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.serg.impl.AbstrsctPay;
import ua.serg.objects.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shpak on 30.08.2016.
 */
public class DBUtils {
    private static Connection con;

    public static void openConnection() {

        try {
            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();

            String url = "jdbc:sqlite:src/ua/serg/db/CommunalDB.db";

            if (con == null) {
                con = DriverManager.getConnection(url);
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    private static void closeStatements(Statement statement, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeStatements(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateDB(String sqlQuery) {
        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate(sqlQuery);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatements(statement);
    }

    public static ObservableList<Tarif> getResultsListTarif() {
        String sqlQuery = "SELECT * FROM View_tarif_lastDate ORDER BY NAME DESC ";
        ObservableList<Tarif> resList = FXCollections.observableArrayList();
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                Tarif tarif = new Tarif();
                tarif.setName(rs.getString("NAME"));
                tarif.setDateChangeOfTarif(LocalDate.parse(rs.getString("DATE")));
                tarif.setCost(rs.getBigDecimal("PRICE"));
                resList.add(tarif);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatements(statement, rs);


        return resList;
    }

    public static ObservableList<Pay> getResultsListPay() {
        String sqlQuery = "SELECT * FROM View_pays";
        ObservableList<Pay> resList = FXCollections.observableArrayList();
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                Pay pay = new Pay();
                pay.setDateOfPay(LocalDate.parse(rs.getString("Date")));
                pay.setCostElectric(rs.getBigDecimal("Electric"));
                pay.setCostWather(rs.getBigDecimal("Wather"));
                pay.setCostHealting(rs.getBigDecimal("Healting"));
                pay.setCostDwelling(rs.getBigDecimal("Dwelling"));
                pay.setCostGas(rs.getBigDecimal("Gas"));
                pay.setCostElevator(rs.getBigDecimal("Elevator"));
                pay.setCostGarbage(rs.getBigDecimal("Garbage"));
                pay.setSum(rs.getBigDecimal("Sum"));
                pay.setComment(rs.getString("Comments"));
                resList.add(pay);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatements(statement, rs);


        return resList;
    }

    public static ObservableList<AbstrsctPay> getResultsListService(String sqlQuery, AbstrsctPay service) {

        ObservableList<AbstrsctPay> resList = FXCollections.observableArrayList();
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                if (service instanceof Gas){
                    service = new Gas();
                }else if (service instanceof Healting){
                    service = new Healting();
                }else if (service instanceof Dwelling){
                    service = new Dwelling();
                }else if (service instanceof Elevator){
                    service = new Elevator();
                }else if (service instanceof Garbage){
                    service = new Garbage();
                }
                service.setDatePay(LocalDate.parse(rs.getString("date")));
                service.setPeriod(rs.getString("period"));
                service.setSum(rs.getBigDecimal("sum"));
                service.setSumPerMonth(rs.getBigDecimal("sum_per_month"));
                resList.add(service);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatements(statement, rs);


        return resList;
    }

    public static ObservableList<AbstractPayWithMetrReadings> getResultsListServiceMetr(String sqlQuery, AbstractPayWithMetrReadings service) {

        ObservableList<AbstractPayWithMetrReadings> resList = FXCollections.observableArrayList();
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                if (service instanceof Electric){
                    service = new Electric();
                }else if (service instanceof Wather){
                    service = new Wather();
                }
                service.setDatePay(LocalDate.parse(rs.getString("date")));
                service.setPeriod(rs.getString("period"));
                service.setMetrReadingsEnd(rs.getInt("metr_readings"));
                service.setToUse(rs.getInt("to_use"));
                service.setSum(rs.getBigDecimal("sum"));
                service.setSumPerMonth(rs.getBigDecimal("sum_per_month"));
                resList.add(service);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatements(statement, rs);


        return resList;
    }

    public static Integer getStartMetrReadings (String db){
        Integer startMetrReadings = 0;
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT metr_readings FROM " + db + " WHERE date=(SELECT MAX(date) FROM " + db + ")");
            startMetrReadings = rs.getInt("metr_readings");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatements(statement, rs);

        return startMetrReadings;
    }

    public static BigDecimal getTarifLastDate (String nameOfTarif){
        BigDecimal tarifLastDate = new BigDecimal(0);
        Statement statement = null;
        ResultSet rs = null;
        String db = "View_tarif_lastDate";

        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT PRICE FROM " + db + " WHERE NAME=" + "'" + nameOfTarif + "'");
            tarifLastDate = rs.getBigDecimal("PRICE");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatements(statement, rs);

        return tarifLastDate;
    }

    public static ObservableList<Tarif> getListTarif(String nameOfTarif) {
        String sqlQuery = "SELECT name, date, price FROM Tarifs t INNER JOIN spr_name_tarifs nt ON t.name_tarif_id = nt.id " +
                "WHERE name = '" + nameOfTarif + "'" + "ORDER BY date DESC";
        ObservableList<Tarif> tarifList = FXCollections.observableArrayList();
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                Tarif tarif = new Tarif();
                tarif.setName(rs.getString("name"));
                tarif.setDateChangeOfTarif(LocalDate.parse(rs.getString("date")));
                tarif.setCost(rs.getBigDecimal("price"));
                tarifList.add(tarif);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatements(statement, rs);


        return tarifList;
    }

    public static void setSumPay(String tbName, LocalDate date, BigDecimal sum, String columnName){
        ObservableList<LocalDate> listDate = FXCollections.observableArrayList();
        LocalDate datePay;
        Statement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT date_pay FROM Pay";
        try {

            statement = con.createStatement();
            rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                datePay = LocalDate.parse(rs.getString("date_pay"));
                listDate.add(datePay);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (listDate.contains(date)){
            sqlQuery = "UPDATE Pay SET " + columnName + "= (SELECT id FROM " + tbName + " WHERE date=" + "'" + date + "'" +" AND sum=" + sum +") WHERE date_pay="+ "'" +date + "'";
            updateDB(sqlQuery);
        }else{
            sqlQuery = "INSERT INTO Pay (date_pay, " + columnName +") VALUES (" + "'" + date + "'" + ", (SELECT id FROM " + tbName + " WHERE date=" + "'" + date + "'" +" AND sum=" + sum + "))";
            updateDB(sqlQuery);
        }
        closeStatements(statement, rs);
    }
}

