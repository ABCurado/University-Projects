package eapli.framework.persistence.repositories.impl.inmemory;

import eapli.framework.persistence.repositories.DeleteableRepository;
import eapli.framework.persistence.repositories.IterableRepository;
import eapli.framework.persistence.repositories.Repository;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by nuno on 20/03/16.
 */
public abstract class InMemoryRepository<T, K>
        implements Repository<T, K>, IterableRepository<T, K>, DeleteableRepository<T, K> {

    protected final Map<K, T> repository = new HashMap<>();

    @Override
    public void delete(T entity) {
        this.repository.remove(entity);
    }

    @Override
    public void deleteById(K entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator(int pagesize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T save(T entity) {
        this.repository.put(newPK(entity), entity);
        return entity;
    }

    @Override
    public Iterable<T> all() {
        return this.repository.values();
    }

    /**
     * This method is used for searching a list without using Optional and
     * Streams, thus returning null when no element is found.
     *
     * @param id K identifier for object
     * @return T if object identified by K is found, otherwise returns null.
     */
    @Override
    public T findById(K id) {
        return this.repository.get(id);
    }

    /**
     * This method is only used for showing the usage of Optional and streams to
     * avoid returning null. In either case, the client code must check for
     * NoSuchElementException.
     *
     * @param id Identifier to look for
     * @return T
     */
    /*
     * @Override public T findById(K id) { Optional<T> value =
     * repository.stream().filter(a -> a.id().equals(id)).findFirst(); T t=
     * value.get(); return t; }
     */
    @Override
    public long size() {
        return this.repository.size();
    }

    @Override
    public Iterator<T> iterator() {
        return this.repository.values().iterator();
    }

    @Override
    public boolean add(T entity) {
        this.repository.put(newPK(entity), entity);
        return true;
    }

    protected abstract K newPK(T entity);
}
