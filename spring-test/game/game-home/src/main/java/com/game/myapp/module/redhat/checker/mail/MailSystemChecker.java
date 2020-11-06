package com.game.myapp.module.redhat.checker.mail;

import java.util.List;

import com.game.common.user.UserBean;
import com.game.constants.MailConts;
import com.game.db.entity.MailEntity;
import com.game.global.Globals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.redhat.checker.RedHatBaseChecker;

public class MailSystemChecker extends RedHatBaseChecker {

	@Override
	protected void doCheck(long uid) {
		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}

		List<MailEntity> list = Globals.getEntityProxy().findAllByUid(MailEntity.class, uid);
		if (list.size() == 0) {
			return;
		}

		boolean flag = false;
		for (MailEntity temp : list) {
			if (temp.getMailType() == MailConts.MAIL_TYPE_SYSTEM && !temp.isHasRead()) {
				flag = true;
				break;
			}
		}

		user.setDirty(RedHatBehavior.MAIL_SYSTEM.getBehavior(), flag);
	}
}
