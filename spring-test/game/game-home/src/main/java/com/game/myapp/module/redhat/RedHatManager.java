package com.game.myapp.module.redhat;

import org.apache.log4j.Logger;

import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.core.handler.MsgBack;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.event.RedHatCheckEvent;
import com.game.myapp.module.event.obj.RedHatObj;
import com.game.myapp.module.redhat.checker.IRedHatChecker;

import buffer.CGGetRedHatinfoMsg.CGGetRedHatinfoProto;
import buffer.CGRedHatCancelMsg.CGRedHatCancelProto;
import buffer.GCGetRedHatinfoMsg;
import buffer.GCRedHatCancelMsg;

public class RedHatManager {

	private Logger log = Logger.getLogger(RedHatManager.class);

	public void check(long uid) {
		for (RedHatBehavior behavior : RedHatBehavior.values()) {
			try {
				IRedHatChecker checker = behavior.getChecker();
				if (checker != null) {
					checker.check(uid, behavior);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 红点全部检测之后, 强制同步红点信息
		UserBean userBean = Globals.getUserManager().getUserBean(uid);
		if (userBean != null) {
			userBean.setRedHatChanged(true);
		}
	}

	/**
	 * 客户端获取小红点信息
	 * 
	 * @param msgBack
	 * 
	 * @param userBean
	 * @param msg
	 */
	public void CGGetRedHatInfo(MsgBack msgBack, UserBean userBean, CGGetRedHatinfoProto msg) {
		msgBack.addBuilder(getRedHatInfoBuilder(userBean));
	}

	public void CGRedHatCancelProto(MsgBack msgBack, UserBean userBean, CGRedHatCancelProto msg) {
		GCRedHatCancelMsg.GCRedHatCancelProto.Builder builder = GCRedHatCancelMsg.GCRedHatCancelProto.newBuilder();
		msgBack.addBuilder(builder);

		RedHatBehavior redHatBehavior = RedHatBehavior.valueOf(msg.getRedId());
		if (redHatBehavior != null) {
			GameGlobals.redHatManager.cancelRedHat(userBean.getUid(), redHatBehavior);
			builder.setStatus(ErrorCodeConst.SUCCESS);
		} else {
			builder.setStatus(ErrorCodeConst.ERROR_PARAM);
		}
	}

	/**
	 * 获取GCGetRedHatinfoProto的消息Builder
	 * 
	 * @param userBean
	 * @return
	 */
	public GCGetRedHatinfoMsg.GCGetRedHatinfoProto.Builder getRedHatInfoBuilder(UserBean userBean) {
		// 登录消息的UserBean的uid是1, 不是有效的uid, 这里不处理红点信息
		userBean = Globals.getUserManager().getUserBean(userBean.getUid());
		if (userBean == null) {
			return null;
		}

		// 重置红点变化
		userBean.setRedHatChanged(false);

		// 检测抽奖红点
		checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.ROLE_HERO_GEM_GET_LUCK_DRAW);

		GCGetRedHatinfoMsg.GCGetRedHatinfoProto.Builder builder = GCGetRedHatinfoMsg.GCGetRedHatinfoProto.newBuilder();
		builder.addAllRedHatIds(userBean.getRedHatIdList());
		log.info("userBean.RedHatIds: " + userBean.getRedHatIdList() + "===UID:" + userBean.getUid());
		builder.setStatus(ErrorCodeConst.SUCCESS);
		return builder;
	}

	/**
	 * 检测小红点
	 * 
	 * @param uid
	 * @param redHatBehavior
	 */
	public void checkBehaviorRedHat(long uid, RedHatBehavior redHatBehavior) {
		Globals.applicationContext.publishEvent(new RedHatCheckEvent(new RedHatObj(uid, redHatBehavior)));
	}

	/**
	 * 取消小红点
	 * 
	 * @param uid
	 * @param redHatBehavior
	 */
	public void cancelRedHat(long uid, RedHatBehavior redHatBehavior) {
		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}

		if (user.isRedHatDirty(redHatBehavior.getBehavior())) {
			user.setDirty(redHatBehavior.getBehavior(), false);
		}
	}
}
