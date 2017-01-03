package eapli.ecafeteria;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.Session;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UnauthorizedException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A "global" (singleton) class with the application settings.
 *
 * @author Paulo Gandra Sousa
 */
public final class AppSettings {

	private final Properties applicationProperties = new Properties();
	// private final static String PROPERTIES_RESOURCE =
	// "eapli/ecafeteria/ecafeteria.properties";
	private final static String PROPERTIES_RESOURCE = "ecafeteria.properties";
	private final static String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
	private final static String UI_MENU_LAYOUT_KEY = "ui.menu.layout";

	// use lazy holder idiom
	// https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
	private static class LazyHolder {

		public static final AppSettings INSTANCE = new AppSettings();
	}

	public static AppSettings instance() {
		return LazyHolder.INSTANCE;
	}

	private AppSettings() {
		loadProperties();
	}

	private void loadProperties() {
		InputStream propertiesStream = null;
		try {
			// propertiesStream = new FileInputStream(PROPERTIES_FILENAME);
			propertiesStream = AppSettings.class.getClassLoader().
				getResourceAsStream(PROPERTIES_RESOURCE);
			if (propertiesStream != null) {
				this.applicationProperties.load(propertiesStream);
			} else {
				throw new FileNotFoundException(
					"property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
			}
		} catch (final IOException exio) {
			setDefaultProperties();

			Logger.getLogger(AppSettings.class.getName()).
				log(Level.SEVERE, null, exio);
		} finally {
			if (propertiesStream != null) {
				try {
					propertiesStream.close();
				} catch (final IOException ex) {
					Logger.getLogger(AppSettings.class.getName()).
						log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	private void setDefaultProperties() {
		this.applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
											   "eapli.ecafeteria.persistence.jpa.JpaRepositoryFactory");
		this.applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "horizontal");
	}

	public String getRepositoryFactory() {
		return this.applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
	}

	public Boolean isMenuLayoutHorizontal() {
		return "horizontal".equalsIgnoreCase(this.applicationProperties.
			getProperty(UI_MENU_LAYOUT_KEY));
	}

	//
	// session
	//
	// TODO move this part to other class? e.g., AppContext
	//
	private Session theSession = null;

	// in a real life situation this method should not exist! anyone could
	// circunvent the authentication
	public void setSession(Session session) {
		this.theSession = session;
	}

	public void removeSession() {
		this.theSession = null;
	}

	public Session session() {
		return this.theSession;
	}

	/**
	 * helper method to check the permission of the currently logged in user
	 */
	public static void ensurePermissionOfLoggedInUser(ActionRight action) {
		if (!AppSettings.instance().session().authenticatedUser().
			isAuthorizedTo(action)) {
			throw new UnauthorizedException("User is not authorized to perform this action",
											AppSettings.instance().session().
											authenticatedUser(), action);
		}
	}

	public static SystemUser getCurrentUser() {
		return AppSettings.instance().session().authenticatedUser();
	}
}
