package com.game.robot;

import java.util.List;

import com.game.common.client.Client;
import com.game.common.client.ClientMessageLogSample;
import com.game.constants.Loggers;
import com.game.robot.config.Config;
import com.game.robot.manager.RobotBattleManager;
import com.game.robot.manager.RobotChatManager;
import com.game.robot.manager.RobotInfoManager;
import com.game.robot.manager.RobotLoginManager;
import com.game.robot.strategy.EStrategy;
import com.game.robot.strategy.IRobotStrategy;
import com.game.robot.thread.StrategryExcutor;
import com.game.robot.thread.StrategyRunnable;
import com.google.common.collect.Lists;
import com.google.protobuf.AbstractMessage.Builder;

/**
 * 单元测试用机器人
 * 
 * @author pky
 *
 */
@SuppressWarnings("rawtypes")
public class Robot extends Client {

	/** 机器人状态 */
	private RobotState state = RobotState.LOGIN;
	/** 测试Case用例枚举 */
	private List<EStrategy> strategyTypeList = Lists.newArrayList();
	/** 测试Case用例 */
	private List<IRobotStrategy> strategyList = Lists.newArrayList();
	/** 登录管理器 */
	private RobotLoginManager loginManager;
	/** 机器人游戏信息管理器 */
	private RobotInfoManager infoManager;
	/** 战斗管理器 */
	private RobotBattleManager battleManager;
	/** 聊天管理器 */
	private RobotChatManager chatManager;

	public Robot(int id) {
		super(id);

		this.loginManager = new RobotLoginManager(this);
		this.infoManager = new RobotInfoManager(this);
		this.battleManager = new RobotBattleManager(this);
		this.chatManager = new RobotChatManager(this);

		// Http心跳
		addRobotStrategy(EStrategy.HEARTBEAT_HTTP);
//		addRobotStrategy(EStrategy.HEARTBEAT_CHAT);
	}

	@Override
	public void onSocketConnect(int type) {
		switch (type) {
		case SOCKET_TYPE_NETTY_BATTLE:// Netty Socket:Battle
		case SOCKET_TYPE_WEBSOCKET_BATLLE:// Web Socket:Battle
			battleManager.onSocketConnect();
			break;

		case SOCKET_TYPE_NETTY_CHAT:// Netty Socket:Chat
			chatManager.onSocketConnect();
			break;

		default:
			break;
		}
	}

	@Override
	public void onSocketError(int type) {
	}

	@Override
	public void onSocketClose(int type) {
	}

	@Override
	public void onHttpMessageSent(long costTime) {
		RobotMain.messageCount(costTime);
	}

	public void sendHttpMessage(int msgType, Builder builder) {
		if (Config.IS_PRINT_LOG && ClientMessageLogSample.isPrintMsg(msgType)) {
			Loggers.robotLogger.info("[Send][" + msgType + "] robotId = " + getId() + ", uid = " + getUid());
		}

		getHttpSession().sendHttpMessage(Config.SERVER_HTTP_URL, msgType, builder);
	}

	public void sendBattleSocketMessage(int msgType, Builder builder) {
		if (Config.IS_PRINT_LOG && ClientMessageLogSample.isPrintMsg(msgType)) {
			Loggers.robotLogger.info("[Send][" + msgType + "] robotId = " + getId() + ", uid = " + getUid());
		}

		if (getWebsocketBattleSession().isConnect()) {
			getWebsocketBattleSession().sendMsg(builder);
		} else if (getSocketBattleSession().isConnect()) {
			getSocketBattleSession().sendMsg(builder);
		} else {
			Loggers.robotLogger.error("No battle socket session valid!");
		}
	}

	public void sendChatSocketMessage(int msgType, Builder builder) {
		if (Config.IS_PRINT_LOG && ClientMessageLogSample.isPrintMsg(msgType)) {
			Loggers.robotLogger.info("[Send][" + msgType + "] robotId = " + getId() + ", uid = " + getUid());
		}

		if (getSocketChatSession().isConnect()) {
			getSocketChatSession().sendMsg(builder);
		} else {
			Loggers.robotLogger.error("No chat socket session valid!");
		}
	}

	public void start() {
		// 登录游戏
		loginManager.login();
	}

	public void doStrategy() {
		if (Config.IS_PRINT_LOG) {
			Loggers.robotLogger.info("Robot state to gaming: id = " + getId() + ", uid = " + getUid());
		}

		// 进入gaming状态
		setState(RobotState.GAMING);

		for (IRobotStrategy strategy : strategyList) {
			// 执行策略的Runnable
			StrategyRunnable runnable = new StrategyRunnable(strategy);
			if (strategy.isRepeatable()) {
				// 循环执行机器人操作
				StrategryExcutor.getInstance().scheduleWithFixedDelay(runnable, strategy.getDelay(), strategy.getInterval());
			} else {
				// 仅执行一次机器人操作
				StrategryExcutor.getInstance().scheduleOnce(runnable, strategy.getDelay());
			}
		}
	}

	// 压力测试的策略
	public void doStressStrategy() {

	}

	/**
	 * 添加机器人执行策略
	 *
	 * @param strategy
	 */
	public void addRobotStrategy(EStrategy strategy) {
		this.strategyTypeList.add(strategy);
		this.strategyList.add(strategy.getStrategyFactory().createStrategy(this));
	}

//	/**
//	 * 添加机器人执行策略
//	 *
//	 * @param strategy
//	 */
//	public void addRobotStrategy(IRobotStrategy strategy) {
//		this.strategyList.add(strategy);
//	}

	public RobotState getState() {
		return state;
	}

	public void setState(RobotState state) {
		this.state = state;
	}

	public List<IRobotStrategy> getStrategyList() {
		return strategyList;
	}

	public RobotLoginManager getLoginManager() {
		return loginManager;
	}

	public RobotInfoManager getInfoManager() {
		return infoManager;
	}

	public RobotBattleManager getBattleManager() {
		return battleManager;
	}

	public RobotChatManager getChatManager() {
		return chatManager;
	}
}
