package exceptions;

public class InvalidPathException extends RuntimeException {
    public InvalidPathException(String msg) {
        super(msg);
    }
}
