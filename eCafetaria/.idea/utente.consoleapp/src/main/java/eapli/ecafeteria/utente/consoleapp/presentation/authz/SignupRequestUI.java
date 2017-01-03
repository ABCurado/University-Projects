package eapli.ecafeteria.utente.consoleapp.presentation.authz;

import eapli.ecafeteria.application.SignupRequestController;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupRequestUI extends AbstractUI {

    private final SignupRequestController theController = new SignupRequestController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final UserDataWidget userData = new UserDataWidget();

        userData.show();

        final SelectWidget<OrganicUnit> selector = new SelectWidget<>(this.theController.getAllOrganicUnit(),
                new OrganicUnitUIVisitor());
        selector.show();

        final OrganicUnit organicUnit = selector.selectedElement();

        final String mecanographicNumber = Console.readLine("Mecanographic Number");

        try {
            this.theController.addSignupRequest(userData.username(), userData.password(), userData.firstName(),
                    userData.lastName(), userData.email(), organicUnit, mecanographicNumber);
        } catch (final DataIntegrityViolationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String headline() {
        return "Sign Up";
    }
}
