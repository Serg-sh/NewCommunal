package ua.serg.utils;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.serg.objects.Pay;
import ua.serg.objects.Tarif;

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
}
