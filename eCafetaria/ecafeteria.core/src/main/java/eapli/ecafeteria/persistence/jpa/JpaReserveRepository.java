/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.ecafeteria.domain.meals.ReserveCanceledState;
import eapli.ecafeteria.domain.meals.ReserveDeliveredState;
import eapli.ecafeteria.domain.meals.ReserveNotDeliveredState;
import eapli.ecafeteria.domain.meals.ReserveState;
import eapli.ecafeteria.persistence.ReserveRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import eapli.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author ruben
 */
public class JpaReserveRepository extends JpaRepository<Reserve, Long> implements ReserveRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public Iterable<Meal> findByUser(CafeteriaUser user) {

		ReserveState stateD = new ReserveNotDeliveredState();
		ReserveState stateC = new ReserveCanceledState();

		final Query q = entityManager().
			createQuery("select r.meal from Reserve r where r.user=:user and r.reserveState !=:stateD "
				+ "and r.reserveState != :stateC ", Reserve.class);
		q.setParameter("user", user);
		q.setParameter("stateD", stateD);
		q.setParameter("stateC", stateC);

		return q.getResultList();
	}

	/**
	 * Method that find reserves in next n days
	 *
	 * @param dateInitial variable representing the current date.
	 * @param dateFinal variable that represents the end date.
	 * @param user CafeteriaUser
	 * @return list of reserves.
	 */
	@Override
	public Iterable<Reserve> FindNextReverves(Calendar dateInitial,
											  Calendar dateFinal,
											  CafeteriaUser user) {

		ReserveState state = new ReserveNotDeliveredState();

		Query q = entityManager().
			createQuery("SELECT r FROM Reserve r WHERE r.user=:user AND r.reserveState =:state ", Reserve.class);

		q.setParameter("user", user);
		q.setParameter("state", state);

		List<Reserve> newList = new ArrayList();
		for (Reserve rev : (List<Reserve>) q.getResultList()) {
			if (DateTime.isBetweenDates(dateInitial, dateFinal, rev.meal().
										date())) {
				newList.add(rev);
			}
		}
		return newList;
	}

	@Override
	public Iterable<Reserve> findNotDelieveredReservesByUser(CafeteriaUser user) {

		ReserveState state = new ReserveNotDeliveredState();

		final Query q = entityManager().
			createQuery("select r from Reserve r where r.user=:user and r.reserveState = :state", Reserve.class);
		q.setParameter("user", user);
		q.setParameter("state", state);

		return q.getResultList();
	}

	@Override
	public List<Reserve> findDeliveredReserves(Shift shift) {

		MealType mealType = shift.mealType();
		ReserveState state = new ReserveDeliveredState(shift);

		final Query q = entityManager().
			createQuery("select r from Reserve r where r.meal.mealInformation.type=:mealType "
				+ "and r.reserveState = :state ", Reserve.class);
		q.setParameter("mealType", mealType);
		q.setParameter("state", state);

		return q.getResultList();
	}

	@Override
	public double calculateCaloricConsumptionByPeriod(Calendar startDate,
													  Calendar endDate,
													  CafeteriaUser theUser) {
		final Query q = entityManager().
			createQuery("SELECT m.mealInformation.dish.nutricionalInfo.calories FROM Meal m, Reserve r WHERE r.user = :theUser "
				+ "AND m.mealInformation.day >= :startDay AND m.mealInformation.day <= :endDay "
				+ "AND m.mealInformation.month >= :startMonth AND m.mealInformation.month <= :endMonth "
				+ "AND m.mealInformation.year >= :startYear AND m.mealInformation.year <= :endYear AND r.reserveState !=:stateD "
				+ "and r.reserveState != :stateC "
				+ "GROUP BY m.id");

		ReserveState stateD = new ReserveNotDeliveredState();
		ReserveState stateC = new ReserveCanceledState();

		q.setParameter("startDay", DateTime.day(startDate));
		q.setParameter("endDay", DateTime.day(endDate));
		q.setParameter("startMonth", DateTime.month(startDate));
		q.setParameter("endMonth", DateTime.month(endDate));
		q.setParameter("startYear", DateTime.year(startDate));
		q.setParameter("endYear", DateTime.year(endDate));
		q.setParameter("theUser", theUser);
		q.setParameter("stateD", stateD);
		q.setParameter("stateC", stateC);

		double res = 0;

		for (Object d : q.getResultList()) {
			res += (double) d;
		}

		return res;

	}

	@Override
	public Iterable<Reserve> findNotDelieveredReservesByUserAndShift(
		CafeteriaUser user,
		Shift shift) {
		Calendar date = shift.date();
		MealType mealType = shift.mealType();

		ReserveState state = new ReserveNotDeliveredState();

		final Query q = entityManager().
			createQuery("select r from Reserve r where r.user=:user and r.reserveState = :state and r.meal.mealInformation.type=:type and r.meal.mealInformation.day=:day and r.meal.mealInformation.month=:month and r.meal.mealInformation.year=:year", Reserve.class);
		q.setParameter("user", user);
		q.setParameter("state", state);
		q.setParameter("type", mealType);
		q.setParameter("day", DateTime.day(date));
		q.setParameter("month", DateTime.month(date));
		q.setParameter("year", DateTime.year(date));

		return q.getResultList();

	}

	@Override
	public List<Reserve> findNotDeliveredReseerves(Shift shift) {
		MealType mealType = shift.mealType();
		Calendar date = shift.date();
		ReserveState state = new ReserveNotDeliveredState();

		final Query q = entityManager().
			createQuery("select r from Reserve r where r.meal.mealInformation.type=:mealType "
				+ "and r.reserveState = :state and r.meal.mealInformation.day=:day "
				+ "and r.meal.mealInformation.month=:month "
				+ "and r.meal.mealInformation.year=:year", Reserve.class);
		q.setParameter("mealType", mealType);
		q.setParameter("state", state);
		q.setParameter("day", DateTime.day(date));
		q.setParameter("month", DateTime.month(date));
		q.setParameter("year", DateTime.year(date));

		return q.getResultList();
	}

}
