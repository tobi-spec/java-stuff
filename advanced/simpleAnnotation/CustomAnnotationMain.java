package advanced.simpleAnnotation;

import java.lang.reflect.Method;

public class CustomAnnotationMain {

    public static void main(String[] args) {
        Class<ClassToAnnotated> clazz = ClassToAnnotated.class;

        for(Method method: clazz.getDeclaredMethods()) {
            if(method.isAnnotationPresent(SimpleAnnotation.class)) {
                SimpleAnnotation simpleAnnotation = method.getAnnotation(SimpleAnnotation.class);
                System.out.println("Method: " + method.getName() +
                                ", Annotation: " + method.getAnnotation(SimpleAnnotation.class) +
                                ", Annotation Value: " + simpleAnnotation.value());
            }
        }
    }
}
