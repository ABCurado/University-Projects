/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence;

import csheets.domain.Contact;
import csheets.domain.List;
import csheets.framework.persistence.repositories.Repository;
import java.util.Calendar;

/**
 *
 * @author Rui Bento
 */
public interface ListRepository extends Repository<List, Long> {

	public Iterable<List> listsByContact(Contact contact);

	public Iterable<List> listVersions(List list);

	public Iterable<List> allPrincipal();

	public Iterable<List> search(Calendar startdate, Calendar endDate,
								 String text, boolean content);

}
