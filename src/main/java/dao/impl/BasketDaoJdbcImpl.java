package dao.impl;

import dao.interfaces.BasketDao;
import model.Product;
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

public class BasketDaoJdbcImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoJdbcImpl.class);

    @Override
    public void addProduct(Product product, User user) {
        String sql = String.format(
                "INSERT INTO basket(product_id, product_name, " +
                        "product_description, product_price, user_id) " +
                "VALUES('%s', '%s', '%s', '%s', '%s')",
                        product.getId(), product.getName(), product.getDescription(),
                        product.getPrice(), user.getId());
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            logger.info(product + "added to basket");
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts(User user) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM basket WHERE user_id = " + user.getId();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                productList.add(new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_description"),
                        resultSet.getDouble("product_price")));
            }
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
            return null;
        }
        return productList;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        String sql = "SELECT FROM basket WHERE id = " + id;
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Optional.of(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")));
            }
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void removeProduct(Product product, User user) {
        String sql = "DELETE FROM basket WHERE product_id = " + product.getId() +
                     " AND user_id = " + user.getId() + ";";
        try (Connection connection = DBConnection.getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            logger.info(product + " removed from basket successful");
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public void clear(User user) {
        String sql = "DELETE FROM basket WHERE user_id = "+ user.getId() + ";";
        try (Connection connection = DBConnection.getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            logger.info("Basket of user " + user.getId() + " is cleared");
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }
}
