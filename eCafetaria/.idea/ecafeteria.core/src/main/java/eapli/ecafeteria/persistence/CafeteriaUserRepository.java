package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.MecanographicNumber;
import eapli.framework.persistence.repositories.Repository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface CafeteriaUserRepository extends Repository<CafeteriaUser, MecanographicNumber> {

    public CafeteriaUser findByUsername(Username username);

}
