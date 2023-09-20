package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);
    User findUserById(Long id);
    List<User> findAllUsers();
    void updateUser(User user);
    void deleteUser(User user);
}
