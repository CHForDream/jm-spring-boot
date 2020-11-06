package com.game.gm.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.CMDConstants;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IGmService;
import com.game.utils.StringUtils;
import com.game.vo.ResponseData;

public class CmdBanChatHandler extends AbsGmContoller {

	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		long uid = getUid(request);
		ResponseData<String, Object> data = ResponseData.newBuild();
		if (uid == 0) {
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
			data.put(CMDConstants.KEY_REASON, CMDConstants.REASON_INVALID_UID);
			return data;
		}
		try {
			long startTime = Long.parseLong(request.getParameter("start_time"));
			long endTime = Long.parseLong(request.getParameter("end_time"));
			// 0全部；1世界；2好友（私聊）
			int type = Integer.parseInt(request.getParameter("type"));
			String reason = request.getParameter("reason");
			if (!StringUtils.isEmpty(reason)) {
				reason = URLEncoder.encode(request.getParameter("reason"), CMDConstants.CHARSET_DEFAULT);
			}

			// 远程调用，禁言玩家聊天
			IGmService service = RpcManager.getRpcImplBySid(Globals.getUserManager().getSid(uid), IGmService.class);
			service.banChat(type, uid, startTime, endTime, reason);

			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
			data.put(CMDConstants.KEY_REASON, CMDConstants.REASON_INVALID_PARAMS);
		}
		return data;
	}
}
