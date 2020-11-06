package com.game.generate.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.QuestBaseBean;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

/**
 * 文件名：QuestBean.java
 * <p>
 * 功能：quest.xls -> questBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "quest.xls", name = "quest", sheetFileName = "quest")
public class QuestBean extends QuestBaseBean {
	private List<ItemVo> rewardList = new ArrayList<ItemVo>();

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		// 任务奖励
		rewardList.addAll(ItemUtil.fromItemArrayString(this.getItem()));
	}

	public List<ItemVo> getRewardList() {
		return rewardList;
	}
}