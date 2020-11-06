package com.game.myapp.module.redhat.checker.achievement;

import com.game.myapp.module.achieve.AchieveType;
import com.game.myapp.module.redhat.RedHatBehavior;

public class AchievementBattleRedChecker extends AchievementBaseRedChecker {

	@Override
	protected void doCheck(long uid) {
		doAchievementCheck(uid, RedHatBehavior.ACHIEVEMENT_BATTLE, AchieveType.A_TYPE_BATTLE.getType());
	}
}
