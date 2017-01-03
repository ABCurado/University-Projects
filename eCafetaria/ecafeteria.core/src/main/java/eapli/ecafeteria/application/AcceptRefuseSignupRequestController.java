package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UserBuilder;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUserBuilder;
import eapli.ecafeteria.domain.mealbooking.SignupRequest;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * Created by AJS on 08/04/2016.
 */
public class AcceptRefuseSignupRequestController implements Controller {

	public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest) throws DataIntegrityViolationException {
		ensurePermissionOfLoggedInUser(ActionRight.Administer);

		if (theSignupRequest == null) {
			throw new IllegalStateException();
		}

		// TODO this controller has some logic that could be moved to a domain
		// service
		// TODO there are some code duplication to create and add the system
		// user
		//
		// add system user
		final UserBuilder userBuilder = new UserBuilder();
		userBuilder.withUsername(theSignupRequest.username()).
			withPassword(theSignupRequest.password())
			.withName(theSignupRequest.name()).withEmail(theSignupRequest.
			email());
		final SystemUser newUser = userBuilder.build();
		final UserRepository userRepository = PersistenceContext.repositories().
			users();

		// error checking if the username is already in the persistence
		if (userRepository.findById(theSignupRequest.username()) == null) {
			// store
			userRepository.add(newUser);
		}
		//
		// add cafeteria user
		//
		final CafeteriaUserBuilder cafeteriaUserBuilder = new CafeteriaUserBuilder();
		cafeteriaUserBuilder.withMecanographicNumber(theSignupRequest.
			mecanographicNumber())
			.withOrganicUnit(theSignupRequest.organicUnit()).
			withSystemUser(newUser);
		final CafeteriaUser cafeteriaUser = cafeteriaUserBuilder.build();
		final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.
			repositories().cafeteriaUsers();
		cafeteriaUserRepository.add(cafeteriaUser);

		//
		// modify Signup Request to accepted
		//
		theSignupRequest.changeToAcceptedStatus();
		final SignupRequestRepository repo = PersistenceContext.repositories().
			signupRequests();

		try {
			repo.save(theSignupRequest);
		} catch (DataConcurrencyException e) {
                    throw new DataIntegrityViolationException("Data has changed or has been deleted since it was last read. Please try again.");
		}

		return theSignupRequest;
	}

	public SignupRequest refuseSignupRequest(SignupRequest theSignupRequest) throws DataConcurrencyException {
		ensurePermissionOfLoggedInUser(ActionRight.Administer);

		if (theSignupRequest == null) {
			throw new IllegalStateException();
		}

		theSignupRequest.changeToRefusedStatus();

		final SignupRequestRepository repo = PersistenceContext.repositories().
			signupRequests();
		return repo.save(theSignupRequest);
	}

	/**
	 *
	 * @return
	 */
	public Iterable<SignupRequest> listPendingSignupRequests() {
		final SignupRequestRepository repo = PersistenceContext.repositories().
			signupRequests();
		return repo.listPendingSignupRequests();
	}
}
