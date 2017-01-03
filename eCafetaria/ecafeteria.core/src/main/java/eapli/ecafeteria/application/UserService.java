package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.persistence.PersistenceContext;

public class UserService {

	/**
	 * Returns a iterable of CafeteriaUsers for a given SystemUser.
	 *
	 * @param currentUser System user.
	 * @return Iterable of CafeteriaUsers.
	 */
	public Iterable<CafeteriaUser> cafeteriaUserBySystemUser(
		SystemUser currentUser) {
		return PersistenceContext.repositories().cafeteriaUsers().
			findByUsername(currentUser.username());
	}

	/**
	 * Returns every system users that are active.
	 *
	 * @return Active system users.
	 */
	public Iterable<SystemUser> activeSystemUsers() {
		return PersistenceContext.repositories().users().activeUsers();
	}

	public SystemUser systemUserByUsername(Username username) {
		return PersistenceContext.repositories().users().findById(username);
	}
}
