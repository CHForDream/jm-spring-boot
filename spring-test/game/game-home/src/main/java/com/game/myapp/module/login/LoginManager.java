package com.game.myapp.module.login;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.game.common.user.UserBean;
import com.game.constants.GameConstants;
import com.game.constants.Loggers;
import com.game.core.cache.EhcacheUtil;
import com.game.core.handler.MsgBack;
import com.game.db.entity.RoleEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.event.LoginSuccessEvent;
import com.game.utils.StringUtils;

import buffer.CGDoLoginMsg;
import buffer.CGDoLoginMsg.CGDoLoginProto;
import buffer.GCDoLoginMsg;

public class LoginManager {
//	1平台账号验证失败;2客户端连接的服务器不是逻辑服;3无效的平台账号;-1001登录异常
	private static final int ERROR_LOGIN_VERIFY_TOKEN_FAILED = 1;
//	private static final int ERROR_LOGIN_NOT_HOME_SERVER = 2;
	private static final int ERROR_LOGIN_INVALID_USERID = 3;
	private Logger logger = Loggers.loginLogger;

	public void doLogin(MsgBack msgBack, String ip, CGDoLoginMsg.CGDoLoginProto msg) {
		long mill = System.currentTimeMillis();
		GCDoLoginMsg.GCDoLoginProto.Builder gcMsg = GCDoLoginMsg.GCDoLoginProto.newBuilder();
		gcMsg.setIsReconnect(msg.getIsReconnect());
		msgBack.addBuilder(gcMsg);
//		if (!Globals.getAppConfigBean().isHomeServer()) {
//			logger.error("ip:" + ip + "  role login " + msg.getUserId() + " not home server");
//			gcMsg.setSessionKey(-1);
//			gcMsg.setStatus(ERROR_LOGIN_NOT_HOME_SERVER);
//			return;
//		}

		if (msg.getUserId() == null || msg.getUserId().length() < 5) {
			logger.error("Error userId: " + msg.getUserId());
			gcMsg.setSessionKey(-1);
			gcMsg.setStatus(ERROR_LOGIN_INVALID_USERID);
			return;
		}

		if (!verifyToken(msg.getUserId(), msg.getToken())) {
			logger.error("Verify token filed! userId = " + msg.getUserId() + ", token = " + msg.getToken());
			gcMsg.setSessionKey(-1);
			gcMsg.setStatus(ERROR_LOGIN_VERIFY_TOKEN_FAILED);
			return;
		}

		// 根据平台账号查找玩家uid。若没有玩家uid，则创建玩家的uid。
		long uid = GameGlobals.accountManager.getUidByUserId(msg.getUserId());
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			// 玩家之前登录的是否是当前服。若没有玩家定位信息，默认玩家之前登录的是当前游戏服
			boolean isThisServer = Globals.getUserManager().isThisServer(uid);
			if (!isThisServer) {
				// 玩家之前不在当前服, 需要清理当前服的缓存，避免数据脏读脏写
				EhcacheUtil.getInstance().clearAll(uid);
			}

			// 获取玩家缓存UserBean
			UserBean userBean = Globals.getUserManager().getUserBean(uid);
			if (userBean == null) {
				userBean = initUserBean(uid, msg);
			} else {
				userBean.setLastOperationTime(mill);
			}

			// 1.更新在线状态。
			// 2.如果玩家之前在其他游戏服，会强制存储未保存的数据。此操作必须在该玩家所有数据操作之前处理。
			// 3.如果Redis中有缓存的离线数据, 加载到UserBean中
			Globals.getUserManager().onLogin(userBean);

			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				role = GameGlobals.roleManager.initRole(uid, msg);
				if (role == null) {
					// 创建账号失败（正常不会出现）
					gcMsg.setStatus(GameConstants.LOGIN_OUTTIME);
					logger.error("Error create RoleEntity: userId = " + msg.getUserId());
					return;
				}
				userBean.setNew(true);
			}

			// 若玩家头像数据为空，但登录消息头像不为空，则更新头像
			if (StringUtils.isEmpty(role.getAvatar()) && !StringUtils.isEmpty(msg.getAvatar())) {
				role.setAvatar(msg.getAvatar());
				Globals.getEntityProxy().updateAsync(role);
			}
			Globals.applicationContext.publishEvent(new LoginSuccessEvent(role));

			gcMsg.setSessionKey(userBean.getToken());
			gcMsg.setStatus(0);
			gcMsg.setUid(uid);
			gcMsg.setIsnew(userBean.isNew() ? 1 : 0);
			gcMsg.setChatServer(Globals.getAppConfigBean().getChatServerIp());
			gcMsg.setChatPort(Globals.getAppConfigBean().getChatServerPort());
		} finally {
			lock.unlock();
		}

		logger.info("Login. uid = " + gcMsg.getUid() + ", ip = " + ip + ", costTime = " + (System.currentTimeMillis() - mill) + "ms");
	}

	private UserBean initUserBean(long uid, CGDoLoginProto msg) {
		UserBean userBean = new UserBean();
		userBean.setUid(uid);
		userBean.setPlatformToken(msg.getPlatformToken());
		userBean.setPassportId(msg.getUserId());
		userBean.setDevId(msg.getDeviceId());
		userBean.setChannel(msg.getChannel());
		userBean.setPlatform(msg.getPlatform());
		userBean.setNew(false);

		long now = System.currentTimeMillis();
		userBean.setLastOperationTime(now);
		userBean.setLoginTime(now);
		userBean.setToken(UUID.randomUUID().hashCode());
		userBean.setFunctionOpen(true);
		Globals.getUserManager().addUser(userBean);
		return userBean;
	}

	private boolean verifyToken(String user_id, String token) {
		// TODO
		return true;
	}
}
