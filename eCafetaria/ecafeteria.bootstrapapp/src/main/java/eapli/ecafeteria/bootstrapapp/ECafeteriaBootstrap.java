package eapli.ecafeteria.bootstrapapp;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.Session;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.actions.Action;
import java.util.HashSet;
import java.util.Set;

/**
 * eCafeteria Bootstrapping data app
 *
 */
public class ECafeteriaBootstrap implements Action {

	public static void main(String[] args) {
		System.out.println("Bootstrapping eCafeteria 2016(c) data");

		new ECafeteriaBootstrap().execute();
	}

	@Override
	public boolean execute() {
		// declare bootstrap actions
		final Action[] actions = {
			new OrganicUnitBootsrap(),
			new UsersBootstrap(),
			new DishTypesBootstrap(),
			new MealTypeBootstrap(),
			new DishBootstrap(),
			new CashRegisterBootstrap(),
			new MenuBootstrap(),
			new KitchenAlertBootstrap()
		};

		// authenticate a super user to be able to register new users, ...
		// in this case we will inject the session but we shouldn't do this
		// AuthenticationService authz = new AuthenticationService();
		// Session adminSession = authz.authenticate(new Username("admin"), new
		// Password("admin"));
		final Set<RoleType> roles = new HashSet<RoleType>();
		roles.add(RoleType.Admin);
		roles.add(RoleType.Cashier);
		roles.add(RoleType.Consult);
		roles.add(RoleType.KitchenManager);
		roles.add(RoleType.MenuManager);
		roles.add(RoleType.User);
		final Session adminSession = new Session(
			new SystemUser("poweruser", "poweruser", "poweruser", "poweruser", "joe@email.org", roles));
		AppSettings.instance().setSession(adminSession);

		// execute all bootstrapping returning true if any of the bootstraping
		// actions returns true
		boolean ret = false;
		for (final Action boot : actions) {
			ret |= boot.execute();
		}
		return ret;
	}
}
