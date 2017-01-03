/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.CashRegister;

import static eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegisterState.closed;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class CashRegisterTest {

    CashRegister instance;
    CashRegisterState state;
    Shift shift;
    Menu menu;
    List<Meal> lstMeals;
    MealType mealType;
    Calendar startDate;
    Calendar endDate;

    public CashRegisterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        instance = new CashRegister("1");
        startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, +1);
        endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, +3);
        lstMeals = new ArrayList<>();
        lstMeals.add(null);
        mealType = new MealType("Jantar");
        menu = new Menu(startDate, endDate, lstMeals);
        //shift = new Shift(menu);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of state method, of class CashRegister.
     */

    @Test
    public void testState() {
        CashRegisterState expResult = closed;
        CashRegisterState result = instance.state();
        assertEquals(expResult, result);
    }
    /**
     * Test of open method, of class CashRegister.
     */

    @Test
    public void testOpen() {
        instance.open(shift);
        CashRegisterState expResult = state.opened;
        CashRegisterState result = instance.state();
        assertEquals(expResult, result);
    }
    /**
     * Test of open must fail method, of class CashRegister.
     */
      //testOpenMustFail(eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegisterTest): Start Date is in the past.
    @Test
    public void testOpenMustFail() {
        instance.open(shift);
        CashRegisterState expResult = state.closed;
        CashRegisterState result = instance.state();
        assertNotSame(expResult, result);
    }

}
