package factory;

import dao.impl.ProductDaoImpl;
import dao.impl.ProductDaoJdbcImpl;
import dao.interfaces.ProductDao;

import java.util.Objects;

public class ProductDaoFactory {

    private static ProductDao instance;

    private ProductDaoFactory() {
    }

    public static ProductDao getInstance() {
        return (Objects.isNull(instance)) ? instance = new ProductDaoJdbcImpl() : instance;
    }
}
