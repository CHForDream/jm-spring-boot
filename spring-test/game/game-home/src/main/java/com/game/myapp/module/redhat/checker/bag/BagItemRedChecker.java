package com.game.myapp.module.redhat.checker.bag;

import com.game.common.user.UserBean;
import com.game.global.Globals;
import com.game.myapp.module.redhat.checker.RedHatBaseChecker;

public class BagItemRedChecker extends RedHatBaseChecker {

	@Override
	protected void doCheck(long uid) {
		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}

//		boolean red = user.isDirty(RedHatBehavior.BAG_ALL.getBehavior());
//		List<GiftsEntity> list = GameGlobals.giftManager.getAll(uid);
//		boolean flag = false;
//		for (GiftsEntity entity : list) {
//			if (entity.getNum() > 0) {
//				flag = true;
//			}
//		}
//		if (red != flag) {
//			user.setDirty(RedHatBehavior.BAG_ALL.getBehavior(), flag);
//		}
	}
}
