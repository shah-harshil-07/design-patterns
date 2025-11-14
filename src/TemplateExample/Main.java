package TemplateExample;

abstract class NotificationSender {
    public final void send(String to, String message) {
        this.checkRateLimits(to);
        this.validateEmailRecepient(to);
        this.composeMessage(message);
        this.sendNotification(to, message);
        this.postSendAnalytics(to);
    }

    private void checkRateLimits(String to) {
        System.out.println("Checking rate limits for: " + to);
    }

    private void validateEmailRecepient(String to) {
        System.out.println("Validating email recipient: " + to);
    }

    protected abstract String composeMessage(String message);
    protected abstract void sendNotification(String to, String message);

    protected void postSendAnalytics(String to) {
        System.out.println("Analytics updated for: " + to);
    }
}

class EmailNotification extends NotificationSender {
    @Override
    public String composeMessage(String message) {
        return "<html><body><p>" + message.trim() + "</p></body></html>";
    }

    @Override
    public void sendNotification(String to, String message) {
        System.out.println("Sending EMAIL to " + to + " with content:\n" + message);
    }
}

// SMSNotification handles sending SMS messages
class SMSNotification extends NotificationSender {
    @Override
    public String composeMessage(String message) {
        return "[SMS] " + message.trim();
    }

    @Override
    public void sendNotification(String to, String message) {
        System.out.println("Sending SMS to " + to + " with message: " + message);
    }

    @Override
    public void postSendAnalytics(String to) {
        System.out.println("Custom SMS analytics for: " + to);
    }
}

public class Main {
    public static void main(String[] args) {
        // Create objects for both notification services
        SMSNotification smsNotification = new SMSNotification();
        EmailNotification emailNotification = new EmailNotification();

        // Sending email notification
        emailNotification.send("example@example.com", "Your order has been placed!");
        
        System.out.println(" ");
        
        // Sending SMS notification
        smsNotification.send("1234567890", "Your OTP is 1234.");
    }
}









