/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.domain.meals.MenuState;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import eapli.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author João Martins
 */
public class JpaMenuRepository extends JpaRepository<Menu, Long> implements MenuRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public Iterable<Menu> listUnPublishedMenus() {
		final Query q = entityManager().
			createQuery("select m from Menu m where m.menuPeriod.menuState=:state", Menu.class);
		q.setParameter("state", MenuState.notPublished);
		return q.getResultList();
	}

	@Override
	public Iterable<Menu> listPublishedMenus() {
		final Query q = entityManager().
			createQuery("select m from Menu m where m.menuPeriod.menuState=:state", Menu.class);
		q.setParameter("state", MenuState.published);
		return q.getResultList();
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
		/*
		 final Query q = entityManager().
		 createQuery("select m from Menu m where m.menuPeriod.startDate=:initialDate AND m.menuPeriod.endDate=:finalDate AND m.menuPeriod.menuState=:published", Menu.class);
		 q.setParameter("initialDate", initialDate);
		 q.setParameter("finalDate", finalDate);
		 return q.getResultList();
		 */
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
