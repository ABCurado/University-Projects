package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import java.util.Calendar;
import java.util.Set;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UserBuilder;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class AddUserController implements Controller {

    public SystemUser addUser(String username, String password, String firstName, String lastName, String email,
            Set<RoleType> roles, Calendar createdOn) throws DataIntegrityViolationException {
        ensurePermissionOfLoggedInUser(ActionRight.Administer);

        final UserBuilder userBuilder = new UserBuilder();
        userBuilder.withUsername(username).withPassword(password).withFirstName(firstName).withLastName(lastName)
                .withEmail(email).withCreatedOn(createdOn).withRoles(roles);

        final SystemUser newUser = userBuilder.build();
        final UserRepository userRepository = PersistenceContext.repositories().users();
        // TODO error checking if the username is already in the persistence
        // store
        userRepository.add(newUser);
        return newUser;
    }

    public SystemUser addUser(String username, String password, String firstName, String lastName, String email,
            Set<RoleType> roles) throws DataIntegrityViolationException {
        return addUser(username, password, firstName, lastName, email, roles, DateTime.now());
    }
}
