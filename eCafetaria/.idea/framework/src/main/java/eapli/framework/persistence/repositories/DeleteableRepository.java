/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.persistence.repositories;

/**
 *
 * @author Paulo Gandra Sousa
 */
public interface DeleteableRepository<T, K> extends Repository<T, K> {

    /**
     * removes the specified entity from the repository.
     *
     * @param entity
     * @throws UnsuportedOperationException if the delete operation makes no
     * sense for this repository
     */
    void delete(T entity);

    /**
     * Removes the entity with the specified ID from the repository.
     *
     * @param entity
     * @throws UnsuportedOperationException if the delete operation makes no
     * sense for this repository
     */
    void deleteById(K entityId);
}
