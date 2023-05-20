package service;

import org.springframework.stereotype.Service;
import pojo.User;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    private static ArrayList<User> users = new ArrayList<>();

    @Override
    public Boolean addUser(User u) {
        if(!"IT民工".equals(u.getCarrer())){
            users.add(u);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<User> getUsers() {
        return users;
    }
}
