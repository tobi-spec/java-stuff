package basic.constructor;

public class MostSimpleConstructorMain {
    String message;

    public MostSimpleConstructorMain(){
        this.message = "Hello World";
    }

    public static void main(String[] args) {
        MostSimpleConstructorMain constructorMain = new MostSimpleConstructorMain();
        System.out.println(constructorMain.message);
    }
}
