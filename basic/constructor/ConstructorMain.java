package basic.constructor;

public class ConstructorMain {
    String message;

    public ConstructorMain(){
        this.message = "Hello World";
    }

    public static void main(String[] args) {
        ConstructorMain constructorMain = new ConstructorMain();
        System.out.println(constructorMain.message);
    }
}
