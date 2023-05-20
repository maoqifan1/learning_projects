package InnerObserverPattern;

import java.util.Observable;
import java.util.Observer;

/**
 * @see java.util.Observer
 * @see DisplayElement
 * @see java.util.Observable
 * */
public class CurrentConditionDisplay implements Observer, DisplayElement {
    Observable observable;
    private float temperature;
    private float humidity;

    /**
     * 现在构造器需要-Observable当参数，并将CurrentConditionDisplay对象登记成观察者
     * */
    public CurrentConditionDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }

    /**
     * 改变update方法，增加observable和数据对象作为参数
     * 在update（）中，先确定观察者属于WhetherData类型，然后利用getter方法获得温度和湿度测量值，
     * 最后display（）方法 将结果显示到控制台
     * */
    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WhetherData){
            WhetherData whetherData = (WhetherData)obs;
            this.temperature = whetherData.getTemperature();
            this.humidity = whetherData.getHumidity();
            display();
        }
    }
}
