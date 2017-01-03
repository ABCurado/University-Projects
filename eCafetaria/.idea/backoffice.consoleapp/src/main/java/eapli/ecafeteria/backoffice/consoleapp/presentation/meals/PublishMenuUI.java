/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.PublishMenuController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Eduardo
 */
public class PublishMenuUI extends AbstractUI {

	private final PublishMenuController controller = new PublishMenuController();

	protected Controller controller() {
		return (Controller) this.controller;
	}

	@Override
	protected boolean doShow() {
		//final Iterable<Menu> menus = this.controller.allMenus();
		//final SelectWidget<Menu> selector = new SelectWidget<>(menus, new PublishMenuPrinter());
		//selector.show();
		//final Menu menu = selector.selectedElement();
		/*
		try {
			this.controller.defineMenu(menu);
		} catch (final DataIntegrityViolationException e) {
			System.out.println("Error");
		}
		 */
		return true;
	}

	@Override
	public String headline() {
		return "Publish Menu";
	}

}
