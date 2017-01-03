/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence;

import csheets.domain.ChatUser;
import csheets.framework.persistence.repositories.Repository;

/**
 *
 * @author nervousdev
 */
public interface ChatUserRepository extends Repository<ChatUser, Long> {

	public ChatUser getChatUser();

	public boolean hasOneChatUser();

}
