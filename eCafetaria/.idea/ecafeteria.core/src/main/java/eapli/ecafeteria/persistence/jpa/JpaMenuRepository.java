/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import java.util.Calendar;
import java.util.List;

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
	public List<Menu> allMenu() {
		return null;
	}

	@Override
	public List<Menu> beforeDate(Calendar date) {
		return null;
	}

}
