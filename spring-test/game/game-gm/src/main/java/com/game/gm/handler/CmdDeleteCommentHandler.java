package com.game.gm.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.CMDConstants;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IGmService;
import com.game.vo.ResponseData;

public class CmdDeleteCommentHandler extends AbsGmContoller {

	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		ResponseData<String, Object> data = ResponseData.newBuild();
		long uid = getUid(request);
		if (uid == 0) {
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
			data.put(CMDConstants.KEY_REASON, CMDConstants.REASON_INVALID_UID);
			return data;
		}

		try {
			// 1:删除英雄评论   2:删除宠物评论
			int type = Integer.parseInt(request.getParameter("type"));
			// 评论id
			int commentId = Integer.parseInt(request.getParameter("xid"));

			IGmService service = RpcManager.getRpcImplBySid(Globals.getUserManager().getSid(uid), IGmService.class);
			service.deleteComment(uid, type, commentId);

			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_SUCCESS);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
			data.put(CMDConstants.KEY_REASON, CMDConstants.REASON_INVALID_PARAMS);
			return data;
		}
	}

}
