package service.impl;

import dao.intrfaces.ProductDao;
import dao.impl.ProductDaoImpl;
import model.Product;
import service.interfaces.ProductService;

import java.util.List;

public class ProductServiceImp implements ProductService {
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public void addProduct(String name, String description, Double price) {
        productDao.createProduct(name, description, price);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
}
