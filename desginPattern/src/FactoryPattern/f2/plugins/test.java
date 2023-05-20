package FactoryPattern.f2.plugins;

import FactoryPattern.f2.plugins.pizza.NYPizzaStore;
import FactoryPattern.f2.plugins.pizza.PizzaType;

public class test {
    public static void main(String[] args){
        NYPizzaStore nyPizzaStore = new NYPizzaStore();
        nyPizzaStore.orderPizza(PizzaType.CheesePizza);

    }
}
