package exception;

public class UnsupportedOperatorException extends RuntimeException {
    public UnsupportedOperatorException(char operator) {
        super("Unsupported operator:" + operator);
    }
}
