/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapapp;

import eapli.ecafeteria.application.DefineDishController;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import java.math.BigDecimal;

public class DishBootstrap implements Action {

    @Override
    public boolean execute() {
        DishType dishType1 = PersistenceContext.repositories().dishTypes().
                findById(1L);
        DishType dishType2 = PersistenceContext.repositories().dishTypes().
                findById(2L);
        DishType dishType3 = PersistenceContext.repositories().dishTypes().
                findById(3L);

        register(dishType1, "Salada de Agrião", 100, 12.30, 5.5, 4.50);
        register(dishType1, "Estrogonofe de carne de soja", 130, 15.60, 6.6, 7.2);
        register(dishType1, "Legumes de inverno com limão", 80, 8.0, 2.0, 9.0);

        register(dishType2, "Bacalhau à Brás", 700, 12.30, 65.8, 10.50);
        register(dishType2, "Salada Russa", 800, 14.20, 20.0, 7.0);
        register(dishType2, "Sardinha Assada", 900, 16.0, 24.4, 12.7);

        register(dishType3, "Rojões", 600, 14.0, 17.9, 9.9);
        register(dishType3, "Bifinhos de Frango", 300, 4.4, 2.2, 5.6);
        register(dishType3, "Costeleta à Salsicheira", 450, 12.30, 30.4, 8.50);

        return false;
    }

    private void register(DishType dishType, String name, double calories,
            double amountSalt, double amountFat, double price) {
        final DefineDishController controller = new DefineDishController();
        try {
            controller.
                    defineDish(dishType, name, calories, amountSalt, amountFat, new BigDecimal(price));
        } catch (final Exception e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
        }
    }
}
