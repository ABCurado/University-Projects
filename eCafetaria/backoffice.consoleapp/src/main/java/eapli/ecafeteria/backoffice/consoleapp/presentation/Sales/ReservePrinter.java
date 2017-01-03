package eapli.ecafeteria.backoffice.consoleapp.presentation.Sales;

import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author JoséBarros(1140364)
 */
public class ReservePrinter implements Visitor<Reserve> {

    @Override
    public void visit(Reserve visitee) {
       // String date = String.format("dd-mm-yyyy", visitee.meal().date());
        System.out.printf("%10s%-11s\n", visitee.meal().dish().name(), visitee.meal().mealType().designation());
    }


    @Override
    public void beforeVisiting(Reserve visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(Reserve visitee) {
        // nothing to do
    }

}
