package com.game.vo;

import com.game.vo.item.ItemVo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyStepsVo {
	private long time;
	private int steps;
	private ItemVo costItem;
}
