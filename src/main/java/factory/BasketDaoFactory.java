package factory;

import dao.impl.BasketDaoJdbcImpl;
import dao.interfaces.BasketDao;

import java.util.Objects;

public class BasketDaoFactory {

    private static BasketDao instance;

    private BasketDaoFactory() {
    }

    public static BasketDao getInstance() {
        return (Objects.isNull(instance)) ? instance = new BasketDaoJdbcImpl() : instance;
    }
}
