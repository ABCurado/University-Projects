package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;

/**
 *
 * @author losa
 */
public class ListUsersController implements Controller {

	/**
	 *
	 * @return all users from repository
	 */
	public Iterable<SystemUser> listUsers() {
		ensurePermissionOfLoggedInUser(ActionRight.Administer);

		final UserRepository userRepository = PersistenceContext.repositories().
			users();
		return userRepository.all();
	}
}
