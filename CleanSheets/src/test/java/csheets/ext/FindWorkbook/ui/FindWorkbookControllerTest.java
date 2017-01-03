/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.FindWorkbook.ui;

import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Carlos Mateus
 */
public class FindWorkbookControllerTest {

	public FindWorkbookControllerTest() {
	}

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
	 * Test of findWorkbook method, of class FindWorkbookController.
	 */
	@Test
	public void testFindWorkbook() {
		System.out.println("findWorkbook");
		File startingDirectory = new File(System.getProperty("user.dir") + "/src/test/java/csheets/ext/FindWorkbook/teste/");
		String pattern = ".*\\.cls";
		FindWorkbookController instance = new FindWorkbookController();
		int expResult = 1;
		List<File> result = instance.findWorkbook(startingDirectory, pattern);
		startingDirectory = new File(System.getProperty("user.dir") + "\\db");
		assertEquals(expResult, result.size());
		expResult = 0;
		result = instance.findWorkbook(startingDirectory, pattern);
		assertEquals(expResult, result.size());

	}
}
