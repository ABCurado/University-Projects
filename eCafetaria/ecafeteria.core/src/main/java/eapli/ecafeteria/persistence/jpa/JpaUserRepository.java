package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.Kitchen.ExecutionControl;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * Created by nuno on 20/03/16.
 */
class JpaUserRepository extends JpaRepository<SystemUser, Username> implements UserRepository {

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }

    @Override
    public List<SystemUser> activeUsers() {
        final Query q = entityManager().
                createQuery("SELECT u FROM SystemUser u WHERE u.active=:active",
                        SystemUser.class);

        q.setParameter("active", true);

        return (List<SystemUser>) q.getResultList();
    }
}
