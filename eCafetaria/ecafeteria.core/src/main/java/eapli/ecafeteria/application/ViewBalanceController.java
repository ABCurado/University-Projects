package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.framework.application.Controller;

/**
 * Shows the current account balance of user
 * 
 * @author JoséBarros(1140364)
 */
public class ViewBalanceController implements Controller {
                  
                  /**
                   * Returns the current user
                   * 
                   * @return  Current user
                   */
	public Iterable<CafeteriaUser> getCafeteriaUserAccount() {
		return new UserService().cafeteriaUserBySystemUser(AppSettings.
			getCurrentUser());
	}

}