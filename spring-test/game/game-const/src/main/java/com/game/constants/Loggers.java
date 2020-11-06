package com.game.constants;

import org.apache.log4j.Logger;

public class Loggers {
	/******************** 游戏逻辑服日志 ********************/
	// 系统日志
	public final static Logger serverLogger = Logger.getLogger("server");
	// 通讯消息
	public final static Logger msgLogger = Logger.getLogger("msg");
	// 登录
	public final static Logger loginLogger = Logger.getLogger("login");
	// 战斗
	public final static Logger battleLogger = Logger.getLogger("battle");
	// 数据库
	public final static Logger dbLogger = Logger.getLogger("db");
	// 缓存
	public final static Logger cacheLogger = Logger.getLogger("cache");
	// Redis
	public final static Logger redisLogger = Logger.getLogger("redis");
	// 排行
	public final static Logger rankLogger = Logger.getLogger("rank");
	// Chat GM
	public final static Logger chatgmLogger = Logger.getLogger("chatgm");
	// 引导
	public final static Logger guideLogger = Logger.getLogger("guide");
	// 道具
	public final static Logger itemLogger = Logger.getLogger("item");
	// 支付
	public final static Logger payLogger = Logger.getLogger("pay");
	// 活动
	public final static Logger activityLogger = Logger.getLogger("activity");
	// 邮件
	public final static Logger mailLogger = Logger.getLogger("mail");
	// 聊天
	public final static Logger chatLogger = Logger.getLogger("chat");
	// 精英副本
	public final static Logger eliteLogger = Logger.getLogger("elite");

	/******************** GM服日志 ********************/
	// GM
	public final static Logger gmLogger = Logger.getLogger("gm");

	/******************** 测试机器人日志 ********************/
	// 客户端
	public final static Logger clientLogger = Logger.getLogger("client");
	// 机器人
	public final static Logger robotLogger = Logger.getLogger("robot");
}
