package exception;

public class ExpressionIsNullOrEmptyException extends RuntimeException {
    public ExpressionIsNullOrEmptyException() {
        super("Expression must not be null or empty");
    }
}
