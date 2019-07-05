package service.impl;

import dao.intrfaces.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.interfaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void addUser(String email, String password) {
        userDao.createUser(email, password);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
