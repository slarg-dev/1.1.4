package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Обработка всех исключений, связанных с работой с базой данных должна находиться в dao
public class UserDaoJDBCImpl implements UserDao {




    // Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
    public void createUsersTable() throws SQLException {
        Connection conn = Util.getConnection();
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    // Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
    public void dropUsersTable() throws SQLException {
        Connection conn = Util.getConnection();
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    // Добавление User в таблицу
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection conn = Util.getConnection();
        try (PreparedStatement query = conn.prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)")) {
            query.setString(1, name);
            query.setString(2, lastName);
            query.setByte(3, age);
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

    }

    // Удаление User из таблицы ( по id )
    public void removeUserById(long id) throws SQLException {
        Connection conn = Util.getConnection();
        try (PreparedStatement query = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            query.setLong(1, id);
            query.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    // Получение всех User(ов) из таблицы
    public List<User> getAllUsers() throws SQLException {
        final Connection conn = Util.getConnection();
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM users")) {
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return users;
    }

    // Очистка содержания таблицы
    public void cleanUsersTable() throws SQLException {
        final Connection conn = Util.getConnection();
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
