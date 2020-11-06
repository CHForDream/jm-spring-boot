package com.game.myapp.module.event.listener;

import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.RoleLoginLog;
import com.game.common.logf.impl.RoleRegisterLog;
import com.game.common.user.UserBean;
import com.game.constants.Loggers;
import com.game.db.entity.RoleEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.event.LoginSuccessEvent;
import com.game.myapp.module.function.ISystemId;
import com.game.myapp.module.guide.GuideConst;

@Component
@Async("gameExecutor")
public class LoginSuccessListener implements ApplicationListener<LoginSuccessEvent> {

	@Override
	public void onApplicationEvent(LoginSuccessEvent event) {
		RoleEntity role = (RoleEntity) event.getSource();
		if (role == null) {
			return;
		}

		long uid = role.getUid();
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			UserBean userBean = Globals.getUserManager().getUserBean(uid);
			if (userBean == null) {
				Loggers.serverLogger.error("No userBean on login! uid = " + uid);
				return;
			}

			if (userBean.isNew()) {
				// 检测引导开启
				GameGlobals.guideManager.onCheck(uid, GuideConst.OPEN_TYPE_LOGIN, 0);
				// 检测角色等级功能开启
				GameGlobals.functionOpenManager.check(uid, ISystemId.ROLE_LEVEL_TYPE, 1 + "");

				// 注册日志
				RoleRegisterLog registLog = new RoleRegisterLog(userBean);
				registLog.setIp(userBean.getIp());
				registLog.setDev(userBean.getDevId());
				registLog.setName(role.getName());
				LogfPrinter.getInstance().push(registLog);
			}

			// 每日重置
			GameGlobals.roleManager.dailyReset(userBean);

			// 初始化或更新玩家活动数据
			GameGlobals.roleActivityManager.initRoleActivityEntity(uid);
			// 初始验证玩家星星能够拥有的英雄数量
			GameGlobals.heroManager.addHeroByDupstar(uid);
			// 更新玩家渠道
			GameGlobals.roleManager.updateRoleChannelAndPlatform(userBean.getUid(), userBean.getChannel(), userBean.getPlatform());

			// 玩家登陆打点日志
			RoleLoginLog loginLog = new RoleLoginLog(userBean);
			loginLog.setLoginTime(userBean.getLoginTime());
			loginLog.setBlueCash(role.getChargeCash());
			loginLog.setRewardBlueCash(role.getRewardBuleCash());
			loginLog.setCoin(role.getCoin());
			LogfPrinter.getInstance().push(loginLog);
		} finally {
			lock.unlock();
		}
	}
}
