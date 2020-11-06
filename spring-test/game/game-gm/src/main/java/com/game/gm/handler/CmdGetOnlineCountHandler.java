package com.game.gm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.CMDConstants;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IOnlineService;
import com.game.vo.ResponseData;
import com.google.common.collect.Maps;

public class CmdGetOnlineCountHandler extends AbsGmContoller {
	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		ResponseData<String, Object> responseData = new ResponseData<String, Object>();

		// 总在线人数
		int totalCount = 0;
		// 每个服的人数
		Map<Integer, Integer> serverOnlineMap = Maps.newTreeMap();
		for (int sid : Globals.getAppConfigBean().getHomeServerMap().keySet()) {
			IOnlineService service = RpcManager.getRpcImplBySid(sid, IOnlineService.class);
			int count = service.getOnlineCount();
			serverOnlineMap.put(sid, count);
			totalCount += count;
		}

		responseData.put("count", totalCount);
		responseData.put("list", serverOnlineMap);
		responseData.put(CMDConstants.KEY_STATE, CMDConstants.STATE_SUCCESS);
		return responseData;
	}
}
