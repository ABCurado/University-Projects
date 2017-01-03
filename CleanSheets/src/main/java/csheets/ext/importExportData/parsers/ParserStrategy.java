package csheets.ext.importExportData.parsers;

/**
 *
 * @author G14
 */
public interface ParserStrategy {
    
    /**
     * Executes the Strategy.
     * 
     * @param filepath The filename to execute the ParserStrategy.
     * @return Object
     */
    public Object execute(String filepath);
    
}
