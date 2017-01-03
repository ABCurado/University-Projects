package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Rui Freitas
 */
public class InMemoryDishRepository extends InMemoryRepository<Dish, Long>
	implements DishRepository {

	long nextID = 1;

	@Override
	protected Long newPK(Dish entity) {
		return ++nextID;
	}

}
