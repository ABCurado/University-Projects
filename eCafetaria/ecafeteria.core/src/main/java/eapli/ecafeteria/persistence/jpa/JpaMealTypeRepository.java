/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import java.util.Calendar;
import javax.persistence.Query;

/**
 *
 * @author João Martins
 */
public class JpaMealTypeRepository extends JpaRepository<MealType, Long> implements MealTypeRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public MealType findByDesignation(String designation) {
		final Query q = entityManager().
			createQuery("select mt from MealType mt where mt.designation.mealTypeName=:newDesignation", MealType.class);
		q.setParameter("newDesignation", designation);

		return (MealType) q.getSingleResult();
	}

	@Override
	public MealType findByDefault() {
		if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 15
			|| (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 15 && Calendar.
			getInstance().get(Calendar.MINUTE) == 0)) {
			final Query q = entityManager().
				createQuery("select mt from MealType mt where mt.designation.mealTypeName like 'Almoço'", MealType.class);

			return (MealType) q.getSingleResult();
		} else {
			final Query q = entityManager().
				createQuery("select mt from MealType mt where mt.designation.mealTypeName like 'Jantar'", MealType.class);
			return (MealType) q.getSingleResult();
		}

	}

}
