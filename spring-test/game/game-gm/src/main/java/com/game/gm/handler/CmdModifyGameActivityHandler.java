package com.game.gm.handler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.CMDConstants;
import com.game.constants.Loggers;
import com.game.core.lock.LockManager;
import com.game.db.entity.ActivityEntity;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.gm.vo.ActivityVo;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IActivityService;
import com.game.utils.JsonUtil;
import com.game.vo.ResponseData;

public class CmdModifyGameActivityHandler extends AbsGmContoller {

	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		ResponseData<String, Object> data = ResponseData.newBuild();
		String json = request.getParameter("activity");
		Loggers.msgLogger.info("json = " + json);
		ActivityVo activityVo = null;
		try {
			activityVo = JsonUtil.toObj(json, ActivityVo.class);
		} catch (Exception e) {
			e.printStackTrace();
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
			return data;
		}

		Loggers.msgLogger.info(activityVo.toString());
		int activityId = activityVo.getType();
		ReentrantLock lock = LockManager.getInstance().getLock(activityId);
		lock.lock();
		try {
			boolean isNew = false;
			ActivityEntity entity = Globals.getEntityProxy().getNotCache(ActivityEntity.class, activityId);
			if (entity == null) {
				isNew = true;
				entity = new ActivityEntity();
				entity.setId(activityId);
			}
			entity.setName(URLDecoder.decode(activityVo.getName(), CMDConstants.CHARSET_DEFAULT));
			entity.setDescription(URLDecoder.decode(activityVo.getDec(), CMDConstants.CHARSET_DEFAULT));
			entity.setOpen(activityVo.getOpen() == 1);
			entity.setShowTimeStart(new Timestamp(activityVo.getShow_start_time()));
			entity.setShowTimeEnd(new Timestamp(activityVo.getShow_end_time()));
			entity.setTimeStart(new Timestamp(activityVo.getStart_time()));
			entity.setTimeEnd(new Timestamp(activityVo.getEnd_time()));
			entity.setVersion(activityVo.getId());// 活动版本使用活动的实例id
			entity.setPriority(activityVo.getPri());// 活动排序优先级
			entity.setRewards(JsonUtil.toJson(activityVo.getRewards()));// 奖励信息
			if (isNew) {
				Globals.getEntityProxy().insert(entity);
			} else {
				Globals.getEntityProxy().update(entity);
			}

			// 通知逻辑服活动变更
			Map<Integer, String> serverMap = Globals.getAppConfigBean().getHomeServerMap();
			for (int sid : serverMap.keySet()) {
				IActivityService service = RpcManager.getRpcImplBySid(sid, IActivityService.class);
				service.onActivityChanged();
			}

			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_SUCCESS);
			Loggers.msgLogger.info("Modify activity successfully!");
			return data;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
			return data;
		} finally {
			lock.unlock();
		}
	}
}
