package com.game.myapp.module.role.page;

import com.game.core.handler.MsgBack;

import buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto;
import buffer.CGRoleHomePageSetUpMsg.CGRoleHomePageSetUpProto;
import buffer.GCFriendGetInfoByShowIdMsg.GCFriendGetInfoByShowIdProto.Builder;

/**
 * 主页
 * 
 * @author 刘朋飞
 *
 */
public class RolehHomepageManager {

	private RoleHomePageService service = new RoleHomePageService();

	public void CGRoleHomePageInfo(MsgBack msgBack, long uid, CGRoleHomePageInfoProto msg) {
		service.CGRoleHomePageInfo(msgBack, uid, msg);
	}

	public void OnInitRole(long uid) {
		this.service.insertRoleInfo(uid);
	}

	public void CGRoleHomePageSetUp(MsgBack msgBack, long uid, CGRoleHomePageSetUpProto msg) {
		service.CGRoleHomePageSetUp(msgBack, uid, msg);
	}

	public void buildFavoriteHeroCodeAndPetCode(long uid, Builder builder) {
		this.service.buildFavoriteHeroCodeAndPetCode(uid, builder);
	}
}
