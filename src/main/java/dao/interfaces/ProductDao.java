package dao.interfaces;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void createProduct(String name, String description, Double price);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);
}
