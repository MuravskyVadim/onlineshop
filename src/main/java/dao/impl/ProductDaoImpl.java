package dao.impl;

import dao.interfaces.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import storage.Storage;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void createProduct(Product product) {
        Storage.productList.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return Storage.productList;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Storage.productList
                .stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }
}
