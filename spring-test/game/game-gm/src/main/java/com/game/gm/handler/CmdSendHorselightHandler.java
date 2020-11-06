package com.game.gm.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.CMDConstants;
import com.game.constants.Loggers;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IGmService;
import com.game.utils.StringUtils;
import com.game.utils.TimeUtils;
import com.game.vo.ResponseData;

/**
 * 发送走马灯
 * 
 * @author LPF
 *
 */
public class CmdSendHorselightHandler extends AbsGmContoller {

	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		ResponseData<String, Object> data = ResponseData.newBuild();
		try {
			String content = request.getParameter("content");// 内容
			if (StringUtils.isEmpty(content)) {
				data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
				data.put(CMDConstants.KEY_REASON, "No content");
				return data;
			}

			content = URLEncoder.encode(content, CMDConstants.CHARSET_DEFAULT);// 内容
			long startTime = Long.parseLong(request.getParameter("start_time"));// 开始时间戳
			long endTime = Long.parseLong(request.getParameter("end_time"));// 结束时间戳
			long inverval = Integer.parseInt(request.getParameter("interval"));// 间隔时间(秒)
			inverval *= TimeUtils.SECOND;// 时间转成毫秒
			Loggers.gmLogger.info("content = " + content + ", startTime = " + startTime + ", endTime = " + endTime + ", inverval = " + inverval);

			// 通知游戏服
			for (int sid : Globals.getAppConfigBean().getHomeServerMap().keySet()) {
				IGmService service = RpcManager.getRpcImplBySid(sid, IGmService.class);
				service.sendHorseLamp(content, startTime, endTime, inverval);
			}
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
