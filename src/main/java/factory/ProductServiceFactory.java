package factory;

import service.interfaces.ProductService;
import service.impl.ProductServiceImp;

import java.util.Objects;

public class ProductServiceFactory {
    private static ProductService instance;

    private ProductServiceFactory() {
    }

    public static ProductService getProductServiceImpl() {

        return (Objects.isNull(instance)) ? instance = new ProductServiceImp() : instance;
    }
}
