package com.game.robot.manager;

import com.game.constants.MsgType;
import com.game.robot.Robot;

import buffer.CGConnectChatMsg.CGConnectChatProto;
import buffer.GCServerBackMsg.GCServerBackProto;

public class RobotChatManager {
	private Robot owner;

	public RobotChatManager(Robot robot) {
		this.owner = robot;
	}

	public void onSocketConnect() {
		CGConnectChatProto.Builder connectProto = CGConnectChatProto.newBuilder();
		connectProto.setUid(owner.getUid());
		owner.sendChatSocketMessage(connectProto.getMsgType(), connectProto);
	}

	public void GCServerBack(GCServerBackProto msg) {
		if (msg.getBackType() != MsgType.CGConnectChatMsg) {
			return;
		}

//		// 添加聊天服心跳
//		owner.addRobotStrategy(EStrategy.HEARTBEAT_CHAT);
	}

	public Robot getOwner() {
		return owner;
	}

	public void setOwner(Robot owner) {
		this.owner = owner;
	}
}
