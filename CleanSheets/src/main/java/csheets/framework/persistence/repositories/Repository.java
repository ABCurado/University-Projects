package csheets.framework.persistence.repositories;

/**
 * A generic interface for repositories.
 *
 * @param <T> the class we want to manage in the repository
 * @param <K> the class denoting the primary key of the entity
 * @author Paulo Gandra Sousa
 */
public interface Repository<T, K> {

    /**
     * Saves an entity either by creating it or updating it in the persistence.
     * store.
     *
     * @param entity Entity to save.
     * @return The entity stored.
     */
    T save(T entity);

    /**
     * Creates a new an entity in the persistence layer. if the entity already
     * exists it will throw an exception.
     *
     * FIXME check which exception to throw. it should not be a persistence
     * layer exception.
     *
     * @param entity Entity
     * @return True if added, false otherwise.
     * @throws csheets.framework.persistence.repositories.DataIntegrityViolationException Data Integrity exception.
     */
    boolean add(T entity) throws DataIntegrityViolationException;

    /**
     * gets all entities from the repository.
     *
     * @return Returns all entities from the reposioty.
     */
    
    Iterable<T> all();

    /**
     * gets the entity with the specified id
     *
     * @param id Find the entity with the given identification.
     * @return Entity with the given identification.
     */
    T findById(K id);

    /**
     * returns the number of entities in the repository.
     *
     * @return The number of entities in the repository.
     */
    long size();

    void delete(T entity);
}
