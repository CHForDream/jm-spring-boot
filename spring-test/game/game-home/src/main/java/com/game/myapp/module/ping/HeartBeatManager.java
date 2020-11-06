package com.game.myapp.module.ping;

import com.game.common.session.GameSession;
import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;

import buffer.CGHeartBeatMsg.CGHeartBeatProto;
import buffer.CGHttpBeatMsg.CGHttpBeatProto;
import buffer.GCHeartBeatMsg;
import buffer.GCHttpBeatMsg;

public class HeartBeatManager {
	public void CGHeartBeatMsg(GameSession session, CGHeartBeatProto msg) {
		if (session == null) {
			return;
		}

		session.setLastHeartBeatMill(System.currentTimeMillis());

		GCHeartBeatMsg.GCHeartBeatProto.Builder gcMsg = GCHeartBeatMsg.GCHeartBeatProto.newBuilder();
		gcMsg.setPong(msg.getPing());
		session.sendMsg(gcMsg);
	}

	public void CGHttpBeat(MsgBack msgBack, UserBean userBean, CGHttpBeatProto msg) {
//		log.info("收到客户端HttpBeat请求,uid:" + userBean.getUid() + "  time:" + DateUtil.getDateStr(new Date()));
//		log.error("CGHttpBeat:   " + userBean.getUid());
		GCHttpBeatMsg.GCHttpBeatProto.Builder builder = GCHttpBeatMsg.GCHttpBeatProto.newBuilder();
		msgBack.addBuilder(builder);
		builder.setStatus(ErrorCodeConst.SUCCESS);

		// 检测玩家0点重置
		GameGlobals.roleManager.dailyReset(userBean);
	}
}
