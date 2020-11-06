package com.game.common.user;

import com.game.core.handler.MsgBack;

public interface IRoleManager {
	@SuppressWarnings("rawtypes")
	com.google.protobuf.GeneratedMessage.Builder buildErrorMessage(int errorStatus);

	void packCommonMessage(MsgBack msgBack, UserBean userBean);

	void saveRoleData(long uid);

	void remoteSaveRoleData(int sid, long uid);

	void onLogout(UserBean userBean);

	void onShutdown();
}
