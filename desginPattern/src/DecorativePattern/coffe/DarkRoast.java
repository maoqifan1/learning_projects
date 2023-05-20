package DecorativePattern.coffe;

/**
 * 具体组件类，继承自 Beverage
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        super.description = "DarkRoast";
    }

    /**
     * @apiNote 重新实现cost()方法，并返回自己的价格
     */
    @Override
    public double cost() {
        return 0.99;
    }
}
