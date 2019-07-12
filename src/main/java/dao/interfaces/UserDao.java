package dao.interfaces;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void createUser(String email, String password);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);
}
