package eapli.ecafeteria.filesystem.ical;

import biweekly.component.VEvent;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.filesystem.impl.iCalEncoder;

/**
 *
 * @author nervousdev
 */
public class iCalReservationsEncoder extends iCalEncoder<Reserve> {

	public iCalReservationsEncoder() {
		super();
	}

	@Override
	public void writeElements(Iterable<Reserve> listElements) {
		VEvent reserve;
		String description;
		for (Reserve r : listElements) {
			description = r.meal().dish().name() + " " + r.meal().mealType().
				designation();
			reserve = new VEvent();
			reserve.setDateStart(r.meal().date().getTime(), false);
			reserve.setDateEnd(r.meal().date().getTime(), false);
			reserve.setDescription(description);

			this.output.addEvent(reserve);
		}
	}

}
