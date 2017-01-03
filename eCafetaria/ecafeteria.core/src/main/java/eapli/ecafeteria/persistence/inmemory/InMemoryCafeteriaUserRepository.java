package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class InMemoryCafeteriaUserRepository extends InMemoryRepository<CafeteriaUser, MecanographicNumber> implements CafeteriaUserRepository {

	@Override
	protected MecanographicNumber newPK(CafeteriaUser u) {
		return u.id();
	}

	@Override
	public Iterable<CafeteriaUser> findByUsername(Username username) {
		List<CafeteriaUser> list = new ArrayList<>();
		Iterable<CafeteriaUser> cafeteriaUsers = repository.values();
		for (CafeteriaUser user : cafeteriaUsers) {
			if (user.getUsername().equals(username)) {
				list.add(user);
				break;
			}
		}
		return list;
	}
}
