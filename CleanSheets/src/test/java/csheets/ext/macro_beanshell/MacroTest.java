/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.macro_beanshell;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rui Bastos
 */
public class MacroTest {

	Macro instance;

	public MacroTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		instance = new Macro(null);
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of run method, of class Macro.
	 */
	@Test
	public void testRun() {
		String code = "{SUM(1;1)}";
		String result = instance.run(code);
		assertEquals("2", result);
		code = "SUM(1;1)}";
		result = instance.run(code);
		assertEquals(true, result.startsWith("Error: "));
		code = "{SUM(1;1)}\n;comment";
		result = instance.run(code);
		assertEquals("2", result);
		code = ";comment";
		result = instance.run(code);
		assertEquals("", result);
		code = "5>2";
		result = instance.run(code);
		assertEquals("TRUE", result);
		code = "5<2";
		result = instance.run(code);
		assertEquals("FALSE", result);
	}

}
