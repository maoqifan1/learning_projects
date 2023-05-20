package FactoryPattern.f2.plugins.pizza;


@SuppressWarnings("ALL")
public abstract class PizzaStore {

    public Pizza orderPizza(PizzaType pizzaType) {

        Pizza pizza;
        pizza = createPizza(pizzaType);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();


        return pizza;
    }

    protected abstract Pizza createPizza(PizzaType pizzaType);

}
