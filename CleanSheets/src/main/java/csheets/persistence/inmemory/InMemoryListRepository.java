/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.inmemory;

import csheets.domain.Contact;
import csheets.domain.List;
import csheets.domain.List.ListLine;
import csheets.framework.persistence.repositories.impl.immemory.InMemoryRepository;
import csheets.persistence.ListRepository;
import csheets.support.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Rui Bento
 */
class InMemoryListRepository extends InMemoryRepository<List, Long>
	implements ListRepository {

	long nextID = 1;

	@Override
	protected Long newPK(List entity) {
		return ++nextID;
	}

	@Override
	public Iterable<List> listsByContact(Contact contact) {
		java.util.List<List> lists = new ArrayList<>();
		for (List l : this.all()) {
			if (l.getContact().equals(contact)
				&& !l.isDeleted()
				&& l.isLatestVersion()) {
				lists.add(l);
			}
		}
		return lists;
	}

	@Override
	public Iterable<List> listVersions(List list) {
		java.util.List<List> lists = new ArrayList<>();
		for (List l : this.all()) {
			if (l.sameNotation(list)) {
				lists.add(l);
			}
		}
		return lists;
	}

	@Override
	public Iterable<List> search(Calendar startDate, Calendar endDate,
								 String text, boolean content) {
		Iterable<List> list = searchDates(startDate, endDate);
		if (text != null && !text.isEmpty()) {
			list = this.search(list, text, content);
		}
		return list;
	}

	public Iterable<List> search(Iterable<List> lists, String expression,
								 Boolean content) {
		Set<List> results = new HashSet();
		for (List list : lists) {
			if (!content) {
				if (list.getTitle().matches(expression)) {
					results.add(list);
				}
			} else {
				for (ListLine line : list.getLines()) {
					if (line.getText().matches(expression)) {
						results.add(list);
					}
				}
			}
		}
		return results;
	}

	public Iterable<List> searchDates(Calendar startDate, Calendar endDate) {
		if (startDate == null && endDate == null) {
			return this.allPrincipal();
		}
		java.util.List<List> lists = new ArrayList<>();
		if (startDate != null && endDate != null) {
			for (List list : this.all()) {
				if (DateTime.isBetweenDates(startDate, endDate, list.
											getTimeCreated()) && !list.
					isDeleted() && list.isLatestVersion()) {
					lists.add(list);
				}
			}
		}
		if (startDate == null) {
			for (List list : this.all()) {
				if (DateTime.isPreviousDate(list.getTimeCreated(), endDate) && !list.
					isDeleted() && list.isLatestVersion()) {
					lists.add(list);
				}
			}
		}
		if (endDate == null) {
			for (List list : this.all()) {
				if (DateTime.isFutureDate(list.getTimeCreated(), startDate) && !list.
					isDeleted() && list.isLatestVersion()) {
					lists.add(list);
				}
			}
		}
		return lists;
	}

	@Override
	public Iterable<List> allPrincipal() {
		java.util.List<List> lists = new ArrayList<>();
		for (List l : this.all()) {
			if (!l.isDeleted()
				&& l.isLatestVersion()) {
				lists.add(l);
			}
		}
		return lists;
	}

}
