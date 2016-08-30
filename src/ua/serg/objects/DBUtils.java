package ua.serg.objects;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
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
}
