package DecorativePattern.coffe;

import java.io.PrintStream;

public class StarBuzzCoffee {
    public static void main(String[] args){
        Beverage expresso = new Expresso();

        expresso = new Mocha(expresso);
        expresso = new Whip(expresso);
        expresso = new Soy(expresso);

        PrintStream out = System.out;
        out.println(expresso.getDescription());
        out.println(expresso.cost());


    }
}
