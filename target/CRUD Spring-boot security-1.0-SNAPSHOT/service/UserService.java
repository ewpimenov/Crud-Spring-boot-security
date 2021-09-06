package web.service;

import web.model.User;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();

    User addUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

    User getUser(int id);

    User findByUsername(String username);

}