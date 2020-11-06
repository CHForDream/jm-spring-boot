package com.game.db.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;
import com.game.utils.JsonUtil;
import com.game.utils.StringUtils;
import com.google.common.collect.Lists;

/**
 * 文件名：RoleEntity.java
 * <p>
 * 功能：1
 * <p>
 * 作者：Builded by AppTools.
 */

@Entity
@Table(name = "t_role_battle")
public class RoleBattleEntity extends BaseEntity<Long> {

	/** 英雄 */
	private int heroId = 0;
	/** 推荐英雄 (不存库) */
	private int recommendHeroId = 0;
	/** 通关普通副本的次数 */
	private int normalDupTimes = 0;
	/** 通关精英副本的次数 */
	private int hardDupTimes = 0;
	/** pve 战前选择道具Id(Json格式:[itemId,itemId]) */
	private String pveItemIds;
	/** pve 战前选择道具IdList(不存库) */
	private List<Integer> pveItemIdList = Lists.newArrayList();
	/** 副本 战前选择道具Id(Json格式:[itemId,itemId]) */
	private String dupItemIds;
	/** 副本 战前选择道具IdList(不存库) */
	private List<Integer> dupItemIdList = Lists.newArrayList();
	/** 精英副本 战前选择道具Id(Json格式:[itemId,itemId]) */
	private String eliteItemIds;
	/** 精英副本 战前选择道具IdList(不存库) */
	private List<Integer> eliteItemIdList = Lists.newArrayList();

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

	@Column(columnDefinition = " int default 0")
	public int getHeroId() {
		return heroId;
	}

	@Transient
	public int getRecommendHeroId() {
		return recommendHeroId;
	}

	@Column(columnDefinition = " int default 0")
	public int getNormalDupTimes() {
		return normalDupTimes;
	}

	@Column(columnDefinition = " int default 0")
	public int getHardDupTimes() {
		return hardDupTimes;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getPveItemIds() {
		if (!pveItemIdList.isEmpty()) {
			pveItemIds = JsonUtil.toJson(pveItemIdList);
		}
		return pveItemIds;
	}

	@Transient
	public List<Integer> getPveItemIdList() {
		return pveItemIdList;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getDupItemIds() {
		if (!dupItemIdList.isEmpty()) {
			dupItemIds = JsonUtil.toJson(dupItemIdList);
		}
		return dupItemIds;
	}

	@Transient
	public List<Integer> getDupItemIdList() {
		return dupItemIdList;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getEliteItemIds() {
		if (!eliteItemIdList.isEmpty()) {
			eliteItemIds = JsonUtil.toJson(eliteItemIdList);
		}
		return eliteItemIds;
	}

	@Transient
	public List<Integer> getEliteItemIdList() {
		return eliteItemIdList;
	}

	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}

	public void setRecommendHeroId(int recommendHeroId) {
		this.recommendHeroId = recommendHeroId;
	}

	public void setNormalDupTimes(int normalDupTimes) {
		this.normalDupTimes = normalDupTimes;
	}

	public void setHardDupTimes(int hardDupTimes) {
		this.hardDupTimes = hardDupTimes;
	}

	public void setPveItemIds(String pveItemIds) {
		if (!StringUtils.isEmpty(pveItemIds)) {
			this.pveItemIdList = JsonUtil.toList(pveItemIds, Integer.class);
		}
		this.pveItemIds = pveItemIds;
	}

	public void setDupItemIds(String dupItemIds) {
		if (!StringUtils.isEmpty(dupItemIds)) {
			this.dupItemIdList = JsonUtil.toList(dupItemIds, Integer.class);
		}
		this.dupItemIds = dupItemIds;
	}

	public void setEliteItemIds(String eliteItemIds) {
		if (!StringUtils.isEmpty(eliteItemIds)) {
			this.eliteItemIdList = JsonUtil.toList(eliteItemIds, Integer.class);
		}
		this.eliteItemIds = eliteItemIds;
	}
}
