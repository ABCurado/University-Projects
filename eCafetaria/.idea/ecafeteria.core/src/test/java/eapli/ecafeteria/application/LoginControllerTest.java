package eapli.ecafeteria.application;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Test;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.UnableToAuthenticateException;

/**
 * Created by Nuno Bettencourt [NMB] on 24/03/16.
 */
public class LoginControllerTest {
    @After
    public void tearDown() throws Exception {
        System.getProperties().remove("persistence.repositoryFactory");
    }

    @Test
    public void ensureUserIsAllowed() throws Exception {
        System.getProperties().setProperty("persistence.repositoryFactory",
                "eapli.ecafeteria.persistence.inmemory.InMemoryRepositoryFactory");

        final String userName = "admin";
        final String password = "admin";
        final String firstName = "John";
        final String lastName = "Doe";
        final String email = "john.doe@emai.l.com";

        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.Admin);

        final AddUserController userController = new AddUserController();
        userController.addUser(userName, password, firstName, lastName, email, roles);

        final LoginController controller = new LoginController();
        controller.login(userName, password);
        assertTrue(true);
    }

    @Test(expected = UnableToAuthenticateException.class)
    public void ensureInvalidUser() throws Exception {
        System.getProperties().setProperty("persistence.repositoryFactory",
                "eapli.ecafeteria.persistence.inmemory.InMemoryRepositoryFactory");

        final String userName = "admin";
        final String password = "admin";
        final String firstName = "John";
        final String lastName = "Doe";
        final String email = "john.doe@emai.l.com";

        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.Admin);

        final AddUserController userController = new AddUserController();
        userController.addUser(userName, password, firstName, lastName, email, roles);

        final String inputUserName = "admin1";
        final String inputPassword = "admin1";

        final LoginController controller = new LoginController();
        controller.login(inputUserName, inputPassword);
        assertTrue(true);
    }

    @Test(expected = UnableToAuthenticateException.class)
    public void ensureInvalidPassword() throws Exception {
        System.getProperties().setProperty("persistence.repositoryFactory",
                "eapli.ecafeteria.persistence.inmemory.InMemoryRepositoryFactory");

        final String userName = "admin";
        final String password = "admin";
        final String firstName = "John";
        final String lastName = "Doe";
        final String email = "john.doe@emai.l.com";

        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.Admin);

        final AddUserController userController = new AddUserController();
        userController.addUser(userName, password, firstName, lastName, email, roles);

        final String inputUserName = "admin";
        final String inputPassword = "admin1";

        final LoginController controller = new LoginController();
        controller.login(inputUserName, inputPassword);
        assertTrue(true);
    }
    /*
     * @Test(expected = InvalidUserException.class) public void
     * ensureInvalidAccessWithEmptyMemoryDatabase() throws Exception {
     * System.getProperties().remove("persistence.repositoryFactory");
     * System.getProperties().setProperty("persistence.repositoryFactory",
     * "eapli.ecafeteria.persistence.inmemory.InMemoryRepositoryFactory");
     *
     * final String inputUserName = "admin"; final String inputPassword =
     * "admin";
     *
     * final LoginController controller = new LoginController();
     * controller.login(inputUserName, inputPassword); assertTrue(true); }
     */
}