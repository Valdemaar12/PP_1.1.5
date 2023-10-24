package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.*;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/userdb";
    private final String USERNAME = "root";
    private final String PASSWORD = "my179sql";

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

    public SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", URL);
            configuration.setProperty("hibernate.connection.username", USERNAME);
            configuration.setProperty("hibernate.connection.password", PASSWORD);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.current_session_context_class", "thread");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.addAnnotatedClass(User.class);
            System.out.println("SessionFactory is created!");
            return configuration.buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Failed to create SessionFactory... : " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

}
