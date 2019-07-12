package service.interfaces;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void addProduct(String name, String description, Double price);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);
}
