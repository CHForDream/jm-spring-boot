package com.game.core.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.Getter;

@Getter
@SuppressWarnings("unchecked")
@Service("EntityManagerBaseService")
public class EntityManagerBaseService implements IService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public <T extends IDbEntity<ID>, ID> T get(Class<T> entityClass, ID id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	@Transactional
	public <T extends IDbEntity<ID>, ID> ID save(T entity) {
		if (!entityManager.contains(entity)) {
			entityManager.persist(entity);
			return entity.getId();
		} else {
			T merge = entityManager.merge(entity);
			entity.setId(merge.getId());
			return merge.getId();
		}
	}

	@Override
	@Transactional
	public <T extends IDbEntity<ID>, ID> boolean update(T entity) {
		entityManager.merge(entity);
		return true;
	}

	@Override
	@Transactional
	public <T extends IDbEntity<ID>, ID> boolean delete(T entity) {
		try {
			return deleteById(entity.getClass(), entity.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public <T extends IDbEntity<ID>, ID> boolean deleteById(Class<T> entityClass, ID id) {
		T entity = get(entityClass, id);
		if (entity != null) {
			entityManager.remove(entity);
			return true;
		}

		return false;
	}

	@Override
	@Transactional
	public <T extends IDbEntity<ID>, ID> List<T> findAll(Class<T> entityClass) {
		return entityManager.createQuery("SELECT o FROM " + entityClass.getSimpleName() + " o").getResultList();
	}

	@Override
	@Transactional
	public <T extends IDbEntity<ID>, ID> List<T> findByUid(Class<T> entityClass, Long uid) {
		return entityManager.createQuery("SELECT o FROM " + entityClass.getSimpleName() + " o WHERE uid=:uid").setParameter("uid", uid).getResultList();
	}

	@Override
	@Transactional
	public void check() {
		entityManager.createQuery("SELECT 1").executeUpdate();
	}
}
