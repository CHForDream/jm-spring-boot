package com.game.db.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.game.core.hibernate.EntityManagerBaseService;

@Component("RoleDao")
@SuppressWarnings("unchecked")
public class RoleDao extends EntityManagerBaseService {
	/** 查询语句名称：查询当前表中最大的uid */
	public static final String QUERY_GET_MAX_UID = "findMaxUid";

	public long findMaxUid() {
		List<Long> result = getEntityManager().createNamedQuery(QUERY_GET_MAX_UID).getResultList();
//		List<Long> result = dbService.findByNamedQuery(QUERY_GET_MAX_UID);
		if (result == null || result.isEmpty() || result.get(0) == null) {
			return 0L;
		}
		return result.get(0);
	}
}
