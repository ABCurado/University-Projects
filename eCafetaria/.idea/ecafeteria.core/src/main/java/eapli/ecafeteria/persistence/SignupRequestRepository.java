package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.mealbooking.SignupRequest;
import eapli.framework.persistence.repositories.Repository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface SignupRequestRepository extends Repository<SignupRequest, Username> {

    Iterable<SignupRequest> listPendingSignupRequests();
}
