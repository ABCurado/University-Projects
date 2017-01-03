package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ValueObject;
import javax.persistence.Embeddable;

/**
 *
 * @author ab
 */
@Embeddable
public class LastMinuteMealNotDeliveredState implements LastMinuteMealState,ValueObject{

    public LastMinuteMealNotDeliveredState() {

    }

    @Override
    public boolean isDeliverable() {
        return true;
    }
    
    @Override
    public boolean isDelivered() {
        return false;
    }
}

