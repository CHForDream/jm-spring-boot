package com.game.global;

import org.springframework.context.ConfigurableApplicationContext;

import com.game.common.data.DataContainer;
import com.game.common.data.define.DataConfigContainer;
import com.game.common.data.define.DataConfigManager;
import com.game.common.msg.MessageManager;
import com.game.common.session.BattleSessionManager;
import com.game.common.session.ChatSessionManager;
import com.game.common.user.IRoleManager;
import com.game.common.user.UserManager;
import com.game.core.db.proxy.IDbProxy;
import com.game.core.db.proxy.impl.EntityProxy;
import com.game.core.lock.LockManager;

public class Globals {
	public static ConfigurableApplicationContext applicationContext = null;

	public static AppConfigBean appConfigBean = null;

	private static IRoleManager ROLE_MANAGER;

	/** 服务器是否强制启动 */
	public static boolean SREVER_FORCE_ON = true;

	/** 服务器是否可以启动的状态 */
	private static boolean SREVER_START = false;

	private static boolean IS_SREVER_STARTED = false;

	public static void changeStartStatus() {
		SREVER_START = true;
	}

	public static boolean isServerStartStatus() {
		return SREVER_START;
	}

	public static void startSever() {
		IS_SREVER_STARTED = true;
	}

	public static boolean stopServer() {
		return IS_SREVER_STARTED = false;
	}

	public static boolean isSreverStarted() {
		return IS_SREVER_STARTED;
	}

	public static LockManager getLockManager() {
		return LockManager.getInstance();
	}

	public static IDbProxy getEntityProxy() {
		return EntityProxy.getInstance();
	}

	public static AppConfigBean getAppConfigBean() {
		if (appConfigBean != null) {
			return appConfigBean;
		}
		appConfigBean = Globals.applicationContext.getBean(AppConfigBean.class);
		return appConfigBean;
	}

	public static DataConfigManager getDataConfigManager() {
		return DataConfigManager.getInstance();
	}

	public static DataContainer getDataContainer() {
		return DataContainer.getInstance();
	}

	public static DataConfigContainer getDataConfigContainer() {
		return DataConfigContainer.getInstance();
	}

	public static UserManager getUserManager() {
		return UserManager.getInstance();
	}

	public static ChatSessionManager getChatSessionManager() {
		return ChatSessionManager.getInstance();
	}

	public static MessageManager getMessageManager() {
		return MessageManager.getInstance();
	}

	public static BattleSessionManager getGameSessionManager() {
		return BattleSessionManager.getInstance();
	}

	public static IRoleManager getRoleManager() {
		return ROLE_MANAGER;
	}

	public static void setRoleManager(IRoleManager roleManager) {
		Globals.ROLE_MANAGER = roleManager;
	}
}
