package test;

import dao.userDao;
import javafx.beans.binding.ObjectExpression;
import model.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MyBatisTest {
    public static void main(String args[]) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        userDao userDao = (userDao) applicationContext.getBean("userDao");
    }        Map<String,Object> userMap = new HashMap<String, Object>();