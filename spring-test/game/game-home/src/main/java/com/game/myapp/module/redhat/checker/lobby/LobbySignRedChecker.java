package com.game.myapp.module.redhat.checker.lobby;

import com.game.common.user.UserBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.redhat.checker.RedHatBaseChecker;

public class LobbySignRedChecker extends RedHatBaseChecker {

	@Override
	protected void doCheck(long uid) {
		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}

		boolean flag = GameGlobals.signManager.checkReceiveSevenReward(uid);
		user.setDirty(RedHatBehavior.LOBBY_SIGN.getBehavior(), flag);
	}
}
