package eapli.ecafeteria.bootstrapapp;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;

public class MealTypeBootstrap implements Action {

    @Override
    public boolean execute() {
        register();
        return false;
    }

    private void register() {
        try {
            final MealTypeRepository repo = PersistenceContext.repositories().
                    mealTypes();

            MealType almoco = new MealType("Almoço", 10, 10);
            MealType jantar = new MealType("Jantar", 16, 16);
            repo.save(jantar);
            repo.save(almoco);
        } catch (final Exception e) {
			// ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
        }
    }
}
