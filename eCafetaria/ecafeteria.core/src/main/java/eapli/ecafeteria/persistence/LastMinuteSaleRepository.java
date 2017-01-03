/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.meals.LastMinuteSale;
import eapli.framework.persistence.repositories.Repository;
import java.util.List;

/**
 *
 * @author AB
 */
public interface LastMinuteSaleRepository extends Repository<LastMinuteSale, Long> {

	public List<LastMinuteSale> getLastMinuteSaleByShift(Shift shift);

}
