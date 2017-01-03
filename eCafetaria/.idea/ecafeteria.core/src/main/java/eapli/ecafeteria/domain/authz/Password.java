/**
 *
 */
package eapli.ecafeteria.domain.authz;

import java.io.Serializable;

import javax.persistence.Embeddable;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;

/**
 * TODO passwords should never be stored in plain format
 *
 * @author Paulo Gandra Sousa
 */
@Embeddable
public class Password implements ValueObject, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String password;

    protected Password() {
        // for ORM only
    }

    public Password(String password) {
        if (Strings.isNullOrEmpty(password)) {
            throw new IllegalStateException();
        }
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Password)) {
            return false;
        }

        final Password password1 = (Password) o;

        return this.password.equals(password1.password);

    }

    @Override
    public int hashCode() {
        return this.password.hashCode();
    }

    /**
     * Check how strong a password is
     *
     * @return how strong a password is
     */
    public PasswordStrength strength() {
        PasswordStrength passwordStrength = PasswordStrength.Weak;
        if (3 > this.password.length()) {
            passwordStrength = PasswordStrength.Weak;
        }
        return passwordStrength;
        // TODO implement the rest of the method
    }

    @Override
    public String toString() {
        return this.password;
    }

    public enum PasswordStrength {
        Weak, Good, Excelent,
    }
}
