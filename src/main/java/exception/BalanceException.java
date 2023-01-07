package exception;

/**
 * Insufficient balance Exception
 */
public class BalanceException extends RuntimeException {

    public BalanceException(String message) {
        super(message);
    }
}
