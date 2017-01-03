/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.jpa;

import csheets.domain.ChatUser;
import csheets.framework.persistence.repositories.impl.jpa.JpaRepository;
import csheets.persistence.ChatUserRepository;
import javax.persistence.Query;

/**
 *
 * @author nervousdev
 */
public class JpaChatUserRepository extends JpaRepository<ChatUser, Long> implements ChatUserRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public ChatUser getChatUser() {
		final Query q = entityManager().
			createQuery("SELECT c FROM ChatUser c", ChatUser.class);
		return (ChatUser) q.getSingleResult();
	}

	@Override
	public boolean hasOneChatUser() {
		final Query q = entityManager().
			createQuery("SELECT COUNT(c.id) FROM ChatUser c", ChatUser.class);
		long res = (Long) q.getSingleResult();
		return res == 1;
	}

}
