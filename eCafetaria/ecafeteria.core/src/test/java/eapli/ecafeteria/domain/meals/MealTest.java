package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.framework.domain.Money;
import java.util.Calendar;
import java.util.Currency;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class MealTest {

	Menu menu;
	Dish dish;
	DishType dishType;
	Money money;
	Meal meal;
	MealType mealType;
	Calendar startDate;

	public MealTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		startDate = Calendar.getInstance();
		startDate.add(Calendar.DAY_OF_YEAR, +1);
		menu = new Menu(startDate, startDate, "Menu Semana 6");
		mealType = new MealType("Jantar", 16, 16);
		dishType = new DishType("Peixe", "Bacalhau Com Natas");
		money = new Money(12, Currency.getInstance("EUR"));
		dish = new Dish(dishType, "Peixe", 200.5, 48, 56.8, money);
		meal = new Meal(mealType, dish, startDate, 1, menu);

	}

	@After
	public void tearDown() {
	}

//    @Test(expected = IllegalArgumentException.class)
//    public void testMealTypeMustNotBeNull() {
//        System.out.println("must have not null MealType");
//        //new Meal(null);
//    }
	/**
	 * Test of setDate method, of class Meal.
	 */
//    @Test(expected = IllegalArgumentException.class)
//    public void testSetDateToPastDateMustFail() {
//        System.out.println("setDate - Past Date must Fail");
//        Calendar newDate = Calendar.getInstance();
//        newDate.add(Calendar.DAY_OF_MONTH, -1);
//        Meal instance = new Meal(new MealType(),dish,newDate,menu);
//        instance.setDate(newDate);
//    }
//    testSetDate(eapli.ecafeteria.domain.meals.MealTest)
//    @Test
//    public void testSetDate() {
//        System.out.println("setDate");
//        Calendar newDate = Calendar.getInstance();
//        newDate.add(Calendar.DAY_OF_MONTH, +1);
//        Meal instance = new Meal(new MealType());
//        instance.setDate(newDate);
//        final Calendar expResult = newDate;
//        final Calendar result = instance.date();
//        assertEquals(expResult, result);
//    }
	/**
	 * Test of setDish method, of class Meal.
	 */
//      testSetDishMustNotAcceptNull(eapli.ecafeteria.domain.meals.MealTest)
//    @Test(expected = IllegalArgumentException.class)
//    public void testSetDishMustNotAcceptNull() {
//        System.out.println("setDish must not accept null");
//        Dish newDish = null;
//        Meal instance = new Meal();
//        instance.setDish(newDish);
//    }
//    @Test
//    public void testSetDish() {
//        System.out.println("setDish");
//        Meal instance = new Meal();
//        instance.setDish(dish);
//        final Dish expResult = dish;
//        final Dish result = instance.dish();
//        assertEquals(expResult, result);
//    }
	/**
	 * Test of mealType method, of class Meal.
	 */
	// instance.mealType(); non existent
	@Test
	public void testMealType() {
		System.out.println("mealType");
		MealType expResult = mealType;
		MealType result = meal.mealType();

		assertEquals(expResult, result);
	}

	/**
	 * Test of date method, of class Meal.
	 */
	//testDate(eapli.ecafeteria.domain.meals.MealTest): expected:<java.util.GregorianCalendar[time=1461609626290,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Europe/Lisbon",offset=0,dstSavings=3600000,useDaylight=true,transitions=222,lastRule=java.util.SimpleTimeZone[id=Europe/Lisbon,offset=0,dstSavings=3600000,useDaylight=true,startYear=0,startMode=2,startMonth=2,startDay=-1,startDayOfWeek=1,startTime=3600000,startTimeMode=2,endMode=2,endMonth=9,endDay=-1,endDayOfWeek=1,endTime=3600000,endTimeMode=2]],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2016,MONTH=3,WEEK_OF_YEAR=18,WEEK_OF_MONTH=5,DAY_OF_MONTH=25,DAY_OF_YEAR=116,DAY_OF_WEEK=2,DAY_OF_WEEK_IN_MONTH=4,AM_PM=1,HOUR=7,HOUR_OF_DAY=19,MINUTE=40,SECOND=26,MILLISECOND=290,ZONE_OFFSET=0,DST_OFFSET=3600000]> but was:<java.util.GregorianCalendar[time=1461609626289,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Europe/Lisbon",offset=0,dstSavings=3600000,useDaylight=true,transitions=222,lastRule=java.util.SimpleTimeZone[id=Europe/Lisbon,offset=0,dstSavings=3600000,useDaylight=true,startYear=0,startMode=2,startMonth=2,startDay=-1,startDayOfWeek=1,startTime=3600000,startTimeMode=2,endMode=2,endMonth=9,endDay=-1,endDayOfWeek=1,endTime=3600000,endTimeMode=2]],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2016,MONTH=3,WEEK_OF_YEAR=18,WEEK_OF_MONTH=5,DAY_OF_MONTH=25,DAY_OF_YEAR=116,DAY_OF_WEEK=2,DAY_OF_WEEK_IN_MONTH=4,AM_PM=1,HOUR=7,HOUR_OF_DAY=19,MINUTE=40,SECOND=26,MILLISECOND=289,ZONE_OFFSET=0,DST_OFFSET=3600000]>
//    @Test
//    public void testDate() {
//        System.out.println("date");
//        Calendar newD = Calendar.getInstance();
//        newD.add(Calendar.DAY_OF_MONTH, +1);
//        meal=new Meal(mealType,dish,newD);
//        Calendar expResult = newD;
//        Calendar result = meal.date();
//        assertEquals(expResult, result);
//    }
	/**
	 * Test of dish method, of class Meal.
	 */
//    Meal should have Dish ,day,MealType in constructor?
	//testDish(eapli.ecafeteria.domain.meals.MealTest): expected:<eapli.ecafeteria.domain.meals.Dish@77f03bb1> but was:<null>
	@Test
	public void testDish() {
		System.out.println("dish");
		Dish expResult = dish;
		Dish result = meal.dish();
		assertEquals(expResult, result);
	}

	/**
	 * Test of hashCode method, of class Meal.
	 */
//      testHashCode(eapli.ecafeteria.domain.meals.MealTest): The test case is a prototype.
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        Meal instance = new Meal();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    /**
//     * Test of equals method, of class Meal.
//     */
//      testEquals(eapli.ecafeteria.domain.meals.MealTest): The test case is a prototype.
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object object = null;
//        Meal instance = new Meal();
//        boolean expResult = false;
//        boolean result = instance.equals(object);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
	/**
	 * Test of toString method, of class Meal.
	 */
//    testToString(eapli.ecafeteria.domain.meals.MealTest): expected:<[]> but was:<[eapli.ecafeteria.domain.meals.Meal[ id=null ]]>
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Meal instance = new Meal();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//	@Test
//	public void testValidUpdateRating() {
//		System.out.println("Invalid Rating");
//		double expected = 5;
//		meal.updateRating(expected);
//		Rating rating = meal.rating();
//		double result = rating.value();
//		assertEquals(result, expected, 0.0);
//	}
	/*
	 @Test
	 public void testInvalidUpdateRating() {
	 System.out.println("Invalid Rating");
	 double expected = -1;
	 meal.updateRating(expected);
	 Rating rating = meal.rating();
	 double result = rating.value();
	 assertEquals(result, expected, 0.0);
	 }

	 @Test
	 public void testInvalidBigUpdateRating() {
	 System.out.println("Invalid Rating");
	 double expected = 6;
	 meal.updateRating(expected);
	 Rating rating = meal.rating();
	 double result = rating.value();
	 assertEquals(result, expected, 0.0);
	 }*/
}
