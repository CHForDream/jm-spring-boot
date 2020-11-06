package com.game.generate.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.DuplicateBaseBean;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

/**
 * 文件名：DuplicateBean.java
 * <p>
 * 功能：duplicate.xls -> duplicateBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "duplicate.xls", name = "duplicate", sheetFileName = "duplicate")
public class DuplicateBean extends DuplicateBaseBean {
	private List<ItemVo> costItems = new ArrayList<ItemVo>();
	// 分数-星等
	private TreeMap<Integer, Integer> scoreStarMap = new TreeMap<Integer, Integer>();
	// 星等-奖励
	private TreeMap<Integer, List<ItemVo>> starAwardMap = new TreeMap<Integer, List<ItemVo>>();

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);
		// 难度奖励
		costItems.addAll(ItemUtil.fromItemArrayString(this.getCostItem()));

		scoreStarMap.put(this.getStarScore1(), 1);
		scoreStarMap.put(this.getStarScore2(), 2);
		scoreStarMap.put(this.getStarScore3(), 3);

		// 星等奖励
		starAwardMap.put(1, ItemUtil.fromItemArrayString(this.getStarAward1()));
		starAwardMap.put(2, ItemUtil.fromItemArrayString(this.getStarAward2()));
		starAwardMap.put(3, ItemUtil.fromItemArrayString(this.getStarAward3()));
	}

	public List<ItemVo> getCostItems() {
		return costItems;
	}

	public TreeMap<Integer, Integer> getScoreStarMap() {
		return scoreStarMap;
	}

	public TreeMap<Integer, List<ItemVo>> getStarAwardMap() {
		return starAwardMap;
	}
}