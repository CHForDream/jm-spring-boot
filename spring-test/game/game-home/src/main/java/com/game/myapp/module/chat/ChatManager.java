package com.game.myapp.module.chat;

import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.ChatLog;
import com.game.common.session.GameSession;
import com.game.common.user.UserBean;
import com.game.constants.ChatConstants;
import com.game.constants.ErrorCodeConst;
import com.game.core.handler.MsgBack;
import com.game.db.entity.BanchatEntity;
import com.game.db.entity.RoleEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.google.protobuf.AbstractMessage.Builder;

import buffer.CGChatGm.CGChatGmProto;
import buffer.CGChatMsg;
import buffer.GCBanchChatMsg;
import buffer.GCChatGm.GCChatGmProto;
import buffer.GCChatMsg;

@SuppressWarnings("rawtypes")
public class ChatManager implements Runnable {
	public static final int CHAT_TOHER = 1001;

	public void doChat(GameSession session, CGChatMsg.CGChatProto cgMsg) {
		int chatType = cgMsg.getChatType();
		// 执行GM指令
		if (Globals.getAppConfigBean().isEnableChatGm() && (cgMsg.getCont().startsWith("!") || cgMsg.getCont().startsWith("！"))) {
			if (GameGlobals.chatGmManager.excuteCmd(session.getUid(), cgMsg.getCont().substring(1))) {
				return;
			}
		}

		// 玩家已被禁言
		BanchatEntity banchatEntity = getBanchatEntity(session.getUid(), chatType);
		if (banchatEntity != null) {
			sendBanChatMessage(session, banchatEntity, chatType);
			return;
		}

		GCChatMsg.GCChatProto.Builder gcMsg = GCChatMsg.GCChatProto.newBuilder();
		gcMsg.setAren(cgMsg.getAren());
		gcMsg.setCont(cgMsg.getCont());
		gcMsg.setFromUid(cgMsg.getFromUid());
		gcMsg.setHead(cgMsg.getHead());
		gcMsg.setLv(cgMsg.getLv());
		gcMsg.setChatType(cgMsg.getChatType());
		gcMsg.setName(cgMsg.getName());
		gcMsg.setSex(cgMsg.getSex());
		gcMsg.setVoice(cgMsg.getVoice());
		if (chatType == ChatConstants.CHAT_BAN_TYPE_WORLD) {
			Globals.getChatSessionManager().addChatAllMsg(gcMsg);
		} else if (chatType == ChatConstants.CHAT_BAN_TYPE_PRIVATE) {
			sendtoPlayer(cgMsg.getToUid(), gcMsg);
		}
		addChatLog(session.getUid(), cgMsg);
	}

	public void sendtoPlayer(long uid, Builder gcMsg) {
		if (Globals.getUserManager().isThisServer(uid)) {
			sendMessage(uid, gcMsg);
			return;
		}
		// TODO 远程调用发送信息
//		this.invokThrd(CHAT_TOHER, uid, gcMsg);
	}

	private void sendMessage(long uid, Builder gcMsg) {
		GameSession player = Globals.getChatSessionManager().getSession(uid);
		if (player != null) {
			player.sendMsg(gcMsg);
		}
	}

	public void CGChatGmProto(MsgBack msgBack, UserBean userBean, CGChatGmProto msg) {
		// 执行GM指令
		if (Globals.getAppConfigBean().isEnableChatGm() && (msg.getGmString().startsWith("!") || msg.getGmString().startsWith("！"))) {
			GameGlobals.chatGmManager.excuteCmd(userBean.getUid(), msg.getGmString().substring(1));
		}
		msgBack.addBuilder(GCChatGmProto.newBuilder().setStatus(ErrorCodeConst.SUCCESS));
	}

	@Override
	public void run() {
		while (true) {
			LinkedBlockingDeque<Builder> msgs = Globals.getChatSessionManager().getMsgs();
			try {
				Builder tempMsg = msgs.take();
				Map<Long, GameSession> session = Globals.getChatSessionManager().getSessionMap();
				for (GameSession temp : session.values()) {
					temp.sendMsg(tempMsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 聊天禁言
	 * 
	 * @param type      0全部；1世界聊天；2私聊
	 * @param uid       玩家uid
	 * @param startTime 禁言开始时间戳
	 * @param endTime   禁言结束时间戳
	 * @param reason    禁言原因
	 * @return
	 */
	public boolean banChat(int type, long uid, long startTime, long endTime, String reason) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			// 先要查找玩家是否存在
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				return false;
			}

			BanchatEntity banchatEntity = Globals.getEntityProxy().get(BanchatEntity.class, uid);
			boolean isInDb = true;
			if (banchatEntity == null) {// 不存在
				banchatEntity = new BanchatEntity();
				isInDb = false;
			}

			switch (type) {
			case ChatConstants.CHAT_BAN_TYPE_ALL: // 全部禁言
				banchatEntity.setStartTimeAll(startTime);
				banchatEntity.setEndTimeAll(endTime);
				banchatEntity.setReasonAll(reason);
				banchatEntity.setStartTimeFriend(startTime);
				banchatEntity.setEndTimeAllFriend(endTime);
				banchatEntity.setReasonFriend(reason);
				break;

			case ChatConstants.CHAT_BAN_TYPE_WORLD:// 世界禁言
				banchatEntity.setStartTimeAll(startTime);
				banchatEntity.setEndTimeAll(endTime);
				banchatEntity.setReasonAll(reason);
				break;

			case ChatConstants.CHAT_BAN_TYPE_PRIVATE:// 好友禁言
				banchatEntity.setStartTimeFriend(startTime);
				banchatEntity.setEndTimeAllFriend(endTime);
				banchatEntity.setReasonFriend(reason);
				break;

			default:
				break;
			}

			// 数据存在走更新的函数
			if (isInDb) {
				Globals.getEntityProxy().updateAsync(banchatEntity);
			} else {
				banchatEntity.setUid(uid);
				Globals.getEntityProxy().insert(banchatEntity);
			}

			return true;
		} finally {
			lock.unlock();
		}
	}

	public BanchatEntity getBanchatEntity(long uid, int type) {
		long currTime = System.currentTimeMillis();
		BanchatEntity banchatEntity = Globals.getEntityProxy().get(BanchatEntity.class, uid);
		if (banchatEntity == null) {
			return null;
		}

		switch (type) {
		case ChatConstants.CHAT_BAN_TYPE_WORLD:
			if (banchatEntity.getStartTimeAll() <= currTime && banchatEntity.getEndTimeAll() >= currTime) {
				return banchatEntity;
			}
			break;
		case ChatConstants.CHAT_BAN_TYPE_PRIVATE:
			if (banchatEntity.getStartTimeFriend() <= currTime && banchatEntity.getEndTimeAllFriend() >= currTime) {
				return banchatEntity;
			}
		default:
			break;
		}
		return null;
	}

	private void sendBanChatMessage(GameSession session, BanchatEntity banchatEntity, int type) {
		GCBanchChatMsg.GCBanchChatProto.Builder gcMsg = GCBanchChatMsg.GCBanchChatProto.newBuilder();
		switch (type) {
		case ChatConstants.CHAT_BAN_TYPE_WORLD:
			gcMsg.setStartTime(banchatEntity.getStartTimeAll());
			gcMsg.setEndTime(banchatEntity.getEndTimeAll());
			break;
		case ChatConstants.CHAT_BAN_TYPE_PRIVATE:
			gcMsg.setStartTime(banchatEntity.getStartTimeFriend());
			gcMsg.setEndTime(banchatEntity.getEndTimeAllFriend());
			break;
		default:
			break;
		}
		session.sendMsg(gcMsg);
	}

	/**
	 * 
	 * 日志打点
	 */
	private void addChatLog(long uid, CGChatMsg.CGChatProto msg) {
		UserBean userBean = Globals.getUserManager().getUserBean(uid);
		ChatLog chatLog = new ChatLog(userBean);
		chatLog.setType(msg.getChatType());
		chatLog.setToUid(msg.getToUid());
		chatLog.setContent(msg.getCont());
		LogfPrinter.getInstance().push(chatLog);
	}

	public void start() {
		new Thread(this, "Thread-chat").start();
	}

}
