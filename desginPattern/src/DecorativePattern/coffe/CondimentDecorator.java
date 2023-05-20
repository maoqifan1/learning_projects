package DecorativePattern.coffe;


/**
 * @apiNote 因为CondimentDecorator 需要能够取代Beverage所以它需要继承自Beverage类
 * */
public abstract class CondimentDecorator extends Beverage {
    /**
     *@apiNote  所有的调料装饰着都必须重新实现getDescription()方法
     * */
    public abstract String getDescription();
}
