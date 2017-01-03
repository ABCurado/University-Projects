package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import javax.persistence.Query;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaCafeteriaUserRepository extends JpaRepository<CafeteriaUser, MecanographicNumber>
	implements CafeteriaUserRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public Iterable<CafeteriaUser> findByUsername(Username username) {
		final Query q = entityManager().
			createQuery("select c from CafeteriaUser c where c.systemUser.username=:id", CafeteriaUser.class);
		q.setParameter("id", username);
		return q.getResultList();
	}
}
