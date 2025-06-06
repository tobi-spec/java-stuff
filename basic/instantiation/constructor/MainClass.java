package basicStuff.instantiation.constructor;

public class MainClass {

    private final ServiceClass serviceClass;

    public MainClass(ServiceClass serviceClass) {
        this.serviceClass = serviceClass;
    }

    private void displayMessage() {
        System.out.println(serviceClass.returnString());
    }

    public static void main(String[] args) {
        ServiceClass service = new ServiceClass();
        MainClass mainClass = new MainClass(service);

        mainClass.displayMessage();
    }
}
