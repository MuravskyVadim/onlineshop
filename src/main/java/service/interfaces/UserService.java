package service.interfaces;

import model.User;

import java.util.List;

public interface UserService {
    void addUser(String email, String password);

    List<User> getAllUsers();
}
