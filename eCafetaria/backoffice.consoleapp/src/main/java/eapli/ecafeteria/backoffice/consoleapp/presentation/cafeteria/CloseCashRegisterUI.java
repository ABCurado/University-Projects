package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.application.CloseCashRegisterController;
import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.presentation.console.AbstractUI;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Diogo Leite<1140260@isep.ipp.pt>
 */
public class CloseCashRegisterUI extends AbstractUI {

	private CloseCashRegisterController controller;

	@Override
	protected boolean doShow() {

		controller = new CloseCashRegisterController();

		try {
			final Shift shift = controller.getShift();
			int lastMinuteMealsSold = controller.lastMinuteMealsSold(shift);
			int reservesDelivered = controller.reservesDelivered(shift);
			List<Reserve> reservesNotDelivered = controller.
				reservesNotDelivered(shift);

			if (controller.close(shift)) {

				System.out.println("Cash Register was sucessfully closed\n");
				System.out.
					println("Last Minute Meals Delivered: " + lastMinuteMealsSold);
				System.out.println("Reserves Delivered: " + reservesDelivered);

				if (reservesNotDelivered.size() > 0) {
					System.out.println("Reserves Not Delivered:\n");
					for (Reserve r : reservesNotDelivered) {
						System.out.println(r.user().mecanographicNumber().
							toString() + "\n" + r.meal().dish().name() + "\n");
					}
				}

				return true;
			} else {
				System.out.println("Cash Register was already closed");
				return false;
			}
		} catch (NoResultException e) {
			System.out.println("Cash Register was already closed");
			return false;
		}
	}

	@Override
	public String headline() {
		return "Close CashRegister";
	}

}
