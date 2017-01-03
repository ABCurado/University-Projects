package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.application.Controller;

/**
 * Created on 29/03/2016.
 */
public class ListDishTypeController implements Controller {

    public Iterable<DishType> listDishTypes() {
        //TODO check if this use case should list all dish types or only active ones
        return new ListDishTypeService().allDishTypes();
    }
}
