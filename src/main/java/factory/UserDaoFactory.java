package factory;

import dao.impl.UserDaoImpl;
import dao.intrfaces.UserDao;

import java.util.Objects;

public class UserDaoFactory {

    private static UserDao instance;

    private UserDaoFactory() {
    }

    public static UserDao getUserDaoImpl() {
        return (Objects.isNull(instance)) ? instance = new UserDaoImpl() : instance;
    }
}
