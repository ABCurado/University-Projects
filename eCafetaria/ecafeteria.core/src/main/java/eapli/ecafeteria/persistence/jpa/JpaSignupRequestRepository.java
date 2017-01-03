package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.mealbooking.SignupRequest;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaSignupRequestRepository extends JpaRepository<SignupRequest, Username> implements SignupRequestRepository {

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }

    @Override
    public Iterable<SignupRequest> listPendingSignupRequests() {
        return match("e.approvalStatus=eapli.ecafeteria.domain.mealbooking.ApprovalStatus.PENDING");
    }
}
