package FactoryPattern.f2.plugins.pizza;

import FactoryPattern.f2.plugins.fac.PizzaIngredientFactory;

public class CheesePizza extends Pizza {

    PizzaIngredientFactory factory;

    public CheesePizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.factory = pizzaIngredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("preparing: " + name);
        dough = factory.createDough();
        sauce = factory.createSauce();
        cheese = factory.createCheese();
    }


}
