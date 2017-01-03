/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.inmemory;

import csheets.domain.ChatUser;
import csheets.framework.persistence.repositories.impl.immemory.InMemoryRepository;
import csheets.persistence.ChatUserRepository;

/**
 *
 * @author nervousdev
 */
public class InMemoryChatUserRepository extends InMemoryRepository<ChatUser, Long>
	implements ChatUserRepository {

	long nextID = 1;

	@Override
	protected Long newPK(ChatUser entity) {
		return ++nextID;
	}

	@Override
	public ChatUser getChatUser() {
		return this.all().iterator().next();
	}

	@Override
	public boolean hasOneChatUser() {
		return this.size() == 1;
	}
}
