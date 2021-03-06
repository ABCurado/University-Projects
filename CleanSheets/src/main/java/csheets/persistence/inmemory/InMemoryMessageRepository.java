/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.inmemory;

import csheets.ext.chat.domain.Message;
import csheets.framework.persistence.repositories.impl.immemory.InMemoryRepository;
import csheets.persistence.MessageRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class InMemoryMessageRepository extends InMemoryRepository<Message, Long> implements MessageRepository {

	long nextID = 1;

	@Override
	protected Long newPK(Message entity) {
		return ++nextID;
	}

	@Override
	public Iterable<Message> roots(List<Message> messages) {
		List<Message> roots = new ArrayList(messages);
		for (Message message : messages) {
			for (Message answer : message.answers()) {
				roots.remove(answer);
			}
		}
		if (!roots.isEmpty()) {
			Collections.sort(roots, new Comparator<Message>() {
							 @Override
							 public int compare(Message msg1, Message msg2) {
								 return msg2.date().compareTo(msg1.date());
							 }
						 });
		}
		return roots;
	}

	@Override
	public Iterable<Message> messages(String host, String name,
									  Message.Type type) {
		List<Message> list = new ArrayList();
		for (Message message : this.all()) {
			if (message.type() == type && message.host().equals(host) && message.
				name().equals(name)) {
				list.add(message);
			}
		}
		return list;
	}

}
