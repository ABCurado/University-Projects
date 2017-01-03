package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

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
    public CafeteriaUser findByUsername(Username username) {
        Iterable<CafeteriaUser> cafeteriaUsers = repository.values();
        for (CafeteriaUser user : cafeteriaUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
