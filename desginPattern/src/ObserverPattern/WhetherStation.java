package ObserverPattern;

public class WhetherStation {
    public static void main(String args[]){
        WhetherData whetherData = new WhetherData();

        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(whetherData);

        whetherData.setMeasurements(80,65,30.4f);
        whetherData.setMeasurements(82,70,29.2f);
        whetherData.setMeasurements(78,90,29.2f);

    }
}
