package com.game.myapp.module.pay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.LogfType;
import com.game.common.logf.impl.PayLog;
import com.game.common.session.GameSession;
import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.core.handler.MsgBack;
import com.game.core.hibernate.key.LongStringPrimaryKey;
import com.game.core.lock.LockManager;
import com.game.db.entity.PayEntity;
import com.game.db.entity.PveCityEntity;
import com.game.db.entity.RoleEntity;
import com.game.db.entity.RolePayInfoEntity;
import com.game.generate.bean.FirstpayBean;
import com.game.generate.bean.HeroBean;
import com.game.generate.bean.PayBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.item.EItemId;
import com.game.myapp.module.item.EItemType;
import com.game.rpc.vo.PayInfo;
import com.game.utils.JsonUtil;
import com.game.vo.item.ItemVo;

import buffer.CGGetPayInfoMsg.CGGetPayInfoProto;
import buffer.CGGetRoleFirstPayInfoMsg.CGGetRoleFirstPayInfoProto;
import buffer.CGReCeiveFirstPayRewardMsg.CGReCeiveFirstPayRewardProto;
import buffer.GCGetPayInfoMsg;
import buffer.GCGetRoleFirstPayInfoMsg;
import buffer.GCPushPaySuccessMsg;
import buffer.GCReCeiveFirstPayRewardMsg;

public class PayManager {
	public static final int PAY_NORMAL = 0;
	public static final int PAY_MONTH_CARD_A = 1;
	public static final int PAY_MONTH_CARD_B = 2;

	public static final int NOT_REWARD = 0;// 不可领取
	public static final int CAN_REWARD = 1;// 可领取
	public static final int ALREADY_REWARD = 2;// 已领取

	/**
	 * 插入充值信息
	 * 
	 * @param pay
	 * @throws Exception
	 */
	public void insertPayInfo(PayInfo pay) {
		ReentrantLock lock = Globals.getLockManager().getLock(pay.getCharid());
		lock.lock();
		try {
			PayEntity payEntity = new PayEntity();
			payEntity.setOrderId(pay.getOrderid());
			payEntity.setServerId(pay.getGssid());
			payEntity.setGuid(pay.getGuid());
			payEntity.setUid(pay.getCharid());
			payEntity.setPayId(pay.getItemid());
			payEntity.setChannel(pay.getGateway());
			payEntity.setCash(pay.getPoint());
			payEntity.setRewardCash(pay.getGift_point());
			payEntity.setCreateTime(pay.getCreatetime());
			payEntity.setPayTime(pay.getPaytime());
			payEntity.setRewardItem(pay.getItems());
			Globals.getEntityProxy().insert(payEntity);
			// 记录下玩家每个挡位的充值次数
			updateRolePayInfo(pay.getCharid(), pay.getPoint(), pay.getGift_point(), pay.getItemid());
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 获取玩家充值数据
	 * 
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public RolePayInfoEntity getRolePayInfoEntity(long uid) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RolePayInfoEntity entity = Globals.getEntityProxy().get(RolePayInfoEntity.class, uid);
			if (entity == null) {
				entity = new RolePayInfoEntity();
				entity.setUid(uid);
				entity.setCash(0);
				entity.setRewardCash(0);
				entity.setPayInfo("");
				Globals.getEntityProxy().insert(entity);
			}
			return entity;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 记录下玩家每个挡位的充值次数
	 * 
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	private void updateRolePayInfo(long uid, int cash, int rewardCash, int payId) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RolePayInfoEntity payInfoEntity = getRolePayInfoEntity(uid);
			payInfoEntity.setCash(payInfoEntity.getCash() + cash);
			payInfoEntity.setRewardCash(payInfoEntity.getRewardCash() + rewardCash);
			Map<Integer, Integer> payInfoMap = getRolePayInfo(payInfoEntity.getPayInfo());
			if (payInfoMap.containsKey(payId)) {
				payInfoMap.put(payId, payInfoMap.get(payId) + 1);
			} else {
				payInfoMap.put(payId, 1);
			}
			payInfoEntity.setPayInfo(JsonUtil.toJson(payInfoMap));
			Globals.getEntityProxy().updateAsync(payInfoEntity);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 充值操作
	 * 
	 * @param payInfo
	 * @return
	 */
	public int handlePay(PayInfo payInfo) {
		long uid = payInfo.getCharid();
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return ErrorCodeConst.ROLE_NOT_EXIST;
		}

		PayBean payBean = Datas.get(PayBean.class, payInfo.getItemid());
		if (payBean == null) {
			return ErrorCodeConst.PAY_CONFIG_NOT_EXIST;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			if (hasOrder(uid, payInfo.getOrderid())) {
				return ErrorCodeConst.PAY_ORDER_IS_EXIST;
			}

			int type = payBean.getType();
			// 月卡A
			if (type == PAY_MONTH_CARD_A) {
				GameGlobals.monthCardManager.updateRoleMonthCardA(uid, GameGlobals.monthCardManager.buyAddTime);
			}
			//月卡B
			if (type == PAY_MONTH_CARD_B) {
				GameGlobals.monthCardManager.updateRoleMonthCardB(uid, GameGlobals.monthCardManager.buyAddTime);
			}

			if (payInfo.getPoint() != 0) {
				GameGlobals.bagManager.addItem(uid, EItemType.ITEM.getType(), EItemId.RECHARGE_CASH.getType(), payInfo.getPoint(), LogfConstants.CHANNEL_PAY,
						payInfo.getOrderid());
			}
			if (payInfo.getGift_point() != 0) {
				GameGlobals.bagManager.addItem(uid, EItemType.ITEM.getType(), EItemId.REWARD_CASH.getType(), payInfo.getGift_point(), LogfConstants.CHANNEL_PAY,
						payInfo.getOrderid());
			}

			if (payInfo.getItems() != null && !payInfo.getItems().equals("")) {
				String[] items = payInfo.getItems().split(";");
				for (String temp : items) {
					int itemType = Integer.parseInt(temp.split(",")[0]);
					int itemId = Integer.parseInt(temp.split(",")[1]);
					int itemNum = Integer.parseInt(temp.split(",")[2]);
					GameGlobals.bagManager.addItem(uid, itemType, itemId, itemNum, LogfConstants.CHANNEL_PAY, payInfo.getOrderid());
				}
			}

			insertPayInfo(payInfo);
			return ErrorCodeConst.SUCCESS;

		} finally {
			lock.unlock();
		}
	}

	private boolean hasOrder(long uid, String orderId) {
		// 重单检测
		PayEntity temp = Globals.getEntityProxy().get(PayEntity.class, new LongStringPrimaryKey(uid, orderId));
		if (temp != null) {
			return true;
		}
		return false;
	}

	public int getTypeById(int id) {
		PayBean payBean = Datas.get(PayBean.class, id);
		return payBean.getType();
	}

	public Map<Integer, Integer> getRolePayInfo(String json) {
		if (json.equals("")) {
			Map<Integer, Integer> result = new HashMap<Integer, Integer>();
			return result;
		}
		return JsonUtil.toMapIntegerKey(json, Integer.class);
	}

	public void CGGetPayInfo(MsgBack msgBack, long uid, CGGetPayInfoProto msg) {
		GCGetPayInfoMsg.GCGetPayInfoProto.Builder builder = GCGetPayInfoMsg.GCGetPayInfoProto.newBuilder();
		msgBack.addBuilder(builder);
		int platform = msg.getPlatform();
		List<PayBean> payBeans = this.getPayConfigsByChannel(platform, uid);

		RolePayInfoEntity entity = this.getRolePayInfoEntity(uid);
		Map<Integer, Integer> statusMap = this.getRolePayInfo(entity.getPayInfo());
		for (PayBean temp : payBeans) {
			GCGetPayInfoMsg.PayInfoProto.Builder payInfoBuilder = GCGetPayInfoMsg.PayInfoProto.newBuilder();
			int id = temp.getId();
			boolean isFirst = (!statusMap.containsKey(id) || statusMap.get(id) <= 0);
			payInfoBuilder.setIsFirst(isFirst);
			payInfoBuilder.setCodeId(id);
			builder.addPayInfos(payInfoBuilder);
		}
		builder.setStatus(ErrorCodeConst.SUCCESS);
	}

	private List<PayBean> getPayConfigsByChannel(int platform, long uid) {
		List<PayBean> result = new ArrayList<PayBean>();
		for (PayBean payBean : Datas.getDataMap(PayBean.class).values()) {
			String playtformConfig = payBean.getPlatform();
			String[] playtformConfigs = playtformConfig.split(",");
			for (String pl : playtformConfigs) {
				if (pl.equals("0") || pl.equals(String.valueOf(platform))) {
					if (checkPayConfig(uid, payBean)) {
						result.add(payBean);
					}
				}
			}
		}
		return result;
	}

	private boolean checkPayConfig(long uid, PayBean payBean) {
		String open = payBean.getOpen();
		if (open.equals("0")) {
			return true;
		}

		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return true;
		}

//		for (String opens : open.split(";")) {
//			int type = Integer.parseInt(opens.split(",")[0]);
//			int num = Integer.parseInt(opens.split(",")[1]);
//			if (type == ISystemId.ROLE_LEVEL_TYPE) {
//				if (role.getLevel() < num) {
//					return false;
//				}
//			}
//		}
		return true;
	}

	/**
	 * 发送充值成功
	 * 
	 * @param uid
	 * @param payId
	 * @param blueCash
	 * @param rewardBluCash
	 * @param items
	 */
	public void sendPaySuccessToRole(long uid, int payId, int blueCash, int rewardBluCash, String items) {
		GameSession session = Globals.getChatSessionManager().getSession(uid);
		if (session == null) {
			return;
		}
		GCPushPaySuccessMsg.GCPushPaySuccessProto.Builder builder = GCPushPaySuccessMsg.GCPushPaySuccessProto.newBuilder();
		builder.setStatus(ErrorCodeConst.SUCCESS);
		builder.setId(payId);
		builder.setBlueCashNum(blueCash);
		builder.setRewardBlueCashNum(rewardBluCash);
		if (blueCash != 0) {
			items += EItemType.ITEM.getType() + "," + EItemId.RECHARGE_CASH.getType() + "," + blueCash + ";";
		}
		if (rewardBluCash != 0) {
			items += EItemType.ITEM.getType() + "," + EItemId.REWARD_CASH.getType() + "," + rewardBluCash + ";";
		}
		builder.setItems(items.equals("") ? "" : items.substring(0, items.length() - 1));
		session.sendMsg(builder);
	}

	/**
	 * 获取玩家首充状态
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 */
	public void CGGetRoleFirstPayInfo(MsgBack msgBack, long uid, CGGetRoleFirstPayInfoProto msg) {
		GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto.Builder builder = GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto.newBuilder();
		msgBack.addBuilder(builder);
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			String roleStatus = role.getFirstPayStatus();
			Map<Integer, Integer> roleStatusMap = getRoleFirstPayStatusMap(roleStatus);
			String status = "";
			for (FirstpayBean firstpayBean : Datas.getDataMap(FirstpayBean.class).values()) {
				int firstPayId = firstpayBean.getId();
				status += (firstPayId + ":" + getFirstpayStatus(firstPayId, roleStatusMap) + ",");
			}
			// 去掉最后一个逗号
			builder.setFirstStatus(status.substring(0, status.length() - 1));
			builder.setStatus(ErrorCodeConst.SUCCESS);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 获取玩家首充状态
	 * 
	 * @return
	 */
	public int getFirstpayStatus(int firstPayId, Map<Integer, Integer> roleStatus) {
		if (!roleStatus.containsKey(firstPayId)) {
			return NOT_REWARD;// 未完成
		}
		return roleStatus.get(firstPayId);
	}

	/**
	 * 将玩家的首充状态转化为Map
	 */
	public Map<Integer, Integer> getRoleFirstPayStatusMap(String roleStatus) {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		if (roleStatus == null || roleStatus.equals("")) {
			return result;
		}
		Map<String, String> mapDB = JsonUtil.toMapStringKey(roleStatus, String.class);
		for (Map.Entry<String, String> temp : mapDB.entrySet()) {
			int id = Integer.parseInt(temp.getKey());
			int value = Integer.parseInt(temp.getValue());
			result.put(id, value);
		}
		return result;
	}

	/**
	 * 检测玩家是否完成了某个首充的任务
	 * 
	 * @param uid
	 * @param payId
	 */
	public boolean onCheckFirstPay(long uid, int payId) {
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return false;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			String roleStatus = role.getFirstPayStatus();
			Map<Integer, Integer> roleStatusMap = getRoleFirstPayStatusMap(roleStatus);
			if (roleStatusMap.containsKey(payId)) {
				return false;
			}

			roleStatusMap.put(payId, CAN_REWARD);
			role.setFirstPayStatus(JsonUtil.toJson(getStringStatusMap(roleStatusMap)));
			Globals.getEntityProxy().updateAsync(role);
			return true;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 领取首充奖励
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 */
	public void CGReceiveFirstPayReward(MsgBack msgBack, long uid, CGReCeiveFirstPayRewardProto msg) {
		GCReCeiveFirstPayRewardMsg.GCReCeiveFirstPayRewardProto.Builder builder = GCReCeiveFirstPayRewardMsg.GCReCeiveFirstPayRewardProto.newBuilder();
		msgBack.addBuilder(builder);
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}
		int code = msg.getCode();
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			String roleStatus = role.getFirstPayStatus();
			Map<Integer, Integer> statusMap = this.getRoleFirstPayStatusMap(roleStatus);
			int status = this.getFirstpayStatus(code, statusMap);
			if (status != CAN_REWARD) {
				builder.setStatus(ErrorCodeConst.NOT_REWARD);
				return;
			}
			FirstpayBean firstpayBean = Datas.get(FirstpayBean.class, code);
			if (firstpayBean == null) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}
			String rewards = "";
			for (ItemVo itemVo : firstpayBean.getRewardList()) {
				int itemType = itemVo.getType();
				int itemId = itemVo.getId();
				int itemNum = itemVo.getNum();
				// 英雄转化
				if (EItemType.HERO.is(itemType) && GameGlobals.heroManager.hasHero(uid, itemId)) {
					HeroBean heroBean = Datas.get(HeroBean.class, itemId);
					itemType = heroBean.getItem().getType();
					itemId = heroBean.getItem().getId();
					itemNum = heroBean.getItem().getNum();
				}
				GameGlobals.bagManager.addItem(uid, itemType, itemId, itemNum, LogfConstants.CHANNEL_FIRST_PAY_REWARD, code + "");
				rewards += itemType + "," + itemId + "," + itemNum + ";";
			}
			statusMap.put(code, ALREADY_REWARD);
			role.setFirstPayStatus(JsonUtil.toJson(getStringStatusMap(statusMap)));
			Globals.getEntityProxy().updateAsync(role);
			builder.setStatus(ErrorCodeConst.SUCCESS);
			builder.setReward(rewards.substring(0, rewards.length() - 1));
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 检测是否需要显示首充按钮
	 */
	public boolean checkIsShowFirstPayButton(RoleEntity role) {
		String roleStatus = role.getFirstPayStatus();
		Map<Integer, Integer> statusMap = this.getRoleFirstPayStatusMap(roleStatus);
		for (FirstpayBean firstpayBean : Datas.getDataMap(FirstpayBean.class).values()) {
			int firstPayId = firstpayBean.getId();
			int status = getFirstpayStatus(firstPayId, statusMap);
			if (status == NOT_REWARD || status == CAN_REWARD) {
				return true;
			}
		}
		return false;
	}

	public Map<String, String> getStringStatusMap(Map<Integer, Integer> statusMap) {
		Map<String, String> result = new HashMap<String, String>();
		Iterator<Integer> keysIT = statusMap.keySet().iterator();
		while (keysIT.hasNext()) {
			Integer temp = keysIT.next();
			Object value = statusMap.get(temp);
			result.put(String.valueOf(temp), String.valueOf(value));
		}
		return result;
	}

	/**
	 * 创建假订单
	 */
	public PayInfo createPayInfo(int itemId, long uid) {
		PayBean payBean = Datas.get(PayBean.class, itemId);
		if (payBean == null) {
			return null;
		}

		PayInfo pay = new PayInfo();
		pay.setOrderid(String.valueOf(System.nanoTime()));
		pay.setGssid(Integer.parseInt(Globals.getAppConfigBean().getGssid()));
		pay.setCharid(uid);
		pay.setGateway("2098161931");
		pay.setItemid(itemId);
		pay.setPoint(payBean.getReward());
		pay.setGift_point(payBean.getAddReward());
		pay.setCreatetime((int) (System.currentTimeMillis() / 1000l));
		pay.setPaytime((int) (System.currentTimeMillis() / 1000l));
		pay.setItems("");
		pay.setCuid("USD");
		pay.setAmount((int) (payBean.getPrice() * 100));
		pay.setItemid2(payBean.getGoogleplayName());
		return pay;
	}

	public void addLog(PayInfo payInfo) {
		UserBean user = Globals.getUserManager().getUserBean(payInfo.getCharid());

		PayLog payLog = new PayLog(LogfType.PAY_SUCCESS, user);
		payLog.setOrderid(payInfo.getOrderid());
		payLog.setGssid(payInfo.getGssid());
		payLog.setGuid(payInfo.getGuid());
		payLog.setGateway(payInfo.getGateway());
		payLog.setItemid(payInfo.getItemid());
		payLog.setPoint(payInfo.getPoint());
		payLog.setGift_point(payInfo.getGift_point());
		payLog.setCreatetime(payInfo.getCreatetime());
		payLog.setPaytime(payInfo.getPaytime());
		payLog.setItems(payInfo.getItems());
		payLog.setCuid(payInfo.getCuid());
		payLog.setAmount(payInfo.getAmount());
		payLog.setItemid2(payInfo.getItemid2());
		PveCityEntity pveCityEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(payInfo.getCharid());
		if (pveCityEntity == null) {
			payLog.setPveId(-1);// 没有数据, 正常不会走到这里
			payLog.setTargetId(-1);// 没有数据, 正常不会走到这里
		} else {
			payLog.setPveId(pveCityEntity.getCurPveTarget());
			payLog.setTargetId(pveCityEntity.getTargetId());
		}

		LogfPrinter.getInstance().push(payLog);
	}
}
