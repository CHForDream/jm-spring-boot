package com.game.robot.strategy.impl;

import com.game.constants.MsgType;
import com.game.robot.Robot;
import com.game.robot.strategy.EStrategy;
import com.game.robot.strategy.EStrategyType;
import com.game.robot.strategy.LoopExecuteStrategy;
import com.google.protobuf.GeneratedMessage;

import buffer.CGHttpBeatMsg.CGHttpBeatProto;
import buffer.GCHttpBeatMsg.GCHttpBeatProto;

@EStrategyType(strategy = EStrategy.HEARTBEAT_HTTP)
public class HeartbeatLoopStrategy extends LoopExecuteStrategy {
	// 心跳间隔毫秒
	private static final long HEARTBEAT_INTERVAL = 5000L;

	public HeartbeatLoopStrategy(Robot robot, long delay) {
		super(robot, delay, HEARTBEAT_INTERVAL);
	}

	@Override
	public void doAction() {
		// 发送心跳
		CGHttpBeatProto.Builder cgMsg = CGHttpBeatProto.newBuilder();
		getRobot().sendHttpMessage(cgMsg.getMsgType(), cgMsg);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCHttpBeatMsg:
			GCHttpBeatProto gcHttpBeatProto = (GCHttpBeatProto) msg;
			if (gcHttpBeatProto.getStatus() == -1000) {
				// 玩家已掉线, 重新登录
				getRobot().getLoginManager().login();
			}

			break;

		default:
			break;
		}
	}

	@Override
	public void afterAction() {
	}
}
