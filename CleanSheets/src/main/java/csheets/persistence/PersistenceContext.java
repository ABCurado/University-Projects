package csheets.persistence;

import csheets.AppSettings;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * provides easy access to the persistence layer. works as a factory of
 * factories
 *
 * @author Paulo Gandra Sousa
 */
public final class PersistenceContext {

	public static RepositoryFactory repositories() {

		final String factoryClassName = AppSettings.instance().
			getRepositoryFactory();
		try {
			return (RepositoryFactory) Class.forName(factoryClassName).
				newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
			Logger.getLogger(PersistenceContext.class.getName()).
				log(Level.SEVERE, null, ex);
			return null;
		}
	}

	private PersistenceContext() {
	}
}
