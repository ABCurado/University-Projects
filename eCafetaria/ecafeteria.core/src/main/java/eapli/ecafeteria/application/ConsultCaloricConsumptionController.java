package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Calendar;

/**
 *
 * @author nervousdev
 */
public class ConsultCaloricConsumptionController implements Controller {

	public double getCaloricConsumptionByPeriod(Calendar startDate,
												Calendar endDate) {
		CafeteriaUser theUser = new UserService().
			cafeteriaUserBySystemUser(AppSettings.getCurrentUser()).iterator().
			next();
		return PersistenceContext.repositories().reserves().
			calculateCaloricConsumptionByPeriod(startDate, endDate, theUser);
	}
}
