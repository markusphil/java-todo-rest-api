package rest;

public class InvalidPathException extends RuntimeException {
    InvalidPathException(String msg) {
        super(msg);
    }
}
