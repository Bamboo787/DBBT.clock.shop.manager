package vn.td.clock.shop.services;

import vn.td.clock.shop.model.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();

    User loginAdmin(String username, String password);

    void add(User newUser);

    void update(User newUser);

    boolean exists(int id);

    boolean checkDuplicateEmail(String email);

    boolean checkDuplicatePhone(String phone);

    boolean checkDuplicateUserName(String userName);

    User getUserById(int id);

}

