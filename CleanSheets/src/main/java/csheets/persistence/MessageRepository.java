/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence;

import csheets.ext.chat.domain.Message;
import csheets.framework.persistence.repositories.Repository;
import java.util.List;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public interface MessageRepository extends Repository<Message, Long> {

	public Iterable<Message> roots(List<Message> messages);

	public Iterable<Message> messages(String host, String name,
									  Message.Type type);

}
