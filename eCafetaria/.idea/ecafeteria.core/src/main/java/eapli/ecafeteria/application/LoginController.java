package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.AuthenticationService;
import eapli.ecafeteria.domain.authz.Password;
import eapli.ecafeteria.domain.authz.Session;
import eapli.ecafeteria.domain.authz.UnableToAuthenticateException;
import eapli.ecafeteria.domain.authz.Username;
import eapli.framework.application.Controller;

/**
 * Created by nuno on 21/03/16.
 */
public class LoginController implements Controller {
    /**
     * This method allows a user to perform login and creates the session.
     *
     * @param userName
     * @param password
     */
    public void login(String userName, String password) throws UnableToAuthenticateException {
        final AuthenticationService authenticationService = new AuthenticationService();
        final Session newSession = authenticationService.authenticate(new Username(userName), new Password(password));
        AppSettings.instance().setSession(newSession);
    }
}
