package exception;

public class ZeroDivideException extends RuntimeException {
    public ZeroDivideException() {
        super("Can't divide by zero!");
    }
}
