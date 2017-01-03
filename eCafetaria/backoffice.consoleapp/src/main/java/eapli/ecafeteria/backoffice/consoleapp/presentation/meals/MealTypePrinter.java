package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class MealTypePrinter implements Visitor<MealType> {

    @Override
    public void visit(MealType visitee) {
        System.out.printf("%-30s\n", visitee.designation());
    }

    @Override
    public void beforeVisiting(MealType visitee) {
            // nothing to do
    }

    @Override
    public void afterVisiting(MealType visitee) {
            // nothing to do
    }
    
}
