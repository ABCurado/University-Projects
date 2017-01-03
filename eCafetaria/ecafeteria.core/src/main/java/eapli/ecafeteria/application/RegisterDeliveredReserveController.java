
package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.MecanographicNumber;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import java.util.NoSuchElementException;

/**
 * Register delivery of reserved meal by user
 * 
 * @author JoséBarros(1140364)
 */
public class RegisterDeliveredReserveController {
                  
                  /**
                   * Returns the current user
                   * 
                   * @param id - mecanographic number
                   * @return current user
                   */
	public CafeteriaUser getCafeteriaUserAccount(String id) {
		MecanographicNumber number = new MecanographicNumber(id);
		CafeteriaUser user = PersistenceContext.repositories().cafeteriaUsers().
			findById(number);

		if (user == null) {
			throw new NoSuchElementException("User not found");
		}

		return user;
	}
                  
                  /**
                   * Returns the list of user reserves
                   * 
                   * @param user - Current User
                   * @return  user reserves
                   */
	public Iterable<Reserve> listReservesByUser(CafeteriaUser user) {
		return PersistenceContext.repositories().reserves().
			findNotDelieveredReservesByUserAndShift(user, getShift());
	}
                  
                  /**
                   * Set state of user reserve for Delivered
                   * 
                   * @param theReserve - Selected Reserve
                   * @throws DataConcurrencyException 
                   */
	public void updateReserveStateToDelivered(Reserve theReserve) throws DataConcurrencyException {
		theReserve.deliverReserve(getShift());
		PersistenceContext.repositories().reserves().
			save(theReserve);
	}

                 /**
                  * Returns the cashier's shift.
                  * @return Cashier's shift
                  */ 
	public Shift getShift() {
		SystemUser currentUser = AppSettings.getCurrentUser();
		Shift shift = PersistenceContext.repositories().shift().
			isOpenedByUser(currentUser);
		if (shift == null) {
			throw new NoSuchElementException("Shift not found");
		}
		return shift;
	}
}

