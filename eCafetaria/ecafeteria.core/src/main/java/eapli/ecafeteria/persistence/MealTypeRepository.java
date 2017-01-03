/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.repositories.Repository;

/**
 *
 * @author João Martins
 */
public interface MealTypeRepository extends Repository<MealType, Long> {

	public MealType findByDesignation(String designation);

	public MealType findByDefault();
}
