package com.game.vo;

import java.util.List;

import com.game.vo.item.ItemVo;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class UserInfoVo {
	/** 是否需要保存离线数据 */
	private boolean needSave = false;
	/** 单机战斗类型. 0PVE;1副本 */
	private int battleType = -1;
	/** PVE Id */
	private int pveId = -1;
	/** 副本Id */
	private int dupId = -1;
	/** 地图targetId */
	private int targetId = -1;
	/** 战斗前使用道具 */
	private List<Integer> preItemIdList = Lists.newArrayList();
	/** 战斗中使用道具 */
	private List<Integer> battleItemIdList = Lists.newArrayList();
	/** 战斗中购买步数数据 */
	private List<BuyStepsVo> buyStepsList = Lists.newArrayList();

	/**
	 * 重置战斗记录信息
	 */
	public void resetBattleInfo() {
		battleType = -1;
		pveId = -1;
		dupId = -1;
		targetId = -1;
		preItemIdList.clear();
		battleItemIdList.clear();
		buyStepsList.clear();

		checkNeedSave();
	}

	public void checkNeedSave() {
		// 有战斗数据
		if (battleType != -1) {
			needSave = true;
			return;
		}

		// 默认不需要保存
		needSave = false;
	}

	/**
	 * 记录购买步数信息
	 * 
	 * @param currentTimeMillis
	 * @param addStepNum
	 * @param itemVo
	 */
	public void recordBuyStepInfo(long currentTimeMillis, int addStepNum, ItemVo itemVo) {
		buyStepsList.add(new BuyStepsVo(currentTimeMillis, addStepNum, itemVo));
	}
}
