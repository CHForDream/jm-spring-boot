package com.game.gm.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.CMDConstants;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IGmService;
import com.game.vo.ResponseData;

/**
 * 踢人下线
 * 
 */
public class CmdKickUserHandler extends AbsGmContoller {

	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		ResponseData<String, Object> data = ResponseData.newBuild();
		long uid = getUid(request);
		if (uid == 0) {
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
			data.put(CMDConstants.KEY_REASON, CMDConstants.REASON_INVALID_UID);
			return data;
		}

		IGmService service = RpcManager.getRpcImplBySid(Globals.getUserManager().getSid(uid), IGmService.class);
		String result = service.kickoffUser(uid);

		if (CMDConstants.STATE_SUCCESS.equals(result)) {
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_SUCCESS);
		} else {
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
			data.put(CMDConstants.KEY_REASON, result);
		}

		return data;
	}
}
