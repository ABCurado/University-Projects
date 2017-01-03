package eapli.ecafeteria.backoffice.consoleapp.presentation.Sales;

import eapli.ecafeteria.application.LoadAccountController;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

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

        // Small cheating to get it working, needs fixing
        CafeteriaUser user = null;
        while (user == null) {
            final String id = Console.readLine("Mecanographic number: ");

            try {
                user = this.theController.getCafeteriaUser(id);
            } catch (NoSuchElementException e) {
                System.out.println("No account was found with this number.\n");
            }
        }
        System.out.println("Cafeteria User accepted.");

        String amount = "0";

        while (BigDecimal.valueOf(Double.parseDouble(amount)).compareTo(BigDecimal.ZERO) < 1) {
            amount = Console.readLine("Amount: ");

            if (BigDecimal.valueOf(Double.parseDouble(amount)).compareTo(BigDecimal.ZERO) < 1) {
                System.out.println("Invalid amount of money.");
            }
        }

        try {
            this.theController.loadAccount(user, BigDecimal.valueOf(Double.parseDouble(amount)));
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