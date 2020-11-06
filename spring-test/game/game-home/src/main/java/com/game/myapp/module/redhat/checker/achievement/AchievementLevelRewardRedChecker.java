package com.game.myapp.module.redhat.checker.achievement;

import com.game.common.user.UserBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.redhat.checker.RedHatBaseChecker;

public class AchievementLevelRewardRedChecker extends RedHatBaseChecker {

	@Override
	public void doCheck(long uid) {
		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}

		boolean flag = GameGlobals.achieveManager.hasLevelReward(uid);
		user.setDirty(RedHatBehavior.ACHIEVEMENT_LEVEL_REWARD.getBehavior(), flag);
	}
}
