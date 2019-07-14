package service.impl;

import dao.interfaces.UserDao;
import factory.UserDaoFactory;
import model.User;
import service.interfaces.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getUserDaoImpl();

    @Override
    public void addUser(String email, String password, String role) {
        userDao.createUser(email, password, role);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
