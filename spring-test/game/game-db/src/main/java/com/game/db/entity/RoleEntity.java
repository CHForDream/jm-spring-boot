package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;

/**
 * 文件名：RoleEntity.java
 * <p>
 * 功能：1
 * <p>
 * 作者：Builded by AppTools.
 */

@NamedQuery(name = "findMaxUid", query = "select max(id) from RoleEntity")
@Entity
@Table(name = "t_role")
public class RoleEntity extends BaseEntity<Long> {

	private long lastLoginTime = 0; // 上次登录时间
	private int coin = 0;// 银币
	private int chargeCash = 0;// 钻石(充值货币)
	private int dupStar = 0; // 副本收集的星星
	private int rewardBuleCash;// 赠送的蓝钻

	private String name = null;// 名字
	private String avatar = null;// 头像
	private int sex = 0;// sex
	private String pfId = "";// 平台Id
	private long showId = 0;// 显示Id
	private long createTime = 0;// 账号创建时间
	private int vip = 0; // vip
	private int friendShip = 0;// 好友数量
	private int sendFriendShipTimes = 0; // 发送好友申请次数

	/** 体力 */
	private int power = 0;
	/** 英雄增加体力上限 */
	private int heroPowerLimit = 0;
	/** 宠物增加体力上限 */
	private int petPowerLimit = 0;
	/** 无限体力结束时间(秒) */
	private long noLimitPowerEndTime;
	/** 更新体力的时间 */
	private long lastUpdatePowerTime;
	/** 数据重置时间 */
	private long resetTime = 0;
	/** 玩家离线时间 */
	private long quitTime;

	private int platform;// 平台
	private int channel;// 渠道

	/** PVE 连胜 */
	private int pveConWinNum = 0;

	/** 分享奖励信息(json) */
	private String shareInfo;
	/** 广告历史累计次数 */
	private int adTotalCount;
	/** 分享奖励重置时间 */
	private long shareResetTime;

	private long monthCardEndTime;// 月卡结束时间
	private long monthCardLastRewardTime;// 月卡上次发送奖励时间
	private long monthCardBEndTime;// 月卡B结束时间
	private int subscribeMonthCardB = 0;// 订阅月卡B 1:订阅 0:未订阅
	private int monthCardBNoLimitReceiveNum = 0;// 月卡B今日领取次数

	private int drawLuckNum = 0;// 今日抽奖次数
	private int fabulousNum = 0;// 今天点赞数量
	private int battleCount;// 战斗场次
	private String firstPayStatus;// 首充状态

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

	@Column(columnDefinition = "TEXT")
	public String getShareInfo() {
		return shareInfo;
	}

	public void setShareInfo(String shareInfo) {
		this.shareInfo = shareInfo;
	}

	@Column(columnDefinition = " int default 0")
	public int getAdTotalCount() {
		return adTotalCount;
	}

	public void setAdTotalCount(int adTotalCount) {
		this.adTotalCount = adTotalCount;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getShareResetTime() {
		return shareResetTime;
	}

	public void setShareResetTime(long shareResetTime) {
		this.shareResetTime = shareResetTime;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getResetTime() {
		return resetTime;
	}

	public void setResetTime(long resetTime) {
		this.resetTime = resetTime;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getLastUpdatePowerTime() {
		return lastUpdatePowerTime;
	}

	public void setLastUpdatePowerTime(long lastUpdatePowerTime) {
		this.lastUpdatePowerTime = lastUpdatePowerTime;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getNoLimitPowerEndTime() {
		return noLimitPowerEndTime;
	}

	public void setNoLimitPowerEndTime(long noLimitPowerEndTime) {
		this.noLimitPowerEndTime = noLimitPowerEndTime;
	}

	@Column(columnDefinition = " int default 0")
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@Column(columnDefinition = " int default 0")
	public int getHeroPowerLimit() {
		return heroPowerLimit;
	}

	public void setHeroPowerLimit(int heroPowerLimit) {
		this.heroPowerLimit = heroPowerLimit;
	}

	@Column(columnDefinition = " int default 0")
	public int getPetPowerLimit() {
		return petPowerLimit;
	}

	public void setPetPowerLimit(int petPowerLimit) {
		this.petPowerLimit = petPowerLimit;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(columnDefinition = " int default 0")
	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	@Column(columnDefinition = " int default 0")
	public int getChargeCash() {
		return chargeCash;
	}

	public void setChargeCash(int chargeCash) {
		this.chargeCash = chargeCash;
	}

	@Column(columnDefinition = " int default 0")
	public int getDupStar() {
		return dupStar;
	}

	public void setDupStar(int dupStar) {
		this.dupStar = dupStar;
	}

	@Column(columnDefinition = " varchar(255) default ''")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(columnDefinition = " int(4) default 0")
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getPfId() {
		return pfId;
	}

	public void setPfId(String pfId) {
		this.pfId = pfId;
	}

	@Column(columnDefinition = " bigint(20) default 0 ")
	public long getShowId() {
		return showId;
	}

	public void setShowId(long showId) {
		this.showId = showId;
	}

	@Column(columnDefinition = " bigint(20) default 0 ")
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@Column(columnDefinition = " int default 0 ")
	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	@Column(columnDefinition = " int default 0")
	public int getFriendShip() {
		return friendShip;
	}

	@Column(columnDefinition = " int default 0")
	public int getPveConWinNum() {
		return pveConWinNum;
	}

	public void setPveConWinNum(int pveConWinNum) {
		this.pveConWinNum = pveConWinNum;
	}

	@Column(columnDefinition = " int default 0")
	public int getSendFriendShipTimes() {
		return sendFriendShipTimes;
	}

	@Column(columnDefinition = " int default 0")
	public int getPlatform() {
		return platform;
	}

	@Column(columnDefinition = " int default 0")
	public int getChannel() {
		return channel;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getMonthCardEndTime() {
		return monthCardEndTime;
	}

	public void setMonthCardEndTime(long monthCardEndTime) {
		this.monthCardEndTime = monthCardEndTime;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getMonthCardLastRewardTime() {
		return monthCardLastRewardTime;
	}

	public void setMonthCardLastRewardTime(long monthCardLastRewardTime) {
		this.monthCardLastRewardTime = monthCardLastRewardTime;
	}

	@Column(columnDefinition = " int default 0")
	public int getRewardBuleCash() {
		return rewardBuleCash;
	}

	@Column(columnDefinition = " int default 0")
	public int getBattleCount() {
		return battleCount;
	}

	@Column(columnDefinition = "TEXT")
	public String getFirstPayStatus() {
		return firstPayStatus;
	}

	public void setFriendShip(int friendShip) {
		this.friendShip = friendShip;
	}

	public void setSendFriendShipTimes(int sendFriendShipTimes) {
		this.sendFriendShipTimes = sendFriendShipTimes;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public void setRewardBuleCash(int rewardBuleCash) {
		this.rewardBuleCash = rewardBuleCash;
	}

	public void setBattleCount(int battleCount) {
		this.battleCount = battleCount;
	}

	public void setFirstPayStatus(String firstPayStatus) {
		this.firstPayStatus = firstPayStatus;
	}

	@Column(columnDefinition = " int default 0")
	public int getFabulousNum() {
		return fabulousNum;
	}

	public void setFabulousNum(int fabulousNum) {
		this.fabulousNum = fabulousNum;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getMonthCardBEndTime() {
		return monthCardBEndTime;
	}

	public void setMonthCardBEndTime(long monthCardBEndTime) {
		this.monthCardBEndTime = monthCardBEndTime;
	}

	@Column(columnDefinition = " int default 0")
	public int getSubscribeMonthCardB() {
		return subscribeMonthCardB;
	}

	public void setSubscribeMonthCardB(int subscribeMonthCardB) {
		this.subscribeMonthCardB = subscribeMonthCardB;
	}

	@Column(columnDefinition = " int default 0")
	public int getMonthCardBNoLimitReceiveNum() {
		return monthCardBNoLimitReceiveNum;
	}

	public void setMonthCardBNoLimitReceiveNum(int monthCardBNoLimitReceiveNum) {
		this.monthCardBNoLimitReceiveNum = monthCardBNoLimitReceiveNum;
	}

	@Column(columnDefinition = " int default 0")
	public int getDrawLuckNum() {
		return drawLuckNum;
	}

	public void setDrawLuckNum(int drawLuckNum) {
		this.drawLuckNum = drawLuckNum;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(long quitTime) {
		this.quitTime = quitTime;
	}
}
