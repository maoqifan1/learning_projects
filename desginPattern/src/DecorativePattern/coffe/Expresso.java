package DecorativePattern.coffe;

/**
 * 具体组件类，继承自Beverage
 * */
public class Expresso extends Beverage {

    public Expresso(){
        super.description = "Expresso";
    }

    /**
     * 实现Beverage中的抽象方法cost()返回自己的价格
     * */
    @Override
    public double cost() {
        return 1.99;
    }
}
