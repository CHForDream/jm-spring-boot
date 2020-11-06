package com.game.myapp.module.pvecity;

/**
 * 关卡静态常量
 * 
 * @author pky
 *
 */
public class PveCityConstants {
	/** 关卡地图类型 */
	public static final int PVE_CITY_TARGET = 1; // 关卡
	public static final int PVE_CITY_SCENICSPOT = 2; // 景点

	/** 建筑解锁条件 */
	public static final int HOME_DEC_LOCK_TYPE_CASH = 1;// 蓝钻
	public static final int HOME_DEC_LOCK_TYPE_VIEW = 2;// 视频
	public static final int HOME_DEC_LOCK_TYPE_PVE = 3;// 关卡
	public static final int HOME_DEC_LOCK_TYPE_HOMEMONEY = 4;// 银币
	public static final int HOME_DEC_LOCK_TYPE_GRADING = 5;// 段位

	/** duplicate 副本形式 */
	public static final int DUP_NO_LIMIT_TIMES = -1; // 副本不限制次数

	/** 地图成就类型 */
	public static final int CITY_INFO_DUP_ACHIEVE = 1; // 副本类相关成就
	public static final int CITY_INFO_PVE_ACHIEVE = 2; // 关卡类相关成就

	/** 地图成就状态 */
	public static final int CITY_ACHIEVE_STATUS_PROCESS = 0; // 任务进行中
	public static final int CITY_ACHIEVE_STATUS_COMPLETE = 1; // 任务已完成
	public static final int CITY_ACHIEVE_STATUS_AWARD = 2; // 任务已领取奖励
}
