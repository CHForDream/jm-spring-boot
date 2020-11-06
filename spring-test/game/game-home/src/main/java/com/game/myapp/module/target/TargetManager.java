package com.game.myapp.module.target;

import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import com.game.common.data.Datas;
import com.game.constants.RoleConstants;
import com.game.generate.bean.HeroBean;
import com.game.generate.bean.TargetBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class TargetManager {
	private TreeMap<Integer, TargetBean> pveTargetMap = Maps.newTreeMap();

	public TargetManager() {
		initPveTargetMap();
	}

	public int getNextTargetId(int targetId) {
		Integer nextTargetId = pveTargetMap.ceilingKey(targetId + 1);
		if (nextTargetId == null) {
			// 没有下一关, 默认为0
			return -1;
		}
		return nextTargetId;
	}

	/**
	 * 获取最大的关卡
	 */
	public int getPveMaxTargetId() {
		return pveTargetMap.lastKey();
	}

	/**
	 * 从100关开始随机关卡
	 * 
	 * @return
	 */
	public int getRandomPveTaskId() {
		List<Integer> randomList = Lists.newArrayList();
		for (TargetBean targetBean : Datas.getDataMap(TargetBean.class).values()) {
			if (targetBean.getId() >= RoleConstants.PVE_FIRST_STAGE_ID && targetBean.getMapLevel() >= 100) {
				randomList.add(targetBean.getId());
			}
		}

		Random rnd = new Random();
		int index = rnd.nextInt(randomList.size() - 1);
		return randomList.get(index);
	}

	public int getPreTargetId(int targetId) {
		Integer preTargetId = pveTargetMap.floorKey(targetId - 1);
		if (preTargetId == null || preTargetId < RoleConstants.PVE_FIRST_STAGE_ID) {
			// 没有前一关, 默认为0
			return 0;
		}
		return preTargetId;
	}

	public int getTargetSteps(int targetId, int heroId) {
		// 关卡步数
		int targetStep = 0;
		TargetBean targetBean = Datas.get(TargetBean.class, targetId);
		if (targetBean != null) {
			targetStep = targetBean.getDemand();
		}

		// 英雄步数
		int heroStep = 0;
		HeroBean heroBean = Datas.get(HeroBean.class, heroId);
		if (heroBean != null) {
			heroStep = heroBean.getAddStep();
		}

		return targetStep + heroStep;
	}

	private void initPveTargetMap() {
		for (TargetBean targetBean : Datas.getDataMap(TargetBean.class).values()) {
			if (targetBean.getId() >= RoleConstants.PVE_FIRST_STAGE_ID && targetBean.getId() < RoleConstants.PVE_LAST_STAGE_ID) {
				pveTargetMap.put(targetBean.getId(), targetBean);
			}
		}
	}
}
