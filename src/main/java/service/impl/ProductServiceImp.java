package service.impl;

import dao.interfaces.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import service.interfaces.ProductService;

import java.util.List;

public class ProductServiceImp implements ProductService {

    private static final ProductDao productDao = ProductDaoFactory.getProductDaoImpl();

    @Override
    public void addProduct(String name, String description, Double price) {
        productDao.createProduct(name, description, price);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }
}
