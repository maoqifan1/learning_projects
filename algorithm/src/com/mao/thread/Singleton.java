package com.mao.thread;

/**
 * @description 双重加锁的单例模式
 */
public class Singleton {
    private volatile static Singleton instance = null;

    private Singleton() {
    }

    public Singleton getInstance() {
        // 先判断是否非空
        if (instance == null) {
            // 给class加锁
            // 这里给instance加锁是一样的效果
            synchronized (Singleton.class) {
                // 这里还要再判断一次，防止重复创建实例
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        // 不为空，则直接返回
        return instance;
    }
}
