package service;

import model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
    UserEntity userLogin(Map<String, Object> userMapper);
    int userRegister(UserEntity userEntity);
}
