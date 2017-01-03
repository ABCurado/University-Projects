package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.Kitchen.ExecutionControl;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.ExecutionControlRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class InMemoryExecutionControlRepository extends InMemoryRepository<ExecutionControl, Long> implements ExecutionControlRepository {

	private long nextId = 1;

	@Override
	protected Long newPK(ExecutionControl entity) {
		return ++nextId;
	}

	@Override
	public List<ExecutionControl> filterByDate(Meal meal, Calendar date) {
		List<ExecutionControl> list = new ArrayList<>();

		for (Map.Entry<Long, ExecutionControl> entry : this.repository.
			entrySet()) {
			if (entry.getValue().is(meal) && entry.getValue().day().equals(date)) {
				list.add(entry.getValue());
			}
		}

		return list;
	}

	@Override
	public ExecutionControl executionControlByMeal(Meal meal) {
		for (Map.Entry<Long, ExecutionControl> entry : this.repository.
			entrySet()) {
			Meal executionMeal = entry.getValue().id();
			if (executionMeal.sameAs(meal)) {
				return entry.getValue();
			}
		}
		return null;
	}

	@Override
	public void delete(ExecutionControl entity) {
		super.delete(entity);
	}

}
