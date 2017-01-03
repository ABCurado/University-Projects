package eapli.ecafeteria.domain.cafeteria.Kitchen;

import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * TODO javadoc
 *
 * @author João Martins
 */
final class KitchenAlertService {

	private String name;
	private int ratio;

	private KitchenAlertService() {
	}

	/**
	 * Method for know what alert is going to return
	 *
	 * @param executionControl
	 * @return alert
	 */
	static public KitchenAlert alert(ExecutionControl executionControl) {
		Double executed = new Double(executionControl.executed());
		Double reserved = new Double(executionControl.id().
			numberDishesReserverd());
		List<KitchenAlert> list = new ArrayList();
		for (KitchenAlert alert : PersistenceContext.repositories().
			kitchenAlerts().all()) {
			list.add(alert);
		}
		Collections.sort(list, new Comparator<KitchenAlert>() {
			@Override
			public int compare(KitchenAlert kA1, KitchenAlert kA2) {
				return kA2.ratio().compareTo(kA1.ratio());
			}
		});
		for (KitchenAlert alert : list) {
			if (executed > reserved * alert.ratio()) {
				return alert;
			}
		}
		return null;
	}

}
