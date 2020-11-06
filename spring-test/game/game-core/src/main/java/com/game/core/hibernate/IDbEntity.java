package com.game.core.hibernate;

/**
 * 基础的实体接口
 *
 *
 * @param <ID>
 */
public interface IDbEntity<ID> {
	ID getId();

	void setId(ID id);
}