package Builder;

import java.util.*;

class BurgerMeal {
    private final  String patty;
    private final String bunType;

    private final String side;
    private final String drinks;
    private final boolean hasCheese;
    private final ArrayList<String> toppings;

    private BurgerMeal(BurgerBuilder builder) {
        this.side = builder.side;
        this.patty = builder.patty;
        this.drinks = builder.drinks;
        this.bunType = builder.bunType;
        this.toppings = builder.toppings;
        this.hasCheese = builder.hasCheese;
    }

    public void getBurgerStats() {
        System.out.println("side: " + this.side);
        System.out.println("patty: " + this.patty);
        System.out.println("drinks: " + this.drinks);
        System.out.println("bun type: " + this.bunType);
        System.out.println("toppings: " + this.toppings);
        System.out.println("has cheese: " + this.hasCheese);
    }

    public static class BurgerBuilder {
        private String patty;
        private String bunType;

        private String side;
        private String drinks;
        private boolean hasCheese;
        private ArrayList<String> toppings;

        public BurgerBuilder(String patty, String bunType) {
            this.patty = patty;
            this.bunType = bunType;
        }

        public BurgerBuilder addCheese() {
            this.hasCheese = true;
            return this;
        }

        public BurgerBuilder addSide(String side) {
            this.side = side;
            return this;
        }

        public BurgerBuilder addDrinks(String drinks) {
            this.drinks = drinks;
            return this;
        }

        public BurgerBuilder addToppings(ArrayList<String> toppings) {
            this.toppings = toppings;
            return this;
        }

        public BurgerMeal build() {
            return new BurgerMeal(this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating burger with only required fields
        BurgerMeal plainBurger = new BurgerMeal.BurgerBuilder("wheat", "veg")
                .build();

        plainBurger.getBurgerStats();
        System.out.println();

        // Burger with cheese only
        BurgerMeal cheeseBurger = new BurgerMeal.BurgerBuilder("wheat", "veg")
                .addCheese()
                .build();

        cheeseBurger.getBurgerStats();
        System.out.println();

        // Fully loaded burger
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add("jalepino");
        toppings.add("olives");
        toppings.add("paneer");
        toppings.add("corn");
        toppings.add("capsicum");

        BurgerMeal fullyLoadedBurger = new BurgerMeal.BurgerBuilder("multigrain", "veg")
                .addCheese()
                .addToppings(toppings)
                .addSide("fries")
                .addDrinks("coke")
                .build();

        fullyLoadedBurger.getBurgerStats();
    }
}
