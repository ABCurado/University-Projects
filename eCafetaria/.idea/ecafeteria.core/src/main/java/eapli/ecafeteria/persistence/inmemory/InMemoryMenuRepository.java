/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
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
	public List<Menu> allMenu() {
		return null;
	}

	@Override
	public List<Menu> beforeDate(Calendar date) {
		return null;
	}

}
