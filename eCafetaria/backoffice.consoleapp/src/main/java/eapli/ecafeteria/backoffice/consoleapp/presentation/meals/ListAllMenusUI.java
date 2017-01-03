package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.ListMenusController;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListAllMenusUI extends AbstractListUI<Menu> {

	private final ListMenusController theController = new ListMenusController();

	@Override
	protected Iterable<Menu> listOfElements() {
		return theController.listMenus();
	}

	@Override
	protected Visitor<Menu> elementPrinter() {
		return new MenuPrinter();
	}

	@Override
	protected String elementName() {
		return "Menu";
	}

}
