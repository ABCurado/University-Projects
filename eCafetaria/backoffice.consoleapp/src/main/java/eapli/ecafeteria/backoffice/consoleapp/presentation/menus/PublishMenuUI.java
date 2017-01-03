package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.ecafeteria.application.PublishMenuController;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 *
 * @author Eduardo
 */
public class PublishMenuUI extends AbstractUI {

	private final PublishMenuController controller = new PublishMenuController();

	@Override
	protected boolean doShow() {
		final Iterable<Menu> menuTyps = this.controller.listMenus();

		if (!menuTyps.iterator().hasNext()) {
			System.out.println("There is no menu unpublished!");
			return true;
		}

		final SelectWidget<Menu> selector = new SelectWidget<>(menuTyps, new MenuPrinter());
		selector.show();
		final Menu theMenuSelected = selector.selectedElement();

		if (theMenuSelected != null) {
			try {
				this.controller.publishMenu(theMenuSelected);
			} catch (DataConcurrencyException ex) {
				System.out.println("Data has been changed. Please try later.");
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return true;
	}

	@Override
	public String headline() {
		return "Publish Menu";
	}

}
