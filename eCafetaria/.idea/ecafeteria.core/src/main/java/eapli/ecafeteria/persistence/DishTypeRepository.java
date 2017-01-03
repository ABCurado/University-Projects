package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.persistence.repositories.Repository;

/**
 * Created by MCN on 29/03/2016.
 */
public interface DishTypeRepository extends Repository<DishType, Long> {

    /**
     * returns only the active dish types
     *
     * @return
     */
    public Iterable<DishType> activeDishTypes();
}
