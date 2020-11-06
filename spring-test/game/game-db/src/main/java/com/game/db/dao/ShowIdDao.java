package com.game.db.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.game.core.hibernate.EntityManagerBaseService;

@Component("ShowIdDao")
@SuppressWarnings("unchecked")
public class ShowIdDao extends EntityManagerBaseService {
	/** 查询语句名称：查询当前表中最大的uid */
	public static final String QUERY_GET_MAX_SHOW_ID = "findMaxShowId";

	public long findMaxShowId() {
		List<Long> result = getEntityManager().createNamedQuery(QUERY_GET_MAX_SHOW_ID).getResultList();
//		List<Long> result = dbService.findByNamedQuery(QUERY_GET_MAX_SHOW_ID);
		if (result == null || result.isEmpty() || result.get(0) == null) {
			return 0L;
		}
		return result.get(0);
	}
}
