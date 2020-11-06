package com.game.rpc.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.game.constants.RoleConstants;
import com.game.db.entity.RoleEntity;

public class RoleInfo implements Serializable {
	private static final long serialVersionUID = -2504623665628570792L;

	private RoleEntity role;
	/** 是否开启了PVP */
	private boolean isOpenPvp = true;
	/** 是否开启了天梯 */
	private boolean isOpenLadder = true;

	private int pveTargetId = RoleConstants.PVE_FIRST_STAGE_ID;

	private List<Integer> heroCodes = new ArrayList<Integer>();

	public void addHero(int heroCode) {
		this.heroCodes.add(heroCode);
	}

	public List<Integer> getHeroCodes() {
		return heroCodes;
	}

	public boolean isOpenPvp() {
		return isOpenPvp;
	}

	public boolean isOpenLadder() {
		return isOpenLadder;
	}

	public void setOpenPvp(boolean isOpenPvp) {
		this.isOpenPvp = isOpenPvp;
	}

	public void setOpenLadder(boolean isOpenLadder) {
		this.isOpenLadder = isOpenLadder;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public int getPveTargetId() {
		return pveTargetId;
	}

	public void setPveTargetId(int pveTargetId) {
		this.pveTargetId = pveTargetId;
	}

}
