package eapli.ecafeteria.domain.authz;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import eapli.framework.authz.Authorisable;
import eapli.framework.domain.AggregateRoot;
import eapli.framework.dto.DTOable;
import eapli.framework.dto.GenericDTO;
import eapli.framework.visitor.Visitable;
import eapli.framework.visitor.Visitor;
import eapli.util.DateTime;

/**
 * An application user.
 *
 * This class represents application users. It follows a DDD approach where User
 * is the root entity of the User Aggregate and all of its properties are
 * instances of value objects.
 *
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 * @author Paulo Gandra Sousa
 *
 */
@Entity
public class SystemUser
        implements AggregateRoot<Username>, Authorisable<ActionRight>, DTOable, Visitable<GenericDTO>, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // TODO probably we should have a db ID (long) different from the domain ID.
    @Id
    private Username username;
    private Password password;
    private Name name;
    private EmailAddress email;
    private RoleSet roles;
    @Temporal(TemporalType.DATE)
    private Calendar createdOn;
    private boolean active;
    @Temporal(TemporalType.DATE)
    private Calendar deactivatedOn;

    // TODO: Why are there two constructors for SystemUser? createdOn shouldn't
    // only be assigned when approved?
    public SystemUser(final String username, final String password, final String firstName, final String lastName,
            final String email, final Set<RoleType> roles) {
        this(username, password, firstName, lastName, email, roles, DateTime.now());
    }

    public SystemUser(final String username, final String password, final String firstName, final String lastName,
            final String email, final Set<RoleType> roles, final Calendar createdOn) {
        if (roles == null) {
            throw new IllegalArgumentException("roles cannot be null");
        }
        this.createdOn = createdOn;
        this.username = new Username(username);
        this.password = new Password(password);
        this.name = new Name(firstName, lastName);
        this.email = new EmailAddress(email);
        this.roles = new RoleSet();

        this.roles.addAll(roles.stream().map(rt -> new Role(rt, this.createdOn)).collect(Collectors.toList()));

        this.active = true;
    }

    public SystemUser(final Username username, final Password password, final Name name,
            final EmailAddress email, final RoleSet roles) {
        this(username, password, name, email, roles, DateTime.now());
    }

    public SystemUser(final Username username, final Password password, final Name name,
            final EmailAddress email, final RoleSet roles, final Calendar createdOn) {
        if (roles == null) {
            throw new IllegalArgumentException("roles cannot be null");
        }
        this.createdOn = createdOn;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.roles = roles;

        this.active = true;
    }

    protected SystemUser() {
        // for ORM
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof SystemUser)) {
            return false;
        }

        final SystemUser that = (SystemUser) other;
        if (this == that) {
            return true;
        }
        if (!this.username.equals(that.username)) {
            return false;
        }
        if (!this.password.equals(that.password)) {
            return false;
        }
        if (!this.name.equals(that.name)) {
            return false;
        }
        if (!this.email.equals(that.email)) {
            return false;
        }
        return this.roles.equals(that.roles);
    }

    @Override
    public Username id() {
        return this.username;
    }

    /**
     * Add role to user.
     *
     * @param role Role to assign to SystemUser.
     */
    public void addRole(final Role role) {
        this.roles.add(role);
    }

    public RoleSet getRoles() {
        return this.roles;
    }

    @Override
    public GenericDTO toDTO() {
        final GenericDTO ret = new GenericDTO("user");
        ret.put("username", this.username.toString());
        ret.put("password", this.password.toString());
        ret.put("name", this.name.toString());
        ret.put("email", this.email.toString());
        ret.put("roles", this.roles.roleTypes().toString());
        // TODO: ASK Isn't it easy to forget mapping an element to DTO when
        // manipulating members?

        return ret;
    }

    /**
     * Remove role from user.
     *
     * @param role Role to remove from SystemUser.
     */
    public void removeRole(final Role role) {
        // TODO should the role be removed or marked as "expired"?
        this.roles.remove(role);
    }

    @Override
    public boolean isAuthorizedTo(final ActionRight action) {
        return action.canBePerformedBy(this.roles.roleTypes());
    }

    public boolean passwordMatches(final Password password) {
        return this.password.equals(password);
    }

    @Override
    public void accept(final Visitor<GenericDTO> visitor) {
        visitor.visit(toDTO());
    }

    @Override
    public boolean is(final Username id) {
        return id().equals(id);
    }

    public Username username() {
        return this.username;
    }

    public Name name() {
        return this.name;
    }

    public boolean isActive() {
        return this.active;
    }

    public void deactivate(Calendar deactivatedOn) {
        // FIXME validate parameters: not null; deactivatedOn > createdOn;
        // cannot deactivate an inactive user
        this.active = false;
        this.deactivatedOn = deactivatedOn;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SystemUser)) {
            return false;
        }

        final SystemUser that = (SystemUser) o;

        // DDD entities are only compared thru their ID field. in this
        // case only username should be compared
        return this.username.equals(that.username);

    }
}
