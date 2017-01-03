/**
 *
 */
package eapli.ecafeteria.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.ecafeteria.AppSettings;

/**
 * provides easy access to the persistence layer. works as a factory of
 * factories
 *
 * @author Paulo Gandra Sousa
 */
public final class PersistenceContext {

    public static RepositoryFactory repositories() {
        // return new InMemoryRepositoryFactory();
        // return new JpaRepositoryFactory();

        final String factoryClassName = AppSettings.instance().getRepositoryFactory();
        try {
            return (RepositoryFactory) Class.forName(factoryClassName).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            // FIXME handle exception properly
            Logger.getLogger(PersistenceContext.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private PersistenceContext() {
    }
}
