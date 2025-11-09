package PrototypeExample;

import java.util.HashMap;
import java.util.Map;

interface EmailTemplate extends Cloneable {
    EmailTemplate clone();
    void sendEmail(String to);
    void setContent(String content);
}

class WelcomeEmail implements EmailTemplate {
    private String content;
    private final String subject;

    public WelcomeEmail() {
        this.subject = "Welcome to Harshil's Repo";
        this.content = "Hi there! Thanks for joining us.";
    }

    @Override
    public void sendEmail(String to) {
        System.out.println("Sending email to: " + to + ": [" + this.subject + "] " + this.content);
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    @SuppressWarnings("CloneDeclaresCloneNotSupported")
    public WelcomeEmail clone() {
        try {
            return (WelcomeEmail) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone failed", e);
        }
    }
}

class EmailTemplateRegistry {
    private static final Map<String, EmailTemplate> templates = new HashMap<>();

    static {
        templates.put("welcome", new WelcomeEmail());
        // templates.put("discount", new DiscountEmail());
        // templates.put("feature-update", new FeatureUpdateEmail());
    }

    public static EmailTemplate getTemplate(String type) {
        return templates.get(type).clone();
    }
}

public class Main {
    public static void main(String[] args) {
        EmailTemplate welcomeEmail1 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail1.setContent("Hi Alice, welcome to Harshil's Github!");
        welcomeEmail1.sendEmail("alice@example.com");

        EmailTemplate welcomeEmail2 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail2.setContent("Hi Bob, thanks for joining!");
        welcomeEmail2.sendEmail("bob@example.com");
    }
}
