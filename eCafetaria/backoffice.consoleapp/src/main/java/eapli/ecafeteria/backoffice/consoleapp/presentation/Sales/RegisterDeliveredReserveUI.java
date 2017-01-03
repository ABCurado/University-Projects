package eapli.ecafeteria.backoffice.consoleapp.presentation.Sales;

import eapli.ecafeteria.application.RegisterDeliveredReserveController;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;

/**
 *
 * @author JoséBarros(1140364)
 */
public class RegisterDeliveredReserveUI extends AbstractUI {

    private final RegisterDeliveredReserveController theController = new RegisterDeliveredReserveController();

    @Override
    protected boolean doShow() {

        CafeteriaUser user = null;
        while (user == null) {
            final String id = Console.readLine("Mecanographic number: ");
            user = this.theController.getCafeteriaUserAccount(id);

            if (user == null) {
                System.out.println("No account was found with this number.\n");
            }
        }
        System.out.println("Cafeteria User accepted.");

        final Iterable<Reserve> reserves = theController.listReservesByUser(user);

        final SelectWidget<Reserve> selector = new SelectWidget<>(reserves, new ReservePrinter());
        selector.show();

        final Reserve theReserve = selector.selectedElement();
        
        if (theReserve != null) {
            try {
                theController.updateReserveStateToDelivered(theReserve);
                System.out.println("Reserve state change completed successfully");
            } catch (DataConcurrencyException ex) {
                System.out.println("Error in change reserve state");
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Register Delivered Reserve";
    }

}
