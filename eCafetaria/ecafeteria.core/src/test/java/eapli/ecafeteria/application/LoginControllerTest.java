package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.Session;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UnableToAuthenticateException;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Nuno Bettencourt [NMB] on 24/03/16.
 */
public class LoginControllerTest {

	@BeforeClass
	public static void setUp() throws UnableToAuthenticateException {
		final Set<RoleType> roles = new HashSet<RoleType>();
		roles.add(RoleType.Admin);
		final Session adminSession = new Session(
			new SystemUser("admin", "admin", "joe", "doe", "joe@email.org", roles));
		AppSettings.instance().setSession(adminSession);
	}

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
		//userController.addUser(userName, password, firstName, lastName, email, roles);

		final LoginController controller = new LoginController();
		//controller.login(userName, password);
		//assertTrue(true);
	}

	@Test //@Test(expected = UnableToAuthenticateException.class)
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
		//userController.addUser(userName, password, firstName, lastName, email, roles);

		final String inputUserName = "admin1";
		final String inputPassword = "admin1";

		final LoginController controller = new LoginController();
		//controller.login(inputUserName, inputPassword);
		//assertTrue(true);
	}

	@Test //@Test(expected = UnableToAuthenticateException.class)
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
		//userController.addUser(userName, password, firstName, lastName, email, roles);

		final String inputUserName = "admin";
		final String inputPassword = "admin1";

		final LoginController controller = new LoginController();
		//controller.login(inputUserName, inputPassword);
		//assertTrue(true);
	}

}
