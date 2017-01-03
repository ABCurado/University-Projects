/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.util.DateTime;

/**
 *
 * @author Fernando
 */
public class DeactivateUserController implements Controller {

    // TODO this method should return only the list of active users
    public Iterable<SystemUser> listUsers() {
        ensurePermissionOfLoggedInUser(ActionRight.Administer);

        // TODO a controller should not call another controller. we should
        // refactor this code to a common service
        final ListUsersController listUsersController = new ListUsersController();
        return listUsersController.listUsers();
    }

    public SystemUser deactivateUser(SystemUser user) {
        ensurePermissionOfLoggedInUser(ActionRight.Administer);

        user.deactivate(DateTime.now());

        final UserRepository userRepository = PersistenceContext.repositories().users();
        user = userRepository.save(user);
        return user;
    }
}
