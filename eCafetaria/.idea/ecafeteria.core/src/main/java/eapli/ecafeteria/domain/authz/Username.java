/**
 *
 */
package eapli.ecafeteria.domain.authz;

import java.io.Serializable;

import javax.persistence.Embeddable;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;

/**
 * a username.
 *
 * it must be at least 6 characters long, with at least one capital letter and a
 * digit.
 *
 * @author Paulo Gandra Sousa
 */
@Embeddable
public class Username implements ValueObject, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String username;

    public Username(String username) {
        if (Strings.isNullOrEmpty(username)) {
            throw new IllegalStateException("username should neither be null nor empty");
        }
        // FIXME validate other invariants, e.g., regular expression
        this.username = username;
    }

    protected Username() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Username)) {
            return false;
        }

        final Username other = (Username) o;

        return this.username.equals(other.username);

    }

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }
}
