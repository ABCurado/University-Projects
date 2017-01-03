/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.persistence.repositories;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Paulo Gandra Sousa
 */
public interface DeleteableRepository<T, K> extends Repository<T, K> {

	/**
	 * removes the specified entity from the repository.
	 *
	 * @param entity
	 * @throws eapli.framework.persistence.DataIntegrityViolationException
     * @throws eapli.framework.persistence.DataConcurrencyException
	 */
	@Override
	void delete(T entity) throws DataIntegrityViolationException, DataConcurrencyException;

	/**
	 * Removes the entity with the specified ID from the repository.
	 *
	 * @param entityId
	 * @throws eapli.framework.persistence.DataIntegrityViolationException
     * @throws eapli.framework.persistence.DataConcurrencyException
	 */
	void deleteById(K entityId) throws DataIntegrityViolationException, DataConcurrencyException;
}
