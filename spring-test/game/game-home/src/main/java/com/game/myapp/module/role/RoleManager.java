package com.game.myapp.module.role;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.game.common.logf.LogfConstants;
import com.game.common.session.GameSession;
import com.game.common.user.IRoleManager;
import com.game.common.user.UserBean;
import com.game.constants.CMDConstants;
import com.game.constants.DayTaskConstants;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.constants.RoleConstants;
import com.game.core.handler.MsgBack;
import com.game.core.lock.LockManager;
import com.game.db.entity.HeroEntity;
import com.game.db.entity.PveCityEntity;
import com.game.db.entity.RoleBattleEntity;
import com.game.db.entity.RoleEntity;
import com.game.db.entity.ShowIdEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.achieve.EAchieveBehavior;
import com.game.myapp.module.battle.BattleType;
import com.game.myapp.module.event.LogoutEvent;
import com.game.myapp.module.function.ISystemId;
import com.game.myapp.module.item.EItemId;
import com.game.myapp.module.item.EItemType;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IRoleService;
import com.game.rpc.vo.RoleInfo;
import com.game.utils.EmojiUtil;
import com.game.utils.JsonUtil;
import com.game.utils.StringUtils;
import com.game.utils.TimeUtils;
import com.game.vo.item.ItemVo;
import com.google.protobuf.GeneratedMessage.Builder;

import buffer.CGDoLoginMsg;
import buffer.CGGetLoginSuccessInfoMsg.CGGetLoginSuccessInfoProto;
import buffer.CGLoadingRoleMsg;
import buffer.CGModifyRoleNameMsg.CGModifyRoleNameProto;
import buffer.CGPowerFullMsg.CGPowerFullProto;
import buffer.CGPowerGetInfoMsg.CGPowerGetInfoProto;
import buffer.CGRoleBattleSelect.CGRoleBattleSelectProto;
import buffer.GCCommonResponseMsg;
import buffer.GCFunctionOpenInfoMsg.GCFunctionOpenInfoProto;
import buffer.GCGetLoginSuccessInfoMsg;
import buffer.GCGetRedHatinfoMsg.GCGetRedHatinfoProto;
import buffer.GCHttpBeatMsg;
import buffer.GCKickOfflineMsg.GCKickOfflineProto;
import buffer.GCLoadingRoleMsg;
import buffer.GCModifyRoleNameMsg;
import buffer.GCPowerFullMsg.GCPowerFullProto;
import buffer.GCPowerGetInfoMsg.GCPowerGetInfoProto;
import buffer.GCRoleBattleSelect.GCRoleBattleSelectProto;

public class RoleManager implements IRoleManager {
	private Logger log = Logger.getLogger(RoleManager.class);

	public RoleManager() {
		Globals.setRoleManager(this);
	}

	public void CGGetLoginSuccessInfo(MsgBack msgBack, long uid, CGGetLoginSuccessInfoProto msg) {
		GCGetLoginSuccessInfoMsg.GCGetLoginSuccessInfoProto.Builder builder = GCGetLoginSuccessInfoMsg.GCGetLoginSuccessInfoProto.newBuilder();
		msgBack.addBuilder(builder);
		builder.setGuide(GameGlobals.guideManager.getLastGuideStep(uid));
		builder.addAllNextGuideIds(GameGlobals.guideManager.getRoleNextGuideIds(uid));
		builder.addAllBattleGuides(GameGlobals.guideManager.getRoleBattleGuideList(uid));
		List<?> functionList = GameGlobals.functionOpenManager.getRoleOpenFunctionList(uid);
		for (Object id : functionList) {
			builder.addFunctionIds(Integer.parseInt(String.valueOf(id)));
		}
		builder.setStatus(ErrorCodeConst.SUCCESS);
	}

	/**
	 * 获取玩家基本信息
	 * 
	 * @param msgBack
	 * 
	 * @param userBean
	 * @param protoInstance
	 * @
	 */
	public void CGLoadingRole(MsgBack msgBack, UserBean userBean, CGLoadingRoleMsg.CGLoadingRoleProto protoInstance) {
		long uid = userBean.getUid();
		RoleEntity role = this.getRoleEntity(uid);
		if (role == null) {
			log.error("CGLoadingRole(): Error uid! role = " + role + ", uid = " + uid);
			return;
		}

		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleBattleEntity battleEntity = this.getRoleBattleEntity(uid);
			if (battleEntity == null) {
				battleEntity = this.initRoleBattle(uid);
			}
			// 在loadingRole之前先发ShareInfo
			GameGlobals.shareManager.CGShareInfo(msgBack, userBean);
			// 重置玩家活动信息
			GameGlobals.roleActivityManager.resetActivityInfo(uid);
			// 活动信息
			GameGlobals.roleActivityManager.CGActivityInfoProto(msgBack, userBean);

			GCLoadingRoleMsg.GCLoadingRoleProto.Builder gcMsg = GCLoadingRoleMsg.GCLoadingRoleProto.newBuilder();
			msgBack.addBuilder(gcMsg);
			gcMsg.setAvatar(role.getAvatar());
			gcMsg.setCash(role.getCoin());
			gcMsg.setExp(0);
			gcMsg.setGold(0);
			gcMsg.setLv(1);
			gcMsg.setSex(role.getSex());
			gcMsg.setName(role.getName());
			gcMsg.setPveChoiceHeroCode(battleEntity.getHeroId());
			gcMsg.setPveConWinNum(role.getPveConWinNum());
			gcMsg.setStep(0);
			gcMsg.setServerTime(System.currentTimeMillis());
			gcMsg.setBlueCash(getAllBlueCash(role));
			gcMsg.setIsShowSign(GameGlobals.signManager.isShowSignButton(uid));
			gcMsg.setIsShowFirtPay(GameGlobals.payManager.checkIsShowFirstPayButton(role));
			gcMsg.setPvpBattleNum(0);
			gcMsg.setDupStar(role.getDupStar());
			// pve关卡检测
			//		this.checkRolePveTaskId(role);
			//		gcMsg.setPveTaskId(role.getPveTaskId());
			gcMsg.setPveTaskId(this.checkRolePveTaskId(uid));
			// 更新体力
			this.updateRolePower(uid, System.currentTimeMillis() / 1000);
			int updatePowerTime = (int) (System.currentTimeMillis() / 1000 - role.getLastUpdatePowerTime());
			gcMsg.setUpdatePowerTime(GameGlobals.configManager.getPowerRecoveryTime() * 60 - updatePowerTime);
			if (role.getPower() >= getPvePowerLimit(uid)) {
				gcMsg.setUpdatePowerTime(0);
			}
			if (role.getNoLimitPowerEndTime() - System.currentTimeMillis() / 1000 > 0) {
				gcMsg.setInfinitePowerEndTime((int) (role.getNoLimitPowerEndTime() - System.currentTimeMillis() / 1000));
			}
			gcMsg.setPower(role.getPower());

			// 检测天梯
//			BattleGlobals.ladderRankBattleManager.checkRoleUpdateLadderInfo(uid);
			// 七日签到检测
			GameGlobals.signManager.checkReceiveSevenReward(uid);
			// 玩家登陆之后load下GM库的系统邮件
			GameGlobals.mailManager.loadGmMail(userBean.getUid());
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 起名
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 * @
	 */
	public void CGModifyRoleName(MsgBack msgBack, long uid, CGModifyRoleNameProto msg) {
		RoleEntity role = getRoleEntity(uid);
		GCModifyRoleNameMsg.GCModifyRoleNameProto.Builder builder = GCModifyRoleNameMsg.GCModifyRoleNameProto.newBuilder();
		msgBack.addBuilder(builder);
		if (role == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}

		String name = msg.getName();
		if (name.length() > 6) {
			builder.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}
		boolean check = GameGlobals.userNameCheckManager.isNameExist(name);
		if (!check) {
			builder.setStatus(ErrorCodeConst.ROLE_NAME_IS_EXIST);
			return;
		}
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			String oldName = role.getName();
			role.setName(name);
			role.setAvatar(msg.getHeader());
			if (role.getSex() != msg.getSex()) {
				role.setSex(msg.getSex());
			}
			Globals.getEntityProxy().updateAsync(role);

			GameGlobals.userNameCheckManager.update(uid, oldName, name);
		} finally {
			lock.unlock();
		}
		builder.setStatus(ErrorCodeConst.SUCCESS);
	}

	/**
	 * 获取体力详情
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 */
	public void CGPowerGetInfo(MsgBack msgBack, long uid, CGPowerGetInfoProto msg) {
		this.packGCPowerGetInfoProto(msgBack, uid);
	}

	public void CGPowerFull(MsgBack msgBack, UserBean userBean, CGPowerFullProto msg) {
		GCPowerFullProto.Builder builder = GCPowerFullProto.newBuilder();
		msgBack.addBuilder(builder);
		long uid = userBean.getUid();
		RoleEntity role = this.getRoleEntity(uid);
		if (role == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			int max = getPvePowerLimit(uid);
			if (role.getPower() >= max) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			// 先读取单次消耗, 根据实际增加体力值计算消耗
			int deltaPower = max - role.getPower();
			ItemVo pveBuyCostBagInfo = GameGlobals.configManager.getPveBuyCostPowerOne();
			int cost = pveBuyCostBagInfo.getNum() * deltaPower;
			if (deltaPower == max) {
				// 玩家购买的体力和体力上限相同, 即玩家没有体力的情况下, 使用满体力消耗(比单价*数量会便宜一些)
				pveBuyCostBagInfo = GameGlobals.configManager.getPveBuyCostPowerFull();
				cost = pveBuyCostBagInfo.getNum();
			}

			if (!GameGlobals.unitManager.isEnough(role, pveBuyCostBagInfo.getId(), cost)) {
				builder.setStatus(ErrorCodeConst.RESOURCE_NOT_ENOUGH);
				return;
			}

			// 扣资源
			GameGlobals.unitManager.cost(uid, pveBuyCostBagInfo.getType(), pveBuyCostBagInfo.getId(), cost, LogfConstants.CHANNEL_BATTLE, "buy_pve_power");

			// 增加体力
			GameGlobals.roleManager.addPower(EPowerChannel.BUY, role.getUid(), deltaPower);

			builder.setPower(role.getPower());
			builder.setStatus(ErrorCodeConst.SUCCESS);

			this.packGCPowerGetInfoProto(msgBack, uid);
		} finally {
			lock.unlock();
		}
	}

	public void CGRoleBattleSelect(MsgBack msgBack, UserBean userBean, CGRoleBattleSelectProto msg) {
		GCRoleBattleSelectProto.Builder gcRoleBattleSelectProto = GCRoleBattleSelectProto.newBuilder();
		msgBack.addBuilder(gcRoleBattleSelectProto);
		gcRoleBattleSelectProto.setStatus(ErrorCodeConst.SUCCESS);
		gcRoleBattleSelectProto.setBattleType(msg.getBattleType());

		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			RoleBattleEntity entity = GameGlobals.roleManager.getRoleBattleEntity(userBean.getUid());
			if (entity == null) {
				entity = initRoleBattle(userBean.getUid());
			}

			boolean isDataChanged = false;
			// 英雄 传入推荐英雄的时候不能将其赋值到默认选择
			if (msg.getRecommendHero() == 0 && entity.getHeroId() != msg.getHeroId()) {
				isDataChanged = true;
				entity.setHeroId(msg.getHeroId());
			}
			// 推荐英雄
			entity.setRecommendHeroId(msg.getRecommendHero());

			// 道具
			String itemIdJson = JsonUtil.toJson(msg.getItemIdList());
			BattleType battleType = BattleType.valueOf(msg.getBattleType());
			switch (battleType) {
			case PVE_MAIN:
				if (StringUtils.isEmpty(entity.getPveItemIds()) || !entity.getPveItemIds().equals(itemIdJson)) {
					isDataChanged = true;
					entity.setPveItemIds(itemIdJson);
				}
				break;
			case PVE_DUP:
				if (StringUtils.isEmpty(entity.getDupItemIds()) || !entity.getDupItemIds().equals(itemIdJson)) {
					isDataChanged = true;
					entity.setDupItemIds(itemIdJson);
				}
				break;
			case PVE_ELITE:
				if (StringUtils.isEmpty(entity.getEliteItemIds()) || !entity.getEliteItemIds().equals(itemIdJson)) {
					isDataChanged = true;
					entity.setEliteItemIds(itemIdJson);
				}
				break;
			default:
				gcRoleBattleSelectProto.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			if (isDataChanged) {
				Globals.getEntityProxy().updateAsync(entity);
			}
		} finally {
			lock.unlock();
		}
	}

	public RoleEntity initRole(long uid, CGDoLoginMsg.CGDoLoginProto msg) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleEntity db = getRoleEntity(uid);
			if (db != null) {
				return db;
			}

			long nowMill = System.currentTimeMillis();
			db = new RoleEntity();
			db.setUid(uid);
			db.setCoin(GameGlobals.configManager.getInitGold());// 金币
			db.setRewardBuleCash(GameGlobals.configManager.getInitCash());// 初始钻石
			db.setChargeCash(0);// 充值钻石
			db.setPfId(msg.getUserId());
			db.setLastLoginTime(nowMill);
			db.setCreateTime(nowMill);
			db.setAvatar(msg.getAvatar());
			db.setSex(msg.getSex());
			db.setShowId(GameGlobals.redisManager.getIncreaseShowId());
			db.setPower(getPvePowerLimit(uid));
			db.setResetTime(0);// 初始为0, 登录时触发一次dailyReset
			db.setMonthCardEndTime(0L);// 月卡结束时间
			db.setMonthCardLastRewardTime(0L);// 月卡奖励最后发放时间
			String name = msg.getNickname();
			if (name != null) {
				name = filterEmoji(name);
			} else {
				name = "";
			}
			db.setName(name);
			db.setCreateTime(nowMill);
			if (Globals.getEntityProxy().insert(db)) {
				afterInitRole(uid, db);
				return db;
			}

			// 重试一次数据存储
			log.error("init role error msg.getNickname():" + msg.getNickname());
			log.error("init role error name:" + name);

			if (Globals.getEntityProxy().insert(db)) {
				afterInitRole(uid, db);
				log.error("init role success:");
				return db;
			}
			return null;
		} finally {
			lock.unlock();
		}
	}

	public RoleBattleEntity initRoleBattle(long uid) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleBattleEntity battleEntity = getRoleBattleEntity(uid);
			if (battleEntity != null) {
				return battleEntity;
			}
			battleEntity = new RoleBattleEntity();
			battleEntity.setId(uid);
			// 默认选择道具，PVE
			for (int itemId : GameGlobals.itemManager.getPrePveBattleItemIdSet()) {
				battleEntity.getPveItemIdList().add(itemId);
			}
			// 默认选择道具，普通副本
			for (int itemId : GameGlobals.itemManager.getPreDupBattleItemIdSet()) {
				battleEntity.getDupItemIdList().add(itemId);
			}
			// 默认选择道具，精英副本
			for (int itemId : GameGlobals.itemManager.getPreEliteBattleItemIdSet()) {
				battleEntity.getEliteItemIdList().add(itemId);
			}
			List<Integer> list = GameGlobals.configManager.getInitHeroList();
			for (Integer tempCode : list) {
				GameGlobals.heroManager.addHeroInit(uid, tempCode);
				//初始化玩家PVE默认的英雄
				battleEntity.setHeroId(tempCode);
			}
			Globals.getEntityProxy().insert(battleEntity);
			return battleEntity;
		} finally {
			lock.unlock();
		}
	}

	public String filterEmoji(String nname) {
		if (nname == null) {
			return "";
		}
		nname = "" + nname.replaceAll("[\\x{10000}-\\x{10FFFF}]", "");
		nname = EmojiUtil.filterEmoji(nname);
		return nname;
	}

	private void afterInitRole(long uid, RoleEntity db) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			// 玩家展示 初始化插入
			ShowIdEntity showIdEntity = new ShowIdEntity();
			showIdEntity.setShowId(db.getShowId());
			showIdEntity.setTargetUid(db.getUid());
			Globals.getEntityProxy().insert(showIdEntity);

			// 初始化玩家的战斗页面信息
			this.initRoleBattle(uid);
			// 初始化主页信息
			GameGlobals.rolehHomepageManager.OnInitRole(uid);

			// 初始化一个改名卡
			GameGlobals.bagManager.addItem(uid, EItemType.ITEM.getType(), EItemId.CHANGE_NAME_CARD.getType(), 1, LogfConstants.CHANNEL_REGISTER,
					"role_register");
		} finally {
			lock.unlock();
		}
	}

	public RoleEntity getRoleEntity(long uid) {
		if (uid == 0) {
			return null;
		}
		return Globals.getEntityProxy().get(RoleEntity.class, uid);
	}

	public RoleBattleEntity getRoleBattleEntity(long uid) {
		return Globals.getEntityProxy().get(RoleBattleEntity.class, uid);
	}

	public RoleInfo remoteGetRoleInfo(long uid) {
		RoleInfo info = new RoleInfo();
		if (Globals.getUserManager().isThisServer(uid)) {
			info = getRoleInfo(uid);
		} else {
			IRoleService service = RpcManager.getRpcImplBySid(Globals.getUserManager().getSid(uid), IRoleService.class);
			service.getRoleInfo(uid);
		}
		return info;
	}

	public RoleInfo getRoleInfo(long uid) {
		RoleEntity role = getRoleEntity(uid);
		RoleInfo info = new RoleInfo();
		info.setRole(role);
		boolean pvpOpen = GameGlobals.functionOpenManager.onCheck(uid, ISystemId.SYSTEM_RANKING);
		info.setOpenPvp(pvpOpen);
		boolean ladderOpen = GameGlobals.functionOpenManager.onCheck(uid, ISystemId.SYSTEM_LADDER);
		info.setOpenLadder(ladderOpen);
		List<HeroEntity> heros = GameGlobals.heroManager.getDBList(uid);
		for (HeroEntity entity : heros) {
			info.addHero(entity.getHeroId());
		}
		PveCityEntity pveEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
		if (pveEntity != null) {
			info.setPveTargetId(pveEntity.getCurPveTarget());
		}
		return info;
	}

	public void updateRoleChannelAndPlatform(long uid, int channel, int platform) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role != null) {
				role.setChannel(channel);
				role.setPlatform(platform);
				Globals.getEntityProxy().updateAsync(role);
			}
		} finally {
			lock.unlock();
		}
	}

	public int getAllBlueCash(RoleEntity role) {
		return role.getRewardBuleCash() + role.getChargeCash();
	}

	public int battleEndUpdateBattleCount(long uid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role != null) {
				role.setBattleCount(role.getBattleCount() + 1);
				Globals.getEntityProxy().updateAsync(role);
			}
			return role.getBattleCount();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 玩家角色每日重置
	 * 
	 * @param userBean
	 */
	public void dailyReset(UserBean userBean) {
		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			long uid = userBean.getUid();
			long now = System.currentTimeMillis();
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (TimeUtils.isToday(role.getResetTime())) {
				return;
			}

			/* 每日0点重置 */

			// 更新每日重置时间
			role.setResetTime(now);
			// 每日重置玩家数据
			resetRoleEntityDaily(role);

			// 月卡奖励邮件
			GameGlobals.monthCardManager.checkSendMonthCardAReward(role);

			// 重置免费道具
			GameGlobals.itemManager.resetFreeItem(uid);

			// 重置玩家活动信息
			GameGlobals.roleActivityManager.resetActivityInfo(uid);

			// 重置每日好友点赞数据
			GameGlobals.friendManager.resetFriendsFabulousData(uid);

			// FIXME 重置精英副本信息(暂时关闭)
//			GameGlobals.eliteManager.reset(uid);

			// 累计登录成就
			GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_LOGIN, 1);
			GameGlobals.taskManager.onTask(uid, DayTaskConstants.TYPE_LOGIN, 1);

			// 每日红点检测(放在所有检测之后)
			GameGlobals.redHatManager.check(uid);
		} finally {
			lock.unlock();
		}
	}

	public String kickUser(long uid) {
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			// 没有玩家数据
			return CMDConstants.REASON_NO_ROLE;
		}

		UserBean userBean = Globals.getUserManager().getUserBean(uid);
		if (userBean == null) {
			// 玩家已不在线
			return CMDConstants.STATE_SUCCESS;
		}

		GameSession gameSession = Globals.getChatSessionManager().getSession(uid);
		if (gameSession != null) {
			// 通知玩家被踢下线
			gameSession.sendMsg(GCKickOfflineProto.newBuilder());
		}

		// 玩家下线
		Globals.getUserManager().offline(uid);

		return CMDConstants.STATE_SUCCESS;
	}

	@Override
	public Builder<?> buildErrorMessage(int errorStatus) {
		GCHttpBeatMsg.GCHttpBeatProto.Builder gcMsg = GCHttpBeatMsg.GCHttpBeatProto.newBuilder();
		gcMsg.setStatus(errorStatus);
		return gcMsg;
	}

	@Override
	public void packCommonMessage(MsgBack msgBack, UserBean userBean) {
		// 玩家货币变化
		if (userBean.getResourceChanged()) {
			RoleEntity role = this.getRoleEntity(userBean.getUid());
			if (role != null) {
				GCCommonResponseMsg.GCCommonResponseProto.Builder builder = GCCommonResponseMsg.GCCommonResponseProto.newBuilder();
				builder.setHomeMoney(role.getCoin());
				builder.setBlueCash(this.getAllBlueCash(role));
				builder.setRewardBlueCash(role.getRewardBuleCash());
				builder.setDupStar(role.getDupStar());
				builder.setStatus(ErrorCodeConst.SUCCESS);
				msgBack.addBuilder(builder);
			}
			userBean.setResourceChanged(false);
		}

		// 检测系统开启变化
		if (userBean.isFunctionOpen()) {
			GCFunctionOpenInfoProto.Builder openFunctionBuilder = GameGlobals.functionOpenManager.getOpenFunctionBuilder(userBean);
			if (openFunctionBuilder != null) {
				msgBack.addBuilder(openFunctionBuilder);
			}
		}

		// 检测红点变化
		if (userBean.isRedHatChanged()) {
			GCGetRedHatinfoProto.Builder redHatInfoBuilder = GameGlobals.redHatManager.getRedHatInfoBuilder(userBean);
			if (redHatInfoBuilder != null) {
				msgBack.addBuilder(redHatInfoBuilder);
			}
		}

		// 检测体力变化
		if (userBean.getPowerChanged()) {
			GameGlobals.roleManager.packGCPowerGetInfoProto(msgBack, userBean.getUid());
			userBean.setPowerChanged(false);
		}

		// 推送最新的活动信息
		if (userBean.isActivityChanged()) {
			GameGlobals.roleActivityManager.CGActivityInfoProto(msgBack, userBean);
			userBean.setActivityChanged(false);
		}

		// 推送最新的英雄列表
		if (userBean.isHeroChanged()) {
			GameGlobals.heroManager.CGGetHeroList(msgBack, userBean);
			userBean.setHeroChanged(false);
		}
	}

	@Override
	public void saveRoleData(long uid) {
		Globals.getEntityProxy().updatePlayerData(uid);
	}

	@Override
	public void remoteSaveRoleData(int sid, long uid) {
		if (sid == Globals.getAppConfigBean().getSid()) {
			saveRoleData(uid);
			return;
		}

		IRoleService service = RpcManager.getRpcImplBySid(sid, IRoleService.class);
		if (service != null) {
			service.remoteSaveRoleData(uid);
		}
	}

	@Override
	public void onLogout(UserBean userBean) {
		Globals.applicationContext.publishEvent(new LogoutEvent(userBean));
	}

	@Override
	public void onShutdown() {
		// 更新user离线状态(Redis)
		Globals.getUserManager().onShutdown();

		// 存储数据
		Globals.getEntityProxy().updateAllData();
	}

	public void resetRoleEntityDaily(RoleEntity role) {
		if (role == null) {
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(role.getUid());
		lock.lock();
		try {
			role.setPveConWinNum(0);
			role.setMonthCardBNoLimitReceiveNum(0);
			role.setDrawLuckNum(0);
			Globals.getEntityProxy().updateAsync(role);
		} finally {
			lock.unlock();
		}
	}

	public int getPvePowerLimit(long uid) {
		int heroPowerLimit = 0;
		int petPowerLimit = 0;
		RoleEntity roleEntity = getRoleEntity(uid);
		int powerLimit = GameGlobals.configManager.getPowerUpperLimit();
		if (GameGlobals.monthCardManager.checkInMonthCardATime(roleEntity)) {
			powerLimit += GameGlobals.configManager.getMonthCardBPveNumAdd();
		}

		if (roleEntity != null) {
			heroPowerLimit = roleEntity.getHeroPowerLimit();
			petPowerLimit = roleEntity.getPetPowerLimit();
		}

		return powerLimit + heroPowerLimit + petPowerLimit;
	}

	/**
	 * 加满体力
	 * 
	 * @param role
	 */
	public void addPowerFull(RoleEntity role) {
		int max = getPvePowerLimit(role.getUid());
		if (role.getPower() >= max) {
			return;
		}

		GameGlobals.roleManager.addPower(EPowerChannel.BUY, role.getUid(), max - role.getPower());
	}

	/**
	 * 增加体力
	 * 
	 * @param channel
	 * @param uid
	 * @param val
	 */
	public void addPower(EPowerChannel channel, long uid, int val) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = this.getRoleEntity(uid);
			int oldPower = role.getPower();
			if (channel.canOverLimit()) {
				role.setPower(oldPower + val);
				Globals.getEntityProxy().updateAsync(role);
				return;
			} else {
				int maxPower = getPvePowerLimit(uid);
				if (oldPower >= maxPower) {
					return;
				}

				role.setPower(Math.min(oldPower + val, maxPower));
				Globals.getEntityProxy().updateAsync(role);
			}
		} finally {
			lock.unlock();
		}
	}

	public int checkPvePower(long uid) {
		RoleEntity role = getRoleEntity(uid);
		if (null == role) {
			return ErrorCodeConst.ROLE_NOT_EXIST;
		}

		if (GameGlobals.roleManager.inNoLimitPowerTime(uid)) {
			return ErrorCodeConst.SUCCESS;
		}
		if (role.getPower() <= 0) {
			if (GameGlobals.itemManager.checkHavePowerDrug(uid)) {
				return ErrorCodeConst.PVE_GOTO_BAG_USE_POWER_DRUG;
			} else {
				return ErrorCodeConst.PVE_COST_CASH_BUY_POWER;
			}
		}
		return ErrorCodeConst.SUCCESS;
	}

	public boolean reducePower(long uid, int val) {
		boolean result = true;
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = this.getRoleEntity(uid);
			int maxPower = getPvePowerLimit(uid);
			if (role.getPower() >= maxPower) {
				role.setLastUpdatePowerTime(System.currentTimeMillis() / 1000);
			}
			if (role.getPower() >= val) {
				role.setPower(role.getPower() - val);
			} else {
				result = false;
			}
			Globals.getEntityProxy().updateAsync(role);
		} finally {
			lock.unlock();
		}
		return result;
	}

	public EPowerChannel powerChannel(String channel) {
		switch (channel) {
		case LogfConstants.CHANNEL_BATTLE:
			return EPowerChannel.BATTLE;
		case LogfConstants.CHANNEL_POWER_AUTO:
			return EPowerChannel.AUTO;
		case LogfConstants.CHANNEL_FRIENDS_FABULOUS:
			return EPowerChannel.FRIENDFABULOUS;
		case LogfConstants.CHANNEL_CHANGE_POWER_BUY:
			return EPowerChannel.BUY;
		default:
			return EPowerChannel.OTHER;
		}
	}

	public boolean inNoLimitPowerTime(long uid) {
		RoleEntity role = this.getRoleEntity(uid);
		if (null == role) {
			return false;
		}
		return role.getNoLimitPowerEndTime() > System.currentTimeMillis() / 1000;
	}

	/**
	 * 更新无限体力时间
	 * 
	 * @param uid
	 * @param val
	 * @return
	 */
	public long updateRoleNoLimitPowerTime(long uid, int val) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = this.getRoleEntity(uid);
			long oldTime = role.getNoLimitPowerEndTime();
			long currTime = System.currentTimeMillis() / 1000;
			if (oldTime > currTime) {
				role.setNoLimitPowerEndTime(oldTime + val);
			} else {
				role.setNoLimitPowerEndTime(currTime + val);
			}
			Globals.getEntityProxy().updateAsync(role);
			return role.getNoLimitPowerEndTime();
		} finally {
			lock.unlock();
		}
	}

	public void updateRolePower(long uid, long currTime) {
		RoleEntity role = this.getRoleEntity(uid);
		if (role == null) {
			Loggers.serverLogger.error("No RoleEntity! uid = " + uid);
			return;
		}

		if (role.getPower() >= getPvePowerLimit(uid)) {
			// 体力已满, 不刷新体力更新时间, 在体力消耗的地方更新
//			role.setLastUpdatePowerTime(currTime);
//			Globals.getEntityProxy().update(role);
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			long lastUpdatePower = role.getLastUpdatePowerTime();
			if (lastUpdatePower == 0) {
				role.setLastUpdatePowerTime(currTime);
				Globals.getEntityProxy().updateAsync(role);
				return;
			}

			int basePowerUpdateTime = GameGlobals.configManager.getPowerRecoveryTime() * 60;
			//月卡B有加成
			if (GameGlobals.monthCardManager.checkInMonthCardBTime(uid)) {
				basePowerUpdateTime = (int) (basePowerUpdateTime / GameGlobals.configManager.getMonthCardBPowerTimeAdd());
			}
			if (currTime - lastUpdatePower < basePowerUpdateTime) {
				return;
			}

			int add = (int) ((currTime - lastUpdatePower) / (basePowerUpdateTime));
			if (add > 0) {
				int prePower = role.getPower();
				addPower(EPowerChannel.AUTO, uid, add);
				if (role.getPower() < getPvePowerLimit(uid)) {
					// 如果体力未满, 按照实际加的体力数量, 增加更新时间
					role.setLastUpdatePowerTime(lastUpdatePower + (role.getPower() - prePower) * basePowerUpdateTime);
				} else {
					// 体力满了, 时间是否更新都无所谓, 消耗的时候会更新
					role.setLastUpdatePowerTime(currTime);
				}

				Globals.getEntityProxy().updateAsync(role);
			}
		} finally {
			lock.unlock();
		}
	}

	public void packGCPowerGetInfoProto(MsgBack back, long uid) {
		GCPowerGetInfoProto.Builder builder = GCPowerGetInfoProto.newBuilder();
		back.addBuilder(builder);
		RoleEntity role = this.getRoleEntity(uid);
		if (role == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}
		// 更新体力
		this.updateRolePower(uid, System.currentTimeMillis() / 1000);

		int updatePowerTime = (int) (System.currentTimeMillis() / 1000 - role.getLastUpdatePowerTime());
		builder.setUpdatePowerTime(GameGlobals.configManager.getPowerRecoveryTime() * 60 - updatePowerTime);
		if (role.getPower() >= getPvePowerLimit(uid)) {
			builder.setUpdatePowerTime(0);
		}
		if (role.getNoLimitPowerEndTime() - System.currentTimeMillis() / 1000 > 0) {
			builder.setInfinitePowerEndTime((int) (role.getNoLimitPowerEndTime() - System.currentTimeMillis() / 1000));
		}
		builder.setStatus(ErrorCodeConst.SUCCESS);
		builder.setPower(role.getPower());
	}

	private int checkRolePveTaskId(long uid) {
		int nextTargetId = RoleConstants.PVE_FIRST_STAGE_ID;
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			PveCityEntity pveEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
			if (pveEntity == null) {
				return nextTargetId;
			}
			nextTargetId = pveEntity.getTargetId();
			if (nextTargetId == 0) {
				nextTargetId = GameGlobals.pveCityManager.getPveCurTarget(uid);
			}
			if (pveEntity.getTargetId() == -1) {
				nextTargetId = GameGlobals.pveCityManager.getPveNextTarget(uid);//;GameGlobals.targetManager.getNextTargetId(role.getPveLastTaskId());
				if (nextTargetId != -1) {
					pveEntity.setTargetId(nextTargetId);
					Globals.getEntityProxy().updateAsync(pveEntity);
				}
			}
			return nextTargetId;
		} finally {
			lock.unlock();
		}
	}
}
