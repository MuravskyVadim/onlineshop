package dao.impl;

import storage.Storage;
import dao.intrfaces.UserDao;
import model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class UserDaoImpl implements UserDao {
    private static Long id = 0L;

    @Override
    public void createUser(String email, String password) {
        if (Objects.nonNull(email) || Objects.nonNull(password)) {
            ++id;
            User user = new User(id, email, password);
            Storage.userList.add(user);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return Storage.userList;
    }
}
