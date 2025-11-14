package MediatorExample;

import java.util.*;

interface DocumentSessionMediator {
    public void join(User user);
    public void broadcast(String change, User sender);
}

class CollaborativeDocument implements DocumentSessionMediator {
    private final ArrayList<User> users;

    public CollaborativeDocument() {
        this.users = new ArrayList<>();
    }

    @Override
    public void join(User user) {
        this.users.add(user);
    }

    @Override
    public void broadcast(String change, User sender) {
        for (User user: this.users) {
            user.receiveChange(change, sender);
        }
    }
}

class User {
    private final String name;
    private final DocumentSessionMediator document;

    public User(String name, DocumentSessionMediator document) {
        this.name = name;
        this.document = document;
    }

    public void makeChange(String change) {
        this.document.broadcast(change, this);
    }

    public void receiveChange(String change, User from) {
        if (!this.name.equalsIgnoreCase(from.name)) {
            System.out.println(this.name + " received: \"" + change + "\" from " + from.name);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        DocumentSessionMediator document = new CollaborativeDocument();

        User bob = new User("Bob", document);
        User alice = new User("Alice", document);
        User charlie = new User("Charlie", document);

        document.join(bob);
        document.join(alice);
        document.join(charlie);

        alice.makeChange("Updated the document title");

        bob.makeChange("Added a new section to the document");

        charlie.makeChange("Added footer in the document");
    }
}
