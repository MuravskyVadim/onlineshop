package dao.interfaces;

import model.Product;

import java.util.List;

public interface ProductDao {

    void createProduct(String name, String description, Double price);

    List<Product> getAllProducts();

    Product getProductById(Long id);
}
