/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import java.util.Calendar;
import javax.persistence.Query;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class JpaShiftRepository extends JpaRepository<Shift, Long> implements ShiftRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public Shift findByOpenedCashRegister(CashRegister cashRegister) {
		final Query q = entityManager().
			createQuery("select s from Shift s where s.cashRegister=:cashRegister"
				+ " and s.state like 'opened'", Shift.class);
		q.setParameter("cashRegister", cashRegister);

		return (Shift) q.getSingleResult();
	}

	@Override
	public Shift openWithMealType(MealType mealType, Calendar date) {
		final Query q = entityManager().
			createQuery("select s from Shift s where s.mealType=:mealType "
				+ "and s.date = :date", Shift.class);
		q.setParameter("mealType", mealType);
		q.setParameter("date", date);

		return (Shift) q.getSingleResult();
	}

	@Override
	public Shift isOpenedByUser(SystemUser currentUser) {
		final Query q = entityManager().
			createQuery("select s from Shift s where s.user=:currentUser"
				+ " and s.state like 'opened'", Shift.class);
		q.setParameter("currentUser", currentUser);

		return (Shift) q.getSingleResult();
	}

	@Override
	public boolean cantOpen(MealType mt, Calendar date) {
		final Query q = entityManager().
			createQuery("select s from Shift s where s.mealType=:mealType"
				+ " and s.state like 'finished' "
				+ " and s.date = :date", Shift.class);
		q.setParameter("mealType", mt);
		q.setParameter("date", date);

		return q.getResultList().size() > 0;
	}

}
