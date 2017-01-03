/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author João Martins
 */
public class InMemoryMealTypeRepository extends InMemoryRepository<MealType, Long> implements MealTypeRepository {

	long nextID = 1;

	@Override
	protected Long newPK(MealType entity) {
		return ++nextID;
	}

	@Override
	public MealType findByDesignation(String designation) {
		for (MealType m : this.all()) {
			if (m.designation().equals(designation)) {
				return m;
			}
		}
		return null;
	}

	@Override
	public MealType findByDefault() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
