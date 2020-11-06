package com.game.myapp.module.function;

/**
 * 系统id
 * 
 * @author lpf
 * @date June.28.2019
 */
public interface ISystemId {

	/*************************** 系统标识id *************************/
	/** 匹配模式 */
	int SYSTEM_MATCH = 1;
	/** 好友 */
	int SYSTEM_FRINED = 2;
	/** 背包系统 */
	int SYSTEM_BAG = 3;
	/** 每日任务 */
	int SYSTEM_EVERY_TASK = 4;
	/** 角色功能 */
	int SYSTEM_ROLE_FUNCTION = 5;
	/** 成就系统 */
	int SYSTEM_ACHIEVEMENT = 6;
	/** 七日签到 */
	int SYSTEM_SEVEN_SIGN = 7;
	/** 商城 */
	int SYSTEM_SHOP = 8;
	/** 聊天系统 */
	int SYSYTE_CHAT = 9;
	/** 宝石 */
	int SYSTEM_GEM = 10;
	/** 灵气 */
	int SYSTEM_REIKI = 11;
	/** 福利系统 */
	int SYSTEM_WELFARE = 13;
	/** 匹配系统 */
	int SYSTEM_RANKING = 14;
	/** 家园系统 */
	int SYSTEM_HOME = 15;
	/** 宝石抽取 */
	int SYSTEM_GEMDRAW = 16;
	/** 天梯系统 */
	int SYSTEM_LADDER = 17;
	/** 主页 */
	int SYSTEM_MAIN = 18;
	/** pve连胜 */
	int SYSTEM_PVECONWIN = 19;
	/** 首充 */
	int SYSTEM_PAY_FIRST = 20;
	/** 主界面看广告视频奖励 */
	int SYSTEM_AD_MAIN = 21;
	/** 公告弹出 */
	int SYSTEM_NOTICE = 22;
	/** 看广告视频结算翻倍 */
	int SYSTEM_AD_BATTLE_REWARD = 23;
	/** 看广告视频加步数 */
	int SYSTEM_AD_PVE_ADD_STEP = 24;
	/** 看广告视频加道具 */
	int SYSTEM_AD_PVE_ADD_ITEM = 25;

	/**************************** 系统开启条件类型 **********************/
	/** 系统开启类型 */
	int ROLE_LEVEL_TYPE = 1;
	/** 道具开启 */
	int ITEM_TYPE = 2;
	/** PVE关卡开启 */
	int PVE_TYPE = 3;
}
