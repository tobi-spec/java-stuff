package basic.constructor;

public class SecondSimpleConstructorMain {
    String message;

    public SecondSimpleConstructorMain(String message){
        this.message = message;
    }

    public static void main(String[] args) {
        SecondSimpleConstructorMain oneSimpleConstructor = new SecondSimpleConstructorMain("Hello World");
        System.out.println(oneSimpleConstructor.message);

        SecondSimpleConstructorMain anotherSimpleConstructor = new SecondSimpleConstructorMain("Hello World, again");
        System.out.println(anotherSimpleConstructor.message);
    }
}
