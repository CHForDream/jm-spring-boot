package com.game.myapp.module.item;

import java.util.ArrayList;
import java.util.List;

import com.game.vo.item.ItemVo;

public class OpenGiftInfo {
	List<ItemVo> result = new ArrayList<ItemVo>();
	private int remind = 0;

	public List<ItemVo> getResult() {
		return result;
	}

	public void setResult(List<ItemVo> result) {
		this.result = result;
	}

	public int getRemind() {
		return remind;
	}

	public void setRemind(int remind) {
		this.remind = remind;
	}

}
