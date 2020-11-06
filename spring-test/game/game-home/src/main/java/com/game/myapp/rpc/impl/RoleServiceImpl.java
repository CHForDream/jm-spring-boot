package com.game.myapp.rpc.impl;

import org.apache.dubbo.config.annotation.DubboService;

import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.rpc.service.IRoleService;
import com.game.rpc.vo.FriendRankInfo;
import com.game.rpc.vo.RoleInfo;

@DubboService
public class RoleServiceImpl implements IRoleService {
	@Override
	public FriendRankInfo remote_get_frined_rankInfo(long uid) {
		return GameGlobals.friendManager.getFriendRankInfo(uid);
	}

	// 远程保存数据必须等数据入库完成之后, 才能进行后面的操作, 这里必须同步等待
//	@Async("gameExecutor")
	@Override
	public void remoteSaveRoleData(long uid) {
		Globals.getRoleManager().saveRoleData(uid);
	}

	@Override
	public RoleInfo getRoleInfo(long uid) {
		try {
			return GameGlobals.roleManager.getRoleInfo(uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
