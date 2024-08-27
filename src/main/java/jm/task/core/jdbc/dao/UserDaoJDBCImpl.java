package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    private Connection connect = Util.getConnection();

    public UserDaoJDBCImpl() throws SQLException {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USER" +
                "(ID BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "NAME VARCHAR(50) NOT NULL, " +
                "LASTNAME VARCHAR(100) NOT NULL, " +
                "AGE INT NOT NULL)";
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e + "Таблица в БД: Неудалось инициализировать...");
        }

    }

    @Override
    public void dropUsersTable() {

        try (Statement statement = connect.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS USER");
        } catch (SQLException e) {
            throw new RuntimeException(e + "Удаление таблицы: Неудача...");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USER (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {

        String sql = "DELETE FROM user WHERE id = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM USER";

        try (Statement statement = connect.createStatement();
             ResultSet result = statement.executeQuery(sql);) {

            while (result.next()) {

                // Обработка полученных данных
                User user = new User();

                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastname"));
                user.setAge(result.getByte("age"));

                users.add(user);
            }


        } catch (SQLException e) {

            throw new RuntimeException(e + "Получение всех User: Неудача...");
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE USER";

        try (Statement statement = connect.createStatement()) {
            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e + "Неудачаная попытка стереть таблицу");
        }

    }
}