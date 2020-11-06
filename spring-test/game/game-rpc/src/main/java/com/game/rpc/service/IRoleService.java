package com.game.rpc.service;

import com.game.rpc.vo.FriendRankInfo;
import com.game.rpc.vo.RoleInfo;

public interface IRoleService {
	FriendRankInfo remote_get_frined_rankInfo(long uid);

	void remoteSaveRoleData(long uid);

	RoleInfo getRoleInfo(long uid);
}
