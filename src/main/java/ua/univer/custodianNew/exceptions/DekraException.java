package ua.univer.custodianNew.exceptions;

public class DekraException extends RuntimeException{

    public DekraException(String message) {
        super(message);
    }

    public DekraException(String message, Throwable cause) {
        super(message, cause);
    }


}
