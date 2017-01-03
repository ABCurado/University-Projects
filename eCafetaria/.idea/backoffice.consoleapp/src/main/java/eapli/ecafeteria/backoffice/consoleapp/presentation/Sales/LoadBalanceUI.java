package eapli.ecafeteria.backoffice.consoleapp.presentation.Sales;

import eapli.ecafeteria.application.LoadAccountController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;

/**
 *
 * @author AB-<1140280>
 */
public class LoadBalanceUI extends AbstractUI {

    private final LoadAccountController theController = new LoadAccountController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        boolean userConfirmation = false;

        while (userConfirmation == false) {
            final String id = Console.readLine("Mecanographic number: ");
            userConfirmation = this.theController.getCafeteriaUserAccount(id);
            
            if (userConfirmation == false ) {
                System.out.println("No account was found with this number.\n");
            }
            
        }
        System.out.println("Cafeteria User accepted.");

        final String amount = Console.readLine("Amount: ");

        try {
            this.theController.loadAccount(amount);
            System.out.println("Load Balance successful.");
        } catch (Exception e) {
            System.out.println("There was a problem loading the account." + e);
        }

        return true;
    }

    @Override
    public String headline() {
        return "Load Balance";
    }

}
