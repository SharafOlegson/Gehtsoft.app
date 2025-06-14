package exception;

public class UnknownOperatorException extends RuntimeException {
    public UnknownOperatorException(String message) {
        super("Unknown operator: " + message);
    }
}
