package com.game.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.game.db.dao.RoleDao;
import com.game.db.dao.ShowIdDao;

/**
 * GameServer用到的数据库访问对象管理器
 * 
 */
@Component("DaoService")
public class DaoService {
	@Autowired
	@Qualifier("RoleDao")
	private RoleDao roleDao;

	@Autowired
	@Qualifier("ShowIdDao")
	private ShowIdDao showIdDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public ShowIdDao getShowIdDao() {
		return showIdDao;
	}

	public void setShowIdDao(ShowIdDao showIdDao) {
		this.showIdDao = showIdDao;
	}
}
