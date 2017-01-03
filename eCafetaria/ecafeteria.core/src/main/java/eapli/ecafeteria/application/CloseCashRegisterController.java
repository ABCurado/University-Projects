package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import java.util.List;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class CloseCashRegisterController implements Controller {

	/**
	 * This method return the shift that is being used
	 *
	 * @return Shift
	 */
	public Shift getShift() {
		SystemUser currentUser = AppSettings.getCurrentUser();
		return PersistenceContext.repositories().shift().
			isOpenedByUser(currentUser);
	}

	/**
	 * This method close the Shift and persist any updates
	 *
	 * @param shift Shift
	 * @return true if the shift can be closed,otherwise return false
	 */
	public boolean close(Shift shift) {
		try {

			shift.close();

			persist(shift);

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method updates any changes on the shift
	 *
	 * @param shift Shift
	 * @throws DataConcurrencyException
	 */
	private void persist(Shift shift) throws DataConcurrencyException {
		PersistenceContext.repositories().shift().save(shift);
	}

	/**
	 * This method returns the number of delivered reserves
	 *
	 * @param shift Shift
	 * @return number of delivered reserves
	 */
	public int reservesDelivered(Shift shift) {

		return PersistenceContext.repositories().reserves().
			findDeliveredReserves(shift).size();
	}

	/**
	 * This method returns the number of last minute meals that were sold
	 *
	 * @param shift Shift
	 * @return number of last minute meals
	 */
	public int lastMinuteMealsSold(Shift shift) {

		return PersistenceContext.repositories().lastMinuteSale().
			getLastMinuteSaleByShift(shift).size();

	}

	public List<Reserve> reservesNotDelivered(Shift shift) {
		return PersistenceContext.repositories().reserves().
			findNotDeliveredReseerves(shift);
	}

}
