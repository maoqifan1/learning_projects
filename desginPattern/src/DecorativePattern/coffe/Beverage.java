package DecorativePattern.coffe;

public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return this.description;
    }

    /**
     * @apiNote 在子类中必须实现此方法
     * */
    public abstract double cost();
}
