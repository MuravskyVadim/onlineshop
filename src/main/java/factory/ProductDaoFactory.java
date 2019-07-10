package factory;

import dao.impl.ProductDaoImpl;
import dao.interfaces.ProductDao;

import java.util.Objects;

public class ProductDaoFactory {

    private static ProductDao instance;

    private ProductDaoFactory() {
    }

    public static ProductDao getProductDaoImpl() {
        return (Objects.isNull(instance)) ? instance = new ProductDaoImpl() : instance;
    }
}
