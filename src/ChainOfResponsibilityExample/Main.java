package ChainOfResponsibilityExample;

abstract class SupportHandler {
    protected SupportHandler nextSupportHandler;

    public void setNextSupportHandler(SupportHandler support) {
        this.nextSupportHandler = support;
    }

    protected abstract void executeSupport(String requestType);
}

class GeneralServiceHandler extends SupportHandler {
    @Override
    public void executeSupport(String requestType) {
        if (requestType.equals("general")) {
            System.out.println("General support: Giving out general support.");
        } else {
            nextSupportHandler.executeSupport(requestType);
        }
    }
}

class BillingServiceHandler extends SupportHandler {
    @Override
    public void executeSupport(String requestType) {
        if (requestType.equals("billing")) {
            System.out.println("Billing support: Giving out billing support.");
        } else {
            nextSupportHandler.executeSupport(requestType);
        }
    }
}

class TechnicalServiceHandler extends SupportHandler {
    @Override
    public void executeSupport(String requestType) {
        if (requestType.equals("technical")) {
            System.out.println("Technical support: Giving out technical support.");
        } else {
            nextSupportHandler.executeSupport(requestType);
        }
    }
}

class DeliveryServiceHandler extends SupportHandler {
    @Override
    public void executeSupport(String requestType) {
        if (requestType.equals("delivery")) {
            System.out.println("Delivery support: Giving out delivery support.");
        } else if (nextSupportHandler != null) {
            nextSupportHandler.executeSupport(requestType);
        } else {
            System.out.println("No support handler available for this request.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SupportHandler billingServiceHandler = new BillingServiceHandler();
        SupportHandler generalServiceHandler = new GeneralServiceHandler();
        SupportHandler deliveryServiceHandler = new DeliveryServiceHandler();
        SupportHandler technicalServiceHandler = new TechnicalServiceHandler();

        generalServiceHandler.setNextSupportHandler(billingServiceHandler);
        billingServiceHandler.setNextSupportHandler(technicalServiceHandler);
        technicalServiceHandler.setNextSupportHandler(deliveryServiceHandler);

        generalServiceHandler.executeSupport("billing");
        generalServiceHandler.executeSupport("delivery");
        generalServiceHandler.executeSupport("unknown");
    }
}
