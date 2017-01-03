///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package eapli.ecafeteria.application;
//
//import eapli.ecafeteria.AppSettings;
//import eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegister;
//import eapli.ecafeteria.domain.meals.Dish.Dish;
//import eapli.ecafeteria.domain.meals.DishType;
//import eapli.ecafeteria.domain.meals.Meal;
//import eapli.ecafeteria.domain.meals.MealType;
//import eapli.ecafeteria.domain.meals.Menu;
//import eapli.ecafeteria.persistence.CashRegisterRepository;
//import eapli.ecafeteria.persistence.PersistenceContext;
//import eapli.framework.domain.Money;
//import eapli.framework.persistence.DataIntegrityViolationException;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Currency;
//import java.util.Iterator;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertEquals;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author Rui Bastos<1140491@isep.ipp.pt>
// */
//public class OpenCashRegisterControllerTest {
//
//    OpenCashRegisterController instance;
//
//    public OpenCashRegisterControllerTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() throws DataIntegrityViolationException {
//        CashRegisterRepository cashRegisters = PersistenceContext.repositories().cashRegisters();
//        cashRegisters.add(new CashRegister("1"));
//        List<Meal> meals = new ArrayList();
//
//        DishType meat = new DishType("Meat", "Meat Dish");
//        Money money = new Money(2, Currency.getInstance("EUR"));
//
//        Calendar now = Calendar.getInstance();
//        now.add(Calendar.DATE, +1);
//        Calendar later = Calendar.getInstance();
//        later.add(Calendar.DATE, +3);
//        MealType mt = new MealType("Lunch");
//        Meal meal1 = new Meal(mt, new Dish(meat, "Arroz de Frango", 200, 3, 6, money), now);
//        Meal meal2 = new Meal(mt, new Dish(meat, "Massa com Panado", 200, 6, 15, money), later);
//
//        meals.add(meal1);
//        meals.add(meal2);
//        Menu menu = new Menu(now, later, meals);
//        PersistenceContext.repositories().menu().add(menu);
//        instance = new OpenCashRegisterController("1");
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of mealTypes method, of class OpenCashRegisterController.
//     */
//    @Test
//    public void testMealTypes() {
//        int expResult = 1;
//        Iterator<MealType> iterator = instance.mealTypes().iterator();
//        int result = 0;
//        while (iterator.hasNext()) {
//            result++;
//        }
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of chooseMeal method, of class OpenCashRegisterController.
//     */
//    @Test
//    public void testChooseMeal() {
//        System.out.println("chooseMeal");
//        /*
//         Calendar day = null;
//         MealType mealType = null;
//         OpenCashRegisterController instance = null;
//         boolean expResult = false;
//         boolean result = instance.chooseMeal(day, mealType);
//         assertEquals(expResult, result);
//         */
//    }
//
//    /**
//     * Test of open method, of class OpenCashRegisterController.
//     */
//    @Test
//    public void testOpen() {
//        System.out.println("open");
//        //OpenCashRegisterController instance = null;
//        //instance.open();
//    }
//
//}
