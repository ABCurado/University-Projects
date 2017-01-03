/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.consoleapp.presentation;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.application.UserService;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;

/**
 *
 * @author nervousdev
 */
public class MainMenuController {

	public Iterable<CafeteriaUser> getCafeteriaUserAccount() {
		return new UserService().cafeteriaUserBySystemUser(AppSettings.
			getCurrentUser());
	}

	public void logoutCurrentUser() {
		AppSettings.instance().removeSession();
	}
}
