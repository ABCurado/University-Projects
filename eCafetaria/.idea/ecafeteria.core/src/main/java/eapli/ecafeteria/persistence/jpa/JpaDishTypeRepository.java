package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;

/**
 * Created by MCN on 29/03/2016.
 */
class JpaDishTypeRepository extends JpaRepository<DishType, Long> implements DishTypeRepository {

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }

    @Override
    public Iterable<DishType> activeDishTypes() {
        return match("e.active=true");
    }
}
