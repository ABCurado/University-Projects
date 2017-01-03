package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class CancelReserveController implements Controller {

	/**
	 * Show reservations of an especific User.The method return the reservations
	 * of the parameter user.
	 *
	 * @param systemU
	 * @return reservations
	 */
	public Iterable<Reserve> showUserReservations(SystemUser systemU) {

		Iterable<CafeteriaUser> user = new UserService().
			cafeteriaUserBySystemUser(systemU);

		if (!user.iterator().hasNext()) {
			return null;
		}

		return PersistenceContext.repositories().reserves().
			findNotDelieveredReservesByUser(user.iterator().next());
	}

	/**
	 * Cancel reserve given a especific reserve and especific user
	 *
	 * @param reserve
	 * @param user
	 * @return true if reserve was canceled,otherwise return false
	 */
	public boolean cancelReserve(Reserve reserve, CafeteriaUser user) {

		try {
			user.cancelReserve(reserve);
			persist(user);
			reserve.cancelReserve();
			PersistenceContext.repositories().reserves().save(reserve);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Update the balance of the user account
	 *
	 * @param user
	 * @throws DataConcurrencyException
	 */
	private void persist(CafeteriaUser user) throws DataConcurrencyException {
		PersistenceContext.repositories().cafeteriaUsers().save(user);
	}

}
