package DecorativePattern.coffe;

public class Mocha extends CondimentDecorator {
    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",mocha";
    }

    // 重新实现cost()方法，返回自己的价格和具体组件所具有的价格
    @Override
    public double cost() {
        return .20 + beverage.cost();
    }
}
