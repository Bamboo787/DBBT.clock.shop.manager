package vn.td.clock.shop.services;

import vn.td.clock.shop.model.Role;
import vn.td.clock.shop.model.User;
import vn.td.clock.shop.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    public static String path = "data/users.csv";
    @Override
    public List<User> getUsers() {
        List<User> newUsers = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newUsers.add(new User(record));
        }
        return newUsers;
    }

    @Override
    public User loginAdmin(String username, String password) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)
                    && user.getRole().equals(Role.ADMIN)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User newUser) {
        newUser.setCreateDate(Instant.now());
        List<User> users = getUsers();
        users.add(newUser);
        CSVUtils.write(path, users);
    }

    @Override
    public void update(User newUser) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getId() == newUser.getId()) {
                if (newUser.getPhone() != null && !newUser.getPhone().isEmpty())
                    user.setName(newUser.getName());
                if (newUser.getPhone() != null && !newUser.getPhone().isEmpty())
                    user.setPhone(newUser.getPhone());
                if (newUser.getAddress() != null && !newUser.getAddress().isEmpty())
                    user.setAddress(newUser.getAddress());
                user.setCreateUpdate(Instant.now());
                CSVUtils.write(path, users);
                break;
            }
        }
    }

    @Override
    public boolean exists(int id) {
        return getUserById(id) != null;
    }

    @Override
    public boolean checkDuplicateEmail(String email) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicatePhone(String phone) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateUserName(String userName) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getUsername().equals(userName))
                return true;
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

}
