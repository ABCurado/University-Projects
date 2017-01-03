package csheets.framework.persistence.repositories;

/**
 *
 * @author Paulo Gandra Sousa
 * @param <T> the class we want to manage in the repository
 * @param <K> the class denoting the primary key of the entity
 */
public interface DeleteableRepository<T, K> extends Repository<T, K> {

    /**
     * removes the specified entity from the repository.
     *
     * @param entity Entity.
     */
    @Override
    void delete(T entity);

    /**
     * Removes the entity with the specified ID from the repository.
     *
     * @param entityId Entity identification.
     */
    void deleteById(K entityId);
}
