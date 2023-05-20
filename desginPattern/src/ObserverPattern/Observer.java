package ObserverPattern;


/**
 * 所有的观察者都必须实现update()方法，以实现观察者接口。
 * */
public interface Observer {

    /**
     * 当气象观测值发生改变时，主题会将这些状态值作为参数传送给观察者
     * */
    void update(float temp, float humidity,float pressure);
}
