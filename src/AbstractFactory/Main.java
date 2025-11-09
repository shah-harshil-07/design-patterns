package AbstractFactory;

interface PaymentGateway {
    void processPayment();
}

interface Invoice {
    void generateInvoce();
}

//========= Payment Gateways supported by India ===========
class Razorpay implements PaymentGateway {
    @Override
    public void processPayment() {
        System.out.println("Payment processed in India by Razorpay");
    }
}

class CCAvenue implements PaymentGateway {
    @Override
    public void processPayment() {
        System.out.println("Payment processed in India by CC Avenue");
    }
}

//========= Payment Gateways supported by the USA ===========
class Stripe implements PaymentGateway {
    @Override
    public void processPayment() {
        System.out.println("Payment processed in USA by Stripe");
    }
}

class Paypal implements PaymentGateway {
    @Override
    public void processPayment() {
        System.out.println("Payment processed in USA by Paypal");
    }
}

//========= Invoice generated in India ===========
class IndianInvoice implements Invoice {
    @Override
    public void generateInvoce() {
        System.out.println("Invoice generated as per Indian laws");
    }
}

//========= Invoice generated in the USA ===========
class USAInvoice implements Invoice {
    @Override
    public void generateInvoce() {
        System.out.println("Invoice generated as per the USA laws");
    }
}

//========= Abstract Factory ===========
interface RegionFactory {
    PaymentGateway createPaymentGateway(String gatewayType);
    Invoice createInvoice();
}

//========= Indian Factory ===========
class IndiaFactory implements RegionFactory {
    @Override
    public PaymentGateway createPaymentGateway(String gatewayType) {
        PaymentGateway paymentGateway = null;

        switch (gatewayType) {
            case "razorpay" -> paymentGateway = new Razorpay();
            case "cc_avenue" -> paymentGateway = new CCAvenue();
            default -> throw new IllegalArgumentException("Unsupported gateway for India: " + gatewayType);
        }

        return paymentGateway;
    }

    @Override
    public Invoice createInvoice() {
        return new IndianInvoice();
    }
}

//========= USA Factory ===========
class USAFactory implements RegionFactory {
    @Override
    public PaymentGateway createPaymentGateway(String gatewayType) {
        PaymentGateway paymentGateway = null;

        switch (gatewayType) {
            case "stripe" -> paymentGateway = new Stripe();
            case "paypal" -> paymentGateway = new Paypal();
            default -> throw new IllegalArgumentException("Unsupported gateway for the USA: " + gatewayType);
        }

        return paymentGateway;
    }

    @Override
    public Invoice createInvoice() {
        return new USAInvoice();
    }
}

class CheckoutService {
    private Invoice invoice = null;
    private PaymentGateway paymentGateway = null;

    public CheckoutService(RegionFactory factory, String gatewayType) {
        this.invoice = factory.createInvoice();
        this.paymentGateway = factory.createPaymentGateway(gatewayType);
    }

    public void checkoutOrder() {
        this.paymentGateway.processPayment();
        this.invoice.generateInvoce();
    }
}

public class Main {
    public static void main(String[] args) {
        USAFactory usaFactory = new USAFactory();
        IndiaFactory indiaFactory = new IndiaFactory();

        CheckoutService indianCheckout = new CheckoutService(indiaFactory, "razorpay");
        indianCheckout.checkoutOrder();

        CheckoutService usaCheckout = new CheckoutService(usaFactory, "paypal");
        usaCheckout.checkoutOrder();
    }
}
