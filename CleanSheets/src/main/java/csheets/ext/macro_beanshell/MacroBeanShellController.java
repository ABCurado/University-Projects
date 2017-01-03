package csheets.ext.macro_beanshell;

import csheets.support.Task;
import csheets.support.TaskManager;
import csheets.support.ThreadManager;
import csheets.ui.ctrl.UIController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.UnsupportedDataTypeException;

/**
 * Macro/BeanShell Controller.
 *
 * @author Rui Bento
 * @author José Barros
 */
public class MacroBeanShellController {

	/**
	 * The user interface controller
	 */
	private UIController uicontroller;

	/**
	 * Beanshell script
	 */
	private BeanShell beanShell;

	/**
	 * Controller constructor
	 *
	 * @param uicontroller The user interface controller
	 */
	public MacroBeanShellController(UIController uicontroller) {
		this.uicontroller = uicontroller;
		this.beanShell = new BeanShell(uicontroller);
	}

	/**
	 * Executes a single code
	 *
	 * @param scriptType Type of script
	 * @param code Script code
	 * @return result
	 */
	public String executeCode(String scriptType, String code) {
		Script script;
		try {
			script = createScript(scriptType);
		} catch (UnsupportedDataTypeException ex) {
			return ex.getMessage();
		}
		try {
			return script.run(code);
		} catch (UnsupportedOperationException ex) {
			return ex.getMessage();
		}
	}

	/**
	 * Creates a macro or beanshell script
	 *
	 * @param scriptType Type of script
	 * @return script
	 * @throws UnsupportedDataTypeException
	 */
	private Script createScript(String scriptType) throws UnsupportedDataTypeException {
		if (scriptType != null) {
			switch (scriptType) {
				case BeanShell.NAME: {
					return new BeanShell(uicontroller);
				}
				case Macro.NAME: {
					return new Macro(uicontroller);
				}
			}
		}
		throw new UnsupportedDataTypeException("Unknown script type.");
	}

	/**
	 * Creates a script example
	 *
	 * @param scriptType Type of script
	 * @return example result
	 */
	public String createExample(String scriptType) {
		Script script;
		try {
			script = createScript(scriptType);
		} catch (UnsupportedDataTypeException ex) {
			return ex.getMessage();
		}
		try {
			return script.getExample();
		} catch (UnsupportedOperationException ex) {
			return ex.getMessage();
		}
	}

	/**
	 * Saves or replace a script in workbook
	 *
	 * @param name Name of script
	 * @param type Type of script
	 * @param content Script code
	 * @param synchronous Execution mode
	 */
	public void saveScript(String name, String type, String content,
						   boolean synchronous) {

		Code oldCode = uicontroller.getActiveWorkbook().getScript(name);

		if (oldCode != null) {
			uicontroller.getActiveWorkbook().getScripts().remove(oldCode);
		}

		Code code = new Code(name, type, content, synchronous);

		uicontroller.getActiveWorkbook().addScript(code);
	}

	/**
	 * Returns a script list of workbook
	 *
	 * @return scripts
	 */
	public List<Code> getSavedScripts() {

		return uicontroller.getActiveWorkbook().getScripts();
	}

	/**
	 * Returns a code of script
	 *
	 * @param script Script
	 * @return Script code
	 * @throws FileNotFoundException exception
	 * @throws IOException exception
	 */
	public String getScriptContent(String script) throws FileNotFoundException, IOException {

		return uicontroller.getActiveWorkbook().getScript(script).getContent();
	}

	/**
	 * Removes a script of list
	 *
	 * @param script Script
	 * @return Delete result
	 */
	public boolean deleteScript(String script) {

		Code code = uicontroller.getActiveWorkbook().getScript(script);
		return uicontroller.getActiveWorkbook().getScripts().remove(code);
	}

	/**
	 * Executes a full script
	 *
	 * @param script_name Name of script
	 */
	public void executeScript(String script_name) {

		Code code = uicontroller.getActiveWorkbook().getScript(script_name);

		Script script;

		try {
			script = createScript(code.getType());
			if (code.isSynchronous()) {
				script.run(code.getContent());
			} else {
				TaskManager tm = new TaskManager();
				Task verify;
				verify = new Task() {
					public void fire() {
						script.run(code.getContent());
					}
				};
				ThreadManager.create("lang.script", new Thread() {
									 public void run() {
										 tm.once(verify);
									 }
								 });
				ThreadManager.run("lang.script");
			}

		} catch (UnsupportedOperationException ex) {
			System.out.println(ex.getMessage());

		} catch (UnsupportedDataTypeException ex) {
			Logger.getLogger(MacroBeanShellController.class.getName()).
				log(Level.SEVERE, null, ex);
		}
	}
}
