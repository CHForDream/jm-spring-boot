package com.game.myapp;

import com.game.db.DaoService;
import com.game.global.Globals;
import com.game.myapp.module.account.AccountManager;
import com.game.myapp.module.achieve.AchieveManager;
import com.game.myapp.module.activity.ActivityManager;
import com.game.myapp.module.activity.RoleActivityManager;
import com.game.myapp.module.battle.PveManager;
import com.game.myapp.module.chat.ChatManager;
import com.game.myapp.module.chatgm.ChatGmManager;
import com.game.myapp.module.config.ConfigManager;
import com.game.myapp.module.daytask.DayTaskManager;
import com.game.myapp.module.elite.EliteManager;
import com.game.myapp.module.friends.FriendManager;
import com.game.myapp.module.function.FunctionOpenManager;
import com.game.myapp.module.guide.GuideManager;
import com.game.myapp.module.hero.HeroManager;
import com.game.myapp.module.item.BagManager;
import com.game.myapp.module.item.GiftManager;
import com.game.myapp.module.item.ItemManager;
import com.game.myapp.module.lamp.HorseLampManager;
import com.game.myapp.module.login.LoginManager;
import com.game.myapp.module.luckdraw.LuckDrawManager;
import com.game.myapp.module.mail.MailManager;
import com.game.myapp.module.online.OnlineManager;
import com.game.myapp.module.pay.MonthCardManager;
import com.game.myapp.module.pay.PayManager;
import com.game.myapp.module.ping.HeartBeatManager;
import com.game.myapp.module.pvecity.PveCityManager;
import com.game.myapp.module.rank.RankService;
import com.game.myapp.module.redhat.RedHatManager;
import com.game.myapp.module.redis.RedisManager;
import com.game.myapp.module.role.RoleManager;
import com.game.myapp.module.role.UnitManager;
import com.game.myapp.module.role.UserNameCheckManager;
import com.game.myapp.module.role.page.RolehHomepageManager;
import com.game.myapp.module.share.ShareManager;
import com.game.myapp.module.shop.ShopManager;
import com.game.myapp.module.sign.SignManager;
import com.game.myapp.module.target.TargetManager;

public class GameGlobals {
	public static LoginManager loginManager = new LoginManager();// 登录
	public static RoleManager roleManager = new RoleManager();// 角色
	public static HeroManager heroManager = new HeroManager();// 英雄
	public static ChatManager chatManager = new ChatManager();// 聊天
	public static FriendManager friendManager = new FriendManager();// 好友
	public static OnlineManager onlineManager = new OnlineManager();// 
	public static BagManager bagManager = new BagManager();// 背包
	public static ItemManager itemManager = new ItemManager();// 物品
	public static GiftManager giftManager = new GiftManager();// 礼包
	public static MailManager mailManager = new MailManager();// 遇见
	public static UnitManager unitManager = new UnitManager();// 货币
	public static DayTaskManager taskManager = new DayTaskManager();// 每日任务
	public static ConfigManager configManager = new ConfigManager();// 配置
	public static AchieveManager achieveManager = new AchieveManager();// 成就
	public static ShopManager shopManager = new ShopManager();// 商店
	public static TargetManager targetManager = new TargetManager();// 关卡
	public static HeartBeatManager heartBeatManager = new HeartBeatManager();// 心跳
	public static ChatGmManager chatGmManager = new ChatGmManager();// 聊天GM
	public static HorseLampManager horseLampManager = new HorseLampManager();// 跑马灯
	public static SignManager signManager = new SignManager();// 签到
	public static FunctionOpenManager functionOpenManager = new FunctionOpenManager();// 系统开启管理
	public static ActivityManager activityManager = new ActivityManager();// 活动
	public static RoleActivityManager roleActivityManager = new RoleActivityManager();// 玩家活动数据
	public static LuckDrawManager luckDrawManager = new LuckDrawManager();// 抽奖
	public static UserNameCheckManager userNameCheckManager = new UserNameCheckManager();// 名称检测
	public static MonthCardManager monthCardManager = new MonthCardManager();// 月卡
	public static GuideManager guideManager = new GuideManager(); // 引导
	public static PayManager payManager = new PayManager(); // 支付
	public static RedHatManager redHatManager = new RedHatManager(); // 小红点
	public static PveCityManager pveCityManager = new PveCityManager(); // 关卡
	public static RankService rankService = new RankService(); //排行服务
	public static ShareManager shareManager = new ShareManager();// 分享&广告
	public static RolehHomepageManager rolehHomepageManager = new RolehHomepageManager(); //主页
	public static DaoService daoService = (DaoService) Globals.applicationContext.getBean("DaoService"); // DAO服务
	public static RedisManager redisManager = new RedisManager(); // Redis服务
	public static AccountManager accountManager = new AccountManager();// 账号
	public static PveManager pveManager = new PveManager();// PVE
	public static EliteManager eliteManager = new EliteManager();// 精英副本
}
