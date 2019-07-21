package dao.impl;

import dao.interfaces.UserDao;
import model.User;
import org.apache.log4j.Logger;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbcImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    @Override
    public void addUser(User user) {
        String sql = String.format("INSERT INTO users(email, password, role) " +
                        "VALUES('%s', '%s', '%s')",
                        user.getEmail(), user.getPassword(), user.getRole());
        try (Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement()) {
            statement.execute(sql);
            logger.info(user + " added to db");
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")));
            }
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
            return null;
        }
        return userList;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id = " + id;
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
             ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
               return Optional.of(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")));
            }
        } catch (NullPointerException | SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = '" + email + "'";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
             ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Optional.of(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")));
            }
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void removeUser(User user) {
        String sql = "DELETE FROM users WHERE id = " + user.getId() + ";";
        try (Connection connection = DBConnection.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            logger.info(user + " removed from db successful");
        } catch (NullPointerException | SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = String.format("UPDATE users SET email = '%s', " +
                        "password = '%s', role = '%s' WHERE id = %d;",
                user.getEmail(), user.getPassword(),
                user.getRole(), user.getId());
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            logger.info(user.getId() + " was updated");
        } catch (NullPointerException | SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }
}
