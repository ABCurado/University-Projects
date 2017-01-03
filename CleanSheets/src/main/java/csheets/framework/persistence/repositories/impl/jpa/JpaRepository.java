package csheets.framework.persistence.repositories.impl.jpa;

import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.framework.persistence.repositories.DeleteableRepository;
import csheets.framework.persistence.repositories.IterableRepository;
import csheets.framework.persistence.repositories.Repository;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.RollbackException;

/**
 * An utility abstract class for implementing JPA repositories.
 *
 * @author Paulo Gandra Sousa
 *
 * <p>
 * based on <a href=
 *         "http://stackoverflow.com/questions/3888575/single-dao-generic-crud-methods-jpa-hibernate-spring">
 * stackoverflow</a> and on
 * <a href="https://burtbeckwith.com/blog/?p=40">burtbeckwith</a>.
 * <p>
 * also have a look at
 * <a href="http://blog.xebia.com/tag/jpa-implementation-patterns/">JPA
 * implementation patterns</a>
 *
 * @param <T> the entity type that we want to build a repository for
 * @param <K> the key type of the entity
 */
public abstract class JpaRepository<T, K extends Serializable>
	implements Repository<T, K>, IterableRepository<T, K>, DeleteableRepository<T, K> {

	@PersistenceUnit
	private static EntityManagerFactory emFactory;
	private static final int DEFAULT_PAGESIZE = 20;

	private final Class<T> entityClass;
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public JpaRepository() {
		final ParameterizedType genericSuperclass = (ParameterizedType) getClass().
			getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	protected EntityManagerFactory entityManagerFactory() {
		if (emFactory == null) {
			emFactory = Persistence.
				createEntityManagerFactory(persistenceUnitName());
		}
		return emFactory;
	}

	protected EntityManager entityManager() {
		if (this.entityManager == null || !this.entityManager.isOpen()) {
			this.entityManager = entityManagerFactory().createEntityManager();
		}
		return this.entityManager;
	}

	/**
	 * adds a new entity to the persistence store
	 *
	 * @param entity Entity.
	 * @return the newly created persistent object
	 */
	public T create(T entity) {
		this.entityManager().persist(entity);
		return entity;
	}

	/**
	 * reads an entity given its K
	 *
	 * @param id Entity identification.
	 * @return Entity.
	 */
	public T read(K id) {
		return this.entityManager().find(this.entityClass, id);
	}

	/**
	 * reads an entity given its K
	 *
	 * @param id Entity identification.
	 * @return Entity.
	 */
	@Override
	public T findById(K id) {
		return read(id);
	}
        
        /**
         * Updates an entity.
         * 
         * @param entity Entity.
         * @return Entity.
         */
	public T update(T entity) {
		return entityManager().merge(entity);
	}

	/**
	 * removes the object from the persistence storage. the object reference is
	 * still valid but the persisted entity is/will be deleted
	 *
	 * @param entity Entity.
	 */
	@Override
	public void delete(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException();
		}
		final EntityManager em = entityManager();
		final EntityTransaction tx = em.getTransaction();
		tx.begin();
		entity = entityManager().merge(entity);
		entityManager().remove(entity);
		tx.commit();
		em.close();
	}

	/**
	 * Removes the entity with the specified ID from the repository.
	 *
	 * @param entityId Entity identification.
	 */
	@Override
	public void deleteById(K entityId) {
		// TODO this is not efficient...
		final T entity = findById(entityId);
		delete(entity);
	}

	/**
	 * returns the number of entities in the persistence store
	 *
	 * @return the number of entities in the persistence store
	 */
	@Override
	public long size() {
		return (Long) entityManager().
			createQuery("SELECT COUNT(*) FROM " + this.entityClass.
				getSimpleName())
			.getSingleResult();
	}

	/**
	 * checks for the existence of an entity with the provided K.
	 *
	 * @param key Entity key.
	 * @return True if it contains the entity, false otherwise.
	 */
	boolean containsEntity(K key) {
		return findById(key) != null;
	}

	/**
	 * adds <b>and commits</b> a new entity to the persistence store
	 *
	 *
	 * TODO it is controversial if the repository class should have explicit
	 * knowledge of when to start a transaction and end it as well as to know
	 * when to open a connection and close it. this is the kind of stuff that
	 * the container (e.g., web server) should handle declaratively
	 *
	 * the following methods open and commit a transaction: add() save()
	 * replace() remove()
	 *
	 * note that other methods in this class just work with the JPA unit of work
	 * and expect the container to begin/commit transactions. they are: create()
	 * update() delete()
	 *
	 * @param entity Entity.
	 * @return the newly created persistent object
	 * @throws DataIntegrityViolationException Data integrity was violated.
	 */
	@Override
	public boolean add(T entity) throws DataIntegrityViolationException {
		if (entity == null) {
			throw new IllegalArgumentException();
		}

		final EntityManager em = entityManager();
		try {
			final EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(entity);
			tx.commit();
		} catch (final RollbackException ex) {
			// TODO need to check and make sure we only throw
			// DataIntegrityViolationException if we get sql state 23505
			throw new DataIntegrityViolationException(ex);
		} finally {
			em.close();
		}
		return true;
	}

	/**
	 * Inserts or updates an entity <b>and commits</b>.
	 *
	 * note that you should reference the return value to use the persisted
	 * entity, as the original object passed as argument might be copied to a
	 * new object
	 *
	 * check <a href=
	 * "http://blog.xebia.com/2009/03/23/jpa-implementation-patterns-saving-detached-entities/">
	 * JPA implementation patterns</a> for a discussion on saveOrUpdate()
	 * behavior and merge()
	 *
	 * @param entity Entity.
	 * @return the persisted entity - might be a different object than the
	 * parameter
	 */
	@Override
	public T save(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException();
		}

		// the following code atempts to do a save or update by checking for
		// persistence exceptions while doing persist()
		// this could be made more efficient if we check if the entity has an
		// autogenerated id
		final EntityManager em = entityManager();
		assert em != null;
		try {
			// transaction will be rolled back if any exception occurs
			// we are especially interested in "detached entity" meaning that
			// the object already exists
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				em.persist(entity);
				tx.commit();
			} catch (final PersistenceException ex) {
				// we need to set up a new transaction if persist() raises an
				// exception
				tx = em.getTransaction();
				tx.begin();
				entity = em.merge(entity);
				tx.commit();
			}
		} finally {
			// we are closing the entity manager here because this code is
			// runing in a non-container managed way. if it was the case to be
			// runing under an application server with a JPA container and
			// managed transactions/sessions, one should not be doing this
			em.close();
		}

		return entity;
	}

	/**
	 * returns the first n entities according to its "natural" order
	 *
	 * @param n Number of entities to retrieve.
	 * @return List of the first number given of entities.
	 */
	@SuppressWarnings("unchecked")
	public List<T> first(int n) {
		final Query q = entityManager().
			createQuery("SELECT e FROM " + this.entityClass.getSimpleName() + " e");
		q.setMaxResults(n);

		return q.getResultList();
	}

	public T first() {
		final List<T> r = first(1);
		return r.isEmpty() ? null : r.get(0);
	}

	public T last() {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	public List<T> page(int pageNumber, int pageSize) {
		final Query q = entityManager().
			createQuery("SELECT e FROM " + this.entityClass.getSimpleName() + " e");
		q.setMaxResults(pageSize);
		q.setFirstResult((pageNumber - 1) * pageSize);

		return q.getResultList();
	}

	/**
	 * an iterator over JPA
	 *
	 * @author Paulo Gandra Sousa
	 *
	 */
	private class JpaPagedIterator implements Iterator<T> {

		private final JpaRepository<T, K> repository;
		private final int pageSize;
		private int currentPageNumber;
		private Iterator<T> currentPage;

		private JpaPagedIterator(JpaRepository<T, K> repository, int pagesize) {
			this.repository = repository;
			this.pageSize = pagesize;
		}

		@Override
		public boolean hasNext() {
			if (needsToLoadPage()) {
				loadNextPage();
			}
			return this.currentPage.hasNext();
		}

		@Override
		public T next() {
			if (needsToLoadPage()) {
				loadNextPage();
			}
			return this.currentPage.next();
		}

		private void loadNextPage() {
			final List<T> page = this.repository.
				page(++this.currentPageNumber, this.pageSize);
			this.currentPage = page.iterator();
		}

		private boolean needsToLoadPage() {
			// either we do not have an iterator yet or we have reached the end
			// of the (current) iterator
			return this.currentPage == null || !this.currentPage.hasNext();
		}
	}

	/**
	 * returns a paged iterator
	 *
	 * @return Iterator of entities.
	 */
	@Override
	public Iterator<T> iterator(int pagesize) {
		return new JpaPagedIterator(this, pagesize);
	}

	@Override
	public Iterator<T> iterator() {
		return new JpaPagedIterator(this, DEFAULT_PAGESIZE);
	}

	@Override
	public List<T> all() {
		// TODO check performance impact of this 'where' clause
		return match("1=1");
	}

	/**
	 * helper method. not to be exposed as public in any situation. the where
	 * clause should use "e" as the query object
	 *
	 * @param where Where clause.
	 * @return List of entities matched.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> match(String where) {
		final String className = this.entityClass.getSimpleName();
		final Query q = entityManager().
			createQuery("SELECT e FROM " + className + " e WHERE " + where);
		return q.getResultList();
	}

	/**
	 * Derived classes should implement this method to return the name of the
	 * persistence unit
	 *
	 * @return the name of the persistence unit
	 */
	protected abstract String persistenceUnitName();
}
