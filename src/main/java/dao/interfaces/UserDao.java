package dao.interfaces;

import model.User;

import java.util.List;

public interface UserDao {

    void createUser(String email, String password);

    List<User> getAllUsers();

    User getUserById(Long id);
}
