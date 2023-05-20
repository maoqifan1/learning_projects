package InnerObserverPattern;


import java.beans.PropertyChangeListener;

public class WhetherStation {
    public static void main(String args[]){
        WhetherData whetherData = new WhetherData();


        ForecastDisplay forecastDisplay =new ForecastDisplay(whetherData);

        whetherData.setMeasurements(80,65,30.4f);
        whetherData.setMeasurements(82,70,29.2f);
        whetherData.setMeasurements(78,90,29.2f);

    }
}
