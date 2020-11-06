package com.game.db.entity;

import java.util.List;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;
import com.game.db.vo.EliteVo;
import com.game.utils.JsonUtil;
import com.game.utils.StringUtils;
import com.google.common.collect.Maps;

@Entity
@Table(name = "t_role_elite")
public class RoleEliteEntity extends BaseEntity<Long> {
	/** 精英副本Json信息 */
	private String eliteInfo;
	/** 精英副本Map(不存库) */
	private TreeMap<Integer, EliteVo> eliteMap = Maps.newTreeMap();

	@Transient
	@Override
	public Long getId() {
		return uid;
	}

	@Id
	@Override
	public long getUid() {
		return uid;
	}

	@Override
	public void setId(Long uid) {
		this.uid = uid;
	}

	/**
	 * 数据存储时调用；业务逻辑中不推荐使用，数据操作使用eliteMap。
	 * 
	 * @param eliteInfo
	 */
	@Column(columnDefinition = "Text")
	public String getEliteInfo() {
		if (!eliteMap.isEmpty()) {
			eliteInfo = JsonUtil.toJson(eliteMap.values());
		}
		return eliteInfo;
	}

	/**
	 * 读取数据库数据时调用；业务逻辑中不推荐使用，数据操作使用eliteMap。
	 * 
	 * @param eliteInfo
	 */
	public void setEliteInfo(String eliteInfo) {
		if (!StringUtils.isEmpty(eliteInfo)) {
			eliteMap.clear();
			List<EliteVo> eliteList = JsonUtil.toList(eliteInfo, EliteVo.class);
			for (EliteVo eliteVo : eliteList) {
				eliteMap.put(eliteVo.getEliteId(), eliteVo);
			}
		}
		this.eliteInfo = eliteInfo;
	}

	@Transient
	public TreeMap<Integer, EliteVo> getEliteMap() {
		return eliteMap;
	}
}
