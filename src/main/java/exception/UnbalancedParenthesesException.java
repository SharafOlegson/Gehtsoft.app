package exception;

public class UnbalancedParenthesesException extends RuntimeException {
    public UnbalancedParenthesesException(String message) {
        super("Unbalanced parentheses in expression: " + message);
    }
}
