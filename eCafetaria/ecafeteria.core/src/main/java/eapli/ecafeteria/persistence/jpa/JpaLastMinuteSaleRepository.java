/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.meals.LastMinuteMealDeliveredState;
import eapli.ecafeteria.domain.meals.LastMinuteMealState;
import eapli.ecafeteria.domain.meals.LastMinuteSale;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.LastMinuteSaleRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author <AB-1140280>
 */
public class JpaLastMinuteSaleRepository extends JpaRepository<LastMinuteSale, Long>
	implements LastMinuteSaleRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public List<LastMinuteSale> getLastMinuteSaleByShift(Shift shift) {

		MealType mealType = shift.mealType();
		Calendar date = shift.date();
		LastMinuteMealState state = new LastMinuteMealDeliveredState(shift);

		final Query q = entityManager().
			createQuery("select l from LastMinuteSale l where l.meal.mealInformation.type=:mealType "
				+ "and l.lastMinuteMealState = :state ", LastMinuteSale.class);
		q.setParameter("mealType", mealType);
		q.setParameter("state", state);

		return q.getResultList();
	}
}
