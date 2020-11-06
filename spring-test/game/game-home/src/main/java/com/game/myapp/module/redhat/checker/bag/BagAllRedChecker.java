package com.game.myapp.module.redhat.checker.bag;

import java.util.List;

import com.game.common.user.UserBean;
import com.game.db.entity.GiftEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.redhat.checker.RedHatBaseChecker;

public class BagAllRedChecker extends RedHatBaseChecker {

	@Override
	protected void doCheck(long uid) {
		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}

		boolean red = user.isRedHatDirty(RedHatBehavior.BAG_ALL.getBehavior());
		List<GiftEntity> list = GameGlobals.giftManager.getAll(uid);
		boolean flag = false;
		for (GiftEntity entity : list) {
			if (entity.getNum() > 0) {
				flag = true;
				break;
			}
		}
		if (red != flag) {
			user.setDirty(RedHatBehavior.BAG_ALL.getBehavior(), flag);
		}
	}
}
