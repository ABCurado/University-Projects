/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class PublishMenuController {
    
    public Iterable<Menu> allMenus() {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

        final MenuRepository menuRepository = PersistenceContext.repositories().menu();
        return menuRepository.all();
    }
    
    public void confirm(Menu menu){
        Menu menuToPublish = menu;
        menuToPublish.defineMenuAsPublished();
    }
    
}
