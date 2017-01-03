/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.framework.persistence.repositories.Repository;

/**
 *
 * @author Rui Freitas
 */
public interface DishRepository extends Repository<Dish, Long> {

	public Dish findByDesignation(String designation);

}
