package com.game.global;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Configuration
public class AppConfigBean {
	/** ----- common ----- */
	/** 服务器版本(暂时保留) */
	@Value("${game.server.version:'0.0.1'}")
	private String version = "0.0.1";
	/** GssId */
	@Value("${game.server.gssid:'20996'}")
	private String gssid;
	/** 服务器编号 */
	@Value("${game.server.sid:1}")
	private int sid;
	/** 是否开启游戏服注册、注销服务 */
	@Value("${game.server.manager.enable:false}")
	private boolean enableServerManager;

	/** ----- home ----- */
//	/** 是否是大厅服 */
//	@Value("${game.home.enable:false}")
//	private boolean isHomeServer;
	/** 是否是主大厅服 */
	@Value("${game.home.main.enable:false}")
	private boolean isMainHome;
	/** 大厅服服务器Map<服务器编号, IP地址> */
	@Value("#{${game.home.servers:'{1:\\\"192.168.10.111\\\"}'}}")
	private Map<Integer, String> homeServerMap = new ConcurrentHashMap<Integer, String>();
	/** GM指令开关 */
	@Value("${game.home.gm.enable:false}")
	private boolean enableChatGm;
	/** 聊天服IP */
	@Value("${game.home.chat.ip:'127.0.0.1'}")
	private String chatServerIp = "";
	/** dubbo port */
	@Value("${game.home.dubbo.port:20880}")
	private int dubboPort;

//	/** ----- battle ----- */
//	/** 是否是战斗服 */
//	@Value("${game.battle.enable:false}")
//	private boolean isBattleServer;
	/** 战斗服Netty服务端口 */
	@Value("${game.battle.port:8088}")
	private int battleServerPort;
//	/** 战斗服是否SSL开关 */
//	@Value("${game.battle.ssl.enbale:false}")
//	private boolean enbaleBattleSsl;

//	/** ----- chat ----- */
//	/** 是否是聊天服 */
//	@Value("${game.chat.enable:false}")
//	private boolean isChatServer;
	/** 聊天服Netty服务端口 */
	@Value("${game.chat.port:8089}")
	private int chatServerPort;

	/** ----- Log ----- */
	/** 是否开启打点日志 */
	@Value("${game.log.enable:false}")
	private boolean isLogOn;
	/** 日志输出路径 */
	@Value("${game.log.path:'/export/logs/gamelog/'}")
	private String logPath;
}
