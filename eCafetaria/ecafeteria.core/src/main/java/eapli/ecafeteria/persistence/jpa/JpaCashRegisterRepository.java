/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegister;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import javax.persistence.Query;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class JpaCashRegisterRepository extends JpaRepository<CashRegister, Long> implements CashRegisterRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public CashRegister findByNumber(String number) {
		final Query q = entityManager().
			createQuery("select cr from CashRegister cr where cr.number=:number", CashRegister.class);
		q.setParameter("number", number);
		return (CashRegister) q.getSingleResult();
	}

}
