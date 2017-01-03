package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.stream.Collectors;

/**
 * Created by MCN on 29/03/2016.
 */
public class InMemoryDishTypeRepository extends InMemoryRepository<DishType, Long> implements DishTypeRepository {

    long nextID = 1;

    @Override
    protected Long newPK(DishType entity) {
        return ++nextID;
    }

    @Override
    public Iterable<DishType> activeDishTypes() {
        return repository.values().stream().filter(e -> e.isActive()).collect(Collectors.toList());
    }
}
