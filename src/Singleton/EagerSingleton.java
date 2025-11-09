package EagerSingleton;

class EagerSingletonExample {
    private static final EagerSingletonExample instance = new EagerSingletonExample();

    private EagerSingletonExample() {
        System.out.println("New instance of the defined class created!!");
    }

    static EagerSingletonExample getInstance() {
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        EagerSingletonExample instance = EagerSingletonExample.getInstance();
        System.out.println("instance hash code => " + instance.hashCode());
    }
}
