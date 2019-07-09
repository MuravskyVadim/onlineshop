package dao.intrfaces;

import model.User;

import java.util.List;

public interface UserDao {

    void createUser(String email, String password);

    List<User> getAllUsers();

    void removeById(Long id);
}
