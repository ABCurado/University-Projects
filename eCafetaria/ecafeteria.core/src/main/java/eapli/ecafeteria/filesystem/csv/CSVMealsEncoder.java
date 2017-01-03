package eapli.ecafeteria.filesystem.csv;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.filesystem.impl.CSVEncoder;

/**
 * TODO javadoc
 *
 * @author nervousdev
 */
public class CSVMealsEncoder extends CSVEncoder<Meal> {

    @Override
    public void writeElements(Iterable<Meal> listElements) {
        for (Meal t : listElements) {
            append(t.date() + " " + t.dish().name());
        }
    }

}
