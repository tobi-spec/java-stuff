package basic.exception.customException;

public class AgeLessThanZeroException extends IllegalArgumentException {
    public AgeLessThanZeroException() {}

    public AgeLessThanZeroException(String message) {
        super(message);
    }

    public AgeLessThanZeroException(Throwable cause) {
        super(cause);
    }

    public AgeLessThanZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
