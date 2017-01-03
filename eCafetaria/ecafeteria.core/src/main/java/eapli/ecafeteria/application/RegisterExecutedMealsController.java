package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.cafeteria.Kitchen.ExecutionControl;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.Observer;

public class RegisterExecutedMealsController implements Controller {

    /**
     * Gets the available meals for the current day of the current active menu.
     *
     * @return List of meals that belong to the current day of the current
     * active menu.
     */
    public Iterable<Meal> getMealsOfActiveMenu() {
        return PersistenceContext.repositories().meals().
                mealsOfPublishedMenuFromDate(DateTime.now());
    }
    
    /**
     * Returns the execution control of a given meal.
     * 
     * @param meal Meal
     * @return ExecutionControl of the given meal.
     */
    public ExecutionControl quantityOfExecuted(Meal meal) {
        return PersistenceContext.repositories().executionControl().
                executionControlByMeal(meal);
    }
    
    /**
     * Executes a number of given meal to its associated date.
     *
     * @param date Date of the execution of the meal.
     * @param meal Meal that was executed.
     * @param quantity Number of meals to execute.
     * @param ui Observer.
     *
     * @return Returns the ExecutionControl concrete instance.
     * @throws eapli.framework.persistence.DataConcurrencyException
     */
    public ExecutionControl addExecutedQuantitiesToMeal(Calendar date, Meal meal,
            int quantity,
            Observer ui) throws DataConcurrencyException {
        ExecutionControl execution = ExecutionControlService.
                getExecutionControlFromDate(meal, date);
        execution.addObserver(ui);
        execution.addExecutedMeals(quantity);

        return PersistenceContext.repositories().executionControl().
                save(execution);
    }
}
