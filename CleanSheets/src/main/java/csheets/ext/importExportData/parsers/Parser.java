package csheets.ext.importExportData.parsers;

/**
 *
 * @author hack
 */
public abstract class Parser {

	/**
	 * File handler.
	 */
	private FileHandler handler;

	/**
	 * File to be parsed.
	 */
	private String file;

	/**
	 * The Strategy the parser will handle.
	 */
	private ParserStrategy strategy;

	/**
	 * Creates a new Parser instance.
	 *
	 * @param handler handler
	 */
	public Parser(FileHandler handler) {
		this.handler = handler;
	}

	/**
	 * Sets the file to be parsed.
	 *
	 * @param file File path.
	 * @return self
	 */
	public Parser with(String file) {
		this.file = file;

		return this;
	}

	/**
	 * Sets the file as content.
	 *
	 * @param contents The string content.
	 * @return self
	 */
	public Parser withContents(String contents) {
		this.file = contents;

		return this;
	}

	/**
	 * Returns the file handler associated with the parser.
	 *
	 * @return File Handler.
	 */
	public FileHandler getHandler() {
		return this.handler;
	}

	/**
	 * Returns the file path associated with the parser.
	 *
	 * @return File path.
	 */
	public String getFile() {
		return this.file;
	}

	/**
	 * Uses the given strategy.
	 *
	 * @param strategy Strategy to execute.
	 * @return self
	 */
	public Parser use(ParserStrategy strategy) {
		this.strategy = strategy;

		return this;
	}

	/**
	 * Gets the Strategy set for the parser.
	 *
	 * @return StrategyInterface
	 */
	public ParserStrategy getStrategy() {
		return this.strategy;
	}

	/**
	 * Parses the given file. Unless you get the file through the withContents
	 * method, you need to read the file by using the FileHandler associated
	 * with this class in order to have access to the file's contents.
	 *
	 * @return Object containing the parsed contents of the file.
	 */
	public abstract Object parse();
}
