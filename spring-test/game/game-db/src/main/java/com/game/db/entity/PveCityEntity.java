package com.game.db.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;
import com.game.utils.JsonUtil;
import com.game.utils.StringUtils;

/**
 * 
 * @author 关卡
 *
 */
@Entity
@Table(name = "t_pvecity")
public class PveCityEntity extends BaseEntity<Long> {

	/** 上一关的关卡id */
	private int lastPveTarget = 0;
	/** 下一关的关卡id */
	private int curPveTarget = 0;
	/** 关卡对应的targetid */
	private int targetId = 0;
	/** 当前关卡的状态 */
	private int curTargetStatus = 0;
	/** 当前所在的城市 */
	private int curCityId = 0;
	/** 玩家所选城市 */
	private int cityId = 0;
	/** 已经开启的城市id */
	private String citys = "";
	/** 副本信息 */
	private String dupInfo = "";
	/** 已领取奖励的城市关卡列表 */
	private String cityAwards;
	/** 已领取奖励的城市副本列表 */
	private String dupAwards;
	/** 已经开启的城市id */
	private List<Integer> cityIds = new ArrayList<Integer>();
	/** 副本对象 */
	private Map<Integer, Map<Integer, Duplicate>> duplicates = new HashMap<Integer, Map<Integer, Duplicate>>();

	/** 已领取奖励的城市关卡列表 */
	private Map<Integer, Integer> cityAwardMap = new HashMap<Integer, Integer>();
	/** 已领取奖励的城市副本列表 */
	private Map<Integer, Integer> dupAwardMap = new HashMap<Integer, Integer>();

	public void addCityId(int cityId) {
		if (cityIds.contains(cityId)) {
			return;
		}
		this.cityIds.add(cityId);
	}

	public void addCityDup(int cityId, int dupId) {
		if (dupId <= 0 || cityId <= 0) {
			return;
		}
		Map<Integer, Duplicate> dupInfos = duplicates.get(cityId);
		if (dupInfos == null) {
			dupInfos = new HashMap<Integer, PveCityEntity.Duplicate>();
			duplicates.put(cityId, dupInfos);
		}
		Duplicate dup = dupInfos.get(dupId);
		if (dup == null) {
			dup = this.new Duplicate();
			dupInfos.put(dupId, dup);
		}
		dup.setDupId(dupId);
	}

	public Duplicate getCityDupInfo(int cityId, int dupId) {
		Map<Integer, Duplicate> dupInfos = duplicates.get(cityId);
		if (dupInfos == null) {
			return null;
		}
		Duplicate dup = dupInfos.get(dupId);
		return dup;
	}

	public Map<Integer, Duplicate> getDupByCity(int cityId) {
		return duplicates.get(cityId);
	}

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

	@Column(columnDefinition = " int default 0")
	public int getLastPveTarget() {
		return lastPveTarget;
	}

	@Column(columnDefinition = " int default 0")
	public int getCurPveTarget() {
		return curPveTarget;
	}

	@Column(columnDefinition = " int default 0")
	public int getTargetId() {
		return targetId;
	}

	@Column(columnDefinition = " int default 0")
	public int getCurTargetStatus() {
		return curTargetStatus;
	}

	@Column(columnDefinition = " int default 0")
	public int getCurCityId() {
		return curCityId;
	}

	@Column(columnDefinition = " int default 0")
	public int getCityId() {
		return cityId;
	}

	@Column(columnDefinition = " TEXT")
	public String getCitys() {
		if (!cityIds.isEmpty()) {
			citys = JsonUtil.toJson(cityIds);
		}
		return citys;
	}

	@Column(columnDefinition = " TEXT")
	public String getDupInfo() {
		if (!duplicates.isEmpty()) {
			dupInfo = JsonUtil.toJson(duplicates);
		}
		return dupInfo;
	}

	@Column(columnDefinition = " TEXT")
	public String getCityAwards() {
		if (!cityAwardMap.isEmpty()) {
			cityAwards = JsonUtil.toJson(cityAwardMap);
		}
		return cityAwards;
	}

	@Column(columnDefinition = " TEXT")
	public String getDupAwards() {
		if (!dupAwardMap.isEmpty()) {
			dupAwards = JsonUtil.toJson(dupAwardMap);
		}
		return dupAwards;
	}

	@Transient
	public List<Integer> getCityIds() {
		return cityIds;
	}

	@Transient
	public Map<Integer, Map<Integer, Duplicate>> getDuplicates() {
		return duplicates;
	}

	@Transient
	public Map<Integer, Integer> getCityAwardMap() {
		return cityAwardMap;
	}

	@Transient
	public Map<Integer, Integer> getDupAwardMap() {
		return dupAwardMap;
	}

	@Override
	public void setId(Long id) {
		this.uid = id;
	}

	public void setLastPveTarget(int lastPveTarget) {
		this.lastPveTarget = lastPveTarget;
	}

	public void setCurPveTarget(int curPveTarget) {
		this.curPveTarget = curPveTarget;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public void setCurTargetStatus(int curTargetStatus) {
		this.curTargetStatus = curTargetStatus;
	}

	public void setCurCityId(int curCityId) {
		this.curCityId = curCityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public void setCitys(String citys) {
		if (!StringUtils.isEmpty(citys)) {
			this.cityIds = JsonUtil.toList(citys, Integer.class);
		}
		this.citys = citys;
	}

	public void setDupInfo(String dupInfo) {
		if (!StringUtils.isEmpty(dupInfo)) {
			this.duplicates = JsonUtil.toMapMapKey(dupInfo, Duplicate.class);
		}
		this.dupInfo = dupInfo;
	}

	public void setCityAwards(String cityAwards) {
		if (!StringUtils.isEmpty(cityAwards)) {
			this.cityAwardMap = JsonUtil.toMapIntegerKey(cityAwards, Integer.class);
		}
		this.cityAwards = cityAwards;
	}

	public void setDupAwards(String dupAwards) {
		if (!StringUtils.isEmpty(dupAwards)) {
			this.dupAwardMap = JsonUtil.toMapIntegerKey(dupAwards, Integer.class);
		}
		this.dupAwards = dupAwards;
	}

	public class Duplicate {
		// 副本id
		private int dupId;
		// 当天打了几次
		private int dupNum;
		// 副本星等
		private int star;

		public int getDupId() {
			return dupId;
		}

		public void setDupId(int dupId) {
			this.dupId = dupId;
		}

		public int getDupNum() {
			return dupNum;
		}

		public void setDupNum(int dupNum) {
			this.dupNum = dupNum;
		}

		public int getStar() {
			return star;
		}

		public void setStar(int star) {
			this.star = star;
		}
	}
}
