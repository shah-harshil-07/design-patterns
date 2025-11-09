package AdapterExample;

interface PaymentGateway {
    void pay(String orderId, double amount);
}

class PayUGateway implements PaymentGateway {
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Payment of $" + amount + " was made on order: " + orderId + " from PayU");
    }
}

class RazorpayGatewayAdapter implements PaymentGateway {
    RazorpayGateway paymentGateway;

    public RazorpayGatewayAdapter() {
        this.paymentGateway = new RazorpayGateway();
    }

    @Override
    public void pay(String orderId, double amount) {
        this.paymentGateway.makePayment(orderId, amount);
    }
}

class RazorpayGateway {
    public void makePayment(String orderId, double amount) {
        System.out.println("Payment of $" + amount + " was made on order: " + orderId + " from Razorpay");
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentGateway payUGateway = new PayUGateway();
        payUGateway.pay("AER125841", 4512.21);

        PaymentGateway razorpayGateway = new RazorpayGatewayAdapter();
        razorpayGateway.pay("AER125842", 8784.54);
    }
}
