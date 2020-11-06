package com.game.myapp.module.share;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.common.user.UserBean;
import com.game.constants.DayTaskConstants;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBack;
import com.game.db.entity.PveCityEntity;
import com.game.db.entity.RoleEntity;
import com.game.generate.bean.ShareBean;
import com.game.generate.bean.TargetBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.achieve.EAchieveBehavior;
import com.game.myapp.module.item.EItemType;
import com.game.utils.JsonUtil;
import com.game.utils.RandomUtil;
import com.game.utils.RandomWeightVO;
import com.game.utils.TimeUtils;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

import buffer.CGShareMsg.CGShareProto;
import buffer.GCShareInfoMsg.GCShareInfoProto;
import buffer.GCShareMsg.GCShareProto;

public class ShareManager {
	public void CGShareInfo(MsgBack msgBack, UserBean userBean) {
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(userBean.getUid());
		if (role == null) {
			return;
		}

		// 检测并初始化分享信息
		checkAndInitShareInfo(role);

		msgBack.addBuilder(buildGCShareInfoProto(role));
	}

	public void CGShare(MsgBack msgBack, UserBean userBean, CGShareProto msg) {
		ShareType shareType = ShareType.valueOf(msg.getShareType());
		if (shareType == null) {
			Loggers.serverLogger.error("No ShareType. shareType = " + msg.getShareType());
			return;
		}

		ShareBean shareBean = Datas.get(ShareBean.class, shareType.getType());
		if (shareBean == null) {
			Loggers.serverLogger.error("No ShareBean data! shareType: " + shareType);
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(userBean.getUid());
			if (role == null) {
				Loggers.serverLogger.error("No RoleEntity. uid = " + userBean.getUid());
				return;
			}

			// 检测并初始化分享信息
			checkAndInitShareInfo(role);

			GCShareProto.Builder gcShareMsg = GCShareProto.newBuilder().setStatus(ErrorCodeConst.SUCCESS).setShareType(msg.getShareType());
			msgBack.addBuilder(gcShareMsg);

			List<ShareInfo> list = JsonUtil.toList(role.getShareInfo(), ShareInfo.class);
			// 更新次数
			if (!updateDailyShareInfo(list, shareType)) {
				// 次数不足
				gcShareMsg.setStatus(ErrorCodeConst.SHARE_NO_TIMES);
				return;
			}

			// 处理分享逻辑
			switch (shareType) {
			case SHARE_DAILY:// 每日分享
				// 给奖励
				String shareRewardStr = GameGlobals.configManager.getShareReward();
				if (shareRewardStr == null) {
					Loggers.serverLogger.error("No share reward data!");
					return;
				}

				// 3,4,100
				String[] split = shareRewardStr.trim().split(",");
				if (split.length != 3) {
					Loggers.serverLogger.error("Error share reward data! rewardStr = " + shareRewardStr);
					return;
				}

				try {
					int itemType = Integer.parseInt(split[0]);
					int itemId = Integer.parseInt(split[1]);
					int itemNum = Integer.parseInt(split[2]);
					GameGlobals.bagManager.addItem(role.getUid(), itemType, itemId, itemNum, LogfConstants.CHANNEL_SHARE, String.valueOf(msg.getShareType()));
					// 奖励内容房间返回消息
					gcShareMsg.setItems(shareRewardStr.trim());
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				break;
			case AD_BATTLE_REWARD:// 战斗结算翻倍
				// 只处理PVE结算翻倍
				List<ItemVo> pveRewardList = userBean.getPveRewardList();
				if (pveRewardList == null || pveRewardList.isEmpty()) {
					// 重复请求
					gcShareMsg.setStatus(ErrorCodeConst.SHARE_DUPLICATION_REQUEST);
					Loggers.serverLogger.error("No PVE reward list! Maybe duplication request. uid = " + userBean.getUid());
					break;
				}

				// 翻倍奖励去除英雄奖励
				for (int i = pveRewardList.size() - 1; i >= 0; i--) {
					ItemVo itemVo = pveRewardList.get(i);
					if (EItemType.HERO.is(itemVo.getType())) {
						pveRewardList.remove(i);
					}
				}

				// 额外奖励的倍数
				int multiple = GameGlobals.configManager.getBattleExtraReward();
				// 发放奖励
				for (ItemVo tempReward : pveRewardList) {
					int itemType = tempReward.getType();
					int itemId = tempReward.getId();
					tempReward.setNum(tempReward.getNum() * multiple);
					int itemNum = tempReward.getNum();
					GameGlobals.bagManager.addItem(userBean.getUid(), itemType, itemId, itemNum, LogfConstants.CHANNEL_BATTLE, "-1");
				}

				// 返回消息加入奖励内容
				gcShareMsg.setItems(ItemUtil.toInfoString(pveRewardList));

				// 给过奖励后, 清空
				userBean.setPveRewardList(null);

				// 累加视频广告次数
				role.setAdTotalCount(role.getAdTotalCount() + 1);

				break;
			case AD_RANDOM_ITEM:// 战前随机道具棋子
//				RoleEntity roleDB = GameGlobals.roleManager.getRoleEntity(userBean.getUid());
//				if (roleDB == null) {
//					Loggers.serverLogger.error("No RoleEntity! uid = " + userBean.getUid());
//					return;
//				}
//
//				int pveTargetId = roleDB.getPveTaskId();

				PveCityEntity pveEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(userBean.getUid());
				if (pveEntity == null) {
					Loggers.serverLogger.error("No PveCityEntity! uid = " + userBean.getUid());
					return;
				}

				int pveTargetId = pveEntity.getTargetId();
				TargetBean targetBean = Datas.get(TargetBean.class, pveTargetId);
				if (targetBean == null) {
					Loggers.serverLogger.error("No TargetBean! TargetId = " + pveTargetId);
					return;
				}

				if (targetBean.getRandomChessList().isEmpty()) {
					Loggers.serverLogger.error("No random chess in TargetBean! TargetId = " + pveTargetId + ", randomChess = " + targetBean.getRandomChess());
					return;
				}

				// 随机棋子
				RandomWeightVO randomVO = RandomUtil.random(targetBean.getRandomChessList());
				int randomChessId = randomVO.getKey();
				// 记录随机棋子到userbean
				userBean.setRandomChessId(randomChessId);
				// 返回棋子信息
				gcShareMsg.setItems(randomChessId + "");

				// 累加视频广告次数
				role.setAdTotalCount(role.getAdTotalCount() + 1);
				break;
			case AD_PVE_ADD_STEP:// PVE加步数
				// 加步数只记次数. 其他什么也不做
				// 累加视频广告次数
				role.setAdTotalCount(role.getAdTotalCount() + 1);
				break;
			default:
				return;
			}

			// 1 广告 2分享
			if (shareBean.getType() == 1) {
				GameGlobals.achieveManager.onTask(userBean.getUid(), EAchieveBehavior.TYPE_VIEW_VIDEO, 1);
				GameGlobals.taskManager.onTask(userBean.getUid(), DayTaskConstants.TYPE_VIEW_VIDEO, 1);
			}

			role.setShareInfo(JsonUtil.toJson(list));
			Globals.getEntityProxy().updateAsync(role);
			Loggers.serverLogger.info("CGShareInvite: shareType = " + shareType.getType() + ", " + list);

			// 检测分享奖励红点
//			GameGlobals.redHatManager.checkBehaviorRedHat(role.getUid(), RedHatBehavior.SHARE_REWARD);

			msgBack.addBuilder(buildGCShareInfoProto(role));
		} finally {
			lock.unlock();
		}
	}

	private void checkAndInitShareInfo(RoleEntity role) {
		ReentrantLock lock = Globals.getLockManager().getLock(role.getUid());
		lock.lock();
		try {
			if (role.getShareInfo() == null || "".equals(role.getShareInfo())) {
				role.setShareInfo(initShareInfo());
				role.setAdTotalCount(0);
				role.setShareResetTime(System.currentTimeMillis());
				Globals.getEntityProxy().updateAsync(role);
				return;
			}

			// 检测是否有新的分享类型没有加到数据中
			checkNewShareType(role);

			boolean flag = TimeUtils.isToday(role.getShareResetTime());
			if (flag) {
				return;
			}

			// 重置分享信息
			List<ShareInfo> list = JsonUtil.toList(role.getShareInfo(), ShareInfo.class);
			for (ShareInfo shareInfo : list) {
				ShareType shareType = ShareType.valueOf(shareInfo.getShareType());
				if (shareType == null || shareType.getResetType().is(ShareResetType.NEVER)) {
					// ResetType为NEVER的不做重置
					continue;
				}

				// 重置分享信息
				shareInfo.reset();
			}

			role.setShareInfo(JsonUtil.toJson(list));
			role.setShareResetTime(System.currentTimeMillis());
			Globals.getEntityProxy().updateAsync(role);

			// 检测分享奖励红点
//			GameGlobals.redHatManager.checkBehaviorRedHat(role.getUid(), RedHatBehavior.SHARE_REWARD);
		} finally {
			lock.unlock();
		}
	}

	private String initShareInfo() {
		List<ShareInfo> shareInfoList = new ArrayList<ShareInfo>();
		for (ShareBean shareBean : Datas.getDataMap(ShareBean.class).values()) {
			ShareInfo shareInfo = new ShareInfo(shareBean.getId(), 0, shareBean.getLimit());
			shareInfoList.add(shareInfo);
		}
		return JsonUtil.toJson(shareInfoList);
	}

	private void checkNewShareType(RoleEntity role) {
		ReentrantLock lock = Globals.getLockManager().getLock(role.getUid());
		lock.lock();
		try {
			List<ShareInfo> shareInfoList = JsonUtil.toList(role.getShareInfo(), ShareInfo.class);
			boolean isChanged = false;
			for (ShareBean shareBean : Datas.getDataMap(ShareBean.class).values()) {
				ShareType shareType = ShareType.valueOf(shareBean.getId());
				if (shareType == null) {
					Loggers.battleLogger.warn("checkNewShareType(): No shareType in Enum. shareType = " + shareBean.getId());
					continue;
				}

				ShareInfo shareInfo = null;
				for (ShareInfo info : shareInfoList) {
					if (shareType.is(info.getShareType())) {
						shareInfo = info;
						break;
					}
				}

				if (shareInfo == null) {
					isChanged = true;

					// 初始化新的分享类型
					shareInfo = new ShareInfo(shareBean.getId(), 0, shareBean.getLimit());
//				for (int index = 0; index < shareBean.getMaxRewardTimes(); index++) {
//					shareInfo.getRewardItemMap().put(index,
//							new ShareRewardItem(index, shareBean.getShareTimesByRewardIndex(index), ShareRewardItem.STATUS_CAN_NOT_GET));
//				}
					shareInfoList.add(shareInfo);
				}
			}

			if (isChanged) {
				role.setShareInfo(JsonUtil.toJson(shareInfoList));
//			role.setShareResetTime(System.currentTimeMillis());
				Globals.getEntityProxy().updateAsync(role);
			}
		} finally {
			lock.unlock();
		}
	}

	private GCShareInfoProto.Builder buildGCShareInfoProto(RoleEntity role) {
		GCShareInfoProto.Builder builder = GCShareInfoProto.newBuilder();
		List<ShareInfo> list = JsonUtil.toList(role.getShareInfo(), ShareInfo.class);
		for (ShareInfo shareInfo : list) {
			builder.addInfoList(shareInfo.getShareInfoBuilder());
		}
		builder.setStatus(ErrorCodeConst.SUCCESS);
//		Loggers.serverLogger.info("GCShareInfo: " + list);
		return builder;
	}

	private boolean updateDailyShareInfo(List<ShareInfo> list, ShareType shareType) {
		ShareInfo shareInfo = null;
		for (ShareInfo info : list) {
			if (info.getShareType() == shareType.getType()) {
				shareInfo = info;
				break;
			}
		}

		if (shareInfo == null) {
			Loggers.serverLogger.error("No entity data for " + shareType);
			return false;
		}

		if (shareInfo.getSharedTimes() >= shareInfo.getLimitTimes()) {
			Loggers.serverLogger.info("No times! shareType =  " + shareType);
			return false;
		}

		// 增加每日分享的次数
		shareInfo.setSharedTimes(shareInfo.getSharedTimes() + 1);
		return true;
	}
}