package FactoryPattern.f2.plugins.fac;

import FactoryPattern.f2.plugins.*;
import FactoryPattern.f2.plugins.abs.*;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThinCurestDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSource();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    @SuppressWarnings("all")
    public Veggies[] createVeggies() {
        Veggies[] veggies = {new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
        return veggies;
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }
}
