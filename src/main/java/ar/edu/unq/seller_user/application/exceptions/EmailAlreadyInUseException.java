package ar.edu.unq.seller_user.application.exceptions;

public class EmailAlreadyInUseException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Email: %s is already in use";

    public EmailAlreadyInUseException(String email) {
        super(String.format(EXCEPTION_MESSAGE, email));
    }
}
