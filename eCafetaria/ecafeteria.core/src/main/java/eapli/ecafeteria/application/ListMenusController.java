package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.application.Controller;

/**
 *
 * @author Rui Freitas
 */
public class ListMenusController implements Controller {

	public Iterable<Menu> listMenus() {
		return new ListMenusService().allMenus();
	}
}
