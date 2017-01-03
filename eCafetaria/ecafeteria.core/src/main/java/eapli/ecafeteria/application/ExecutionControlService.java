package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.cafeteria.Kitchen.ExecutionControl;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.ExecutionControlRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.List;

final class ExecutionControlService {

    private ExecutionControlService() {
    }

    static public ExecutionControl getExecutionControlFromDate(Meal meal,
            Calendar date) {
        ExecutionControlRepository repository = PersistenceContext.
                repositories().executionControl();

        List<ExecutionControl> list = repository.filterByDate(meal, date);

        if (list.isEmpty()) {
            return new ExecutionControl(meal);
        }

        return list.get(0);
    }

}
