package ObserverPattern;

/**
 * 此布告板实现了Observer接口，所以可以从WhetherData对象中获得改变
 * 实现了DisplayElement接口，应为我们的api规定所有的布告板都必须实现此接口
 * @see ObserverPattern.Observer
 * @see ObserverPattern.DisplayElement
 * */
public class CurrentConditionDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private Subject whetherData;

    /**
     * @param whetherData 构造器需使用该对象（主题）作为注册之用
     * */
    public CurrentConditionDisplay(Subject whetherData) {
        this.whetherData = whetherData;
        whetherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current Conditions: " + temperature + "F degrees and " + humidity + "%humidity");
    }

    /**
     * 当该方法被调用时我们将温度和湿度保存起来并调用display()方法
     * */
    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
}
