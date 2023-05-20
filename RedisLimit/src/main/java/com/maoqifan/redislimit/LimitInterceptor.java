package com.maoqifan.redislimit;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.mockito.internal.util.StringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;

@Aspect
@Configuration
@Slf4j
public class LimitInterceptor {
    private static final String UNKNOWN = "unknown";
    @Resource
    private RedisTemplate<String, Serializable> limitRedisTemplate;

    /**
     * <p>
     * 在com.maoqifan.redislimit.Limit注解标注的所有 public 方法上添加环绕通知。
     * 这里使用了Spring AOP的切点表达式，
     * execution表示切点表达式，* *(..)表示对所有方法，@annotation表示对指定注解标注的方法。
     * </p>
     * <p>
     * MethodSignature是用来获取方法签名的工具类，
     * 它是继承了java.lang.reflect.Method和java.lang.reflect.GenericDeclaration的抽象类。
     * 它可以用来获取方法名、返回类型、参数类型、参数名称等。
     * </p>
     */
    @Around("execution(public * *(..)) && @annotation(com.maoqifan.redislimit.Limit)")
    public Object interceptor(ProceedingJoinPoint pjp) {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        // 获得方法上的@Limit注解
        Limit limitAnnotation = method.getAnnotation(Limit.class);
        // 获得限流类型
        LimitType limitType = limitAnnotation.limitType();

        String name = limitAnnotation.name();
        String key;
        int limitPeriod = limitAnnotation.period();
        int limitCount = limitAnnotation.count();

        // 根据限流类型获取不同的key，如果不传，则以方法名作为key
        key = switch (limitType) {
            case IP -> getIpAddr();
            case CUSTOMER -> limitAnnotation.key();
            default -> method.getName().toUpperCase();
        };

        ImmutableList<String> keys = ImmutableList.of(StringUtil.join(limitAnnotation.prefix(), key));

        try {
            String luaScript = luaScript();
            RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
            // 参数 ->待执行脚本列表， key列表， 可变参数argv列表,写的脚本返回什么，范型就是什么
            Number count = limitRedisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
            log.info("Access try count is {} for name={} and key={}", count, name, key);
            if (count != null && count.intValue() < limitCount) {
                return pjp.proceed();
            } else {
                throw new RuntimeException("You have been dragged into the blacklist");
            }
            FileSystemXmlApplicationContext

        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
            throw new RuntimeException("server exception");
        }

    }

    /**
     * @description 编写lua脚本限流
     * <p>
     * 调用不超过限流最大值，则直接返回
     * 从第一次调用开始限流， 设置对应键值的过期
     * </p>
     */
    public String luaScript() {
        return """
                local c
                c = redis.call('get', KEYS[1])
                if c and tonumber(c) > tonumber(ARGV[1]) then
                return c;
                end
                c = redis.call('incr', KEYS[1])
                if tonumber(c) == 1 then
                redis.call('expire', KEYS[1], ARGV[2])
                end
                return c;
                """;
    }

    public String getIpAddr() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
