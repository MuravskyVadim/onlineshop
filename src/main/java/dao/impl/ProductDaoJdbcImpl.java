package dao.impl;

import dao.interfaces.ProductDao;
import model.Product;
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

public class ProductDaoJdbcImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoJdbcImpl.class);

    @Override
    public void addProduct(Product product) {
        Connection connection = DBConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("INSERT INTO products(name, description, price) " +
                            "VALUES('%s', '%s', '%s')",
                            product.getName(), product.getDescription(), product.getPrice());
            statement.execute(sql);
            logger.info(product + " was added to db");
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {
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
             Statement statement = connection.createStatement()) {
             ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM products WHERE id = " + id);
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
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM products WHERE id = " + product.getId());
            preparedStatement.execute();
            logger.info(product + " removed from db");
        } catch (NullPointerException | SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection connection = DBConnection.getConnection();) {
            String sql = String.format("UPDATE products " +
                            "SET name = '%s', description = '%s', price = '%s' WHERE id = %d;",
                            product.getName(), product.getDescription(),
                            product.getPrice(), product.getId());
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            logger.info(product.getId() + " was edited");
        } catch (NullPointerException | SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }
}
