/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.meals.LastMinuteSale;
import eapli.ecafeteria.persistence.LastMinuteSaleRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.List;

/**
 *
 * @author AB
 */
public class InMemoryLastMinuteSaleRepository extends InMemoryRepository<LastMinuteSale, Long>
	implements LastMinuteSaleRepository {

	long nextID = 1;

	@Override
	protected Long newPK(LastMinuteSale entity) {
		return ++nextID;
	}

	@Override
	public List<LastMinuteSale> getLastMinuteSaleByShift(Shift shift) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
