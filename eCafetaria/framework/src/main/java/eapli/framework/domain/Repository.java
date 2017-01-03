/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain;

import java.io.Serializable;
import java.util.List;

/**
 * A repository is a domain-driven design pattern to abstract the details of
 * persisting domain objects. it exposes a pure domain based interface without
 * leaking details of the implementation of the actual persistence mechanism.
 * there should be one repository per aggregate root only
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 *            the type of the entity
 * @param <ID>
 *            the type of the entity's identity
 */
public interface Repository<T extends AggregateRoot<?>, ID extends Serializable> extends List<T> {

    /**
     * returns the first n entities according to its "natural" order
     *
     * @param n
     * @return
     */
    Iterable<T> first(int n);

    /**
     * returns a page of results using the natural order of the collection
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Iterable<T> page(int pageNumber, int pageSize);

    /**
     * reads an entity given its ID
     *
     * @param id
     * @return
     */
    T read(ID id);

    /**
     * inserts or updates an entity
     *
     * @param entity
     * @return the persisted entity - might be a different object than the
     *         parameter
     */
    T save(T entity);

    /**
     * updates an existing entity in the repository
     *
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * checks for the existence of an entity with the provided ID.
     *
     * @param key
     * @return
     */
    boolean containsEntity(ID key);
}
