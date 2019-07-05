package service.interfaces;

import model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(String name, String description, Double price);

    List<Product> getAllProducts();
}
