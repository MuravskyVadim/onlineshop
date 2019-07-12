package service.interfaces;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(String email, String password);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);
}
