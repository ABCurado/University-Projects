package csheets.framework.persistence.repositories.impl.immemory;

/**
 * Created by nuno on 22/03/16.
 */
public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }
}
