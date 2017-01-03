package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UserBuilder;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class AddUserController implements Controller {

	public SystemUser addUser(String username, String password, String firstName,
							  String lastName, String email,
							  Set<RoleType> roles, Calendar createdOn) {
		ensurePermissionOfLoggedInUser(ActionRight.Administer);

		final UserBuilder userBuilder = new UserBuilder();
		userBuilder.withUsername(username).withPassword(password).
			withFirstName(firstName).withLastName(lastName)
			.withEmail(email).withRoles(roles).withCreatedOn(createdOn);

		final SystemUser newUser = userBuilder.build();
		try {
			final UserRepository userRepository = PersistenceContext.
				repositories().
				users();
			// TODO error checking if the username is already in the persistence
			// store
			userRepository.add(newUser);
		} catch (DataIntegrityViolationException ex) {
			return null;
		}
		return newUser;
	}

	public SystemUser addUser(String username, String password, String firstName,
							  String lastName, String email,
							  Set<RoleType> roles) throws DataIntegrityViolationException {
		return addUser(username, password, firstName, lastName, email, roles, DateTime.
					   now());
	}
}
