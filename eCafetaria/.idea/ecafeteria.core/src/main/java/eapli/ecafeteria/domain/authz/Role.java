/**
 *
 */
package eapli.ecafeteria.domain.authz;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import eapli.framework.domain.ValueObject;
import eapli.util.DateTime;

/**
 * @author Paulo Gandra Sousa
 */

public class Role implements ValueObject, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private RoleType type;
    @Temporal(TemporalType.DATE)
    private Calendar assignedOn;

    public Role(RoleType type) {
        this(type, DateTime.now());
    }

    public Role(RoleType type, Calendar assignedOn) {
        if (type == null || assignedOn == null) {
            throw new IllegalStateException();
        }
        this.type = type;
        this.assignedOn = assignedOn;
    }

    protected Role() {
        // for ORM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }

        final Role other = (Role) o;
        return this.type == other.type && this.assignedOn.equals(other.assignedOn);
    }

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }

    @Override

    public String toString() {
        return this.type + "@" + this.assignedOn;
    }

    public RoleType type() {
        return this.type;
    }
}
