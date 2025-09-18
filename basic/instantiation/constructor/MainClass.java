package basic.instantiation.constructor;

public class MainClass {

    private final basicStuff.instantiation.constructor.ServiceClass serviceClass;

    public MainClass(basicStuff.instantiation.constructor.ServiceClass serviceClass) {
        this.serviceClass = serviceClass;
    }

    private void displayMessage() {
        System.out.println(serviceClass.returnString());
    }

    public static void main(String[] args) {
        basicStuff.instantiation.constructor.ServiceClass service = new basicStuff.instantiation.constructor.ServiceClass();
        MainClass mainClass = new MainClass(service);

        mainClass.displayMessage();
    }
}
