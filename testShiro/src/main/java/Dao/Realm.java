package Dao;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

public interface Realm {
    String getName();//返回一个唯一的realm名称
    boolean supports(AuthenticationToken token); // 判断此realm是否支持此token
    AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException;//根据token获取认证信息
}
