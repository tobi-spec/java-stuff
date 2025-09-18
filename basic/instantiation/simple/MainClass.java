package basic.instantiation.simple;

public class MainClass {

    public static void main(String[] args) {
        basicStuff.instantiation.simple.ServiceClass serviceClass = new basicStuff.instantiation.simple.ServiceClass();
        String message = serviceClass.returnString();

        System.out.println(message);
    }
}
