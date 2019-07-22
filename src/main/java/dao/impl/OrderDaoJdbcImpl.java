package dao.impl;

import dao.interfaces.OrderDao;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import utils.Code;
import utils.DBConnection;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class OrderDaoJdbcImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);
    private static final String ADD_ORDER = "INSERT INTO orders(" +
            "user_id, code_value, first_name, last_name, city, " +
            "street, house_number, phone_number) " +
            "VALUES(%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM orders INNER JOIN users " +
            "ON orders.user_id = users.id WHERE orders.id = ";
    @Override
    public Optional<Long> addOrder(Order order) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     String.format(
                             ADD_ORDER,
                             order.getUser().getId(),
                             order.getUser().getCode().getValue(),
                             order.getFirstName(),
                             order.getLastName(),
                             order.getCity(),
                             order.getStreet(),
                             order.getHouseNumber(),
                             order.getPhoneNumber()),
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                Optional<Long> orderId = Optional.of(resultSet.getLong(1));
                logger.info(orderId.get() + " added to orders");
                return orderId;
            }
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        try (Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ORDER_BY_ID + id);
            if (resultSet.next()) {
                Code code = new Code();
                code.setValue(resultSet.getString("code_value"));
                User user = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                return Optional.of(new Order(
                        resultSet.getLong("id"),
                        user, code,
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("city"),
                        resultSet.getString("street"),
                        resultSet.getString("house_number"),
                        resultSet.getString("phone_number")));
            }
        } catch (NullPointerException | SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
        return Optional.empty();
    }
}
