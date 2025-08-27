package designePatterns.singletonPattern;

public class SingletonMain {

    public static void main(String[] args) {

        Singleton singleton1 = Singleton.getInstance();
        singleton1.showMessage();

        Singleton singleton2 = Singleton.getInstance();
        singleton2.showMessage();

        if (singleton1 == singleton2) {
            System.out.println("Both references are the same");
        } else {
            System.out.println("references are not the same");
        }
    }
}
