package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    String DB_INSERT_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    String DB_REMOVE_USER = "DELETE FROM users WHERE id=?";
    String DB_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT)";
    String DB_DROP_TABLE = "DROP TABLE IF EXISTS users";
    String DB_GET_ALL = "SELECT * FROM users";
    String DB_CLEAN_TABLE = "DELETE FROM users";

    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
