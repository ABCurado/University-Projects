package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.MealMenuController;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class RemoveMenuUI extends AbstractUI {

    @Override
    protected boolean doShow() {
        final MealMenuController controller = new MealMenuController();

        final Iterable<Menu> menus = controller.listUnPublishedMenus();
        final SelectWidget<Menu> selector = new SelectWidget<>(menus, new MenuPrinter());
        selector.show();
        final Menu menu = selector.selectedElement();

        if (menu != null) {
            try {
                controller.removeMenu(menu);
            } catch (DataIntegrityViolationException ex) {
                System.out.println("Data has an integrity violation. Please try again.");
            } catch (DataConcurrencyException ex) {
                System.out.println("Data has changed or has been deleted since it was last read. Please try again.");
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Remove Menu";
    }

}
