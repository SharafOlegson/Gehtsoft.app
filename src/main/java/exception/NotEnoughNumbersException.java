package exception;

public class NotEnoughNumbersException extends RuntimeException {
    public NotEnoughNumbersException() {
        super("Invalid expression - not enough numbers");
    }
}
