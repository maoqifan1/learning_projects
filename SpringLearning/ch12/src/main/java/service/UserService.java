package service;

import pojo.User;

import java.util.ArrayList;

public interface UserService {
    Boolean addUser(User u);
    ArrayList<User> getUsers();
}
