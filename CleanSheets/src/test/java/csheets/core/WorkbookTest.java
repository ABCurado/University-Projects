package csheets.core;

import csheets.core.formula.VariableArray;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.macro_beanshell.BeanShell;
import csheets.ext.macro_beanshell.Code;
import csheets.ext.macro_beanshell.Macro;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class WorkbookTest {

	private List<Code> scripts;
	private Workbook workbook;
	private final Code code1;
	private final Code code2;
	private List<VariableArray> variables;
	private Spreadsheet spreadsheet;
	private Cell A1;
	private Cell B1;

	public WorkbookTest() {
		this.workbook = new Workbook(2);
		this.spreadsheet = this.workbook.getSpreadsheet(0);
		this.A1 = this.spreadsheet.getCell(0, 0);
		this.B1 = this.spreadsheet.getCell(1, 0);

		try {
			this.A1.setContent("=@global:=10"); // Adds value to first Position.
			this.B1.setContent("=@global[2]:=20"); //Adds value to second Position.
		} catch (FormulaCompilationException ex) {
			Logger.getLogger(WorkbookTest.class.getName()).
				log(Level.SEVERE, null, ex);
		}

		scripts = new ArrayList<>();
		code1 = new Code("test1", BeanShell.NAME, "Hello world", true);
		scripts.add(code1);
		code2 = new Code("test2", Macro.NAME, "Amazing", false);
		scripts.add(code2);

		workbook.getScripts().addAll(scripts);
	}

	@Test
	public void testGetSpreadsheetCount() {

		// create a workbook with 2 sheets
		Workbook wb = new Workbook(2);

		assertEquals("Result", 2, wb.getSpreadsheetCount());
	}

	@Test
	public void testGetScriptWithWrongName() {
		String scriptToReturn = "test3";
		Code code = workbook.getScript(scriptToReturn);
		Assert.assertNull(code);
	}

	@Test
	public void testGetScriptWithRightName() {
		String scriptToReturn = "test2";
		Code code = workbook.getScript(scriptToReturn);
		Assert.assertNotNull(code);
		assertEquals(code, code2);
	}

	@Test
	public void testAddScript() {
		int firstSize = workbook.getScripts().size();
		System.out.println("First Length = " + firstSize);

		Code code3 = new Code("test3", BeanShell.NAME, "Hi", false);
		Assert.assertNotNull(code3);
		workbook.addScript(code3);

		int secondSize = workbook.getScripts().size();
		System.out.println("Second Length = " + secondSize);

		assertTrue(secondSize > firstSize);
	}

	@Test
	public void testClearList() {
		int firstSize = workbook.getScripts().size();
		System.out.println("First Length = " + firstSize);

		workbook.clearScripts();

		int secondSize = workbook.getScripts().size();
		System.out.println("Second Length = " + secondSize);

		assertTrue(secondSize < firstSize);
		assertEquals("Size", 0, workbook.getScripts().size());
	}

	/**
	 * Test for updateVariable method in Workbook class. Adds a new Value to an
	 * already occupied position.
	 */
	@Test
	public void testUpdateValue() {
		Value value = new Value(30);
		this.workbook.updateValue("@global", value, 2); //position already being used. previous result is this position was 20.
		String expResult = "30";
		String result = workbook.getVariableValue("@global", 2).toString();
		assertEquals(expResult, result);
	}

	/**
	 * Test for addVariable method in Workbook Class. Adds a new Variable to the
	 * workbook and verifies if the Variables exist.
	 */
	@Test
	public void testAddVariable() {
		boolean flag = true;
		Value value = new Value(20);
		Value value2 = new Value("test");
		VariableArray var = new VariableArray("@test", value, 4);
		VariableArray var2 = new VariableArray("@test2", value2, 10);
		this.workbook.addVariable(var);
		this.workbook.addVariable(var2);
		if (!workbook.variableExist("@test")) {
			flag = false;
		}
		if (!workbook.variableExist("@test2")) {
			flag = false;
		}
		assertEquals(true, flag);
	}

}
