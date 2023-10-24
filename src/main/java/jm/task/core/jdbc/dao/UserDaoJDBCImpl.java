package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection;

    public UserDaoJDBCImpl() {
        connection = new Util().getConnection();
    }

    public void createUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DB_CREATE_TABLE)) {
            preparedStatement.executeUpdate();
            System.out.println("Новая база данных создана.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DB_DROP_TABLE)) {
            preparedStatement.executeUpdate();
            System.out.println("База данных удалена.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DB_INSERT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DB_REMOVE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User с id - " + id + " удалён из базы данных.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DB_GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                resultList.add(user);
            }
            System.out.println("Запрос на получение всех User из базы данных - успешно выполнен.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DB_CLEAN_TABLE)) {
            preparedStatement.executeUpdate();
            System.out.println("База данных очищена.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
