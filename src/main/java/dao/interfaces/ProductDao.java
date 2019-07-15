package dao.interfaces;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void createProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);
}
