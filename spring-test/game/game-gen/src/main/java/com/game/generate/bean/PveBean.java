package com.game.generate.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.PveBaseBean;
import com.game.utils.StringUtils;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

/**
 * 文件名：PveBean.java
 * <p>
 * 功能：pve.xls -> pveBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "pve.xls", name = "pve", sheetFileName = "pve")
public class PveBean extends PveBaseBean {
	// 通关奖励
	private final List<ItemVo> rewardList = new ArrayList<ItemVo>();
	// 难度奖励
	private final List<ItemVo> hardRewardList = new ArrayList<ItemVo>();
	// 宝箱奖励
	private final List<ItemVo> chessRewardList = new ArrayList<ItemVo>();
	// 剩余步数奖励
	private final List<ItemVo> stepRewardList = new ArrayList<ItemVo>();
	// 推荐道具
	private final List<ItemVo> recommendItemList = new ArrayList<ItemVo>();

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		// 通关奖励
		rewardList.addAll(ItemUtil.fromItemArrayString(this.getReward()));

		// 难度奖励
		hardRewardList.addAll(ItemUtil.fromItemArrayString(this.getHardReward()));

		// 宝箱奖励
		chessRewardList.addAll(ItemUtil.fromItemArrayString(this.getChestReward()));

		// 剩余步数奖励
		stepRewardList.addAll(ItemUtil.fromItemArrayString(this.getStepGet()));

		// 推荐道具
		if (!StringUtils.isEmpty(this.getRecommendItems())) {
			String[] bags = this.getRecommendItems().split(";");
			for (int i = 0; i < bags.length; i++) {
				String[] item = bags[i].split(",");
				if (item == null || item.length < 2) {
					continue;
				}
				ItemVo info = new ItemVo();
				info.setType(Integer.parseInt(item[0]));
				info.setId(Integer.parseInt(item[1]));
//					info.setItemNum(Integer.parseInt(item[2]));
				recommendItemList.add(info);
			}
		}
	}

	public List<ItemVo> getRewardList() {
		return rewardList;
	}

	public List<ItemVo> getHardRewardList() {
		return hardRewardList;
	}

	public List<ItemVo> getChessRewardList() {
		return chessRewardList;
	}

	public List<ItemVo> getStepRewardList() {
		return stepRewardList;
	}

	public List<ItemVo> getRecommendItemList() {
		return recommendItemList;
	}
}