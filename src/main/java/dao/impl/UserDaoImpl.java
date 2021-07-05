package dao.impl;

import org.apache.log4j.Logger;
import storage.Storage;
import dao.intrfaces.UserDao;
import model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void createUser(String email, String password) {
        if (Objects.nonNull(email) || Objects.nonNull(password)) {
            User user = new User(UserIdGenerator.getId(), email, password);
            Storage.userList.add(user);
            logger.info("User " + user + " added to db");
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return Storage.userList;
    }

    @Override
    public void removeById(Long id) {
        List<User> allUsers = Storage.userList;
        for (User user : allUsers) {
            if (user.getId().equals(id)) {
                allUsers.remove(user);
                break;
            }
        }
    }
}
