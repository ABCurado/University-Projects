package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.Kitchen.ExecutionControl;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.repositories.Repository;
import java.util.Calendar;
import java.util.List;

public interface ExecutionControlRepository extends Repository<ExecutionControl, Long> {

	public List<ExecutionControl> filterByDate(Meal meal, Calendar date);

	public ExecutionControl executionControlByMeal(Meal meal);

}
