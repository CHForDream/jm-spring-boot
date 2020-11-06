package com.game.myapp.module.item;

import com.game.utils.TimeUtils;

public class ItemConstants {
	/** 无限道具有效时间调整，增加一点逻辑时间，单位秒 */
	public static final long INFINITE_ITEM_ADJUST_TIME = TimeUtils.SECOND * 2;

	/** 战斗中道具类型 -》 item表 arena列 */
	public static final int TYPE_BATTLE = 1;
	/** 无限道具类型 -》 item表 arena列 */
	public static final int TYPE_INFINITE = 2;
}
