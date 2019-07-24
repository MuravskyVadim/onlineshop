package dao.impl;

import dao.interfaces.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoJdbcImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoJdbcImpl.class);
    private static final String ADD_PRODUCT = "INSERT INTO products(name, description, price) " +
            "VALUES('%s', '%s', '%s')";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM products";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ";
    private static final String REMOVE_PRODUCT = "DELETE FROM products WHERE id = ";
    private static final String UPDATE_PRODUCT = "UPDATE products " +
            "SET name = '%s', description = '%s', price = '%s' WHERE id = %d;";

    @Override
    public void addProduct(Product product) {
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                String.format(
                        ADD_PRODUCT,
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()))) {
            preparedStatement.execute();
            logger.info(product + " was added to db");
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                productList.add(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")));
            }
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
            return null;
        }
        return productList;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_PRODUCT_BY_ID + id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")));
            }
        } catch (NullPointerException | SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void removeProduct(Product product) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     REMOVE_PRODUCT + product.getId())) {
            preparedStatement.execute();
            logger.info(product + " removed from db");
        } catch (NullPointerException | SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    String.format(
                            UPDATE_PRODUCT,
                            product.getName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getId()));
            preparedStatement.executeUpdate();
            logger.info(product.getId() + " was edited");
        } catch (NullPointerException | SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }
}
