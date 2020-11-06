package com.game.robot.manager;

import com.game.constants.Loggers;
import com.game.robot.Robot;
import com.game.robot.RobotState;
import com.game.robot.config.Config;
import com.game.robot.thread.StrategryExcutor;

import buffer.CGDoLoginMsg.CGDoLoginProto;
import buffer.CGDoLoginMsg.CGDoLoginProto.Builder;
import buffer.CGEliteDuplicateInfo.CGEliteDuplicateInfoProto;
import buffer.CGGetHeroListMsg.CGGetHeroListProto;
import buffer.CGGetLoginSuccessInfoMsg.CGGetLoginSuccessInfoProto;
import buffer.CGLoadingRoleMsg.CGLoadingRoleProto;
import buffer.CGPveCityInfoMsg.CGPveCityInfoProto;
import buffer.GCDoLoginMsg.GCDoLoginProto;
import buffer.GCGetLoginSuccessInfoMsg.GCGetLoginSuccessInfoProto;
import buffer.GCLoadingRoleMsg.GCLoadingRoleProto;

public class RobotLoginManager {
	private Robot owner;

	public RobotLoginManager(Robot robot) {
		this.owner = robot;
	}

	public void login() {
		owner.setState(RobotState.LOGIN);

		// 客户端登录流程
		StrategryExcutor.getInstance().scheduleOnce(new Runnable() {
			@Override
			public void run() {
				// 发送login
				CGDoLoginProto.Builder cgDoLogin = buildCGDoLoginProto();
				owner.sendHttpMessage(cgDoLogin.getMsgType(), cgDoLogin);

				// 发送登录成功CGGetLoginSuccessInfoProto. msgType:5003;
				CGGetLoginSuccessInfoProto.Builder cgGetLoginSuccessInfo = CGGetLoginSuccessInfoProto.newBuilder();
				owner.sendHttpMessage(cgGetLoginSuccessInfo.getMsgType(), cgGetLoginSuccessInfo);

				// HeroList
				CGGetHeroListProto.Builder cgGetHeroList = CGGetHeroListProto.newBuilder();
				cgGetHeroList.setReqType(0);
				owner.sendHttpMessage(cgGetHeroList.getMsgType(), cgGetHeroList);

				// 发送loadingRole
				CGLoadingRoleProto.Builder cgLoadingRole = CGLoadingRoleProto.newBuilder();
				owner.sendHttpMessage(cgLoadingRole.getMsgType(), cgLoadingRole);

				// 发送getHomeInfo
//				CGGetHomeInfoProto.Builder cgGetHomeInfo = CGGetHomeInfoProto.newBuilder();
//				owner.sendHttpMessage(cgGetHomeInfo.getMsgType(), cgGetHomeInfo);

				CGPveCityInfoProto.Builder cgPveCityBuilder = CGPveCityInfoProto.newBuilder();
				owner.sendHttpMessage(cgPveCityBuilder.getMsgType(), cgPveCityBuilder);

				// 精英副本信息
				CGEliteDuplicateInfoProto.Builder cgEliteDuplicateInfo = CGEliteDuplicateInfoProto.newBuilder();
				owner.sendHttpMessage(cgEliteDuplicateInfo.getMsgType(), cgEliteDuplicateInfo);

				// 开始执行测试策略
				owner.doStrategy();

				// 连接聊天服
//				new Thread("chat-" + owner.getId()) {
//					public void run() {
//						owner.getSocketChatSession().connect(ClientNettyType.CHAT, owner.getInfoManager().getChatServer(),
//								owner.getInfoManager().getChatServerPort());
//					}
//				}.start();
			}
		}, 200L);
	}

	public void GCDoLoginMsg(GCDoLoginProto msg) {
		// 收到登录消息, 获取玩家信息
//		Loggers.robotLogger.info("Receive GCDoLoginProto");
		if (msg.getStatus() != 0) {
			Loggers.robotLogger.info("Login failed! robotId = " + owner.getId() + ", status = " + msg.getStatus());
			return;
		}

		// 记录登录信息
		owner.setUid(msg.getUid());
		owner.setSessionKey(msg.getSessionKey());
		owner.getInfoManager().setChatServer(msg.getChatServer());
		owner.getInfoManager().setChatServerPort(msg.getChatPort());
	}

	public void GCLoadingRoleProto(GCLoadingRoleProto msg) {
		owner.getInfoManager().setGcLoadingRoleProto(msg);
		owner.getInfoManager().setGold((int) msg.getGold());
		owner.getInfoManager().setHomeMoney((int) msg.getCash());
		owner.getInfoManager().setBlueCash((int) msg.getBlueCash());
	}

	public void GCGetLoginSuccessInfoProto(GCGetLoginSuccessInfoProto msg) {
		// 记录引导信息
		owner.getInfoManager().setGuide(msg.getGuide());
		owner.getInfoManager().setNextGuideIds(msg.getNextGuideIdsList());
		owner.getInfoManager().setBattleGuides(msg.getBattleGuidesList());
	}

	private Builder buildCGDoLoginProto() {
		CGDoLoginProto.Builder builder = CGDoLoginProto.newBuilder();
		builder.setNickname(Config.USER_NAME + owner.getId());
		builder.setUserId(Config.USER_NAME + owner.getId());
		builder.setSex(1);
		builder.setAvatar("");
		return builder;
	}
}
