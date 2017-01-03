/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.persistence.repositories.Repository;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author ruben
 */
public interface ReserveRepository extends Repository<Reserve, Long> {

	public Iterable<Meal> findByUser(CafeteriaUser user);

	public Iterable<Reserve> FindNextReverves(Calendar dateInitial,
											  Calendar dateFinal,
											  CafeteriaUser user);

	public Iterable<Reserve> findNotDelieveredReservesByUser(CafeteriaUser user);

	public Iterable<Reserve> findNotDelieveredReservesByUserAndShift(
		CafeteriaUser user, Shift shift);

	public List<Reserve> findDeliveredReserves(Shift shift);

	public List<Reserve> findNotDeliveredReseerves(Shift shift);

	public double calculateCaloricConsumptionByPeriod(Calendar startDate,
													  Calendar endDate,
													  CafeteriaUser theUser);

}
