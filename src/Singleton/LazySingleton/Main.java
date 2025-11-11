package Singleton.LazySingleton;

class LazySingletonExample {
    private static LazySingletonExample instance = null;

    private LazySingletonExample() {
        System.out.println("LazySingletonExample instance created!!");
    }

    public static LazySingletonExample getInstance() {
        if (instance == null) {
            instance = new LazySingletonExample();
        }

        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        LazySingletonExample instance1 = LazySingletonExample.getInstance();
        System.out.println("instance1 hashCode => " + instance1.hashCode());

        LazySingletonExample instance2 = LazySingletonExample.getInstance();
        System.out.println("instance2 hashCode => " + instance2.hashCode());
    }
}
