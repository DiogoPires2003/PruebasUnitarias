package exceptions;

public class InvalidPairingException extends Exception {
    public InvalidPairingException(String message) {
        super(message);
    }

    public InvalidPairingException(String message, Throwable cause) {
        super(message, cause);
    }
}