package com.game.common.logf.impl;

import java.util.ArrayList;
import java.util.List;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StoreLuckDrawLog extends AbstractLog {
	private int drawType; // 抽奖类型 
	private int cost; // 消耗数量
	private List<ItemVo> itemList = new ArrayList<ItemVo>();
	private int free;
	private int doubleDraw;

	public StoreLuckDrawLog(UserBean userbean) {
		super(LogfType.LUCK_DRAW, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(drawType).append("|").append(cost).append("|").append(ItemUtil.toInfoString(itemList)).append("|")
				.append(free).append("|").append(doubleDraw);
		return sb.toString();
	}

	public void addItem(int itemType, int itemId, int itemNum) {
		this.itemList.add(new ItemVo(itemType, itemId, itemNum));
	}
}
