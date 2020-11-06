package com.game.myapp.module.event.listener;

import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.RoleLogoutLog;
import com.game.common.user.UserBean;
import com.game.core.lock.LockManager;
import com.game.db.entity.RoleEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.event.LogoutEvent;

@Component
@Async("gameExecutor")
public class LogoutListener implements ApplicationListener<LogoutEvent> {

	@Override
	public void onApplicationEvent(LogoutEvent event) {
		UserBean userBean = (UserBean) event.getSource();
		ReentrantLock lock = LockManager.getInstance().getLock(userBean.getUid());
		lock.lock();
		try {
			RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(userBean.getUid());
			if (roleEntity == null) {
				return;
			}

			// 更新玩家上次登录时间
			roleEntity.setLastLoginTime(userBean.getLoginTime());
			// 更新玩家的离线时间
			roleEntity.setQuitTime(System.currentTimeMillis());
			Globals.getEntityProxy().updateAsync(roleEntity);

			// 主动存储未入库的数据(数据库)
			Globals.getRoleManager().saveRoleData(userBean.getUid());

			// 更新user离线状态(Redis)
			Globals.getUserManager().onLogout(userBean);

			// 玩家登出打点日志
			RoleLogoutLog loginLog = new RoleLogoutLog(userBean);
			loginLog.setLoginTime(userBean.getLoginTime());
			loginLog.setBlueCash(roleEntity.getChargeCash());
			loginLog.setRewardBlueCash(roleEntity.getRewardBuleCash());
			loginLog.setCoin(roleEntity.getCoin());
			LogfPrinter.getInstance().push(loginLog);
		} finally {
			lock.unlock();
		}

		// 释放用户锁
		LockManager.getInstance().releaseLock(userBean.getUid());
	}
}
