package dao.impl;

import dao.intrfaces.ProductDao;
import model.Product;
import storage.Storage;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void createProduct(String name, String description, Double price) {
        Product product = new Product(ProductIdGenerator.getId(), name, description, price);
        Storage.productList.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return Storage.productList;
    }
}
