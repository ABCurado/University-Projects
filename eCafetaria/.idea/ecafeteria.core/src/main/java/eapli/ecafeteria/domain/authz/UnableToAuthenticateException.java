package eapli.ecafeteria.domain.authz;

/**
 * Created by nuno on 22/03/16.
 */
public class UnableToAuthenticateException extends Exception {
    public UnableToAuthenticateException(String message) {
        super(message);
    }
}
