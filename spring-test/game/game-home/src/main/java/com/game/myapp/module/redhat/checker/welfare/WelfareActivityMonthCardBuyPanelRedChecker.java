package com.game.myapp.module.redhat.checker.welfare;

import com.game.common.user.UserBean;
import com.game.db.entity.RoleEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.redhat.checker.RedHatBaseChecker;
import com.game.utils.TimeUtils;

public class WelfareActivityMonthCardBuyPanelRedChecker extends RedHatBaseChecker {

	@Override
	protected void doCheck(long uid) {
		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}

		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return;
		}

		boolean hasMonthCard = GameGlobals.monthCardManager.checkInMonthCardATime(role);
		if (hasMonthCard) {
			// 每天第一次登录, 玩家月卡距离到期不到5天, 则显示小红点
			int diffDay = TimeUtils.differentDays(role.getMonthCardEndTime(), System.currentTimeMillis());
			boolean flag = (diffDay <= 5);
			boolean currFlag = user.isRedHatDirty(RedHatBehavior.WELFARE_ACTIVITY_MONTH_CARD_BUY_PANEL.getBehavior());
			if (flag != currFlag) {
				user.setDirty(RedHatBehavior.WELFARE_ACTIVITY_MONTH_CARD_BUY_PANEL.getBehavior(), flag);
			}
		} else {
			// 每天第一次登录, 并且玩家没有购买月卡, 则显示小红点
			user.setDirty(RedHatBehavior.WELFARE_ACTIVITY_MONTH_CARD_BUY_PANEL.getBehavior(), !TimeUtils.isToday(role.getLastLoginTime()));
		}
	}
}
