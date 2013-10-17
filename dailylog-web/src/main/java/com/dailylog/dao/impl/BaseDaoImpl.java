package com.dailylog.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Repository;

import com.dailylog.dao.BaseDao;

@Repository
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	protected SessionFactory sessionFactory;

	/**
     *
     */
	public BaseDaoImpl() {
		super();
	}

	/**
	 * insert a new entity. create- and lastmodified-date is set with current
	 * time.
	 * 
	 * @param entity
	 *            - detached entity object
	 */
	public void save(T entity) {
		if (null == entity) {
			throw new InvalidDataAccessResourceUsageException("entity must not be null");
		}

		sessionFactory.getCurrentSession().save(entity);
	}

	/**
	 * inserts a new detached entity or updates if it already exists. create-
	 * and update-date are set automatically.
	 * 
	 * @param entity
	 *            - entity object to be inserted or updated
	 */
	public void saveOrUpdate(T entity) {
		if (null == entity) {
			throw new InvalidDataAccessResourceUsageException("entity must not be null");
		}

		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		if (null == entity) {
			throw new InvalidDataAccessResourceUsageException("entity must not be null");
		}
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();
	}

	public void detach(T entity) {
		if (null == entity) {
			throw new InvalidDataAccessResourceUsageException("entity must not be null");
		}
		sessionFactory.getCurrentSession().evict(entity);
	}

	public void refresh(T entity) {
		if (null == entity) {
			throw new InvalidDataAccessResourceUsageException("entity must not be null");
		}
		sessionFactory.getCurrentSession().refresh(entity);
	}

	@SuppressWarnings("unchecked")
	public T merge(T entity) {
		if (null == entity) {
			throw new InvalidDataAccessResourceUsageException("entity must not be null");
		}
		return (T) sessionFactory.getCurrentSession().merge(entity);
	}
}
