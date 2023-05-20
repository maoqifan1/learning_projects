import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class TestShiro {

    @Test
    public void test() {
        // 创建工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory();
        // 获取实例
        SecurityManager securityManager = factory.getInstance();
        // 设定核心
        SecurityUtils.setSecurityManager(securityManager);
        // 获得对象
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("maoqifan", "19825069685");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        // 断言用户登录成功
        Assert.assertTrue(subject.isAuthenticated());
        // 退出登录
        subject.logout();
    }

    @Test
    public void test1() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-user.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken("zhang", "123"));
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(subject.isAuthenticated());
        subject.logout();


    }
}
