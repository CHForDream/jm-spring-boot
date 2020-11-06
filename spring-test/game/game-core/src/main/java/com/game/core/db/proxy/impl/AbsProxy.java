package com.game.core.db.proxy.impl;

import java.util.List;

import com.game.core.db.proxy.IDbProxy;
import com.game.core.hibernate.orm.BaseEntity;
import com.google.common.collect.Lists;

@SuppressWarnings("rawtypes")
public abstract class AbsProxy implements IDbProxy {
	/**
	 * 获取下一层代理层
	 */
	private IDbProxy nextProxyLayer = null;

	@Override
	public void setNextProxy(IDbProxy proxy) {
		this.nextProxyLayer = proxy;
	}

	@Override
	public IDbProxy getNextProxy() {
		return this.nextProxyLayer;
	}

	@Override
	public <T extends BaseEntity> boolean insert(T entity) {
		if (nextProxyLayer != null) {
			return nextProxyLayer.insert(entity);
		}
		return false;
	}

	@Override
	public <T extends BaseEntity, ID> T get(Class<T> clazz, ID id) {
		if (nextProxyLayer != null) {
			return nextProxyLayer.get(clazz, id);
		}
		return null;
	}

	@Override
	public <T extends BaseEntity, ID> T getNotCache(Class<T> clazz, ID id) {
		if (nextProxyLayer != null) {
			return nextProxyLayer.get(clazz, id);
		}
		return null;
	}

	@Override
	public <T extends BaseEntity> boolean update(T entity) {
		if (nextProxyLayer != null) {
			return nextProxyLayer.update(entity);
		}
		return false;
	}

	@Override
	public <T extends BaseEntity> boolean updateAsync(T entity) {
		if (nextProxyLayer != null) {
			return nextProxyLayer.updateAsync(entity);
		}
		return false;
	}

	@Override
	public <T extends BaseEntity> boolean delete(T entity) {
		if (nextProxyLayer != null) {
			return nextProxyLayer.delete(entity);
		}
		return false;
	}

	@Override
	public <T extends BaseEntity> List<T> findAll(Class<T> clazz) {
		if (nextProxyLayer != null) {
			return nextProxyLayer.findAll(clazz);
		}
		return Lists.newArrayList();
	}

	@Override
	public <T extends BaseEntity> List<T> findAllNotCache(Class<T> clazz) {
		if (nextProxyLayer != null) {
			return nextProxyLayer.findAllNotCache(clazz);
		}
		return Lists.newArrayList();
	}

	@Override
	public <T extends BaseEntity> List<T> findAllByUid(Class<T> clazz, long uid) {
		if (nextProxyLayer != null) {
			return nextProxyLayer.findAllByUid(clazz, uid);
		}
		return Lists.newArrayList();
	}

	@Override
	public <T extends BaseEntity> List<T> findAllByUidNotCache(Class<T> clazz, long uid) {
		if (nextProxyLayer != null) {
			return nextProxyLayer.findAllByUidNotCache(clazz, uid);
		}
		return Lists.newArrayList();
	}

	@Override
	public void updatePlayerData(long uid) {
		if (nextProxyLayer != null) {
			nextProxyLayer.updatePlayerData(uid);
		}
	}

	@Override
	public void updateAllData() {
		if (nextProxyLayer != null) {
			nextProxyLayer.updateAllData();
		}
	}
}
