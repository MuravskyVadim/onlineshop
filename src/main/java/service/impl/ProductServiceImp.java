package service.impl;

import dao.interfaces.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import service.interfaces.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImp implements ProductService {

    private static final ProductDao productDao = ProductDaoFactory.getProductDaoImpl();

    @Override
    public void addProduct(Product product) {
        productDao.createProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productDao.getProductById(id);
    }
}
