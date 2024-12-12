package exceptions;
public class ConnectException extends Exception {

    // Allows additional information about the connection issue
    public ConnectException(String message) {
        super(message);
    }

    // Allows wrapping another exception, useful for debugging the root cause
    public ConnectException(String message, Throwable cause) {
        super(message, cause);
    }
}
}
