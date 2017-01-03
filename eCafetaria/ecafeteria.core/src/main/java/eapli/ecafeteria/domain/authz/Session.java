/**
 *
 */
package eapli.ecafeteria.domain.authz;

import java.util.UUID;

import eapli.framework.domain.ValueObject;

/**
 * @author Paulo Gandra Sousa
 *
 */
public class Session implements ValueObject {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final SystemUser user;
    private final UUID token;

    public SystemUser authenticatedUser() {
        return this.user;
    }

    public Session(SystemUser user) {
        if (user == null) {
            throw new IllegalStateException("user must not be null");
        }
        this.user = user;
        this.token = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return this.user.id() + "@" + this.token;
    }
}
