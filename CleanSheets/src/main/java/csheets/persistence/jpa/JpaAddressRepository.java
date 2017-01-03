/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.jpa;

import csheets.domain.Address;
import csheets.framework.persistence.repositories.impl.jpa.JpaRepository;
import csheets.persistence.AddressRepository;

/**
 *
 * @author Martins
 */
class JpaAddressRepository extends JpaRepository<Address, Long> implements AddressRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public void delete(Address entity) {
		for (Address address : this.all()) {
			if (address.equals(entity)) {
				super.delete(entity);
			}
		}
	}
}
