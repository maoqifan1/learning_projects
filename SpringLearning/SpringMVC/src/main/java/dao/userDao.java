package dao;

import model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.Map;

@Repository("userDao")
@Mapper
public interface userDao {
    UserEntity userLogin(Map<String,Object> userMapper);
    Integer userRegister(UserEntity userEntity);
}
