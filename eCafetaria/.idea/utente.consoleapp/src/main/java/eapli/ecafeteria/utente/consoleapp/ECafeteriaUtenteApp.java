package eapli.ecafeteria.utente.consoleapp;

import eapli.ecafeteria.bootstrapapp.ECafeteriaBootstrap;
import eapli.ecafeteria.utente.consoleapp.presentation.FrontMenu;

/**
 * eCafeteria User App.
 */
public final class ECafeteriaUtenteApp {

	/**
	 * Empty constructor is private to avoid instantiation of this class.
	 */
	private ECafeteriaUtenteApp() {
	}

	public static void main(final String[] args) {

		// only needed because of the in memory persistence
		new ECafeteriaBootstrap().execute();

		new FrontMenu().show();
	}
}
