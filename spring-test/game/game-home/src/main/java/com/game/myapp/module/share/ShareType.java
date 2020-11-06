package com.game.myapp.module.share;

import java.util.HashMap;
import java.util.Map;

public enum ShareType {
	SHARE_DAILY(1, ShareResetType.DAILY), // 每日分享
	AD_BATTLE_REWARD(2, ShareResetType.DAILY), // 战斗结算翻倍
	AD_PVE_ADD_STEP(3, ShareResetType.DAILY), // PVE加步数
	AD_RANDOM_ITEM(4, ShareResetType.DAILY), // 战前随机道具

	;

	private static Map<Integer, ShareType> valueMap = new HashMap<Integer, ShareType>();

	static {
		for (ShareType typeEnum : ShareType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;
	/** 重置类型 */
	private final ShareResetType resetType;

	ShareType(int type, ShareResetType resetType) {
		this.type = type;
		this.resetType = resetType;
	}

	public static ShareType valueOf(int type) {
		return valueMap.get(type);
	}

	public boolean is(int type) {
		return this.type == type;
	}

	public boolean is(ShareType shareType) {
		return this == shareType;
	}

	public int getType() {
		return type;
	}

	public ShareResetType getResetType() {
		return resetType;
	}
}
