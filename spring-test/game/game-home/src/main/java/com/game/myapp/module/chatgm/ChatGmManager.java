package com.game.myapp.module.chatgm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.GMLog;
import com.game.common.user.UserBean;
import com.game.constants.ChatConstants;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.core.cache.CacheType;
import com.game.core.db.EntityClassManager;
import com.game.core.handler.MsgBack;
import com.game.core.handler.MsgBuilder;
import com.game.core.hibernate.orm.BaseEntity;
import com.game.db.entity.AccountEntity;
import com.game.db.entity.AchieveEntity;
import com.game.db.entity.CompetitionEntity;
import com.game.db.entity.DaytaskEntity;
import com.game.db.entity.FriendreqEntity;
import com.game.db.entity.FriendsEntity;
import com.game.db.entity.FriendsFabulousEntity;
import com.game.db.entity.GiftEntity;
import com.game.db.entity.HeroCommentEntity;
import com.game.db.entity.HeroEntity;
import com.game.db.entity.ItemEntity;
import com.game.db.entity.LatelyfriendEntity;
import com.game.db.entity.LuckDrawInfoEntity;
import com.game.db.entity.PveCityEntity;
import com.game.db.entity.RoleActivityEntity;
import com.game.db.entity.RoleBattleEntity;
import com.game.db.entity.RoleEliteEntity;
import com.game.db.entity.RoleEntity;
import com.game.db.entity.RoleGuideEntity;
import com.game.db.entity.RoleInfoEntity;
import com.game.db.entity.RolePayInfoEntity;
import com.game.db.entity.RoleSystemMailEntity;
import com.game.db.entity.ShowIdEntity;
import com.game.db.entity.SignEntity;
import com.game.db.entity.UserNameUidEntity;
import com.game.generate.bean.ItemBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.activity.EActivityLogicType;
import com.game.myapp.module.item.EItemId;
import com.game.myapp.module.item.EItemType;
import com.game.rpc.vo.PayInfo;
import com.game.utils.StringUtils;
import com.google.protobuf.ByteString;

import buffer.CGPveCityInfoMsg.CGPveCityInfoProto;
import buffer.GCChatMsg;
import buffer.GCChatMsg.GCChatProto.Builder;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ChatGmManager {

	private Logger logger = Loggers.chatgmLogger;

	public boolean excuteCmd(long uid, String content) {
		if (content == null || content.equals("")) {
			return false;
		}

		List<String> paramList = new ArrayList<String>();
		paramList.add(String.valueOf(uid));
		content = content.trim();
		String[] cmdParamArray = content.split("\\s+");// 单个空格或多个空格分隔成数组
		String cmd = cmdParamArray[0];
		UserBean user = Globals.getUserManager().getUserBean(uid);
		// 日志打点
		GMLog gmLog = new GMLog(user);
		gmLog.setGmArray(Arrays.asList(cmdParamArray));
		LogfPrinter.getInstance().push(gmLog);

		EChatGmType gmType = null;
		if (StringUtils.isNumeric(cmd)) {
			// 道具指令
			gmType = EChatGmType.ITEM;
			String numStr = cmdParamArray[1];// 道具数量
			cmdParamArray = new String[4];
			cmdParamArray[0] = EChatGmType.ITEM.getCmd();
			cmdParamArray[1] = EItemType.ITEM.getType() + "";
			cmdParamArray[2] = cmd;// 道具Id
			cmdParamArray[3] = numStr;
		} else {
			gmType = EChatGmType.typeOf(cmd.toLowerCase());
			if (gmType == null) {
				return false;
			}
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			switch (gmType) {
			case HELP:// 帮助[!help].
				doHelp(uid);
				return true;
			case ITEM_ID:// 物品Id
				sendItemIds(uid);
				return true;
			case ITEM:// 添加物品[!item type id num] !item 3 1 100
				handleItem(uid, cmdParamArray);
				return true;
			case ITEM_CLEAR:// 删除所有道具
			case ITEM_CLEAR2:// 删除所有道具
				GameGlobals.itemManager.gmClearAllItem(uid);
				return true;
			case DELETEROLE:
			case DELETEROLE2:
				deleteRole(uid);
				return true;
			case PAY:
				int payId = Integer.parseInt(cmdParamArray[1]);
				PayInfo payInfo = GameGlobals.payManager.createPayInfo(payId, uid);
				if (payInfo != null) {
					int result = GameGlobals.payManager.handlePay(payInfo);
					if (result == ErrorCodeConst.SUCCESS) {
						GameGlobals.payManager.addLog(payInfo);
						logger.info("ChatGm: pay success. uid = " + uid + ", payId = " + payId);
					}
				}
				return true;
			case MONTHCARD:
				GameGlobals.monthCardManager.updateRoleMonthCardA(uid, GameGlobals.monthCardManager.buyAddTime);
				return true;
			case ALLHERO:
				GameGlobals.heroManager.gmAllHero(uid);
				return true;
			case UID:
				GameGlobals.chatManager.sendtoPlayer(uid, buildChatMsg("uid: " + uid));
				return true;
			case ONLINE:
				GameGlobals.chatManager.sendtoPlayer(uid, buildChatMsg("在线人数: " + Globals.getUserManager().getOnlinePlayerNum()));
				return true;
			case PVE:
				int pveId = Integer.parseInt(cmdParamArray[1]);
				logger.info("ChatGm: pve. uid = " + uid + ", pveId = " + pveId);
				GameGlobals.pveCityManager.gmUpdatePveId(uid, pveId);
				buildGCPveCityInfoProto(uid);
				return true;
			case PVE_STATUS:
				int pveId1 = Integer.parseInt(cmdParamArray[1]);
				int status = Integer.parseInt(cmdParamArray[2]);
				logger.info("ChatGm: pves. uid = " + uid + ", pveId = " + pveId1 + ", status = " + status);
				GameGlobals.pveCityManager.gmUpdatePveIdWithStatus(uid, pveId1, status);
				buildGCPveCityInfoProto(uid);
				return true;
			case ACTIVITY_PVE_WINNING_STREAK:
				conwinOnOff(cmdParamArray, uid, content);
				return true;
			case ELITE_ALL_1:
			case ELITE_ALL_2:
				GameGlobals.eliteManager.gmOpenAll(uid);
				return true;
			case ELITE_RESET_1:
			case ELITE_RESET_2:
				GameGlobals.eliteManager.reset(uid);
				return true;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			GameGlobals.chatManager.sendtoPlayer(uid, buildChatMsg("指令错误: " + content));
			return true;
		} finally {
			lock.unlock();
		}

		return false;
	}

	private void sendItemIds(long uid) {
		TreeMap<Integer, ItemBean> dataMap = Datas.getDataMap(ItemBean.class);
		StringBuilder sb = new StringBuilder();
		for (ItemBean itemBean : dataMap.values()) {
			String itemName = itemBean.getName();
			if (itemName.length() > 10) {
				itemName = itemName.substring(0, 10);
			}
			sb.append(itemBean.getId()).append(itemName).append(" ");
		}
		GameGlobals.chatManager.sendtoPlayer(uid, buildChatMsg(sb.toString()));
	}

	private void handleItem(long uid, String[] cmdParamArray) {
		int itemType = Integer.parseInt(cmdParamArray[1]);
		int itemId = Integer.parseInt(cmdParamArray[2]);
		int itemNum = Integer.parseInt(cmdParamArray[3]);

		if (itemNum > 0) {
			// 添加
			GameGlobals.bagManager.addItem(uid, itemType, itemId, itemNum, LogfConstants.CHANNEL_GM + "", "");
			logger.info("ChatGm: add item. uid = " + uid + ", itemType = " + itemType + ", itemId = " + itemId + ", itemNum = " + itemNum);
		} else {
			// 消耗
			EItemId eItemId = EItemId.valueOf(itemId);
			if (eItemId == null) {
				eItemId = EItemId.UNKNOWN;
			}

			switch (eItemId) {
			case POWER:
				GameGlobals.roleManager.reducePower(uid, Math.abs(itemNum));
				break;
			case COIN:
			case REWARD_CASH:
			case RECHARGE_CASH:
				GameGlobals.unitManager.cost(uid, itemType, itemId, Math.abs(itemNum), LogfConstants.CHANNEL_GM + "", "GM");
				break;
			case UNKNOWN:
			default:
				GameGlobals.itemManager.costItem(uid, itemId, Math.abs(itemNum));
				break;
			}
			logger.info("ChatGm: cost item. uid = " + uid + ", itemType = " + itemType + ", itemId = " + itemId + ", itemNum = " + itemNum);
		}
	}

	private void conwinOnOff(String[] cmdParamArray, long uid, String content) {
		String conWinOnOff = cmdParamArray[1];
		if (conWinOnOff.equals("on")) {
			GameGlobals.activityManager.gmOnOffActivity(EActivityLogicType.BATTLE_PVE_WINNING_STREAK_1, true);
			logger.info("ChatGm: WINNING_STREAK on.");
		} else if (conWinOnOff.equals("off")) {
			GameGlobals.activityManager.gmOnOffActivity(EActivityLogicType.BATTLE_PVE_WINNING_STREAK_1, false);
			logger.info("ChatGm: WINNING_STREAK off.");
		} else {
			GameGlobals.chatManager.sendtoPlayer(uid, buildChatMsg("指令错误参数: " + content));
		}
	}

	private void buildGCPveCityInfoProto(long uid) {
		MsgBack msgBack = new MsgBack();
		GameGlobals.pveCityManager.CGPveCityInfo(msgBack, uid, CGPveCityInfoProto.getDefaultInstance());
		List<MsgBuilder> builderList = msgBack.getBuilder();
		for (MsgBuilder msgBuilder : builderList) {
			GameGlobals.chatManager.sendtoPlayer(uid, msgBuilder.getBuilder());
		}
	}

	private void doHelp(long uid) {
		for (EChatGmType gmType : EChatGmType.values()) {
			GameGlobals.chatManager.sendtoPlayer(uid, buildChatMsg(gmType.getCmdDescription()));
		}
	}

	private Builder buildChatMsg(String content) {
		GCChatMsg.GCChatProto.Builder gcMsg = GCChatMsg.GCChatProto.newBuilder();
		gcMsg.setAren("");
		gcMsg.setCont(content);// 内容
		gcMsg.setFromUid(0);
		gcMsg.setHead("");
		gcMsg.setLv(1);
		gcMsg.setChatType(ChatConstants.CHAT_BAN_TYPE_WORLD);
		gcMsg.setName("GM");
		gcMsg.setSex(1);
		gcMsg.setVoice(ByteString.EMPTY);
		return gcMsg;
	}

	private Class[] deleteEntityClassArray = { AchieveEntity.class, CompetitionEntity.class, DaytaskEntity.class, FriendreqEntity.class, FriendsEntity.class,
			FriendsFabulousEntity.class, GiftEntity.class, HeroCommentEntity.class, HeroEntity.class, ItemEntity.class, LatelyfriendEntity.class,
			LuckDrawInfoEntity.class, PveCityEntity.class, RoleActivityEntity.class, RoleBattleEntity.class, RoleEliteEntity.class, RoleGuideEntity.class,
			RoleInfoEntity.class, RolePayInfoEntity.class, RoleSystemMailEntity.class, ShowIdEntity.class, SignEntity.class };

	private void deleteRole(long uid) throws Exception {
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return;
		}
		String pfId = role.getPfId();
		if (Globals.getUserManager().getUserBean(uid) != null) {
			if (Objects.equals("", pfId)) {
				pfId = Globals.getUserManager().getUserBean(uid).getPassportId();
			}
			Globals.getUserManager().remove(uid);
		}

		// 清空账号
		AccountEntity dbEntity = new AccountEntity();
		dbEntity.setPfId(pfId);
		Globals.getEntityProxy().delete(dbEntity);
		Globals.getEntityProxy().delete(role);

		//删除玩家名字信息
		List<UserNameUidEntity> names = Globals.getEntityProxy().findAll(UserNameUidEntity.class);
		for (UserNameUidEntity userNameUidEntity : names) {
			if (userNameUidEntity.getUid() == uid) {
				Globals.getEntityProxy().delete(userNameUidEntity);
			}
		}

		List<Class<?>> entityClassList = EntityClassManager.getEntityClassList();
		//  删除玩家其他数据
		for (Class clazz : deleteEntityClassArray) {
			if (!entityClassList.contains(clazz)) {
				continue;
			}

			int indexOf = entityClassList.indexOf(clazz);
			clazz = entityClassList.get(indexOf);

			CacheType annotation = (CacheType) clazz.getAnnotation(CacheType.class);
//			Loggers.dbLogger.info("clazz: " + clazz.getSimpleName() + ", annotation: " + annotation);
			int cacheType = annotation == null ? CacheType.COMMON : annotation.type();
			switch (cacheType) {
			case CacheType.COMMON:
				BaseEntity entity = Globals.getEntityProxy().get(clazz, uid);
				if (entity != null) {
//					Loggers.dbLogger.info("delete single entity: " + entity);
					Globals.getEntityProxy().delete(entity);
				}
				break;
			case CacheType.ROLE_MULTI_MAPPING:
				List<BaseEntity> entityList = Globals.getEntityProxy().findAllByUid(clazz, uid);
				if (entityList != null && !entityList.isEmpty()) {
					for (BaseEntity baseEntity : entityList) {
//						Loggers.dbLogger.info("delete multi entity: " + baseEntity);
						Globals.getEntityProxy().delete(baseEntity);
					}
				}
				break;
			}
		}
	}
}
