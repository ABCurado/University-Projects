/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.Kitchen.KitchenAlert;
import eapli.ecafeteria.persistence.KitchenAlertRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Joao Martins
 */
class InMemoryKitchenAlertRepository extends InMemoryRepository<KitchenAlert, Long> implements KitchenAlertRepository {

	long nextID = 1;

	@Override
	protected Long newPK(KitchenAlert entity) {
		return ++nextID;
	}

}
