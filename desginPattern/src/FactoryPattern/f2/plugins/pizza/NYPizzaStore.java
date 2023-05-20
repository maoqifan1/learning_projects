package FactoryPattern.f2.plugins.pizza;

import FactoryPattern.f2.plugins.fac.NYPizzaIngredientFactory;
import FactoryPattern.f2.plugins.fac.PizzaIngredientFactory;

public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(PizzaType pizzaType) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if (pizzaType.equals(PizzaType.CheesePizza)) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        } else if (pizzaType.equals(PizzaType.ClamsPizza)) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("New York Style Clam Pizza");
        }

        return pizza;
    }

}
