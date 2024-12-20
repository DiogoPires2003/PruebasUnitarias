package exceptions;


public class PMVPhisicalException extends Exception {

    // Default constructor
    public PMVPhisicalException() {
        super();
    }

    // Constructor with a custom error message
    public PMVPhisicalException(String message) {
        super(message);
    }

    // Constructor with a custom error message and a cause
    public PMVPhisicalException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with only a cause
    public PMVPhisicalException(Throwable cause) {
        super(cause);
    }
}