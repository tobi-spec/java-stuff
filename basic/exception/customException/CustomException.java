package basic.exception.customException;

public class CustomException {

    public static void main(String[] args) throws AgeLessThanZeroException {
        validateAge(-3);
    }

    public static void validateAge(int age) throws AgeLessThanZeroException {
        if (age < 0) {
            throw new AgeLessThanZeroException("Age is less than 0", new RuntimeException());
        }
    }
}
