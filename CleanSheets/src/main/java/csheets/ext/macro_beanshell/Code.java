package csheets.ext.macro_beanshell;

import java.io.Serializable;

/**
 * Saves the code information. Name, type, content and execution mode of script,
 *
 * @author José Barros
 */
public class Code implements Serializable {

	/**
	 * Name of script
	 */
	private String name;

	/**
	 * Type of script
	 */
	private String type;

	/**
	 * Script Content (code)
	 */
	private String content;

	/**
	 * Execution mode
	 */
	private boolean synchronous;

	/**
	 * Code constructor
	 *
	 * @param name Script name
	 * @param type Script type
	 * @param content Code of Script
	 * @param synchronous Execution mode
	 */
	public Code(String name, String type, String content, boolean synchronous) {
		if (name != null && type != null && content != null) {
			this.name = name;
			this.type = type;
			this.content = content;
			this.synchronous = synchronous;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns a name of script
	 *
	 * @return script name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a type of script
	 *
	 * @return script type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Return a code of script
	 *
	 * @return code
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Returns a boolean of the execution mode (Sync - TRUE, aSync - FALSE)
	 *
	 * @return execution mode
	 */
	public boolean isSynchronous() {
		return synchronous;
	}
}
