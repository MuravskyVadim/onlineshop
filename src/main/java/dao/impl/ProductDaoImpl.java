package dao.impl;

import storage.Storage;
import dao.intrfaces.ProductDao;
import model.Product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ProductDaoImpl implements ProductDao {
    private static Long id = 0L;

    @Override
    public void createProduct(String name, String description, Double price) {
        if (Objects.nonNull(name) || Objects.nonNull(price)) {
            ++id;
            Product product = new Product(id, name, description, price);
            Storage.productList.add(product);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return Storage.productList;
    }
}
