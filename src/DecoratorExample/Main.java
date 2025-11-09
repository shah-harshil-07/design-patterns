package DecoratorExample;

interface Pizza {
    double getCost();
    String getDescription();
}

class PlainPizza implements Pizza {
    @Override
    public double getCost() {
        return 20;
    }

    @Override
    public String getDescription() {
        return "Plain Pizza";
    }
}

abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

class EightChesse extends  PizzaDecorator {
    public EightChesse(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 20;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + 8 types of cheese";
    }
}

class VeggieDelight extends PizzaDecorator {
    public VeggieDelight(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 30;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + exotic delight of stuffed vegetables";
    }
}

public class Main {
    public static void main(String[] args) {
        Pizza pizza = new PlainPizza();
        System.out.println("Plain pizza cost: " + pizza.getCost());
        System.out.println("Plain pizza description: " + pizza.getDescription());

        pizza = new EightChesse(pizza);
        System.out.println("Eight cheese pizza cost: " + pizza.getCost());
        System.out.println("Eight cheese pizza description: " + pizza.getDescription());

        pizza = new VeggieDelight(pizza);
        System.out.println("Eight cheese Veg delight pizza cost: " + pizza.getCost());
        System.out.println("Eight cheese Veg delight pizza description: " + pizza.getDescription());
    }
}

