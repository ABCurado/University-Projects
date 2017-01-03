/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.ecafeteria.persistence.ReserveRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import eapli.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author ruben
 */
class InMemoryReserveRepository extends InMemoryRepository<Reserve, Long> implements ReserveRepository {

	long nextID = 1;

	@Override
	protected Long newPK(Reserve entity) {
		return ++nextID;
	}

	@Override
	public Iterable<Meal> findByUser(CafeteriaUser user) {
		Iterable<Reserve> reserves = repository.values();
		List<Meal> meals = new ArrayList();

		for (Reserve reserve : reserves) {
			if (reserve.user().equals(user)) {
				meals.add(reserve.meal());
			}
		}
		return meals;
	}

	@Override
	public Iterable<Reserve> FindNextReverves(Calendar dateInitial,
											  Calendar dateFinal,
											  CafeteriaUser user) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Iterable<Reserve> findNotDelieveredReservesByUser(CafeteriaUser user) {

		Calendar now = Calendar.getInstance(Locale.ENGLISH);

		Iterable<Reserve> list = this.all();

		Iterator<Reserve> iterator = list.iterator();

		while (iterator.hasNext()) {
			Reserve next = iterator.next();

			if (!next.user().equals(user) && next.meal().date().after(now)) {
				iterator.remove();
			}
		}

		return list;
	}

	@Override
	public List<Reserve> findDeliveredReserves(Shift shift) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public double calculateCaloricConsumptionByPeriod(Calendar startDate,
													  Calendar endDate,
													  CafeteriaUser theUser) {
		double res = 0;

		for (Reserve r : all()) {
			if (r.isDelivered() && DateTime.
				isBetweenDates(startDate, endDate, r.meal().date()) && r.user().
				equals(theUser)) {
				res += r.meal().dish().calories();
			}
		}

		return res;
	}

	@Override
	public Iterable<Reserve> findNotDelieveredReservesByUserAndShift(
		CafeteriaUser user,
		Shift shift) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Reserve> findNotDeliveredReseerves(Shift shift) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
