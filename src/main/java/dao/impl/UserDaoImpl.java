package dao.impl;

import org.apache.log4j.Logger;
import storage.Storage;
import dao.interfaces.UserDao;
import model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void createUser(String email, String password) {
        if (Objects.nonNull(email) || Objects.nonNull(password)) {
            User user = new User(UserIdGenerator.getId(), email, password);
            Storage.userList.add(user);
            logger.info(user + " added to db");
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return Storage.userList;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Storage.userList
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Storage.userList
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }
}
