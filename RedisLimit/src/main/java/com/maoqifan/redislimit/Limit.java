package com.maoqifan.redislimit;

import java.lang.annotation.*;

/**
 * @description 自定义注解限流
 * <p>
 *     period表示请求限制时间段，count表示在period这个时间段内允许放行请求的次数。
 *     limitType代表限流的类型，可以根据请求的IP、自定义key，
 *     如果不传limitType属性则默认用方法名作为默认key。
 * </p>
 */
// 作用于方法
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Limit {
    // 名字
    String name() default "";

    // key
    String key() default "";

    // key的前缀
    String prefix() default "";

    // 给定的时间范围 单位（秒）
    int period();

    // 一定时间内最多访问次数
    int count();

    // 限流的类型（用户自定义key或者请求ip)
    LimitType limitType() default LimitType.CUSTOMER;
}
