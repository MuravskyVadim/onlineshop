package service.impl;

import model.Product;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImp implements ProductService {
    private final List<Product> productList = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }
}
