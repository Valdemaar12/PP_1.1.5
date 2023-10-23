package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/userdb";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection to DB is succesfull!");
        } catch (SQLException e) {
            System.out.println("Connection failed... : " + e);
        }
        return connection;
    }

}
