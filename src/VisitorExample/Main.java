package VisitorExample;

import java.util.*;

interface Product {
    public void deriveMetrics(MetricsCalculator calculator);
}

interface MetricsCalculator {
    public void deriveMetrics(GiftCard product);
    public void deriveMetrics(DigitalProduct product);
    public void deriveMetrics(PhysicalProduct product);
}

class PhysicalProduct implements Product {
    protected final String name;
    protected final double weight;
    public PhysicalProduct(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public void deriveMetrics(MetricsCalculator calculator) {
        calculator.deriveMetrics(this);
    }
}

class DigitalProduct implements Product {
    protected final String name;
    protected final double downloadSize;
    public DigitalProduct(String name, double downloadSize) {
        this.name = name;
        this.downloadSize = downloadSize;
    }

    @Override
    public void deriveMetrics(MetricsCalculator calculator) {
        calculator.deriveMetrics(this);
    }
}

class GiftCard implements Product {
    protected final String code;
    protected final double amount;
    public GiftCard(String code, double amount) {
        this.code = code;
        this.amount = amount;
    }

    @Override
    public void deriveMetrics(MetricsCalculator calculator) {
        calculator.deriveMetrics(this);
    }
}

class ShippingCostCalculator implements MetricsCalculator {
    @Override
    public void deriveMetrics(PhysicalProduct product) {
        System.out.println("Shipping cost for " + product.name + ": Rs. " + (product.weight * 10));
    }

    @Override
    public void deriveMetrics(DigitalProduct product) {
        System.out.println(product.name + " is digital -- No shipping cost.");
    }

    @Override
    public void deriveMetrics(GiftCard product) {
        System.out.println("GiftCard delivery via email -- No shipping cost.");
    }
}

class InvoiceGenerator implements MetricsCalculator {
    @Override
    public void deriveMetrics(PhysicalProduct product) {
        System.out.println("Invoice: " + product.name + " - Shipping to customer");
    }

    @Override
    public void deriveMetrics(DigitalProduct product) {
        System.out.println(product.name + " is digital -- No shipping cost.");
    }

    @Override
    public void deriveMetrics(GiftCard product) {
        System.out.println("GiftCard delivery via email -- No shipping cost.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Create instances of different products
        List<Product> cart = new ArrayList<>();
        cart.add(new PhysicalProduct("Shoes", 1.2));
        cart.add(new DigitalProduct("Ebook", 100));
        cart.add(new GiftCard("TUF500", 500));

        MetricsCalculator invoiceGenerator = new InvoiceGenerator();
        MetricsCalculator shippingCostCalculator = new ShippingCostCalculator();

        // Loop through cart and perform actions based on product type
        for (Product product: cart) {
            product.deriveMetrics(shippingCostCalculator);
            product.deriveMetrics(invoiceGenerator);
        }
    }
}
