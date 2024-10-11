package basic.constructorInstantiation;

public class MainClass {

    private ServiceClass serviceClass;

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
