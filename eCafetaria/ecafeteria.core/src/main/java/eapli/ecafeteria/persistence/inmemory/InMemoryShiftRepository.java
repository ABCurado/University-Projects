/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Calendar;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class InMemoryShiftRepository extends InMemoryRepository<Shift, Long> implements ShiftRepository {

	long nextID = 1;

	@Override
	protected Long newPK(Shift entity) {
		return ++nextID;
	}

	@Override
	public Shift findByOpenedCashRegister(CashRegister cashRegister) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Shift openWithMealType(MealType mealType, Calendar date) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Shift isOpenedByUser(SystemUser currentUser) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean cantOpen(MealType mt, Calendar date) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
