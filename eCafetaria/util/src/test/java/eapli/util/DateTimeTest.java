/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.util;

import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class DateTimeTest {

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of weekNumber method, of class DateTime.
	 */
	@Test
	public void testJanuaryFirst2014IsWeekOne() {
		System.out.println("weekNumber");
		//TODO do not use own methods whcih have not been tested yet
		Calendar date = DateTime.newCalendar(2014, 01, 01);
		int expResult = 1;
		int result = DateTime.weekNumber(date);
		assertEquals(expResult, result);
	}

	/**
	 * Test of weekNumber method, of class DateTime.
	 */
	@Test
	public void testMarch21st2014IsWeek12() {
		System.out.println("weekNumber");
		//TODO do not use own methods whcih have not been tested yet
		Calendar date = DateTime.newCalendar(2014, 03, 21);
		int expResult = 12;
		int result = DateTime.weekNumber(date);
		assertEquals(expResult, result);
	}

	/**
	 * Test of beginningOfWeek method, of class DateTime.
	 */
//	@Test
//	public void testFirstDayOfWeek13Of2014Is16() {
//		System.out.println("beginningOfWeek");
//		int year = 2014;
//		int week = 13;
//		Calendar expResult = DateTime.newCalendar(2014, 03, 16);
//		Calendar result = DateTime.beginningOfWeek(year, week);
//		assertEquals(expResult, result);
//	}
	/**
	 * Test of endOfWeek method, of class DateTime.
	 */
//	@Test
//	public void testLastDayOfWeek13Of2014Is22() {
//		System.out.println("endOfWeek");
//		int year = 2014;
//		int week = 13;
//		Calendar expResult = DateTime.newCalendar(2014, 03, 22);
//		Calendar result = DateTime.endOfWeek(year, week);
//		assertEquals(expResult, result);
//	}
	/**
	 * Test of endOfMonth method, of class DateTime.
	 */
	@Test
	public void testLastDayOfMarch2014is31() {
		System.out.println("endOfMonth");
		Calendar reference = DateTime.newCalendar(2014, 03, 21);
		Calendar expResult = DateTime.newCalendar(2014, 03, 31);
		Calendar result = DateTime.endOfMonth(reference);
		assertEquals(expResult, result);
	}

	/**
	 * Test of endOfMonth method, of class DateTime.
	 */
	@Test
	public void testLastDayOfFebruary2014is28() {
		System.out.println("endOfMonth");
		Calendar reference = DateTime.newCalendar(2014, 02, 21);
		Calendar expResult = DateTime.newCalendar(2014, 02, 28);
		Calendar result = DateTime.endOfMonth(reference);
		assertEquals(expResult, result);
	}

	/**
	 * Test of endOfMonth method, of class DateTime.
	 */
	@Test
	public void testLastDayOfFebruary2012is29() {
		System.out.println("endOfMonth");
		Calendar reference = DateTime.newCalendar(2012, 02, 21);
		Calendar expResult = DateTime.newCalendar(2012, 02, 29);
		Calendar result = DateTime.endOfMonth(reference);
		assertEquals(expResult, result);
	}

	/**
	 * Test of parseDate method, of class DateTime.
	 */
//	@Test
//	public void testParseDate_String_String() {
//		System.out.println("parseDate");
//		String aDateString = "2014-03-21";
//		String format = "YYYY-MM-DD";
//		Calendar expResult = DateTime.newCalendar(2014, 03, 21);
//		Calendar result = DateTime.parseDate(aDateString, format);
//		assertEquals(expResult, result);
//	}
	@Test
	public void testIsSameDate() {
		System.out.println("isSameDate");
		Calendar d1 = DateTime.now();
		Calendar d2 = DateTime.now();

		boolean result = DateTime.isSameDate(d1, d2);
		assertEquals(true, result);
	}

	@Test
	public void testIsBetweenDatesTrueDay() {
		System.out.println("testIsBetweenDatesTrueDay");
		Calendar start = DateTime.newCalendar(2016, 1, 1);
		Calendar date = DateTime.newCalendar(2016, 1, 2);
		Calendar end = DateTime.newCalendar(2016, 1, 3);

		boolean result = DateTime.isBetweenDates(start, end, date);
		assertEquals(true, result);
	}

	@Test
	public void testIsBetweenDatesTrueMonth() {
		System.out.println("testIsBetweenDatesTrueMonth");
		Calendar start = DateTime.newCalendar(2016, 1, 1);
		Calendar date = DateTime.newCalendar(2016, 2, 1);
		Calendar end = DateTime.newCalendar(2016, 3, 1);

		boolean result = DateTime.isBetweenDates(start, end, date);
		assertEquals(true, result);
	}

	@Test
	public void testIsBetweenDatesTrueYears() {
		System.out.println("testIsBetweenDatesTrueYears");
		Calendar start = DateTime.newCalendar(2016, 1, 1);
		Calendar date = DateTime.newCalendar(2017, 1, 1);
		Calendar end = DateTime.newCalendar(2018, 1, 1);

		boolean result = DateTime.isBetweenDates(start, end, date);
		assertEquals(true, result);
	}

	@Test
	public void testIsBetweenDatesFalseDay() {
		System.out.println("testIsBetweenDatesFalseDay");
		Calendar start = DateTime.newCalendar(2016, 1, 1);
		Calendar date = DateTime.newCalendar(2016, 1, 4);
		Calendar end = DateTime.newCalendar(2016, 1, 3);

		boolean result = DateTime.isBetweenDates(start, end, date);
		assertEquals(false, result);
	}

	@Test
	public void testIsBetweenDatesFalseMonth() {
		System.out.println("testIsBetweenDatesFalseMonth");
		Calendar start = DateTime.newCalendar(2016, 1, 1);
		Calendar date = DateTime.newCalendar(2016, 4, 1);
		Calendar end = DateTime.newCalendar(2016, 3, 1);

		boolean result = DateTime.isBetweenDates(start, end, date);
		assertEquals(false, result);
	}

	@Test
	public void testIsBetweenDatesFalseYears() {
		System.out.println("testIsBetweenDatesFalseYears");
		Calendar start = DateTime.newCalendar(2016, 1, 1);
		Calendar date = DateTime.newCalendar(2018, 1, 1);
		Calendar end = DateTime.newCalendar(2017, 1, 1);

		boolean result = DateTime.isBetweenDates(start, end, date);
		assertEquals(false, result);
	}

	@Test
	public void testIsBetweenDatesSame() {
		System.out.println("testIsBetweenDatesSameStart");
		Calendar start = DateTime.newCalendar(2016, 1, 1);
		Calendar date = DateTime.newCalendar(2016, 1, 1);
		Calendar end = DateTime.newCalendar(2016, 1, 1);

		boolean result = DateTime.isBetweenDates(start, end, date);
		assertEquals(true, result);
	}

	@Test
	public void testIsBetweenDatesSameStart() {
		System.out.println("testIsBetweenDatesSameStart");
		Calendar start = DateTime.newCalendar(2016, 2, 1);
		Calendar date = DateTime.newCalendar(2016, 2, 1);
		Calendar end = DateTime.newCalendar(2016, 3, 1);

		boolean result = DateTime.isBetweenDates(start, end, date);
		assertEquals(true, result);
	}

	@Test
	public void testIsBetweenDatesSameEnd() {
		System.out.println("testIsBetweenDatesSameEnd");
		Calendar start = DateTime.newCalendar(2016, 1, 1);
		Calendar date = DateTime.newCalendar(2016, 3, 1);
		Calendar end = DateTime.newCalendar(2016, 3, 1);

		boolean result = DateTime.isBetweenDates(start, end, date);
		assertEquals(true, result);
	}

	@Test
	public void testIsTimeBeforeOrEqualFail() {
		System.out.println("isTimeBeforeOrEqual - Fail");
		Calendar d1 = DateTime.newCalendar(2016, 1, 1, 13, 1, 15);
		Calendar d2 = DateTime.newCalendar(2016, 1, 1, 12, 1, 15);

		boolean result = DateTime.isTimeBeforeOrEqual(d1, d2);
		assertEquals(false, result);
	}

	@Test
	public void testIsTimeBeforeOrEqualPassWithSameTime() {
		System.out.println("isTimeBeforeOrEqual - Pass(Same time)");
		Calendar d1 = DateTime.newCalendar(2016, 1, 1, 12, 1, 15);
		Calendar d2 = DateTime.newCalendar(2016, 1, 1, 12, 1, 15);

		boolean result = DateTime.isTimeBeforeOrEqual(d1, d2);
		assertEquals(true, result);
	}

	@Test
	public void testIsTimeBeforeOrEqualPassWithSmallerTime() {
		System.out.println("isTimeBeforeOrEqual - Pass(smaller time)");
		Calendar d1 = DateTime.newCalendar(2016, 1, 1, 11, 1, 15);
		Calendar d2 = DateTime.newCalendar(2016, 1, 1, 12, 1, 15);

		boolean result = DateTime.isTimeBeforeOrEqual(d1, d2);
		assertEquals(true, result);
	}

	@Test
	public void testOverlappingDatesFalse() {
		System.out.println("testOverlappingDatesFalse");
		Calendar s1 = DateTime.newCalendar(2001, 1, 1);
		Calendar e1 = DateTime.newCalendar(2002, 2, 2);
		Calendar s2 = DateTime.newCalendar(2003, 3, 3);
		Calendar e2 = DateTime.newCalendar(2004, 4, 4);
		assertEquals(DateTime.overlappingDates(s1, e1, s2, e2), false);
	}

	@Test
	public void testOverlappingDatesFalseOneDay() {
		System.out.println("testOverlappingDatesFalseOneSecond");
		Calendar s1 = DateTime.newCalendar(2001, 1, 1);
		Calendar e1 = DateTime.newCalendar(2001, 1, 2);
		Calendar s2 = DateTime.newCalendar(2001, 1, 3);
		Calendar e2 = DateTime.newCalendar(2001, 1, 4);
		assertEquals(DateTime.overlappingDates(s1, e1, s2, e2), false);
	}

	@Test
	public void testOverlappingDatesTrueSame() {
		System.out.println("testOverlappingDatesTrueSame");
		Calendar s1 = DateTime.newCalendar(2001, 1, 1);
		Calendar e1 = DateTime.newCalendar(2002, 2, 2);
		Calendar s2 = DateTime.newCalendar(2001, 1, 1);
		Calendar e2 = DateTime.newCalendar(2002, 2, 2);
		assertEquals(DateTime.overlappingDates(s1, e1, s2, e2), true);
	}

	@Test
	public void testOverlappingDatesTrueOneSecond() {
		System.out.println("testOverlappingDatesTrueOneDay");
		Calendar s1 = DateTime.newCalendar(2001, 1, 1);
		Calendar e1 = DateTime.newCalendar(2002, 2, 2);
		Calendar s2 = DateTime.newCalendar(2002, 2, 2);
		Calendar e2 = DateTime.newCalendar(2003, 3, 3);
		assertEquals(DateTime.overlappingDates(s1, e1, s2, e2), true);
	}

	@Test
	public void testdateCurrentWeekDayMonday() {
		System.out.println("testdateCurrentWeekDayMonday");
		Calendar result = DateTime.dateCurrentWeekDay(Calendar.MONDAY);
		Calendar expResult = DateTime.now();
		expResult.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		assertEquals(DateTime.isSameDate(result, expResult), true);
	}

	@Test
	public void testdateCurrentWeekDayFriday() {
		System.out.println("testdateCurrentWeekDayFriday");
		Calendar result = DateTime.dateCurrentWeekDay(Calendar.FRIDAY);
		Calendar expResult = DateTime.now();
		expResult.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		assertEquals(DateTime.isSameDate(result, expResult), true);
	}

}
