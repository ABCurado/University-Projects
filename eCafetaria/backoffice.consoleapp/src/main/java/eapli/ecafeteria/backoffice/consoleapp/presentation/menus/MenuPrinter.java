package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.visitor.Visitor;
import eapli.util.DateTime;

/**
 *
 * @author Eduardo
 */
public class MenuPrinter implements Visitor<Menu> {

	@Override
	public void visit(Menu visitee) {
		System.out.
			printf("%-30s %-10s %-10s\n", visitee.description(), DateTime.
				   format(visitee.startDate()), DateTime.
				   format(visitee.endDate()));
	}

	@Override
	public void beforeVisiting(Menu visitee) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void afterVisiting(Menu visitee) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
