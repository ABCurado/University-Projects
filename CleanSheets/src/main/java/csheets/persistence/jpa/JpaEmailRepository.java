/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.jpa;

import csheets.ext.email.InformationEmailSent;
import csheets.framework.persistence.repositories.impl.jpa.JpaRepository;
import csheets.persistence.EmailRepository;

/**
 *
 * @author Gabriel
 */
public class JpaEmailRepository extends JpaRepository<InformationEmailSent, Long> implements EmailRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

}
