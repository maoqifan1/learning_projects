package InnerObserverPattern;

import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Observer, DisplayElement {

    private float lastPressure;
    private float currentPressure = 29.9f;

    public ForecastDisplay(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current Conditions: " + currentPressure + " pressure and Last Pressure: " + lastPressure);
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WhetherData) {
            WhetherData whetherData = (WhetherData) obs;
            lastPressure = currentPressure;
            currentPressure = whetherData.getPressure();
            display();
        }
    }
}
