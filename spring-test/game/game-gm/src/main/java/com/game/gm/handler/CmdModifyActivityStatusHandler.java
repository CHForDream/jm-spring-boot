package com.game.gm.handler;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.game.constants.CMDConstants;
import com.game.core.lock.LockManager;
import com.game.db.entity.ActivityEntity;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IActivityService;
import com.game.utils.StringUtils;
import com.game.vo.ResponseData;

public class CmdModifyActivityStatusHandler extends AbsGmContoller {

	private Logger logger = Logger.getLogger(CmdModifyActivityStatusHandler.class);

	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		ResponseData<String, Object> data = ResponseData.newBuild();
		String typeStr = request.getParameter("type");
		String optStr = request.getParameter("opt");
		String priStr = request.getParameter("pri");
		if (StringUtils.isEmpty(typeStr)) {
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
			data.put(CMDConstants.KEY_REASON, "No activity type");
			return data;
		}

		try {
			int activityId = Integer.parseInt(typeStr);

			boolean isOptChanged = !StringUtils.isEmpty(optStr);
			int opt = 0;// 1:开启 0:关闭
			if (isOptChanged) {
				opt = Integer.parseInt(optStr);
			}

			boolean isPriChanged = !StringUtils.isEmpty(priStr);
			int pri = 0;// 排序权重
			if (isPriChanged) {
				pri = Integer.parseInt(priStr);
			}

			ReentrantLock lock = LockManager.getInstance().getServerLock();
			lock.lock();
			try {
				ActivityEntity entity = Globals.getEntityProxy().getNotCache(ActivityEntity.class, activityId);
				if (entity == null) {
					data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
					data.put(CMDConstants.KEY_REASON, "No activity info! type = " + typeStr);
					return data;
				} else {
					if (isOptChanged) {
						entity.setOpen(opt == 1);
					}
					if (isPriChanged) {
						entity.setPriority(pri);
					}

					Globals.getEntityProxy().update(entity);

					// 通知逻辑服活动变更
					Map<Integer, String> serverMap = Globals.getAppConfigBean().getHomeServerMap();
					for (int sid : serverMap.keySet()) {
						IActivityService service = RpcManager.getRpcImplBySid(sid, IActivityService.class);
						service.onActivityChanged();
					}
					logger.info("收到修改活动状态的请求, type:" + typeStr + ", opt:" + optStr + ", pri:" + priStr);
				}
			} finally {
				lock.unlock();
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
