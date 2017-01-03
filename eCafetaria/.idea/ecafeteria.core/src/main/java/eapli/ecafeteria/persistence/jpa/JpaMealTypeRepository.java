/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import java.util.Date;

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
	public MealTypeRepository typeMeal(Date date) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Iterable<MealType> activeMealTypes() {
		return match("e.active=true");
	}

}
