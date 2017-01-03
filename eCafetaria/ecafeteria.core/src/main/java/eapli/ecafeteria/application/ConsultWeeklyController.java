package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.application.Controller;
import eapli.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConsultWeeklyController implements Controller {

	public Iterable<Meal> mealsCurrentWeekDay(int dayWeek) throws Exception {
		return new ListMealService().mealsCurrentWeekDay(dayWeek);
	}

	public Iterable<Reserve> reservesCurrentWeekDay(int dayWeek) throws Exception {
		Calendar date = DateTime.dateCurrentWeekDay(dayWeek);
		ConsultReservesController controller = new ConsultReservesController();
		List<Reserve> reserves = new ArrayList();
		for (Reserve reserve : controller.getReservesBetweenDates(date, date)) {
			if (DateTime.isSameDate(reserve.meal().date(), date)) {
				reserves.add(reserve);
			}
		}
		return reserves;
	}

}
