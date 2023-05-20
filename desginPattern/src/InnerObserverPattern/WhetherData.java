package InnerObserverPattern;

import java.util.Observable;

/**
 * @see java.util.Observable
 * */
public class WhetherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public WhetherData(){}

    /**
     * @see Observable#setChanged()
     * @see Observable#notifyObservers()
     * 在调用notifyObservers之前先调用setChanged方法表示数据已更新
     * */
    public void measurementsChanged(){
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
