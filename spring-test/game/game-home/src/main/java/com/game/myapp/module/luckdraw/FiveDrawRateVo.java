package com.game.myapp.module.luckdraw;

import com.game.utils.IWeight;

public class FiveDrawRateVo implements IWeight {

	private int num;
	private int rate;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public int getWeight() {
		return rate;
	}
}
