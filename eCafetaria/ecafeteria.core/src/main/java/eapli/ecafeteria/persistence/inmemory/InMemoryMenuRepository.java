/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import eapli.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author João Martins
 */
public class InMemoryMenuRepository extends InMemoryRepository<Menu, Long> implements MenuRepository {

	long nextID = 1;

	@Override
	protected Long newPK(Menu entity) {
		return ++nextID;
	}

	@Override
	public Iterable<Menu> listUnPublishedMenus() {
		List<Menu> menus = new ArrayList();
		for (Menu menu : this.all()) {
			if (!menu.isPublished()) {
				menus.add(menu);
			}
		}
		return menus;
	}

	@Override
	public Iterable<Menu> listPublishedMenus() {
		List<Menu> menus = new ArrayList();
		for (Menu menu : this.all()) {
			if (menu.isPublished()) {
				menus.add(menu);
			}
		}
		return menus;
	}

	@Override
	public Iterable<Menu> menusCanBePublished() {
		List<Menu> publishedMenus = new ArrayList();
		nextPublishedMenus().iterator().forEachRemaining(publishedMenus::add);
		List<Menu> menus = new ArrayList();
		for (Menu menu : this.nextNotPublishedMenus()) {
			List<Meal> meals = new ArrayList((Collection) PersistenceContext.
				repositories().meals().mealsOfMenu(menu));
			if (!meals.isEmpty()) {
				for (Meal meal : meals) {
					if (meal.numberDishesPlanned() < 1) {
						return new ArrayList();
					}
				}
				boolean noOverlappingDates = true;
				for (Menu publishedMenu : publishedMenus) {
					if (DateTime.overlappingDates(menu.startDate(), menu.
												  endDate(), publishedMenu.
												  startDate(), publishedMenu.
												  endDate())) {
						noOverlappingDates = false;
						break;
					}
				}
				if (noOverlappingDates) {
					menus.add(menu);
				}
			}
		}
		return menus;
	}

	@Override
	public Iterable<Menu> publishedMenuBetweenDate(Calendar initialDate,
												   Calendar finalDate) {
		List<Menu> menus = new ArrayList();
		for (Menu menu : this.listPublishedMenus()) {
			if (DateTime.isSameDate(menu.startDate(), initialDate) && DateTime.
				isSameDate(menu.endDate(), finalDate)) {
				menus.add(menu);
			}
		}
		return menus;
	}

	@Override
	public Iterable<Menu> publishedMenuBetweenDates(Calendar date) {
		List<Menu> menus = new ArrayList();
		for (Menu menu : this.listPublishedMenus()) {
			if (DateTime.isBetweenDates(menu.startDate(), menu.endDate(), date)) {
				menus.add(menu);
			}
		}
		return menus;
	}

	@Override
	public Iterable<Menu> notPublishedMenuBetweenDates(Calendar date) {
		List<Menu> menus = new ArrayList();
		for (Menu menu : this.listUnPublishedMenus()) {
			if (DateTime.isBetweenDates(menu.startDate(), menu.endDate(), date)) {
				menus.add(menu);
			}
		}
		return menus;
	}

	@Override
	public Iterable<Menu> nextPublishedMenus() {
		Calendar date = DateTime.now();
		List<Menu> menus = new ArrayList();
		for (Menu menu : this.listPublishedMenus()) {
			if (DateTime.isSameDate(date, menu.startDate()) || DateTime.
				isFutureDate(menu.startDate(), date)) {
				menus.add(menu);
			}
		}
		return menus;
	}

	@Override
	public Iterable<Menu> nextNotPublishedMenus() {
		Calendar date = DateTime.now();
		List<Menu> menus = new ArrayList();
		for (Menu menu : this.listUnPublishedMenus()) {
			if (DateTime.isSameDate(date, menu.startDate()) || DateTime.
				isFutureDate(menu.startDate(), date)) {
				menus.add(menu);
			}
		}
		return menus;
	}
}
