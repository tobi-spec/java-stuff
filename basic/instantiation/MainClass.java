package basic.instantiation;

public class MainClass {

    public static void main(String[] args) {
        ServiceClass serviceClass = new ServiceClass();
        String message = serviceClass.returnString();

        System.out.println(message);
    }
}
