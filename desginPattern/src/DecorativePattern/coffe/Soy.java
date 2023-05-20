package DecorativePattern.coffe;

public class Soy extends Beverage {
    private Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",soy";
    }

    @Override
    public double cost() {
        return 0.80 + beverage.cost();
    }

}
