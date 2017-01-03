package csheets.framework.persistence.repositories.impl.immemory;

import csheets.framework.persistence.repositories.DeleteableRepository;
import csheets.framework.persistence.repositories.IterableRepository;
import csheets.framework.persistence.repositories.Repository;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by nuno on 20/03/16.
 *
 * @param <T> T
 * @param <K> K
 */
public abstract class InMemoryRepository<T, K>
	implements Repository<T, K>, IterableRepository<T, K>, DeleteableRepository<T, K> {

	protected final Map<K, T> repository = new HashMap<>();

	@Override
	public void delete(T entity) {
		this.repository.remove(this.getKey(entity));
	}

	@Override
	public void deleteById(K entityId) {
		throw new UnsupportedOperationException();
	}

	public K getKey(T entity) {
		for (Entry entry : this.repository.entrySet()) {
			if (entry.getValue() == entity) {
				return (K) entry.getKey();
			}
		}
		return null;
	}

	@Override
	public Iterator<T> iterator(int pagesize) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T save(T entity) {
		T outher = (T) this.getKey(entity);
		if (outher == null) {
			this.add(entity);
		}
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
