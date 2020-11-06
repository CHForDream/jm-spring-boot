package com.game.common.logf;

public class LogfConstants {
	/********************** 日志文件名称 ********************/
	public static final String LOG_TYPE_LOGIN = "login";
	public static final String LOG_TYPE_CHAT = "chat";
	public static final String LOG_TYPE_BATTLE = "battle";
	public static final String LOG_TYPE_ITEM = "item";
	public static final String LOG_TYPE_ACHIEVEMENT = "achieve";
	public static final String LOG_TYPE_TASK = "task";
	public static final String LOG_TYPE_ACTIVITY = "activity";
	public static final String LOG_TYPE_HERO = "hero";
	public static final String LOG_TYPE_PAY = "pay";
	public static final String LOG_TYPE_GUIDE = "guide";
	public static final String LOG_TYPE_HOME = "home";
	public static final String LOG_TYPE_FRIEND = "friend";

	/*************************** 战斗日志类型 *******************/
	public static final String PVE_COMPLETE_APP = "pve_complete_app";//APP完成PVE关卡
	public static final String PVE_START_APP = "pve_start_app";//APP开始PVE战斗
	public static final String DUP_COMPLETE_APP = "dup_complete_app";//APP完成副本
	public static final String DUP_START_APP = "dup_start_app";//APP开始副本战斗
	/***************************** END **********************/

	/************************* 聊天 *************************************/
	public static final String CHAT_TYPE = "chat";// 聊天
	public static final String LOGIN_TYPE = "login";// 登陆
	public static final String LOGOUT_TYPE = "offline";// 退出
	public static final String REGISTER_TYPE = "register";// 注册
	public static final String GM_TYPE = "gm";// 聊天GM
	/***************************** END **********************/

	/*************************** 道具相关日志类型 *******************/
	public static final String ADD_ITEM = "add_item";// 获得道具
	public static final String COST_ITEM = "cost_item";// 消耗道具
	public static final String INSERT_HERO = "get_hero";// 获得英雄
	public static final String ADD_CURRENCY = "add_currency";// 获得货币
	public static final String COST_CURRENCY = "cost_currency";// 消耗货币
	public static final String BUY_ITEM = "buy_item";
	/***************************** END **********************/

	/*************************** 活动日志类型 *******************/
	public static final String COMPLETE_ACTIVITY = "complate_activity";// 完成任务
	public static final String RECEIVE_ACTIVITY = "receive_activity";// 领取活动奖励
	public static final String SEVEN_SIGN = "seven_sign";// 七日签到
	public static final String MONTH_SIGN = "month_sign";// 月签到
	/***************************** END **********************/

	public static final String NOHEVEID = "";// 物品的获得需要发送来源的唯一标识,没有标识的发送空字符串

	// 获得和消耗方式
	public static final String CHANNEL_BATTLE = "battle";// 战斗
	public static final String CHANNEL_TASK = "task";// 任务
	public static final String CHANNEL_ACHIEVEMENT = "achievement";// 成就
	public static final String CHANNEL_GM = "gm";// GM
	public static final String CHANNEL_MAIL = "mail";// 邮件
	public static final String CHANNEL_SELL = "sell";// 售出
	public static final String CHANNEL_USE = "use";// 使用
	public static final String CHANNEL_GIFT = "gift";// 好友赠送礼物
	public static final String CHANNEL_SHOP = "shop";// 商城购买
	public static final String CHANNEL_SEVEN_SIGN = "sign";// 七日签到
	public static final String CHANNEL_REWARD = "reward";
	public static final String CHANNEL_ACTIVITY = "activity";// 活动
	public static final String CHANNEL_GEMSTONE = "gemStone";// 宝石
	public static final String CHANNEL_LUCKDRAW = "luckdraw";// 抽奖
	public static final String CHANNEL_PAY = "pay";// 充值
	public static final String CHANNEL_FIRST_PAY_REWARD = "firstPay";// 首充
	public static final String CHANNEL_GUIDE = "guide";// 引导
	public static final String CHANNEL_HOME = "home";// 家园
	public static final String CHANNEL_SHARE = "share";// 分享
	public static final String CHANNEL_UPGRADE = "upgrade";// 升级
	public static final String CHANNEL_HOME_EXCHANGE = "home_exchange";// 家园兑换
	public static final String CHANNEL_MAIN_TASK = "main_task";// 主线任务
	public static final String CHANNEL_POWER_CARD = "power_card";//体力卡
	public static final String CHANNEL_POWER_AUTO = "power_auto";//自动添加
	public static final String CHANNEL_LADDER = "ladder";//天梯
	public static final String CHANNEL_COST_RESOURCE = "cost_resource";//消耗资源
	public static final String CHANNEL_FRIENDS_FABULOUS = "friends_fabulous";//好友点赞
	public static final String CHANNEL_REGISTER = "role_register";//注册
	public static final String CHANNEL_CHANGE_NAME = "change_name";//改名
	public static final String CHANNEL_CHANGE_POWER_BUY = "power_buy";//购买
	public static final String CHANNEL_DAILY_RESET = "daily_reset";//每日重置
	public static final String CHANNEL_CITYAWARD = "cityaward";// 城市通关奖励
	/***************************** END **********************/

	/*************************** 成就相关日志类型 *******************/
	public static final String ACHIEVEMENT_UPGRADE = "achievement_upgrade";// 成就升级
	public static final String ACHIEVEMENT_COMPLETE = "achievement_complete";// 成就完成
	/***************************** END **********************/

	/*************************** 任务相关日志类型 *******************/
	public static final String DAILY_TASK_COMPLETE = "day_task_complete";// 完成每日任务
	public static final String DAILY_TASK_POINT_UPDATE = "day_task_point_update";//任务活跃度提升
	public static final String DAILY_TASK_RECEIVE_BOX = "receive_day_task_box";// 领取宝箱
	public static final String LUCK_DRAW = "luck_draw";// 抽奖
	/***************************** END **********************/

	/*************************** 充值相关日志类型 *******************/
	public static final String PAY_SUCCESS = "pay_success";// 充值成功
	public static final String PAY_RECEIVE = "pay_receive";// 收到充值请求
	/***************************** END **********************/

	/*************************** 引导相关日志类型 *******************/
	public static final String GUIDE_TRIGGER = "guide_trigger";//触发引导
	public static final String GUIDE_COMPLETE = "guide_complete";//完成引导
	/***************************** END **********************/

	/********************** 好友 ***************************/
	public static final String FRIEND_ADD = "friend_add";//添加好友
	public static final String FRIEND_FABULOUS = "friend_fabulous";//好友点赞
}
