package FactoryPattern.f2.plugins.fac;

import FactoryPattern.f2.plugins.*;
import FactoryPattern.f2.plugins.abs.*;

public class ChicagoIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThinCurestDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new Mozzarella();
    }

    @Override
    @SuppressWarnings("ALL")
    public Veggies[] createVeggies() {
        Veggies[] veggies = {new BlackOlives(), new EggPlant(), new Spinach()};
        return veggies;
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        return new FrozenClams();
    }
}
