package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.util.DateTime;

public class DeactivateUserController implements Controller {

    /**
     * Returns the active system users.
     * 
     * @return List of System Users that are active.
     */
    public Iterable<SystemUser> listUsers() {
        ensurePermissionOfLoggedInUser(ActionRight.Administer);
        
        return new UserService().activeSystemUsers();
    }
    
    /**
     * Deactivates a System User.
     * 
     * @param user System User.
     * @return The deactivated user. 
     * @throws DataConcurrencyException 
     */
    public SystemUser deactivateUser(SystemUser user) throws DataConcurrencyException {
        ensurePermissionOfLoggedInUser(ActionRight.Administer);

        user.deactivate(DateTime.now());

        final UserRepository userRepository = PersistenceContext.repositories().users();
        return userRepository.save(user);
    }
}
