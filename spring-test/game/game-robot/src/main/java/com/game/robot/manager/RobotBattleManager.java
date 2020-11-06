package com.game.robot.manager;

import com.game.robot.Robot;

import buffer.CGConnectMsg.CGConnectProto;

public class RobotBattleManager {
	private Robot owner;

	public RobotBattleManager(Robot robot) {
		this.owner = robot;
	}

	public void onSocketConnect() {
		CGConnectProto.Builder connectProto = CGConnectProto.newBuilder();
		connectProto.setUid(owner.getUid());
		owner.sendBattleSocketMessage(connectProto.getMsgType(), connectProto);
	}

	public Robot getOwner() {
		return owner;
	}

	public void setOwner(Robot owner) {
		this.owner = owner;
	}
}
