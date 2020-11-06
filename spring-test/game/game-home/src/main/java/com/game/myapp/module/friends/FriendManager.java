package com.game.myapp.module.friends;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.FriendAddLog;
import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.core.handler.MsgBack;
import com.game.core.hibernate.key.LongLongPrimaryKey;
import com.game.db.entity.FriendreqEntity;
import com.game.db.entity.FriendsEntity;
import com.game.db.entity.FriendsFabulousEntity;
import com.game.db.entity.LatelyfriendEntity;
import com.game.db.entity.PveCityEntity;
import com.game.db.entity.RoleEntity;
import com.game.db.entity.ShowIdEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.function.ISystemId;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IFriendService;
import com.game.rpc.service.IRoleService;
import com.game.rpc.vo.FriendRankInfo;
import com.game.rpc.vo.RoleInfo;
import com.game.rpc.vo.RoleStatus;
import com.game.utils.DateUtil;
import com.game.vo.LatyFriendVO;

import buffer.CGDeleteFriendMsg;
import buffer.CGFriendFabulousMsg.CGFriendFabulousProto;
import buffer.CGFriendGetInfoByShowIdMsg.CGFriendGetInfoByShowIdProto;
import buffer.CGFriendsListMsg;
import buffer.CGFriendsRankInfoMsg.CGFriendsRankInfoProto;
import buffer.CGReceiveFriendShipMsg.CGReceiveFriendShipProto;
import buffer.CGSendFriendShipMsg.CGSendFriendShipProto;
import buffer.GCDeleteFriendMsg;
import buffer.GCDoFriendReqMsg;
import buffer.GCFriendGetInfoByShowIdMsg.FriendsHeroInfoProto;
import buffer.GCFriendGetInfoByShowIdMsg.GCFriendGetInfoByShowIdProto;
import buffer.GCFriendReqListMsg;
import buffer.GCFriendsListMsg;
import buffer.GCFriendsListMsg.FriendGCFriendsList;
import buffer.GCFriendsListMsg.GCFriendsListProto;
import buffer.GCFriendsRankInfoMsg.FriendsRankInfoProto;
import buffer.GCFriendsRankInfoMsg.GCFriendsRankInfoProto;
import buffer.GCReceiveFriendShipMsg;
import buffer.GCSendFriendReqMsg;
import buffer.GCSendFriendShipMsg;

public class FriendManager {
	public static final int LIST_TYPE_FRIEND = 1;
	public static final int LIST_TYPE_LATELY = 2;

	private FriendsFabulousService fabulousService = new FriendsFabulousService();

	/**
	 * 获取好友列表
	 * 
	 * @param msgBack
	 * 
	 * @param userBean
	 * @param cgMsg
	 * @return
	 */
	public void CGFriendsListMsg(MsgBack msgBack, UserBean userBean, CGFriendsListMsg.CGFriendsListProto cgMsg) {
		int reqType = cgMsg.getResource();
		List<FriendsEntity> list = Globals.getEntityProxy().findAllByUid(FriendsEntity.class, userBean.getUid());
		GCFriendsListMsg.GCFriendsListProto.Builder gcMsg = GCFriendsListMsg.GCFriendsListProto.newBuilder();

		List<Long> uidList = new ArrayList<Long>();
		List<FriendInfo> friends = new ArrayList<FriendInfo>();

		for (FriendsEntity temp : list) {
			FriendInfo info = new FriendInfo();
			info.setDb(temp);
			info.setTimeMill(temp.getOffMill());
			friends.add(info);
			uidList.add(temp.getFriendUid());
		}

		Map<Long, RoleStatus> statusMap = getRoleStatus(uidList);
		// 封装好友信息
		for (FriendInfo temp : friends) {
			GCFriendsListMsg.FriendGCFriendsList.Builder tempFriend = GCFriendsListMsg.FriendGCFriendsList.newBuilder();
			long friendUid = temp.getDb().getFriendUid();
			RoleInfo roleInfo = GameGlobals.roleManager.remoteGetRoleInfo(friendUid);
			if (roleInfo == null) {
				continue;
			}

			RoleStatus status = statusMap.get(temp.getDb().getFriendUid());
			// 天梯验证
			if (reqType == 2) {
				// 不在线的玩家不显示
				if (status.getStatus() == 0) {
					continue;
				}
				if (!roleInfo.isOpenLadder()) {
					continue;
				}
			}
			tempFriend.setAvatar(roleInfo.getRole().getAvatar());
			tempFriend.setFriendId(temp.getDb().getFriendUid());
			tempFriend.setNickName(temp.getDb().getName());
			tempFriend.setSex(temp.getDb().getSex());

			if (status != null) {
				tempFriend.setGameStatus(status.getStatus());
				tempFriend.setOffMill(status.getOffMill());
			}

			if (temp.getDb().getDayReceive() == DateUtil.getDay(System.currentTimeMillis())) {
				tempFriend.setCanReceiveFriendShip(temp.getDb().getShipStatus());
			}

			tempFriend.setHasSendFriendShip(temp.getDb().getDaySend() == DateUtil.getDay(System.currentTimeMillis()) ? 2 : 1);

			tempFriend.setShowId(roleInfo.getRole().getShowId());
			gcMsg.addFriends(tempFriend);
		}

		// 封装推荐好友
		packRecommentFriendsInfo(gcMsg, userBean.getUid(), reqType);

		// 点赞相关数据
		this.fabulousService.fabulous(userBean.getUid(), gcMsg);

		gcMsg.setType(cgMsg.getType());
		msgBack.addBuilder(gcMsg);
	}

	public void CGFriendReqListMsg(MsgBack msgBack, UserBean userBean, buffer.CGFriendReqListMsg.CGFriendReqListProto cgMsg) {
		List<FriendreqEntity> list = Globals.getEntityProxy().findAllByUid(FriendreqEntity.class, userBean.getUid());
		GCFriendReqListMsg.GCFriendReqListProto.Builder gcMsg = GCFriendReqListMsg.GCFriendReqListProto.newBuilder();

		List<FriendReqInfo> friends = new ArrayList<FriendReqInfo>();
		for (FriendreqEntity temp : list) {
			FriendReqInfo info = new FriendReqInfo();
			info.setDb(temp);
			info.setTimeMill(temp.getCreateTime());
			friends.add(info);
		}
		Collections.sort(friends);

		for (FriendReqInfo temp : friends) {
			GCFriendReqListMsg.FriendReqGCFriendReqList.Builder tempReq = GCFriendReqListMsg.FriendReqGCFriendReqList.newBuilder();
			tempReq.setAvatar(temp.getDb().getAvatar() == null ? "" : temp.getDb().getAvatar());
			tempReq.setFriendId(temp.getDb().getFriendUid());
			tempReq.setNickName(temp.getDb().getName());
			tempReq.setVip(temp.getDb().getVip());
			tempReq.setSex(temp.getDb().getSex());
			gcMsg.addFriends(tempReq);
		}
		msgBack.addBuilder(gcMsg);
	}

	public void CGDoFriendReqMsg(MsgBack msgBack, UserBean userBean, buffer.CGDoFriendReqMsg.CGDoFriendReqProto cgMsg) {
		GCDoFriendReqMsg.GCDoFriendReqProto.Builder gcMsg = GCDoFriendReqMsg.GCDoFriendReqProto.newBuilder();
		msgBack.addBuilder(gcMsg);

		boolean has = false;
		List<FriendreqEntity> list = Globals.getEntityProxy().findAllByUid(FriendreqEntity.class, userBean.getUid());
		Iterator<FriendreqEntity> it = list.iterator();
		while (it.hasNext()) {
			FriendreqEntity temp = it.next();
			if (temp.getFriendUid() == cgMsg.getUid()) {
				it.remove();
				Globals.getEntityProxy().delete(temp);
				has = true;
			}
		}

		if (!has) {
			gcMsg.setStatus(ErrorCodeConst.FRIEND_IS_EXIST);
			GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.FRIEND_INVATATION);
			return;
		}

		List<FriendsEntity> friends = Globals.getEntityProxy().findAllByUid(FriendsEntity.class, userBean.getUid());
		if (friends.size() >= GameGlobals.configManager.getFriendSize()) {
			gcMsg.setStatus(ErrorCodeConst.MYSELF_FRINEDS_IS_FULL);
			GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.FRIEND_INVATATION);
			return;
		}

		List<FriendsEntity> tempList = Globals.getEntityProxy().findAllByUid(FriendsEntity.class, cgMsg.getUid());
		if (tempList.size() >= GameGlobals.configManager.getFriendSize()) {
			gcMsg.setStatus(ErrorCodeConst.OTHER_FRINEDS_IS_FULL);
			GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.FRIEND_INVATATION);
			return;
		}

		if (cgMsg.getType() == 1) {
			addFriend(userBean.getUid(), cgMsg.getUid());

			// 检测红点
			GameGlobals.redHatManager.checkBehaviorRedHat(userBean.getUid(), RedHatBehavior.FRIEND_INVATATION);
		}
		gcMsg.setStatus(ErrorCodeConst.SUCCESS);
	}

	public void CGSendFriendReqMsg(MsgBack msgBack, UserBean userBean, buffer.CGSendFriendReqMsg.CGSendFriendReqProto cgMsg) {
		GCSendFriendReqMsg.GCSendFriendReqProto.Builder gcMsg = GCSendFriendReqMsg.GCSendFriendReqProto.newBuilder();
		msgBack.addBuilder(gcMsg);
		FriendsEntity friendDB = GameGlobals.friendManager.getFriendEntity(userBean.getUid(), cgMsg.getUid());
		if (friendDB != null) {
			gcMsg.setStatus(ErrorCodeConst.FRIEND_IS_EXIST);
			return;
		}
		List<FriendsEntity> friends = Globals.getEntityProxy().findAllByUid(FriendsEntity.class, userBean.getUid());

		if (friends.size() >= GameGlobals.configManager.getFriendSize()) {
			gcMsg.setStatus(ErrorCodeConst.MYSELF_FRINEDS_IS_FULL);
			return;
		}

		List<FriendsEntity> other = Globals.getEntityProxy().findAllByUid(FriendsEntity.class, cgMsg.getUid());
		if (other.size() >= GameGlobals.configManager.getFriendSize()) {
			gcMsg.setStatus(ErrorCodeConst.OTHER_FRINEDS_IS_FULL);
			return;
		}

		RoleEntity role = GameGlobals.roleManager.getRoleEntity(userBean.getUid());
		if (Globals.getUserManager().isThisServer(cgMsg.getUid())) {
			this.insertFriendreqEntity(cgMsg.getUid(), userBean.getUid(), role.getAvatar(), role.getName(), role.getSex(), 0, cgMsg.getDeclaration());
			return;
		}

		// 远程调用插入好友申请数据
		IFriendService friendService = RpcManager.getRpcImplBySid(Globals.getUserManager().getSid(cgMsg.getUid()), IFriendService.class);
		friendService.insertFriendreqEntity(cgMsg.getUid(), userBean.getUid(), role.getAvatar(), role.getName(), role.getSex(), 0, cgMsg.getDeclaration());
	}

	public void CGDeleteFriendMsg(MsgBack msgBack, UserBean userBean, CGDeleteFriendMsg.CGDeleteFriendProto cgMsg) {
		GCDeleteFriendMsg.GCDeleteFriendProto.Builder gcMsg = GCDeleteFriendMsg.GCDeleteFriendProto.newBuilder();
		msgBack.addBuilder(gcMsg);

		FriendsEntity db = this.getFriendEntity(userBean.getUid(), cgMsg.getUid());
		if (db != null) {
			Globals.getEntityProxy().delete(db);
		}

		if (Globals.getUserManager().isThisServer(cgMsg.getUid())) {
			deleteFriendsEntity(cgMsg.getUid(), userBean.getUid());
			return;
		}

		IFriendService friendService = RpcManager.getRpcImplBySid(Globals.getUserManager().getSid(cgMsg.getUid()), IFriendService.class);
		friendService.deleteFriendsEntity(cgMsg.getUid(), userBean.getUid());
	}

	public void CGSendFriendShipMsg(MsgBack msgBack, UserBean userBean, CGSendFriendShipProto cgMsg) {
		GCSendFriendShipMsg.GCSendFriendShipProto.Builder gcMsg = GCSendFriendShipMsg.GCSendFriendShipProto.newBuilder();
		msgBack.addBuilder(gcMsg);

		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			FriendsEntity db = this.getFriendEntity(userBean.getUid(), cgMsg.getTargetUid());
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(userBean.getUid());
			if (db == null) {
				gcMsg.setStatus(1);
				return;
			}
			if (db.getDaySend() == DateUtil.getDay(System.currentTimeMillis())) {
				gcMsg.setStatus(1);
				return;
			}
			if (role.getSendFriendShipTimes() >= GameGlobals.configManager.getSendFriendShipLimit()) {
				gcMsg.setStatus(1);
				return;
			}

			db.setDaySend(DateUtil.getDay(System.currentTimeMillis()));
			role.setSendFriendShipTimes(role.getSendFriendShipTimes() + 1);

			Globals.getEntityProxy().updateAsync(role);
			Globals.getEntityProxy().updateAsync(db);

			if (Globals.getUserManager().isThisServer(cgMsg.getTargetUid())) {
				addShip(cgMsg.getTargetUid(), role.getUid());
				return;
			} else {
				IFriendService friendService = RpcManager.getRpcImplBySid(Globals.getUserManager().getSid(cgMsg.getTargetUid()), IFriendService.class);
				friendService.addShip(cgMsg.getTargetUid(), role.getUid());
			}
		} finally {
			lock.unlock();
		}
	}

	public void CGReceiveFriendShipMsg(MsgBack msgBack, UserBean userBean, CGReceiveFriendShipProto cgMsg) {
		GCReceiveFriendShipMsg.GCReceiveFriendShipProto.Builder gcMsg = GCReceiveFriendShipMsg.GCReceiveFriendShipProto.newBuilder();
		msgBack.addBuilder(gcMsg);

		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			FriendsEntity db = this.getFriendEntity(userBean.getUid(), cgMsg.getTargetUid());
			if (db == null) {
				gcMsg.setStatus(1);
				return;
			}
			if (db.getDayReceive() != DateUtil.getDay(System.currentTimeMillis())) {
				gcMsg.setStatus(1);
				return;
			}
			if (db.getShipStatus() != 1) {
				gcMsg.setStatus(1);
				return;
			}
			db.setShipStatus(2);
			Globals.getEntityProxy().updateAsync(db);

			RoleEntity role = GameGlobals.roleManager.getRoleEntity(userBean.getUid());

			role.setFriendShip(role.getFriendShip() + 1);
			Globals.getEntityProxy().updateAsync(role);

			gcMsg.setCanReceiveFriendShip(2);
			gcMsg.setFriendShip(role.getFriendShip());
			gcMsg.setHasSendFriendShip(db.getDaySend() == DateUtil.getDay(System.currentTimeMillis()) ? 2 : 1);
			gcMsg.setTargetUid(cgMsg.getTargetUid());
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 排行信息
	 * 
	 * @param msgBack
	 * 
	 * @param userBean
	 * @param msg
	 * @return
	 */
	public void CGFriendsRankInfo(MsgBack msgBack, UserBean userBean, CGFriendsRankInfoProto msg) {
		GCFriendsRankInfoProto.Builder builder = GCFriendsRankInfoProto.newBuilder();
		msgBack.addBuilder(builder);
		List<FriendsEntity> friendsEntities = Globals.getEntityProxy().findAllByUid(FriendsEntity.class, userBean.getUid());

		// 把自己加到列表里面去
		FriendsEntity owner = new FriendsEntity();
		owner.setFriendUid(userBean.getUid());
		friendsEntities.add(owner);

		for (FriendsEntity entity : friendsEntities) {
			long friendUid = entity.getFriendUid();
			FriendRankInfo friendRankInfo = this.getFriendRankInfoByUid(friendUid);
			if (friendRankInfo == null) {
				continue;
			}
			FriendsRankInfoProto.Builder rankInfo = FriendsRankInfoProto.newBuilder();
			rankInfo.setHeader(friendRankInfo.getHeader());
			rankInfo.setName(friendRankInfo.getName());
			rankInfo.setPveTaskId(friendRankInfo.getPveTaskId());
			rankInfo.setRoleLevel(friendRankInfo.getLevel());
			rankInfo.setUid(friendUid);
			builder.addRankInfos(rankInfo);
		}
		builder.setStatus(ErrorCodeConst.SUCCESS);
	}

	/**
	 * 点赞
	 * 
	 * @param msgBack
	 * 
	 * @param userBean
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public void CGFriendFabulous(MsgBack msgBack, UserBean userBean, CGFriendFabulousProto msg) {
		this.fabulousService.CGFriendFabulous(msgBack, userBean, msg);
	}

	public void CGFriendGetInfoByShowId(MsgBack msgBack, UserBean userBean, CGFriendGetInfoByShowIdProto msg) {
		GCFriendGetInfoByShowIdProto.Builder builder = GCFriendGetInfoByShowIdProto.newBuilder();
		msgBack.addBuilder(builder);
		ShowIdEntity showIdEntity = Globals.getEntityProxy().get(ShowIdEntity.class, msg.getShowId());
		if (showIdEntity == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}
		RoleInfo roleInfo = GameGlobals.roleManager.remoteGetRoleInfo(showIdEntity.getTargetUid());
		if (roleInfo == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}
		FriendsHeroInfoProto.Builder hero = null;
		for (int heroId : roleInfo.getHeroCodes()) {
			hero = FriendsHeroInfoProto.newBuilder();
			hero.setHeroCode(heroId);
			builder.addHeroCodes(hero);
		}

		builder.setName(roleInfo.getRole().getName());
		builder.setHeader(roleInfo.getRole().getAvatar());
		//		builder.setPveTaskId(roleInfo.getRole().getPveTaskId());
		builder.setPveTaskId(roleInfo.getPveTargetId());
		builder.setUid(roleInfo.getRole().getUid());
		builder.setShowId(roleInfo.getRole().getShowId());

		GameGlobals.rolehHomepageManager.buildFavoriteHeroCodeAndPetCode(userBean.getUid(), builder);

		builder.setStatus(ErrorCodeConst.SUCCESS);
	}

	/**
	 * 封装推荐好友信息
	 * 
	 * @param gcMsg
	 * @param uid
	 */
	private void packRecommentFriendsInfo(GCFriendsListProto.Builder gcMsg, long uid, int reqType) {
		try {
			List<RecommonedFriendVo> recommentFriendList = this.getRecommentFrineds(uid, reqType);
			for (RecommonedFriendVo temp : recommentFriendList) {
				FriendGCFriendsList.Builder builder = FriendGCFriendsList.newBuilder();
				builder.setFriendId(temp.getUid());
				builder.setAvatar(temp.getAvatar());
				builder.setNickName(temp.getName());
				builder.setSex(temp.getSex());
				builder.setStep(temp.getDuan());
				builder.setShowId(temp.getShowId());
				gcMsg.addRecommentFriends(builder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<Long, RoleStatus> getRoleStatus(List<Long> uidList) {
//		Map<Integer, List<Long>> serverMap = new HashMap<Integer, List<Long>>();
//		Map<Long, RoleStatus> map = new HashMap<Long, RoleStatus>();
//		for (Long tempUid : uidList) {
//			int server = Globals.getUserManager().getSid(tempUid);
//			List<Long> list = serverMap.get(server);
//			if (list == null) {
//				list = new ArrayList<Long>();
//				serverMap.put(server, list);
//			}
//			list.add(tempUid);
//		}
//		for (Map.Entry<Integer, List<Long>> entry : serverMap.entrySet()) {
//			IOnlineService service = RpcManager.getRpcImplBySid(entry.getKey(), IOnlineService.class);
//			List<Long> rpcUidList = entry.getValue();
//			Map<Long, RoleStatus> statusMap = service.getStatus(rpcUidList);
//			// RPC远程调用出错，会导致返回的数据为null，再试一次
//			if (statusMap == null) {
//				statusMap = service.getStatus(rpcUidList);
//			}
//			if (statusMap != null && !statusMap.isEmpty()) {
//				map.putAll(statusMap);
//			}
//		}
//		return map;
		return new HashMap<Long, RoleStatus>();
	}

	public void getLatelyList(MsgBack msgBack, UserBean userBean, CGFriendsListMsg.CGFriendsListProto cgMsg) {
		List<LatelyfriendEntity> list = Globals.getEntityProxy().findAllByUid(LatelyfriendEntity.class, userBean.getUid());

		List<Long> uidList = new ArrayList<Long>();
		boolean needReload = false;
		for (LatelyfriendEntity temp : list) {
			uidList.add(temp.getFriendUid());
			if (!DateUtil.isToday(temp.getCreateTime())) {
				Globals.getEntityProxy().delete(temp);
				needReload = true;
			}
		}
		if (needReload) {
			list = Globals.getEntityProxy().findAllByUid(LatelyfriendEntity.class, userBean.getUid());
		}
		GCFriendsListMsg.GCFriendsListProto.Builder gcMsg = GCFriendsListMsg.GCFriendsListProto.newBuilder();
		Map<Long, RoleStatus> statusMap = getRoleStatus(uidList);

		List<LatelyInfo> friends = new ArrayList<LatelyInfo>();
		for (LatelyfriendEntity temp : list) {
			LatelyInfo info = new LatelyInfo();
			info.setDb(temp);
			info.setTimeMill(temp.getCreateTime());
			friends.add(info);
		}

		Collections.sort(friends);
		for (LatelyInfo temp : friends) {
			GCFriendsListMsg.FriendGCFriendsList.Builder tempFriend = GCFriendsListMsg.FriendGCFriendsList.newBuilder();
			tempFriend.setAvatar(temp.getDb().getAvatar() == null ? "" : temp.getDb().getAvatar());
			tempFriend.setFriendId(temp.getDb().getFriendUid());
			tempFriend.setNickName(temp.getDb().getName());
			tempFriend.setVip(temp.getDb().getVip());
			tempFriend.setSex(temp.getDb().getSex());
			RoleStatus status = statusMap.get(temp.getDb().getFriendUid());
			if (status != null) {
				tempFriend.setGameStatus(status.getStatus());
				tempFriend.setOffMill(status.getOffMill());
			}

			gcMsg.addFriends(tempFriend);
		}
		gcMsg.setType(cgMsg.getType());
		msgBack.addBuilder(gcMsg);
	}

	public boolean isFriend(long uid1, long uid2) {
		FriendsEntity friendEntity1 = getFriendEntity(uid1, uid2);
		FriendsEntity friendEntity2 = getFriendEntity(uid2, uid1);
		return friendEntity1 != null && friendEntity2 != null;
	}

	public void addFriend(long uid1, long uid2) {
		this.addFriendInfo(uid1, uid2);
		this.addFriendInfo(uid2, uid1);
	}

	public void addFriendInfo(long uid, long friendUid) {
		if (Globals.getUserManager().isThisServer(uid)) {
			insertFriendsEntity(uid, friendUid);
			return;
		}

		IFriendService service = RpcManager.getRpcImplBySid(Globals.getUserManager().getSid(uid), IFriendService.class);
		service.insertFriendsEntity(uid, friendUid);
	}

	private void insertFriendsEntity(long uid, long friendUid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			// 查询是否已经是好友
			LongLongPrimaryKey key = new LongLongPrimaryKey(uid, friendUid);
			FriendsEntity friendsEntity = Globals.getEntityProxy().get(FriendsEntity.class, key);
			if (friendsEntity != null) {
				// 已经是好友, 不做处理
				return;
			}

			RoleInfo roleInfo = GameGlobals.roleManager.remoteGetRoleInfo(friendUid);
			FriendsEntity friend = new FriendsEntity();
			friend.setUid(uid);
			friend.setFriendUid(friendUid);
			friend.setArenaStr("");
			friend.setArena(0);
			friend.setAvatar(roleInfo.getRole().getAvatar());
			friend.setCreateTime(System.currentTimeMillis());
			friend.setLevel(0);
			friend.setLoginMill(roleInfo.getRole().getLastLoginTime());
			friend.setName(roleInfo.getRole().getName());
			friend.setSex(roleInfo.getRole().getSex());
			// 保存好友关系
			Globals.getEntityProxy().insert(friend);
			// 添加日志
			LogfPrinter.getInstance().push(new FriendAddLog(Globals.getUserManager().getUserBean(uid), friendUid));
		} finally {
			lock.unlock();
		}
	}

	public void insertFriendreqEntity(long targetUid, long requestUid, String avatar, String name, int sex, int step, String declaration) {
		ReentrantLock lock = Globals.getLockManager().getLock(targetUid);
		lock.lock();
		try {
			FriendreqEntity friendreqEntity = Globals.getEntityProxy().get(FriendreqEntity.class, new LongLongPrimaryKey(targetUid, requestUid));
			if (friendreqEntity != null) {
				return;
			}

			friendreqEntity = new FriendreqEntity();
			friendreqEntity.setUid(targetUid);
			friendreqEntity.setFriendUid(requestUid);
			friendreqEntity.setArea("");
			friendreqEntity.setAvatar(avatar);
			friendreqEntity.setName(name);
			friendreqEntity.setSex(sex);
			friendreqEntity.setPoint(step);
			friendreqEntity.setDeclaration(declaration);
			friendreqEntity.setCreateTime(System.currentTimeMillis());
			friendreqEntity.setAreaNO(0);
			friendreqEntity.setVip(0);
			Globals.getEntityProxy().insert(friendreqEntity);
		} finally {
			lock.unlock();
		}

		// 检测好友申请红点
		GameGlobals.redHatManager.checkBehaviorRedHat(targetUid, RedHatBehavior.FRIEND_INVATATION);
	}

	public void addLatelyFriends(long uid, List<LatyFriendVO> latelyFriendList) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			Map<Long, FriendsEntity> map = this.getFriendEntityMap(uid);
			Map<Long, LatelyfriendEntity> latelyMap = this.getFriendLatelyMap(uid);
			for (LatyFriendVO temp : latelyFriendList) {
				if (map.get(temp.getLatelyId()) != null) {
					continue;
				}
				if (latelyMap.get(temp.getLatelyId()) != null) {
					LatelyfriendEntity db = latelyMap.get(temp.getLatelyId());
					db.setCreateTime(System.currentTimeMillis());
					Globals.getEntityProxy().updateAsync(db);
				} else {
					LatelyfriendEntity db = new LatelyfriendEntity();
					db.setUid(temp.getUid());
					db.setAvatar(temp.getAvatar());
					db.setCreateTime(System.currentTimeMillis());
					db.setFriendUid(temp.getLatelyId());
					db.setName(temp.getName());
					db.setSex(temp.getSex());
					db.setArenaStr(temp.getArena());
					Globals.getEntityProxy().insert(db);
				}
			}
		} finally {
			lock.unlock();
		}
	}

	private FriendsEntity getFriendEntity(long uid, long targetId) {
		return Globals.getEntityProxy().get(FriendsEntity.class, new LongLongPrimaryKey(uid, targetId));
	}

	private Map<Long, FriendsEntity> getFriendEntityMap(long uid) {
		Map<Long, FriendsEntity> map = new HashMap<Long, FriendsEntity>();
		List<FriendsEntity> list = Globals.getEntityProxy().findAllByUid(FriendsEntity.class, uid);
		for (FriendsEntity temp : list) {
			map.put(temp.getFriendUid(), temp);
		}
		return map;
	}

	private Map<Long, LatelyfriendEntity> getFriendLatelyMap(long uid) {
		Map<Long, LatelyfriendEntity> map = new HashMap<Long, LatelyfriendEntity>();
		List<LatelyfriendEntity> list = Globals.getEntityProxy().findAllByUid(LatelyfriendEntity.class, uid);
		for (LatelyfriendEntity temp : list) {
			map.put(temp.getFriendUid(), temp);
		}
		return map;
	}

	public void deleteFriendsEntity(long uid, long uid2) {
		FriendsEntity friendDB = this.getFriendEntity(uid, uid2);
		if (friendDB != null) {
			Globals.getEntityProxy().delete(friendDB);
		}
	}

	public void addShip(long targetUid, long sendUid) {
		ReentrantLock lock = Globals.getLockManager().getLock(targetUid);
		lock.lock();
		try {
			FriendsEntity db = this.getFriendEntity(targetUid, sendUid);
			if (db == null) {
				return;
			}
			if (db.getDayReceive() == DateUtil.getDay(System.currentTimeMillis())) {
				return;
			}

			db.setDayReceive(DateUtil.getDay(System.currentTimeMillis()));
			db.setShipStatus(1);
			Globals.getEntityProxy().updateAsync(db);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 将在线玩家以推荐规则作下筛选
	 * 
	 * @param uid 角色UID
	 * @return
	 */
	private List<RecommonedFriendVo> sortOnlineUserByRecomment(long uid, int type) {
		List<RecommonedFriendVo> result = new ArrayList<RecommonedFriendVo>();
		Map<Long, FriendsEntity> ownerFriendsMap = this.getFriendEntityMap(uid);

		// 好友列表已满
		if (ownerFriendsMap.size() >= GameGlobals.configManager.getFriendSize()) {
			return result;
		}
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return result;
		}

		for (long recommendUid : Globals.getUserManager().getAll().keySet()) {
			if (recommendUid == uid || ownerFriendsMap.containsKey(recommendUid)) {
				continue;
			}
			RoleEntity recommendRole = GameGlobals.roleManager.getRoleEntity(recommendUid);
			if (recommendRole == null) {
				continue;
			}
			// PVP
			if (type == 1 && !GameGlobals.functionOpenManager.onCheck(recommendUid, ISystemId.SYSTEM_RANKING)) {
				continue;
			}
			// LADDER
			if (type == 2 && !GameGlobals.functionOpenManager.onCheck(recommendUid, ISystemId.SYSTEM_LADDER)) {
				continue;
			}

			RecommonedFriendVo recommonedFriendVo = new RecommonedFriendVo();
			recommonedFriendVo.setName(recommendRole.getName());
			recommonedFriendVo.setUid(recommendUid);
			recommonedFriendVo.setSex(recommendRole.getSex());
			recommonedFriendVo.setAvatar(recommendRole.getAvatar());
			recommonedFriendVo.setShowId(recommendRole.getShowId());
			recommonedFriendVo.setWeight(1);
			result.add(recommonedFriendVo);
		}

//		Collections.sort(result);
		return result;
	}

	/**
	 * 获取推荐好友的列表
	 * 
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	private List<RecommonedFriendVo> getRecommentFrineds(long uid, int reqType) {
		List<RecommonedFriendVo> resultList = new ArrayList<RecommonedFriendVo>();
		List<RecommonedFriendVo> allList = this.sortOnlineUserByRecomment(uid, reqType);
		int size = Math.min(allList.size(), 6);
		if (size == 0) {
			return resultList;
		}
		for (int index = 0; index < size; index++) {
			resultList.add(allList.get(index));
		}
		return resultList;
	}

	private FriendRankInfo getFriendRankInfoByUid(long uid) {
		if (Globals.getUserManager().isThisServer(uid)) {
			return this.getFriendRankInfo(uid);
		} else {
			IRoleService service = RpcManager.getRpcImplBySid(Globals.getUserManager().getSid(uid), IRoleService.class);
			return service.remote_get_frined_rankInfo(uid);
		}
	}

	public FriendRankInfo getFriendRankInfo(long uid) {
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return null;
		}
		FriendRankInfo result = new FriendRankInfo();
		result.setHeader(role.getAvatar());
		result.setName(role.getName());
		PveCityEntity pveEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
		if (pveEntity != null) {
			result.setPveTaskId(pveEntity.getCurPveTarget());
		}
		return result;
	}

	/**
	 * 重置好友点赞状态
	 * 
	 * @param uid
	 */
	public void resetFriendsFabulousData(long uid) {
		this.fabulousService.resetFriendsFabulousData(uid);
	}

	public FriendsFabulousEntity getFabulousEntityByUid(long targetUid) {
		return this.fabulousService.getFabulousEntityByUid(targetUid);
	}
}
