/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.inmemory;

import csheets.domain.Address;
import csheets.framework.persistence.repositories.impl.immemory.InMemoryRepository;
import csheets.persistence.AddressRepository;

/**
 *
 * @author Martins
 */
class InMemoryAddressRepository extends InMemoryRepository<Address, Long> implements AddressRepository {

	long nextID = 1;

	@Override
	protected Long newPK(Address entity) {
		return ++nextID;
	}

}
