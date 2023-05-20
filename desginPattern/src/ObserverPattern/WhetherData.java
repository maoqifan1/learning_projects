package ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class WhetherData implements Subject {

    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WhetherData() {
        this.observers = new ArrayList<>();
    }

    /**
     * 当注册观察者时，我们只需要把它加到ArrayList的后面即可
     * */
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }


    /**
     * 同样的，当观察者想取消注册，我们只需要把它从ArrayList中移除
     * */
    @Override
    public void removeObserver(Observer o) {
        int idx = observers.indexOf(o);
        if (idx > 0)
            observers.remove(o);
    }


    /**
     * 把状态告诉每一个观察者，应为观察者都实现了update()方法，所以我们知道如何通知它们
     * */
    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    /**
     * 当从气象站得到更新观察值时，我们通知观察者
     * */
    public void measurementsChanged(){
        notifyObserver();
    }

    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        measurementsChanged();
    }
}
