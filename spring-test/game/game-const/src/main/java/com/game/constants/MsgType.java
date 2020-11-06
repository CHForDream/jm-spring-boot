package com.game.constants;

public class MsgType {
	/*---------- Socket ----------*/
	public static final int CGConnectMsg = 1000;// 链接
	public static final int GCServerBackMsg = 1500;// 通用协议，服务器处理完成
	public static final int CGHeartBeatMsg = 1019;//
	public static final int GCHeartBeatMsg = 1019;//
	public static final int CGChatMsg = 2004;// 发送聊天内容
	public static final int GCChatMsg = 2004;// 返回聊天内容
	public static final int CGConnectChatMsg = 2005;// 建立聊天連接

	/*---------- HTTP ----------*/
	public static final int CGDoLoginMsg = 2001;// 登录
	public static final int GCDoLoginMsg = 2001;// 登录
	public static final int CGLoadingRoleMsg = 2002;// 加载角色信息
	public static final int GCLoadingRoleMsg = 2002;// 加载角色信息
	public static final int CGGetHeroListMsg = 2003;// 获取英雄列表
	public static final int GCGetHeroListMsg = 2003;// 获取英雄列表
	public static final int CGHttpBeatMsg = 2007;// http心跳
	public static final int GCHttpBeatMsg = 2007;// http心跳
	public static final int CGChatGm = 2008;// GM
	public static final int GCChatGm = 2008;// GM

	public static final int CGFriendsListMsg = 2010;// 获取好友列表
	public static final int GCFriendsListMsg = 2010;// 获取好友列表
	public static final int CGSendFriendReqMsg = 2011;// 请求添加好友
	public static final int GCSendFriendReqMsg = 2011;// 请求添加好友
	public static final int CGDoFriendReqMsg = 2012;// 处理好友请求
	public static final int GCDoFriendReqMsg = 2012;// 处理好友请求
	public static final int CGFriendReqListMsg = 2013;// 获取好友请求列表
	public static final int GCFriendReqListMsg = 2013;// 获取好友请求列表
	public static final int CGDeleteFriendMsg = 2014;// 删除好友
	public static final int CGVisitFriendMsg = 2020;// 送好友點
	public static final int CGSendFriendShipMsg = 2021;// 送亲密度
	public static final int CGReceiveFriendShipMsg = 2022;// 收亲密度

	public static final int CGGetBagListMsg = 2101;// 获取背包列表
	public static final int GCGetBagListMsg = 2101;// 获取背包列表
	public static final int CGUserItemMsg = 2102;// 使用道具
	public static final int CGSellBagMsg = 2103;// 出售道具

	public static final int CGMailgetListMsg = 2201;// 获取邮件列表
	public static final int GCMailgetListMsg = 2201;// 获取邮件列表
	public static final int CGMailReadMsg = 2202;// 读取邮件
	public static final int CGMailDeleteMsg = 2203;// 删除邮件

	public static final int CGGetDayTaskListMsg = 2210;// 获取任务列表
	public static final int GCGetDayTaskListMsg = 2210;// 获取任务列表
	public static final int CGGetDayTaskRewardMsg = 2211;// 领取任务奖励
	public static final int GCGetDayTaskRewardMsg = 2211;// 领取任务奖励

	public static final int CGGetAchieveListMsg = 2220;// 获取成就列表
	public static final int GCGetAchieveListMsg = 2220;// 获取成就列表
	public static final int CGGetAchieveRewardMsg = 2221;// 领取任务奖励
	public static final int GCGetAchieveRewardMsg = 2221;// 领取任务奖励

	public static final int CGShopBuyMsg = 2230;// 商城购买
	public static final int GCShopBuyMsg = 2230;// 商城购买

	/****************************** gm系统 ****************************/
	public static final int GCBanchChatMsg = 3302;// 服务器主动返回[禁言协议]
	public static final int GCKickOfflineMsg = 3303;// 服务器主动返回[踢人下线]
	public static final int GCLampMsg = 3304;// 服务器主动推送走马灯

	/****************************** 签到系统 ****************************/
	public static final int CGReceiveSevenSignRewardMsg = 3305;// 7日签到
	public static final int GCReceiveSevenSignRewardMsg = 3305;// 7日签到
	public static final int CGGetSevenSignStatusMsg = 3306;// 获取七日签到的状态
	public static final int GCGetSevenSignStatusMsg = 3306;// 获取七日签到的状态

	public static final int GCRedHatMsg = 3307;// 小红点
	public static final int CGRedHatCancelMsg = 5507;// 取消小红帽请求
	public static final int GCRedHatCancelMsg = 5507;// 取消小红帽结果

	/****************************** 抽奖系统 ****************************/
	public static final int CGGetLuckDrawInfoMsg = 3316;// 获取玩家抽奖信息
	public static final int GCGetLuckDrawInfoMsg = 3316;// 获取玩家抽奖信息
	public static final int CGLuckDrawMsg = 3317;// 抽奖
	public static final int GCLuckDrawMsg = 3317;// 抽奖

	/****************************** 充值系统 ****************************/
	public static final int CGGetMonthCardInfoMsg = 4401;// 获取月卡信息
	public static final int GCGetMonthCardInfoMsg = 4401;// 获取月卡信息
	public static final int CGGetPayInfoMsg = 4402;// 获取充值界面信息
	public static final int GCGetPayInfoMsg = 4402;// 获取充值界面信息
	public static final int GCPushPaySuccessMsg = 4403;// 充值成功
	public static final int CGGetRoleFirstPayInfoMsg = 4404;// 获取首充状态
	public static final int GCGetRoleFirstPayInfoMsg = 4404;// 获取首充状态
	public static final int CGReCeiveFirstPayRewardMsg = 4405;// 领取首充奖励

	/****************************** 引导系统 ****************************/
	public static final int CGUpdateRoleGuideMsg = 5501;// 客户端上报引导步数
	public static final int GCSendGuideMsg = 5502;// 发送触发的引导id
	public static final int CGGetLoginSuccessInfoMsg = 5003;// 登陆成功后触发
	public static final int GCGetLoginSuccessInfoMsg = 5003;// 登陆成功后触发
	public static final int CGGetRedHatInfoMsg = 5504;// 获取小红帽
	public static final int GCGetRedHatInfoMsg = 5504;// 获取小红帽
	public static final int CGModifyRoleNameMsg = 5506;// 修改玩家昵称
	public static final int GCFunctionOpenInfoMsg = 5509;// 角色开启的系统id

	/****************************** 关卡系统 ****************************/
	public static final int CGPveCityInfoMsg = 7010;// 获取关卡详细信息
	public static final int GCPveCityInfoMsg = 7010;// 获取关卡详细信息
	public static final int CGPveCityAttachMsg = 7011;// 景点镶嵌
	public static final int GCPveCityAttachMsg = 7011;// 景点镶嵌
	public static final int CGPveCityUnlockMsg = 7012;// 解锁关卡
	public static final int GCPveCityUnlockMsg = 7012;// 解锁关卡
	public static final int GCPveCityAwardMsg = 7013;// 关卡的奖励信息
	public static final int CGPveCityBuyMsg = 7014;// 关卡购买装饰
	public static final int GCPveCityBuyMsg = 7014;// 关卡购买装饰
	public static final int CGCityInfoMsg = 7015;// 城市及景点信息
	public static final int GCCityInfoMsg = 7015;// 城市及景点信息
	public static final int CGCityAwardMsg = 7016;// 关卡购买装饰
	public static final int GCCityAwardMsg = 7016;// 关卡购买装饰
	public static final int CGChangeCityMsg = 7017;// 切换城市 做引导用
	/****************************** 分享系统 ****************************/
	public static final int CGShareInfoMsg = 8001;// 获取分享信息
	public static final int GCShareInfoMsg = 8001;// 获取分享信息
	public static final int CGShareMsg = 8002;// 领取奖励

	public static final int GCCommonResponseMsg = 9001;// 服务器返回通用协议

	public static final int CGPveBuyPowerMsg = 1072;// 蓝钻购买体力
	public static final int CGPowerGetInfoMsg = 1073;//刷新玩家体力
	public static final int CGBattlePveFailedAddStepMsg = 21004;// 消耗蓝钻恢复步数
	public static final int GCBattlePveFailedAddStepMsg = 21004;// 
	public static final int CGRankInfoMsg = 23001;// 排行信息
	public static final int GCRankInfoMsg = 23001;// 排行信息

	public static final int CGHeroCommentMsg = 24013;//英雄评论
	public static final int CGHeroCommentFabulousMsg = 24014;//点赞
	public static final int CGHeroCommentGetInfoMsg = 24015;//查看评论信息
	public static final int CGHeroCommentDelMsg = 24016;//删除评论
	public static final int CGFriendsRankInfoMsg = 24017;//好友排行信息
	public static final int GCFriendsRankInfoMsg = 24017;//好友排行信息
	public static final int CGFriendFabulousMsg = 24019;//给好友点赞
	public static final int GCFriendFabulousMsg = 24019;//给好友点赞
	public static final int CGFriendGetInfoByShowIdMsg = 24020;//根据showId查找好友信息

	public static final int CGRoleHomePageInfoMsg = 24022;//获取主页信息
	public static final int GCRoleHomePageInfoMsg = 24022;//获取主页信息
	public static final int CGRoleHomePageSetUpMsg = 24023;//修改主页信息
	public static final int GCRoleHomePageSetUpMsg = 24023;//修改主页信息

	public static final int CGPowerFullMsg = 24024;//一键加满体力
	public static final int CGReceiveNoLimitPowerMsg = 24026;//领取无限体力
	public static final int CGSubscribeMonthCardBMsg = 24027;//订阅月卡B

	public static final int CGRoleBattleSelect = 24101;// 战前选择英雄, 道具
	public static final int GCRoleBattleSelect = 24101;// 战前选择英雄, 道具

	// PVE(单机)
	public static final int CGBattlePveStart = 25000;// 单机PVE战斗开始
	public static final int GCBattlePveStart = 25000;// 单机PVE战斗开始
	public static final int CGBattlePveResult = 25001;// 单机PVE战斗结算
	public static final int GCBattlePveResult = 25001;// 单机PVE战斗结算
	public static final int CGBattlePveItemList = 25002;// 单机PVE战前道具信息
	public static final int GCBattlePveItemList = 25002;// 单机PVE战前道具信息

	// 副本(单机)
	public static final int CGDuplicateCheck = 25102;// 副本资源验证
	public static final int GCDuplicateCheck = 25102;// 副本资源验证

	// 精英副本（单机）
	public static final int CGEliteDuplicateInfo = 25201;// 精英副本.信息列表
	public static final int GCEliteDuplicateInfo = 25201;// 精英副本.信息列表
	public static final int CGEliteDuplicateBuyChallengeTimes = 25202;// 精英副本.购买次数
	public static final int GCEliteDuplicateBuyChallengeTimes = 25202;// 精英副本.购买次数
	public static final int CGEliteDuplicateSyncStep = 25203;// 精英副本.同步步数
	public static final int GCEliteDuplicateSyncStep = 25203;// 精英副本.同步步数
	public static final int CGEliteDuplicateGiveUp = 25204;// 精英副本.放弃挑战
	public static final int GCEliteDuplicateGiveUp = 25204;// 精英副本.放弃挑战
	public static final int CGEliteDuplicateGameStart = 25205;// 精英副本.游戏开始
	public static final int GCEliteDuplicateGameStart = 25205;// 精英副本.游戏开始
	public static final int CGEliteDuplicateGameEnd = 25206;// 精英副本.游戏结束
	public static final int GCEliteDuplicateGameEnd = 25206;// 精英副本.游戏结束
	public static final int CGEliteDuplicateBuySteps = 25207;// 精英副本.购买步数
	public static final int GCEliteDuplicateBuySteps = 25207;// 精英副本.购买步数

	/****************************** 活动 ****************************/
	public static final int CGActivityInfo = 26000;// 活动信息
	public static final int GCActivityInfo = 26000;// 活动信息
	public static final int CGActivityGetReward = 26001;// 领取活动奖励
	public static final int GCActivityGetReward = 26001;// 领取活动奖励
}
