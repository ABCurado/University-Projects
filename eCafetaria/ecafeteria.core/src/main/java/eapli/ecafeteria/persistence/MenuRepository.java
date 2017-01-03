/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.persistence.repositories.Repository;
import java.util.Calendar;

/**
 *
 * @author João Martins
 */
public interface MenuRepository extends Repository<Menu, Long> {

	public Iterable<Menu> menusCanBePublished();

	public Iterable<Menu> nextPublishedMenus();

	public Iterable<Menu> publishedMenuBetweenDate(Calendar initialDate,
												   Calendar finalDate);

	public Iterable<Menu> publishedMenuBetweenDates(Calendar date);

	public Iterable<Menu> listUnPublishedMenus();

	public Iterable<Menu> listPublishedMenus();

	public Iterable<Menu> notPublishedMenuBetweenDates(Calendar date);

	public Iterable<Menu> nextNotPublishedMenus();
}
