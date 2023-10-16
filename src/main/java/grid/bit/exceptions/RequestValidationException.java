package grid.bit.exceptions;

public class RequestValidationException extends RuntimeException{
    public RequestValidationException(String message) {
        super(message);
    }
}
