package com.game.myapp.module.redhat.checker.achievement;

import com.game.myapp.module.achieve.AchieveType;
import com.game.myapp.module.redhat.RedHatBehavior;

public class AchievementUpgradeRedChecker extends AchievementBaseRedChecker {

	@Override
	public void doCheck(long uid) {
		doAchievementCheck(uid, RedHatBehavior.ACHIEVEMENT_UPGRADE, AchieveType.A_TYPE_UP.getType());
	}
}
