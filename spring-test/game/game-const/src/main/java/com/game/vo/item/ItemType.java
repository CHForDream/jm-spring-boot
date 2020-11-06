package com.game.vo.item;

import java.util.HashMap;
import java.util.Map;

public enum ItemType {
	ITEM(3), // 道具
	GIFT(4), // 礼包
	HERO(10), // 英雄
	PET(20), // 宠物

	;

	private static Map<Integer, ItemType> valueMap = new HashMap<Integer, ItemType>();

	static {
		for (ItemType typeEnum : ItemType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;

	ItemType(int type) {
		this.type = type;
	}

	public static ItemType valueOf(int type) {
		return valueMap.get(type);
	}

	public boolean is(int type) {
		return this.type == type;
	}

	public boolean is(ItemType itemType) {
		return this == itemType;
	}

	public int getType() {
		return type;
	}
}
