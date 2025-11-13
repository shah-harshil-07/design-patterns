class OrderContext {
    private OrderState currentState;

    public OrderContext() {
        this.currentState = new OrderPlacedState();
    }

    public void setCurrentState(OrderState state) {
        this.currentState = state;
    }

    public void next() {
        currentState.next(this);
    }

    public void cancel() {
        currentState.cancel(this);
    }

    public String getCurrentState() {
        return currentState.getName();
    }
}

interface OrderState {
    public String getName();
    public void next(OrderContext order);
    public void cancel(OrderContext order);
}

class OrderPlacedState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setCurrentState(new PreparingFoodState());
        System.out.println("Order status changed to `Preparing food`");
    }

    @Override
    public void cancel(OrderContext context) {
        context.setCurrentState(new OrderCancelledState());
        System.out.println("Order cancelled!!");
    }

    @Override
    public String getName() {
        return "Order placed";
    }
}

class PreparingFoodState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setCurrentState(new OutForDeliveryState());
        System.out.println("Order status changed to `Out for delivery`");
    }

    @Override
    public void cancel(OrderContext context) {
        context.setCurrentState(new OrderCancelledState());
        System.out.println("Order cancelled!!");
    }

    @Override
    public String getName() {
        return "Preparing food";
    }
}

class OutForDeliveryState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setCurrentState(new DeliveredState());
        System.out.println("Order status changed to `Delivered`");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel. Order is out for delivery.");
    }

    @Override
    public String getName() {
        return "Out for delivery";
    }
}

class DeliveredState implements OrderState {
    @Override
    public void next(OrderContext context) {
        System.out.println("Have a happy meal!!");
    }
    
    @Override
    public void cancel(OrderContext context) {
        System.out.println("Order cannot be cancelled after delivery!");
    }

    @Override
    public String getName() {
        return "Delivered";
    }
}

class OrderCancelledState implements OrderState {
    @Override
    public void next(OrderContext context) {
        System.out.println("Cancelled order cannot move to next state.");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("Order is already cancelled.");
    }

    @Override
    public String getName() {
        return "Order cancelled";
    }
}

public class Main {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();

        System.out.println("Initial State: " + order.getCurrentState());

        order.next();
        order.next();
        order.next();

        order.cancel();

        System.out.println("Final State: " + order.getCurrentState());
    }
}









