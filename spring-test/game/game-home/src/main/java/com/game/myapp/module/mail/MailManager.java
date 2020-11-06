package com.game.myapp.module.mail;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.logf.LogfConstants;
import com.game.common.user.UserBean;
import com.game.constants.Loggers;
import com.game.constants.MailConts;
import com.game.core.handler.MsgBack;
import com.game.core.hibernate.key.LongLongPrimaryKey;
import com.game.core.lock.LockManager;
import com.game.db.entity.MailEntity;
import com.game.db.entity.RoleSystemMailEntity;
import com.game.db.entity.SystemMailEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.rpc.vo.RoleInfo;
import com.game.utils.StringUtils;
import com.game.utils.UuidGenerator;

import buffer.GCMailDeleteMsg;
import buffer.GCMailReadMsg;
import buffer.GCMailgetListMsg;

public class MailManager {

	public void CGMailgetListMsg(MsgBack msgBack, UserBean userBean, buffer.CGMailgetListMsg.CGMailgetListProto cgMsg) {
		List<MailEntity> list = Globals.getEntityProxy().findAllByUid(MailEntity.class, userBean.getUid());
		GCMailgetListMsg.GCMailgetListProto.Builder gcMsg = GCMailgetListMsg.GCMailgetListProto.newBuilder();
		int mailType = cgMsg.getMailType();
		gcMsg.setMailType(mailType);

		Collections.sort(list, new Comparator<MailEntity>() {
			@Override
			public int compare(MailEntity o1, MailEntity o2) {
				return (int) (o2.getCreateTime() - o1.getCreateTime());
			}
		});

		long curTime = System.currentTimeMillis();
		for (MailEntity temp : list) {
			if (temp.getMailType() != mailType) {
				continue;
			}
			GCMailgetListMsg.MailGCMailgetList.Builder tempMail = GCMailgetListMsg.MailGCMailgetList.newBuilder();
			tempMail.setContent(temp.getContent());
			tempMail.setMailId(temp.getMailId());
			tempMail.setReadstatus(temp.isHasRead() ? 2 : 0);
			tempMail.setRemindMill(curTime - temp.getCreateTime());// 邮件已收到时间
			tempMail.setRewards(temp.getItems() == null ? "" : temp.getItems());
			tempMail.setRewardStatus(temp.isGotItems() ? 2 : 0);
			tempMail.setTitle(temp.getTitle());
			tempMail.setName(temp.getName());
			tempMail.setHead(temp.getAvatar());
			tempMail.setSendTime(temp.getCreateTime());
			gcMsg.addMails(tempMail);
		}
		msgBack.addBuilder(gcMsg);
	}

	public void CGMailReadMsg(MsgBack msgBack, UserBean userBean, buffer.CGMailReadMsg.CGMailReadProto cgMsg) {
		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			switch (cgMsg.getReadType()) {
			case 1: {// 已读
				MailEntity db = this.getMailEntity(userBean.getUid(), cgMsg.getParams());
				if (db == null) {
					Loggers.mailLogger.error("No mail! uid = " + userBean.getUid() + ", mailId = " + cgMsg.getParams());
					return;
				}
				db.setHasRead(true);
				Globals.getEntityProxy().updateAsync(db);
				break;
			}
			case 2: {// 已读 + 领取附件
				MailEntity db = this.getMailEntity(userBean.getUid(), cgMsg.getParams());
				if (db == null) {
					Loggers.mailLogger.error("No mail! uid = " + userBean.getUid() + ", mailId = " + cgMsg.getParams());
					return;
				}

				if (db.getItems() != null && !"".equals(db.getItems()) && !db.isGotItems()) {
					String[] itemArray = db.getItems().split(";");
					for (String temp : itemArray) {
						GameGlobals.bagManager.addItem(userBean.getUid(), Integer.parseInt(temp.split(",")[0]), Integer.parseInt(temp.split(",")[1]),
								Integer.parseInt(temp.split(",")[2]), LogfConstants.CHANNEL_MAIL, String.valueOf(db.getMailId()));
					}
				}
				db.setGotItems(true);
				db.setHasRead(true);

				Globals.getEntityProxy().updateAsync(db);
				break;
			}
			case 3: {// 领取全部
				List<MailEntity> list = Globals.getEntityProxy().findAllByUid(MailEntity.class, userBean.getUid());
				for (MailEntity temp : list) {
					if (temp.getMailType() == cgMsg.getMailType() && !temp.isGotItems()) {
						temp.setGotItems(true);
						temp.setHasRead(true);
						Globals.getEntityProxy().updateAsync(temp);
						String[] itemArray = temp.getItems().split(";");
						for (String tempSplite : itemArray) {
							GameGlobals.bagManager.addItem(userBean.getUid(), Integer.parseInt(tempSplite.split(",")[0]),
									Integer.parseInt(tempSplite.split(",")[1]), Integer.parseInt(tempSplite.split(",")[2]), LogfConstants.CHANNEL_MAIL,
									String.valueOf(temp.getMailId()));
						}
					}
				}
				break;
			}

			default:
				break;
			}
		} finally {
			lock.unlock();
		}

		GCMailReadMsg.GCMailReadProto.Builder gcMsg = GCMailReadMsg.GCMailReadProto.newBuilder();
		gcMsg.setReadType(cgMsg.getReadType());
		gcMsg.setMailId(cgMsg.getParams());
		msgBack.addBuilder(gcMsg);
		// 检测邮件小红点
		GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.MAIL_SYSTEM);
		GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.MAIL_FRINED);
//		GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.MAIL_SYSTEM_ATTACHMENT);
//		GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.MAIL_FRIEND_ATTACHMENT);
	}

	public void CGMailDeleteMsg(MsgBack msgBack, UserBean userBean, buffer.CGMailDeleteMsg.CGMailDeleteProto cgMsg) {
		if (cgMsg.getDelType() == 1) {// 删除单个邮件
			MailEntity db = this.getMailEntity(userBean.getUid(), cgMsg.getParams());
			if (db != null && (db.isGotItems() || db.isHasRead())) {
				Globals.getEntityProxy().delete(db);
			}
		} else if (cgMsg.getDelType() == 2) {// 删除全部邮件
			List<MailEntity> list = Globals.getEntityProxy().findAllByUid(MailEntity.class, userBean.getUid());
			for (MailEntity mailEntity : list) {
				if (mailEntity.getMailType() == cgMsg.getMailType() && (mailEntity.isGotItems() || StringUtils.isEmpty(mailEntity.getItems()))) {
					Globals.getEntityProxy().delete(mailEntity);
				}
			}
		}
		GCMailDeleteMsg.GCMailDeleteProto.Builder gcMsg = GCMailDeleteMsg.GCMailDeleteProto.newBuilder();
		gcMsg.setDelType(cgMsg.getDelType());
		gcMsg.setMailType(cgMsg.getMailType());
		gcMsg.setParams(cgMsg.getParams());
		msgBack.addBuilder(gcMsg);

		// 检测邮件小红点
		GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.MAIL_SYSTEM);
		GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.MAIL_FRINED);
//		GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.MAIL_SYSTEM_ATTACHMENT);
//		GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.MAIL_FRIEND_ATTACHMENT);
	}

	public void addMail(int mailType, String title, String content, long toUid, long fromUid, String items, long systemMailId, String params) {
		RoleInfo fromRoleInfo = null;
		String fromName = "";
		int fromSex = 0;
		String fromAvatar = "";
		if (fromUid != 0) {
			fromRoleInfo = GameGlobals.roleManager.remoteGetRoleInfo(fromUid);
			fromName = fromRoleInfo.getRole().getName();
			fromSex = fromRoleInfo.getRole().getSex();
			fromAvatar = fromRoleInfo.getRole().getAvatar();
		}
		ReentrantLock lock = Globals.getLockManager().getLock(toUid);
		lock.lock();
		try {
			MailEntity dbEntity = new MailEntity();
			dbEntity.setUid(toUid);
			dbEntity.setMailId(UuidGenerator.generateLongUuid());
			dbEntity.setAvatar(fromAvatar);
			dbEntity.setContent(content);
			dbEntity.setCreateTime(System.currentTimeMillis());
			dbEntity.setHasRead(false);
			dbEntity.setItems(items);
			dbEntity.setMailType(mailType);
			dbEntity.setName(fromName);
			dbEntity.setSex(fromSex);
			dbEntity.setTitle(title);
			dbEntity.setFromUid(fromUid);
			dbEntity.setParams(params);
			dbEntity.setSystemMailId(systemMailId);
			Globals.getEntityProxy().insert(dbEntity);
		} finally {
			lock.unlock();
		}

		// 检测邮件小红点
		GameGlobals.redHatManager.checkBehaviorRedHat(toUid, RedHatBehavior.MAIL_SYSTEM);
		GameGlobals.redHatManager.checkBehaviorRedHat(toUid, RedHatBehavior.MAIL_FRINED);
//		GameGlobals.redHatManager.checkBehaviorRedHat(toUid, RedHatBehavior.MAIL_SYSTEM_ATTACHMENT);
//		GameGlobals.redHatManager.checkBehaviorRedHat(toUid, RedHatBehavior.MAIL_FRIEND_ATTACHMENT);
	}

	/**
	 * 玩家登陆的时候需要load下GM的系统邮件
	 * 
	 * @param uid
	 */
	public void loadGmMail(long uid) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			List<SystemMailEntity> mailEntities = Globals.getEntityProxy().findAll(SystemMailEntity.class);
			for (SystemMailEntity systemMailEntity : mailEntities) {
				long mailId = systemMailEntity.getId();
				if (systemMailEntity.getInvalidTime() < System.currentTimeMillis()) {
					continue;
				}

				if (hasSystemMail(uid, mailId)) {
					continue;
				}

				addSysteMailId(uid, mailId);
				addMail(MailConts.MAIL_TYPE_SYSTEM, systemMailEntity.getTitle(), systemMailEntity.getContent(), uid, 0, systemMailEntity.getItems(), mailId,
						"");
			}
		} finally {
			lock.unlock();
		}
	}

	private void addSysteMailId(long uid, long mailId) {
		RoleSystemMailEntity roleSystemMailEntity = Globals.getEntityProxy().get(RoleSystemMailEntity.class, uid);
		roleSystemMailEntity.getSystemMailIdSet().add(mailId);
		Globals.getEntityProxy().updateAsync(roleSystemMailEntity);
	}

	public boolean hasSystemMail(long uid, long mailId) {
		RoleSystemMailEntity roleSystemMailEntity = Globals.getEntityProxy().get(RoleSystemMailEntity.class, uid);
		if (roleSystemMailEntity == null) {
			roleSystemMailEntity = new RoleSystemMailEntity();
			roleSystemMailEntity.setUid(uid);
			Globals.getEntityProxy().insert(roleSystemMailEntity);
			return false;
		}

		return roleSystemMailEntity.getSystemMailIdSet().contains(mailId);
	}

	private MailEntity getMailEntity(long uid, long mailId) {
		return Globals.getEntityProxy().get(MailEntity.class, new LongLongPrimaryKey(uid, mailId));
	}
}
