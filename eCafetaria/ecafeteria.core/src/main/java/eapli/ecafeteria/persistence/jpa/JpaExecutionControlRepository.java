package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.Kitchen.ExecutionControl;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.ExecutionControlRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import eapli.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;

public class JpaExecutionControlRepository extends JpaRepository<ExecutionControl, Long> implements ExecutionControlRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public List<ExecutionControl> filterByDate(Meal meal, Calendar date) {
		final Query q = entityManager().
			createQuery("SELECT e FROM ExecutionControl e WHERE e.meal=:meal", ExecutionControl.class);
		q.setParameter("meal", meal);

		List<ExecutionControl> newList = new ArrayList();
		for (ExecutionControl exe : (List<ExecutionControl>) q.getResultList()) {
			if (DateTime.isSameDate(exe.day(), date)) {
				newList.add(exe);
			}
		}
		return newList;
	}

	@Override
	public ExecutionControl executionControlByMeal(Meal meal) {
		final Query q = entityManager().
			createQuery("SELECT e FROM ExecutionControl e WHERE e.meal=:meal", ExecutionControl.class);
		q.setParameter("meal", meal);
		return (ExecutionControl) q.getSingleResult();
	}

}
