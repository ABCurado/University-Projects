package csheets.framework.persistence.repositories;


import java.util.Iterator;

/**
 * A Repository which can be efficiently iterated.
 *
 * the implementation class must provide for an efficient iterator over all
 * entities in the repository. this is particular the case for database-backed
 * up persistence stores where one expects a cursor like behaviour. it is the
 * responsibility of the implementation class to handle disconnected scenarios
 * or not.
 *
 * @author Paulo Gandra Sousa
 * @param <T> the class we want to manage in the repository
 * @param <K> the class denoting the primary key of the entity
 */
public interface IterableRepository<T, K> extends Repository<T, K>, Iterable<T> {

    Iterator<T> iterator(int pagesize);
}
