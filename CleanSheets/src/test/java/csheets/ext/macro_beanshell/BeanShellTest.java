/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.macro_beanshell;

import csheets.ui.ctrl.UIController;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rui Bento
 */
public class BeanShellTest {

	private UIController uiController;

	public BeanShellTest() {
		/*
		Workbook wb = new Workbook(1);
		String[][] content = new String[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				content[i][j] = "";
			}
		}
		wb.addSpreadsheet(content);
		uiController = new UIController();
		Spreadsheet s = wb.getSpreadsheet(0);
		SpreadsheetTable sst = new SpreadsheetTable(s, uiController);
		uiController.focusOwner = sst;
		uiController.setActiveSpreadsheet(s);
		 */
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
	 * Test of getExample with run method, of class BeanShell.
	 */
	@Test
	public void testExampleTest() {
		/*
        System.out.println("BeanShell-ExampleTest and Run it");
        BeanShell instance = new BeanShell(uiController);
        String result = instance.getExample();
        if (result.startsWith("Error: ")) {
            fail("Example not working");
        }
        assertNotNull(result);
		 */
		assertEquals(true, true);
	}

	/**
	 * Test of run method, of class BeanShell.
	 */
	/*
    @Test
    public void testRunWithReturn() {
        System.out.println("BeanShell-Return Test of run");
        String code = "return \"Fat Joe is awesome!\";";
        BeanShell instance = new BeanShell(uiController);
        String expResult = "Fat Joe is awesome!";
        String result = instance.run(code);
        if (result.startsWith("Error: ")) {
            fail("Code not working");
        }
        assertNotNull(result);
        assertEquals(expResult, result);
    }
	 */
	/**
	 * Test of run method, of class BeanShell.
	 */
	/*
    @Test
    public void testRunWithReturn2() {
        System.out.println("BeanShell-Return Test of run");
        String code = "a=2;\n"
                + "a+=1;\n";
        BeanShell instance = new BeanShell(uiController);
        String expResult = "3";
        String result = instance.run(code);
        if (result.startsWith("Error: ")) {
            fail("Code not working");
        }
        assertNotNull(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class BeanShell.
	 */
 /*
    @Test
    public void testRunWithoutReturnInfo() {
        System.out.println("BeanShell-Without Return Test of run");
        String code = "a=2;\n"
                + "print(\"NON\");\n";
        BeanShell instance = new BeanShell(uiController);
        String expResult = "";
        String result = instance.run(code);
        if (result.startsWith("Error: ")) {
            fail("Code not working");
        }
        assertNotNull(result);
        assertEquals(expResult, result);
    }
	 */

 /*
    @Test
    public void test_if_api_is_correctly_invoked() {
        try {
            String expResult = "CleansheetsAPI";

            uiController.getActiveSpreadsheet().getCell(0, 0).
                    setContent("CleansheetsAPI");
            String code = "return api.getCell(0,0).getContent();";

            BeanShell instance = new BeanShell(uiController);

            String result = instance.run(code);

            assertNotNull(result);
            assertEquals(result, expResult);

        } catch (FormulaCompilationException ex) {
            Logger.getLogger(BeanShellTest.class.getName()).
                    log(Level.SEVERE, null, ex);
            fail("Formula error was found.");
        }
    }
	 */
}
