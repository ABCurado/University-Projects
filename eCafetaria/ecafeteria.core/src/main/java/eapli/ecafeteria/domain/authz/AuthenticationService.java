package eapli.ecafeteria.domain.authz;

import eapli.ecafeteria.persistence.PersistenceContext;

public class AuthenticationService {

    /**
     * Checks if a user can be authenticated with the username/password pair
     *
     * FIXME we are using exceptions for logic behaviour handling...
     *
     * @param username
     * @param pass
     * @return the authenticated user or null otherwise
     */
    public Session authenticate(Username username, Password pass) throws UnableToAuthenticateException {
        if (username == null) {
            throw new IllegalStateException("a username must be provided");
        }
        final SystemUser user = retrieveUser(username);
        if (null == user) {
            throw new UnableToAuthenticateException("Invalid User");
        }
        if (user.passwordMatches(pass) && user.isActive()) {
            return createSessionForUser(user);
        } else {
            throw new UnableToAuthenticateException("Invalid User");
        }
    }

    private Session createSessionForUser(SystemUser user) {
        return new Session(user);
    }

    private SystemUser retrieveUser(Username userName) {
        return PersistenceContext.repositories().users().findById(userName);
    }
}
