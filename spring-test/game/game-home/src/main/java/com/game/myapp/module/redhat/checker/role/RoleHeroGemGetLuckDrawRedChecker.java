package com.game.myapp.module.redhat.checker.role;

import com.game.common.user.UserBean;
import com.game.db.entity.LuckDrawInfoEntity;
import com.game.global.Globals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.redhat.checker.RedHatBaseChecker;

public class RoleHeroGemGetLuckDrawRedChecker extends RedHatBaseChecker {

	@Override
	protected void doCheck(long uid) {
		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}
		LuckDrawInfoEntity drawInfoEntity = Globals.getEntityProxy().get(LuckDrawInfoEntity.class, uid);
		if (drawInfoEntity == null) {
			return;
		}

		Long currTime = System.currentTimeMillis();
		long nextFreeTime = drawInfoEntity.getNextFreeLuckDrawTime();
		boolean isTimeFree = (currTime >= nextFreeTime);
		user.setDirty(RedHatBehavior.ROLE_HERO_GEM_GET_LUCK_DRAW.getBehavior(), isTimeFree);
	}
}
