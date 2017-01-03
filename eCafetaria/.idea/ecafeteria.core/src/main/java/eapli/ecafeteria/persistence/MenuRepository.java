/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.persistence.repositories.Repository;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author João Martins
 */
public interface MenuRepository extends Repository<Menu, Long> {

	public List<Menu> allMenu();

	public List<Menu> beforeDate(Calendar date);
}
