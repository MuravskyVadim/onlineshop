package dao.impl;

import dao.interfaces.UserDao;
import model.User;
import org.apache.log4j.Logger;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbcImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);
    private static final String ADD_USER = "INSERT INTO users(email, password, role) " +
            "VALUES('%s', '%s', '%s')";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = '%s'";
    private static final String REMOVE_USER = "DELETE FROM users WHERE id = ";
    private static final String UPDATE_USER = "UPDATE users SET email = '%s', " +
            "password = '%s', role = '%s' WHERE id = %d;";

    @Override
    public void addUser(User user) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     String.format(
                             ADD_USER,
                             user.getEmail(),
                             user.getPassword(),
                             user.getRole()))) {
            preparedStatement.execute();
            logger.info(user + " added to db");
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
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
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_USER_BY_ID + id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
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
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     String.format(GET_USER_BY_EMAIL, email));
             ResultSet resultSet = preparedStatement.executeQuery()) {
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
        try (Connection connection = DBConnection.getConnection();) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(REMOVE_USER + user.getId());
            preparedStatement.execute();
            logger.info(user + " removed from db successful");
        } catch (NullPointerException | SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     String.format(
                             UPDATE_USER,
                             user.getEmail(),
                             user.getPassword(),
                             user.getRole(),
                             user.getId()))) {
            preparedStatement.executeUpdate();
            logger.info(user.getId() + " was updated");
        } catch (NullPointerException | SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }
}
