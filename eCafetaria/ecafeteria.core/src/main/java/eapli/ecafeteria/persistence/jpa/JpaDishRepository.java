package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;

/**
 *
 * @author Rui Freitas
 */
public class JpaDishRepository extends JpaRepository<Dish, Long> implements DishRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public Dish findByDesignation(String designation) {
		for (Dish dish : this.all()) {
			if (dish.name().equalsIgnoreCase(designation)) {
				return dish;
			}
		}
		return null;
	}

}
