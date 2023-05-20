package ObserverPattern;


public interface Subject {

    void registerObserver(Observer o);

    /**
     * registerObserver()和removeObserver()方法都需要一个观察者作为变量
     * 该观察者是用来被注册或被删除的
     * */

    void removeObserver(Observer o);

    /**
     * 当主题改变时，该方法会被调用以通知所有观察者
     * */
    void notifyObserver();


}
