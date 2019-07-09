package service.impl;

import dao.intrfaces.UserDao;
import factory.UserDaoFactory;
import model.User;
import service.interfaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getUserDaoImpl();

    @Override
    public void addUser(String email, String password) {
        userDao.createUser(email, password);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
