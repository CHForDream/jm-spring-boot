package com.game.constants;

import com.game.utils.TimeUtils;

public class UserConstants {
	/** 数据存储检测时间 */
	public static final long DB_SAVE_TIME = TimeUtils.SECOND * 30;

	/** 心跳离线检测时间 */
	public static final long ONLINE_TIME = TimeUtils.SECOND * 90;
	public static final long NOTICE_TIME = TimeUtils.MIN * 5;

	/** 聊天会话超时时间 */
	public static final long SESSION_CHAT_TIMEOUT = TimeUtils.MIN * 3;
	/** 战斗会话超时时间 */
	public static final long SESSION_BATTLE_TIMEOUT = TimeUtils.MIN * 30;
	/** 页面点击间隔时间 */
	public static final long OPR_TIME = TimeUtils.SECOND * 5;
}
