package csheets.framework.persistence.repositories;

/**
 * An exception that violates a data integrity constraint like a duplicate key
 *
 * @author Paulo Gandra Sousa
 *
 */
public class DataIntegrityViolationException extends Exception {

    /**
     *
     */
    public DataIntegrityViolationException() {
    }

    /**
     * @param arg0 Message
     */
    public DataIntegrityViolationException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0 Message
     */
    public DataIntegrityViolationException(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0 Message
     * @param arg1 Throwable
     */
    public DataIntegrityViolationException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
}
