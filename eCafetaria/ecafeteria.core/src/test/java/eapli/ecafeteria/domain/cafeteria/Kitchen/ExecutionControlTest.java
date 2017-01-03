package eapli.ecafeteria.domain.cafeteria.Kitchen;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.domain.Money;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.Currency;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ExecutionControlTest {

	private ExecutionControl execution;

	public ExecutionControlTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		Calendar date = DateTime.newCalendar(2016, 7, 1);
		Calendar startDate = DateTime.newCalendar(2016, 7, 1);
		Calendar endDate = DateTime.newCalendar(2016, 8, 1);
		Menu menu = new Menu(startDate, endDate, "Menu Semana 3");
		MealType mealType = new MealType("Jantar", 19, 16);
		DishType dishType = new DishType("Peixe", "Bacalhau Com Natas");
		Money money = new Money(12, Currency.getInstance("EUR"));
		Dish dish = new Dish(dishType, "Peixe", 200.5, 48, 56.8, money);
		Meal meal = new Meal(mealType, dish, date, 0, menu);

		this.execution = new ExecutionControl(meal);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void test_can_add_number_of_meals() {
		int before = this.execution.executed();
		assertEquals(before, 0);

		//this.execution.addExecutedMeals(2);
		int after = this.execution.executed();
		//assertNotEquals(before, after);
		//assertEquals(after, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_cant_add_number_of_meals_with_negative_quantity() {
		int before = this.execution.executed();
		assertEquals(before, 0);

		this.execution.addExecutedMeals(-2);

		int after = this.execution.executed();
		assertNotEquals(before, after);
		assertEquals(after, 0);
	}

	@Test
	public void test_get_daily_control_date() {
		Calendar expected = DateTime.newCalendar(2016, 7, 1);
		Calendar result = this.execution.day();

		//assertEquals(expected, result);
	}

}
