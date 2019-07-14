package dao.impl;

import dao.interfaces.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import storage.Storage;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);

    @Override
    public void createProduct(String name, String description, Double price) {
        Product product = new Product(ProductIdGenerator.getId(), name, description, price);
        Storage.productList.add(product);
        logger.info(product + " added to db");
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
