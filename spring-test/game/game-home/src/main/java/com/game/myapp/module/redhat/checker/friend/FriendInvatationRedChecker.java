package com.game.myapp.module.redhat.checker.friend;

import java.util.List;

import com.game.common.user.UserBean;
import com.game.db.entity.FriendreqEntity;
import com.game.db.entity.FriendsEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.redhat.checker.RedHatBaseChecker;

public class FriendInvatationRedChecker extends RedHatBaseChecker {

	@Override
	protected void doCheck(long uid) {
		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}

		// 如果达到好友上限, 或申请列表为空, 则去掉红点
		List<FriendsEntity> friends = Globals.getEntityProxy().findAllByUid(FriendsEntity.class, uid);
		List<FriendreqEntity> list = Globals.getEntityProxy().findAllByUid(FriendreqEntity.class, uid);
		boolean flag = !(friends.size() >= GameGlobals.configManager.getFriendSize() || list.isEmpty());
		user.setDirty(RedHatBehavior.FRIEND_INVATATION.getBehavior(), flag);
	}
}
