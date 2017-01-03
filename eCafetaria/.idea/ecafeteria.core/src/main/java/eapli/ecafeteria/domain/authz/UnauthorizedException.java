/**
 *
 */
package eapli.ecafeteria.domain.authz;

/**
 * @author Paulo Gandra Sousa
 *
 */
public class UnauthorizedException extends RuntimeException {

    private final SystemUser user;
    private final ActionRight action;

    /**
     * @param message
     */
    public UnauthorizedException(String message, SystemUser user, ActionRight action) {
        super(message);
        this.action = action;
        this.user = user;
    }

    public SystemUser user() {
        return this.user;
    }

    public ActionRight intendedAction() {
        return this.action;
    }
}
