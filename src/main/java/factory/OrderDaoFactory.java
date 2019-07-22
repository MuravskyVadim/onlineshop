package factory;

import dao.impl.OrderDaoJdbcImpl;
import dao.interfaces.OrderDao;

import java.util.Objects;

public class OrderDaoFactory {

    private static OrderDao instance;

    private OrderDaoFactory() {
    }

    public static OrderDao getInstance() {
        return (Objects.isNull(instance)) ? instance = new OrderDaoJdbcImpl() : instance;
    }
}
