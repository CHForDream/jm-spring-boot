package com.game.common.user;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import com.game.core.lock.LockManager;
import com.game.vo.UserInfoVo;
import com.game.vo.item.ItemVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {
	private long uid;
	private long lastOperationTime = 0;
	private int token = 0;
	private String ip = "";
	private String platformToken = "";
	private String passportId = "";
	private String devId = "";
	private long loginTime = 0;
	private int channel;
	private int platform;
	private boolean isNew = false;

	/** 上一次PVE的奖励内容 */
	private List<ItemVo> pveRewardList = null;
	/** PVE随机棋子Id */
	private int randomChessId = 0;
	/** 玩家数据(离线要临时缓存的数据) */
	private UserInfoVo userInfoVo = new UserInfoVo();

	/** 玩家进入战斗的时间 */
	private long oprStartTime = 0L;
	/** 玩家战斗结算的时间 */
	private long oprEndTime = 0L;

	/** 是否资源发生了变化 */
	private AtomicBoolean isResourceChanged = new AtomicBoolean(true);
	/** 是否体力发生了变化 */
	private AtomicBoolean isPowerChanged = new AtomicBoolean(false);
	/** 是否开启了新的系统 */
	private AtomicBoolean isFunctionOpen = new AtomicBoolean(true);
	/** 是否活动发生了变化 */
	private AtomicBoolean isActivityChanged = new AtomicBoolean(false);
	/** 英雄是否发生了变化 */
	private AtomicBoolean isHeroChanged = new AtomicBoolean(false);
	/** 是否红点发生了变化 */
	private AtomicBoolean isRedHatChanged = new AtomicBoolean(true);
	/** 红点列表 */
	private List<Integer> redHatIdList = new CopyOnWriteArrayList<Integer>();

	public boolean getResourceChanged() {
		return isResourceChanged.get();
	}

	public void setResourceChanged(boolean isResourceChanged) {
		this.isResourceChanged.set(isResourceChanged);
	}

	public boolean getPowerChanged() {
		return isPowerChanged.get();
	}

	public void setPowerChanged(boolean isPowerChanged) {
		this.isPowerChanged.set(isPowerChanged);
	}

	public boolean isFunctionOpen() {
		return isFunctionOpen.get();
	}

	public void setFunctionOpen(boolean openfunction) {
		this.isFunctionOpen.set(openfunction);
	}

	public boolean isActivityChanged() {
		return isActivityChanged.get();
	}

	public void setActivityChanged(boolean isActivityChanged) {
		this.isActivityChanged.set(isActivityChanged);
	}

	public boolean isHeroChanged() {
		return isHeroChanged.get();
	}

	public void setHeroChanged(boolean isHeroChanged) {
		this.isHeroChanged.set(isHeroChanged);
	}

	public boolean isRedHatChanged() {
		return isRedHatChanged.get();
	}

	public void setRedHatChanged(boolean isRedHatChanged) {
		this.isRedHatChanged.set(isRedHatChanged);
	}

	public boolean isRedHatDirty(int id) {
		return redHatIdList.contains(id);
	}

	public int setDirty(int id, boolean flag) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			if (flag) {
				return addRedHatId(id);
			} else {
				return removeRedHatId(id);
			}
		} finally {
			lock.unlock();
		}
	}

	private int addRedHatId(int id) {
		if (redHatIdList.contains(id)) {
			return 0;
		}

		redHatIdList.add(id);
		this.setRedHatChanged(true);

		return id;
	}

	private int removeRedHatId(int id) {
		int index = redHatIdList.indexOf(id);
		if (index == -1) {
			return 0;
		}
		try {
			redHatIdList.remove(index);
			this.setRedHatChanged(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
