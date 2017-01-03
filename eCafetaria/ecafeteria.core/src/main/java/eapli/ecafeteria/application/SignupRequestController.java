package eapli.ecafeteria.application;

import java.util.Calendar;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.mealbooking.SignupRequest;
import eapli.ecafeteria.domain.mealbooking.SignupRequestBuilder;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupRequestController implements Controller {

    public SignupRequest addSignupRequest(final String username, final String password, final String firstName,
            final String lastName, final String email, OrganicUnit organicUnit, String mecanographicNumber,
            final Calendar createdOn) throws DataIntegrityViolationException {

        if (!AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.Administer)) {
            // TODO check which exception to throw
            throw new IllegalStateException("user is not authorized to perform this action");
        }

        final SignupRequestBuilder signupRequestBuilder = new SignupRequestBuilder();
        signupRequestBuilder.withUsername(username).withPassword(password).withFirstName(firstName)
                .withLastName(lastName).withEmail(email).withCreatedOn(createdOn).withOrganicUnit(organicUnit)
                .withMecanographicNumber(mecanographicNumber);

        final SignupRequest newSignupRequest = signupRequestBuilder.build();
        final SignupRequestRepository signupRequestRepository = PersistenceContext.repositories().signupRequests();
        // TODO error checking if the username is already in the persistence
        // store
        signupRequestRepository.add(newSignupRequest);
        return newSignupRequest;
    }

    public SignupRequest addSignupRequest(final String username, final String password, final String firstName,
            final String lastName, final String email, OrganicUnit organicUnit, String mecanographicNumber)
            throws DataIntegrityViolationException {

        return addSignupRequest(username, password, firstName, lastName, email, organicUnit, mecanographicNumber,
                DateTime.now());
    }

    public Iterable<OrganicUnit> getAllOrganicUnit() {
        final OrganicUnitRepository organicUnitRepository = PersistenceContext.repositories().organicUnits();

        return organicUnitRepository.all();
    }
}
