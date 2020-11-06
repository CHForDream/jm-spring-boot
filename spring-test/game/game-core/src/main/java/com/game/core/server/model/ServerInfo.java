package com.game.core.server.model;

import lombok.Data;

@Data
public class ServerInfo {
	/**
	 * 游戏服务Id(默认从1递增)
	 */
	private int sid;
	/**
	 * 服务器状态
	 */
	private int status;
	/**
	 * 锁定状态的服务不接受批处理操作
	 * 0, 未锁
	 * 1, 锁定
	 */
	private int isLocked;
	/**
	 * 维护中的服务用户引流由平台特殊处理
	 * 0, 正常
	 * 1, 维护中
	 */
	private int isMaintaining;
	/**
	 * 在线人数
	 */
	private int online;
	/**
	 * 内网ip
	 */
	private String priIP;
	/**
	 * 外网ip
	 */
	private String pubIP;
	/**
	 * 大厅服端口(默认80)
	 */
	private int serverPort;
	/**
	 * 战斗服端口(默认8088)
	 */
	private int battlePort;
	/**
	 * Dubbo服务端口(默认20880)
	 */
	private int dubboPort;
	/**
	 * 心跳时间戳
	 * 暂定为int 单位：秒
	 */
	private int heartbeat;
}
