package com.game.robot.strategy.impl;

import com.game.common.client.netty.ClientNettyType;
import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.game.robot.Robot;
import com.game.robot.strategy.EStrategy;
import com.game.robot.strategy.EStrategyType;
import com.game.robot.strategy.LoopExecuteStrategy;
import com.google.protobuf.GeneratedMessage;

import buffer.CGHeartBeatMsg.CGHeartBeatProto;

@EStrategyType(strategy = EStrategy.HEARTBEAT_CHAT)
public class HeartbeatChatLoopStrategy extends LoopExecuteStrategy {
	// 心跳间隔毫秒
	private static final long HEARTBEAT_INTERVAL = 10000L;

	public HeartbeatChatLoopStrategy(Robot robot, long delay) {
		super(robot, delay, HEARTBEAT_INTERVAL);
	}

	@Override
	public void doAction() {
		if (getRobot().getSocketChatSession().isConnect()) {
			// 发送心跳
			CGHeartBeatProto.Builder cgMsg = CGHeartBeatProto.newBuilder();
			getRobot().sendChatSocketMessage(cgMsg.getMsgType(), cgMsg);
		} else {
			Loggers.robotLogger.warn("Chat server is disconnect!");
			// 连接已断开, 重连
			getRobot().getSocketChatSession().connect(ClientNettyType.CHAT, getRobot().getInfoManager().getChatServer(),
					getRobot().getInfoManager().getChatServerPort());
		}
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCHeartBeatMsg:
			// do nothing
			break;

		default:
			break;
		}
	}

	@Override
	public void afterAction() {
	}
}
