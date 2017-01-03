package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;

/**
 *
 * @author nervousdev
 */
public class ChangeUserPasswordController implements Controller {

	public void updateUser(SystemUser theUser) throws DataConcurrencyException {
		PersistenceContext.repositories().users().save(theUser);
	}

	public SystemUser currentUser(Username username) {
		return new UserService().systemUserByUsername(username);
	}
}
