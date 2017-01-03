/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.repositories.Repository;
import java.util.Calendar;

/**
 * Shift Repository
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public interface ShiftRepository extends Repository<Shift, Long> {

	/**
	 * This method returns a new shift that is associated to a specific opened
	 * cash register
	 *
	 * @param cashRegister
	 * @return Shift
	 */
	public Shift findByOpenedCashRegister(CashRegister cashRegister);

	/**
	 * This method return the shift with a specific meal type and date,if this
	 * exists
	 *
	 * @param mealType
	 * @param date
	 * @return Shift
	 */
	public Shift openWithMealType(MealType mealType, Calendar date);

	/**
	 * This method return the shift that is being used
	 *
	 * @param currentUser
	 * @return Shift
	 */
	public Shift isOpenedByUser(SystemUser currentUser);

	/**
	 * This method checks if the shift can be opened
	 *
	 * @param mt meal type
	 * @param date date
	 * @return true if shift can be opened ,otherwise return false
	 */
	public boolean cantOpen(MealType mt, Calendar date);

}
