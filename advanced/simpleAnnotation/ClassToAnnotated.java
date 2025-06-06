package advanced.simpleAnnotation;

public class ClassToAnnotated {

    @SimpleAnnotation(value = "Hello World!")
    public void annotatedMethod() {
        System.out.println("This is a annotated method");
    }

    public void normalMethod() {
        System.out.println("This is a normal method");
    }
}
