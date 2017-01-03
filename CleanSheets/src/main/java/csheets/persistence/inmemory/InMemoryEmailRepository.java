/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.inmemory;

import csheets.ext.email.InformationEmailSent;
import csheets.framework.persistence.repositories.impl.immemory.InMemoryRepository;
import csheets.persistence.EmailRepository;

/**
 *
 * @author Martins
 */
public class InMemoryEmailRepository extends InMemoryRepository<InformationEmailSent, Long>
	implements EmailRepository {

	long nextID = 1;

	@Override
	protected Long newPK(InformationEmailSent entity) {
		return ++nextID;
	}
}
