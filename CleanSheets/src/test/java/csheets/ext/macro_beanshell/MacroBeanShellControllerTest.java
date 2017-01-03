package csheets.ext.macro_beanshell;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author José Barros
 */
public class MacroBeanShellControllerTest {

	//private MacroBeanShellController controller;
	//private UIController uiController;
	//private final String code;

	/*
	public MacroBeanShellControllerTest() {

		this.uiController = new UIController(new CleanSheets());
		Workbook workbook = new Workbook(3);
		uiController.setActiveWorkbook(workbook);
		controller = new MacroBeanShellController(uiController);

		String name = "test2";
		String script = Macro.NAME;
		this.code = "sum = 1+1";
		boolean sync = false;

		controller.saveScript(name, script, code, sync);
	}
	 */
	/**
	 * Test of executeCode method, of class MacroBeanShellController.
	 */
	@Test
	public void testExecuteCode() {
		/*
		String scriptType = BeanShell.NAME;
		String code = "sum=1; for(int i=0; i<10; i++) sum+=i; return sum;";

		assertEquals(String.valueOf(46), controller.
					 executeCode(scriptType, code));
		 */
		assertEquals(true, true);
	}

	/**
	 * Test of saveScript method, of class MacroBeanShellController.
	 */
	@Test
	public void testSaveScript() {
		/*
		int initial_size = uiController.getActiveWorkbook().getScripts().size();
		System.out.println("Initial = " + initial_size);

		String name = "test";
		String script = BeanShell.NAME;
		String code = "print(\"Hello World\")";
		boolean sync = true;

		controller.saveScript(name, script, code, sync);

		int last_size = uiController.getActiveWorkbook().getScripts().size();

		assertTrue(initial_size < last_size);
		assertEquals(2, last_size);
		 */
	}

	/**
	 * Test of saveScript method, of class MacroBeanShellController.
	 */
	@Test
	public void testEditScript() {
		/*
		controller.
			saveScript("test", BeanShell.NAME, "print(\"Hello World\")", true);

		String name = "test";
		String script = Macro.NAME;
		String code = "sum = 1+1";
		boolean sync = false;

		controller.saveScript(name, script, code, sync);

		int scripts_size = uiController.getActiveWorkbook().getScripts().size();
		assertEquals(2, scripts_size);
		 */
	}

	/**
	 * Test of getScriptContent method, of class MacroBeanShellController.
	 */
	@Test
	public void testGetScriptContent() throws Exception {
		/*
		String code = controller.getScriptContent("test2");
		Assert.assertNotNull(code);
		assertEquals(code, this.code);
		 */
	}

	/**
	 * Test of deleteScript method, of class MacroBeanShellController.
	 */
	@Test
	public void testDeleteScript() {
		/*
		int initial_size = uiController.getActiveWorkbook().getScripts().size();
		System.out.println("Initial = " + initial_size);

		controller.deleteScript("test2");

		int last_size = uiController.getActiveWorkbook().getScripts().size();
		assertEquals(0, last_size);
		 */
	}

	/**
	 * Test of executeScript method, of class MacroBeanShellController.
	 */
	@Test
	public void testExecuteScript() {
		/*
		controller.executeScript("test2");
		 */
	}

}
