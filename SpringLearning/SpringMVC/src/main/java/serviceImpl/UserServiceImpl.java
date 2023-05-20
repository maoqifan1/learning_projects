package serviceImpl;

import dao.userDao;
import model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

import java.util.Map;

public class UserServiceImpl implements UserService {
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext");
    @Autowired
    private dao.userDao userDao;

    @Override
    public UserEntity userLogin(Map<String, Object> userMap){
        userDao = (userDao)applicationContext.getBean("userDao");
        UserEntity userEntity = userDao.userLogin(userMap);
        return userEntity;
    }

    @Override
    public int userRegister(UserEntity userEntity){
        userDao = (userDao)applicationContext.getBean("userDao");
        int result = userDao.userRegister(userEntity);
        return result;
    }

}
