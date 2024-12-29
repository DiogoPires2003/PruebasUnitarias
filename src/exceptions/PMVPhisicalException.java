package exceptions;


public class PMVPhisicalException extends Exception {

    public PMVPhisicalException() {
        super();
    }

    public PMVPhisicalException(String message) {
        super(message);
    }

    public PMVPhisicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public PMVPhisicalException(Throwable cause) {
        super(cause);
    }
}