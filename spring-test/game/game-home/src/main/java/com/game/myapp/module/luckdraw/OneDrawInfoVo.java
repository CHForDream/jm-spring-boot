package com.game.myapp.module.luckdraw;

import java.io.Serializable;

public class OneDrawInfoVo implements Serializable {
	private static final long serialVersionUID = -462947251793710991L;
	private int free;
	private int num;

	public int getFree() {
		return free;
	}

	public void setFree(int free) {
		this.free = free;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
