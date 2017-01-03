/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.Kitchen.KitchenAlert;
import eapli.ecafeteria.persistence.KitchenAlertRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;

/**
 *
 * @author Joao Martins
 */
public class JpaKitchenAlertRepository extends JpaRepository<KitchenAlert, Long> implements KitchenAlertRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}
}
