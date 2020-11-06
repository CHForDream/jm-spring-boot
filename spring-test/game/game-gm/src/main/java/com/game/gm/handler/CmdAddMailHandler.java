package com.game.gm.handler;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.CMDConstants;
import com.game.constants.MailConts;
import com.game.db.entity.SystemMailEntity;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IGmService;
import com.game.utils.StringUtils;
import com.game.vo.ResponseData;

/**
 * 添加邮件
 * 
 * @author LPF
 *
 */
public class CmdAddMailHandler extends AbsGmContoller {

	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		// 好友邮件
//		http://127.0.0.1/gm?code=add_mail&type=1&title=1&content=1&items=&touid=13100471331&fromUid=13100471331
//		http://127.0.0.1/gm?code=add_mail&type=1&title=1&content=1&items=4,1,1&touid=13100471331&fromUid=13100471331
//		type: 1好友
//		items: 物品类型,物品id,物品数量;物品类型,物品id,物品数量;...

		// 系统邮件
//		http://127.0.0.1/gm?code=add_mail&type=2&title=1&content=1&items=&orderId=1&invalid_time=14100471330000
//		http://127.0.0.1/gm?code=add_mail&type=2&title=1&content=1&items=4,1,1&orderId=1&invalid_time=14100471330000
//		type: 2系统
//		items: 物品类型,物品id,物品数量;物品类型,物品id,物品数量;...
		// orderId:整数, 每次请求, orderId需要不同, 重复了则添加邮件失败
		// invalid_time:Long整型, 14100471330000(2416-10-29 05:35:30)

		ResponseData<String, Object> data = ResponseData.newBuild();
		try {
			int mailType = Integer.parseInt(request.getParameter("type"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String items = request.getParameter("items");
			if (mailType == MailConts.MAIL_TYPE_FRIEND) {
				long toUid = Long.parseLong(request.getParameter("touid"));
				long fromUid = Long.parseLong(request.getParameter("fromUid"));
				IGmService service = RpcManager.getRpcImplBySid(Globals.getUserManager().getSid(toUid), IGmService.class);
				service.sendMail(mailType, title, content, toUid, fromUid, items, 0L, "");
			} else if (mailType == MailConts.MAIL_TYPE_SYSTEM) {// 增加系统邮件
				String orderId = request.getParameter("orderId");
				if (StringUtils.isEmpty(orderId)) {
					data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
					data.put(CMDConstants.KEY_REASON, "Invalid order id!");
					return data;
				}
				String invalidTime = request.getParameter("invalid_time");
				long mailId = insertSystemMail(title, content, items, Long.parseLong(invalidTime), orderId);
				if (mailId == -1L) {
					data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
					data.put(CMDConstants.KEY_REASON, "order is exist");
					return data;
				}

				// 通知游戏服
				for (int sid : Globals.getAppConfigBean().getHomeServerMap().keySet()) {
					IGmService service = RpcManager.getRpcImplBySid(sid, IGmService.class);
					service.sendSystemMail(mailId);
				}
			}
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_FAILD);
			data.put(CMDConstants.KEY_REASON, CMDConstants.REASON_INVALID_PARAMS);
			return data;
		}
		return data;
	}

	private long insertSystemMail(String title, String content, String items, long invalidTime, String orderId) throws Exception {
		ReentrantLock lock = Globals.getLockManager().getServerLock();
		lock.lock();
		try {
			List<SystemMailEntity> results = Globals.getEntityProxy().findAll(SystemMailEntity.class);
			for (SystemMailEntity entity : results) {
				if (entity.getOrderId().equals(orderId)) {
					return -1L;
				}
			}

			long currTime = System.currentTimeMillis();
			SystemMailEntity mailEntity = new SystemMailEntity();
			mailEntity.setTitle(title);
			mailEntity.setContent(content);
			mailEntity.setInvalidTime(invalidTime);
			mailEntity.setItems(items);
			mailEntity.setCreateTime(currTime);
			mailEntity.setOrderId(orderId);
			Globals.getEntityProxy().insert(mailEntity);
			return mailEntity.getId();
		} finally {
			lock.unlock();
		}
	}
}
