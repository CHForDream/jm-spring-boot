package com.game.myapp.module.shop;

public enum EShopType {
	HERO(1), // 英雄
	ITEM(5), // 道具
	GIFT(6), // 礼品
	;
	/** 类型 */
	private final int type;

	EShopType(int type) {
		this.type = type;
	}

	public boolean is(int type) {
		return this.type == type;
	}

	public int getType() {
		return type;
	}
}
