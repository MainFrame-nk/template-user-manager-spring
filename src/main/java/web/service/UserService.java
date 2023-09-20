package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    User findUserById(Long id);
    List<User> findAllUsers();
    void updateUser(User user);
    void deleteUser(User user);
}
