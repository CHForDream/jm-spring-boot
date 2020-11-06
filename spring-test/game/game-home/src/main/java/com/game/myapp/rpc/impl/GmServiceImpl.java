package com.game.myapp.rpc.impl;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.scheduling.annotation.Async;

import com.game.common.session.GameSession;
import com.game.common.thread.GameThreadPoolManager;
import com.game.constants.Loggers;
import com.game.constants.MailConts;
import com.game.core.lock.LockManager;
import com.game.db.entity.SystemMailEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.lamp.LampBean;
import com.game.rpc.service.IGmService;
import com.game.utils.UuidGenerator;

@DubboService
@Async("gameExecutor")
public class GmServiceImpl implements IGmService {
	@Override
	public void sendMail(int mailType, String title, String content, long toUid, long fromUid, String items, long systemMailId, String params) {
		Loggers.gmLogger.info("Send user mail. mailType = " + mailType + ", title" + title + ", content" + content + ", toUid" + toUid + ", fromUid" + fromUid
				+ ", items" + items + ", systemMailId" + systemMailId + ", params" + params);
		// 给指定玩家发送邮件
		GameGlobals.mailManager.addMail(mailType, title, content, toUid, fromUid, items, systemMailId, params);
	}

	@Override
	public void sendSystemMail(long mailId) {
		Loggers.gmLogger.info("Send system mail. mailId = " + mailId);
		// 异步处理系统邮件发送
		GameThreadPoolManager.getInstance().execute(new SendSystemMailRunnable(mailId));
	}

	@Override
	public void banChat(int type, long uid, long startTime, long endTime, String reason) {
		Loggers.gmLogger.info("BanChat. type = " + type + ", uid" + uid + ", startTime" + startTime + ", endTime" + endTime + ", reason" + reason);
		// 聊天禁言
		GameGlobals.chatManager.banChat(type, uid, startTime, endTime, reason);
	}

	@Override
	public void deleteComment(long uid, int type, int commentId) {
		Loggers.gmLogger.info("Delete comment. uid = " + uid + ", type" + type + ", commentId" + commentId);
		// 删除评论
		GameGlobals.heroManager.getHeroCommentManager().gmDelComment(uid, type, commentId);
	}

	@Override
	public String kickoffUser(long uid) {
		Loggers.gmLogger.info("Kickoff user. uid = " + uid);
		// 踢玩家下线
		return GameGlobals.roleManager.kickUser(uid);
	}

	@Override
	public void sendHorseLamp(String content, long startTime, long endTime, long inverval) {
		Loggers.gmLogger.info("Send horselamp. content = " + content + ", startTime" + startTime + ", endTime" + endTime + ", inverval" + inverval);
		LampBean lampBean = new LampBean();
		lampBean.setId(UuidGenerator.generateLongUuid());
		lampBean.setContent(content);
		lampBean.setStartTime(startTime);
		lampBean.setEndTime(endTime);
		lampBean.setInterval(inverval);
		lampBean.setLastTime(startTime - inverval);
		GameGlobals.horseLampManager.addLamp(lampBean);
	}

	private static class SendSystemMailRunnable implements Runnable {
		private long mailId;

		public SendSystemMailRunnable(long mailId) {
			this.mailId = mailId;
		}

		@Override
		public void run() {
			SystemMailEntity systemMailEntity = Globals.getEntityProxy().get(SystemMailEntity.class, mailId);
			if (systemMailEntity == null) {
				Loggers.mailLogger.error("No SystemMailEntity! mailId = " + mailId);
				return;
			}

			Map<Long, GameSession> session = Globals.getChatSessionManager().getSessionMap();
			for (GameSession temp : session.values()) {
				ReentrantLock lock = LockManager.getInstance().getLock(temp.getUid());
				lock.lock();
				try {
					// 系统邮件已存在
					if (GameGlobals.mailManager.hasSystemMail(temp.getUid(), mailId)) {
						continue;
					}
					GameGlobals.mailManager.addMail(MailConts.MAIL_TYPE_SYSTEM, systemMailEntity.getTitle(), systemMailEntity.getContent(), temp.getUid(), 0,
							systemMailEntity.getItems(), mailId, "");
				} finally {
					lock.unlock();
				}
			}
		}
	}
}