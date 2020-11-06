package com.game.constants;

/**
 * 
 * @author pky
 *
 */

public class ErrorCodeConst {
	public static final int CASH_NOT_ENOUGH = 9999;// 钻石不足

	public static final int SUCCESS = 10000;
	public static final int ROLE_NOT_EXIST = 10001;// 角色不存在
	public static final int ERROR_PARAM = 10002;// 参数错误
	public static final int HERO_NOT_EXIST = 10003;// 武将不存在

	public static final int RESOURCE_NOT_ENOUGH = 10004;// 资源不足
	public static final int STONE_CODE_SAME = 10005;// 已穿戴相同类型的宝石
	public static final int LV_IS_FULL = 10006;// 已满级
	public static final int USER_NAME_IS_EXIST = 10007;// 名称重复
	public static final int PAY_ORDER_IS_EXIST = 10008;// 订单已存在
	public static final int PAY_CONFIG_NOT_EXIST = 10009;// 挡位不存在
	public static final int HOME_SERVER_ERROR = 10020;// 服务器处理异常
	public static final int ROLE_NAME_IS_EXIST = 10021;// 名称已存在
	public static final int NOT_REWARD = 10022;// 不可领取
	public static final int CAN_REWARD = 10023;// 可领取
	public static final int ALREADY_REWARD = 10024;// 已领取
	public static final int ITEM_NOT_EXIST = 10025;// 道具不存在
	public static final int ROOM_NOT_EXIST = 10026;// 房间不存在
	public static final int NO_HAVE_HOME = 10027;// 未解锁任何房间
	public static final int HOME_NO_LOCK = 10028;// 水晶未解锁
	public static final int HOME_DEC_IS_EXIST = 10029;// 道具已存在
	public static final int MYSELF_FRINEDS_IS_FULL = 10030;// 好友已满
	public static final int OTHER_FRINEDS_IS_FULL = 10031;// 对方好友已满
	public static final int SHOP_HERO_IS_EXIST = 10032;// 英雄已存在
	public static final int FRIEND_IS_EXIST = 10033;// 好友已存在
	public static final int USER_IN_BATTLE = 10034;// 玩家在战斗中
	public static final int PVE_POWER_NOT_FULL = 10036;// 体力不足
	public static final int RANK_ERROR_RANK_TYPE = 10037;// 排行类型错误
	public static final int LADDER_BATTLE_IS_MODIFY_ADDRESS = 10038;// 本次天梯赛已修改过位置
	public static final int LADDER_BATTLE_NOT_HAVE_NUM = 10039;//天梯赛次数已用尽
	public static final int LADDER_BATTLE_IS_SETTLEMENT = 10040;//天梯赛正在结算
	public static final int BATTLE_PLAYER_NOT_FULL = 10041;//人数不足
	public static final int LADDER_INVENT_NOT_SAME_AREA = 10042;//不可邀请,不在同一区
	public static final int LADDER_POINT_ALSO_HAVA = 10043;//次数未用尽
	public static final int ROOM_PERSON_FULL = 10044;//房间已满
	public static final int LADDER_NOT_OPEN = 10045;//天梯未在开放时间
	public static final int FUNCTION_NOT_OPEN = 10046;//系统未开放
	public static final int INVITATION_IS_INVALID = 10047;//邀请信息失效
	public static final int PVE_GOTO_BAG_USE_POWER_DRUG = 10048;//前往背包使用体力药 
	public static final int PVE_COST_CASH_BUY_POWER = 10049;//使用蓝钻购买体力 
	public static final int TARGET_FUNCTION_NOT_OPEN = 10050;//对方系统未开启
	public static final int BATTLE_ROOM_PRIVILEGES_ERROR = 10051;//权限错误
	public static final int BATTLE_ROOM_OTHER_NOT_PREPARE = 10052;//对方未准备
	public static final int CANNOT_REPEAT_COMMENT = 10053;//不能重复评论

	public static final int FRIENDS_FABULOUS_ALREADY = 10054;//今天已赞

	public static final int SHARE_NO_TIMES = 10055;//无分享次数
	public static final int SHARE_DUPLICATION_REQUEST = 10056;//重复请求
	public static final int NO_IN_MONTH_CARD_TIME = 10057;//不在月卡时间内

	public static final int LUCK_DRAW_NOT_ENOUGH = 10059;// 抽奖次数不足
	public static final int PVP_MATCH_BATTLE_FAILD_FULL = 10060;//匹配失败次数已满,今天无法匹配

	public static final int NOHAVE_POWER_LIMIT_RECEIVE_NUM = 10061;// 领取次数用尽

	public static final int BATTLE_PVE_ERROR_TARGET_ID = 10062;// 关卡错误

	/** 关卡错误信息 */
	public static final int PVE_ATTACH_UNLOCK = 10063; // 关卡未解锁 不能镶嵌
	public static final int PVE_ATTACH_PVE_TARGET = 10064; // pve关卡 不是景点关卡不能镶嵌
	public static final int PVE_ATTACH_GROUP_ERROR = 10065; // 镶嵌组错误 不能镶嵌在该点
	public static final int PVE_COMPLETE_STATUS_ERROR = 10066; // 当前关卡未解锁不能进行下一关
	public static final int PVE_ATTACH_NO_DEC = 10067; // 没有该装饰不能镶嵌
	public static final int PVE_BUY_DEC_EXISTS = 10068; // 装饰存在不需购买
	public static final int PVE_LOCK_STATUS_ERROR = 10069; // 当前关卡未解锁不能进行下一关
	public static final int PVE_UNLOCK_STATUS_ERROR = 10070; // 当前关卡pve未完成不能解锁下一关
	public static final int PVE_PVEPASS_STATUS_ERROR = 10071; // 当前关卡未镶嵌建筑不能解锁下一关

	public static final int SHOP_HERO_LOCK_ERROR = 10073; // 英雄未开放不能获取

	/** 副本错误信息 */
	public static final int DUPLICATE_ERROR_ID = 10100;// 副本id错误
	public static final int DUPLICATE_ERROR_OPEN = 10101;// 副本尚未开启 需先通关相应的关卡

	/** 精英副本 */
	public static final int ELITE_DUPLICATE_NO_CHALLENGE_BUY_TIMES = 10200;// 精英副本没有挑战购买次数
	public static final int ELITE_DUPLICATE_NOT_START = 10201;// 精英副本未开始挑战
	public static final int ELITE_DUPLICATE_INVALID_STEP = 10202;// 精英副本上报步数异常
	public static final int ELITE_DUPLICATE_NO_STEPS_BUY_TIMES = 10203;// 精英副本没有步数购买次数
	public static final int ELITE_DUPLICATE_STATE_ERROR = 10204;// 精英副本状态异常
	public static final int ELITE_DUPLICATE_NO_CHALLENGE_TIME = 10205;// 没有挑战次数
}
