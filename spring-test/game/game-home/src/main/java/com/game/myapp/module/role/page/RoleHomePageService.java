package com.game.myapp.module.role.page;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.logf.LogfConstants;
import com.game.constants.ErrorCodeConst;
import com.game.core.handler.MsgBack;
import com.game.core.lock.LockManager;
import com.game.db.entity.FriendsFabulousEntity;
import com.game.db.entity.PveCityEntity;
import com.game.db.entity.RoleBattleEntity;
import com.game.db.entity.RoleEntity;
import com.game.db.entity.RoleInfoEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.item.EItemId;

import buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto;
import buffer.CGRoleHomePageSetUpMsg.CGRoleHomePageSetUpProto;
import buffer.GCFriendGetInfoByShowIdMsg.GCFriendGetInfoByShowIdProto.Builder;
import buffer.GCRoleHomePageInfoMsg.GCRoleHomePageInfoProto;
import buffer.GCRoleHomePageSetUpMsg.GCRoleHomePageSetUpProto;

public class RoleHomePageService {

	private RoleHomePageDao dao = new RoleHomePageDao();

	public void CGRoleHomePageInfo(MsgBack msgBack, long uid, CGRoleHomePageInfoProto msg) {
		GCRoleHomePageInfoProto.Builder builder = GCRoleHomePageInfoProto.newBuilder();
		msgBack.addBuilder(builder);

		long targetUid = msg.getTargetUid();
		RoleEntity targetRole = GameGlobals.roleManager.getRoleEntity(targetUid);
		if (targetRole == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}

		builder.setHeader(targetRole.getAvatar());
		builder.setName(targetRole.getName());

		PveCityEntity pveEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(targetUid);
		if (pveEntity != null) {
			builder.setPveTaskId(pveEntity.getCurPveTarget());
		}
//		builder.setPveTaskId(targetRole.getPveTaskId());
		if (uid != targetUid) {
			builder.setIsMyFriend(GameGlobals.friendManager.isFriend(uid, targetUid));
		}

		builder.setSex(targetRole.getSex());
		builder.setShowId(targetRole.getShowId());

		RoleInfoEntity roleInfoEntity = this.dao.getRoleInfoEntityByUid(targetUid);
		builder.setHeroCode(roleInfoEntity.getFavouriteHero());
		builder.setPersonSign(roleInfoEntity.getPersonSign());
		builder.setPetsCode(roleInfoEntity.getFavouritePet());

		long favouriteUid = roleInfoEntity.getFavouriteFriend();
		builder.setFriendUid(favouriteUid);
		if (favouriteUid != 0) {
			RoleEntity favouriteFriendRole = GameGlobals.roleManager.getRoleEntity(favouriteUid);
			if (favouriteFriendRole != null) {
				builder.setFriendName(favouriteFriendRole.getName());
				builder.setFriendSex(favouriteFriendRole.getSex());
				builder.setFriendHeader(favouriteFriendRole.getAvatar());
			}
		}

		int changeNameCardNum = GameGlobals.itemManager.getItemNumByItemId(targetUid, EItemId.CHANGE_NAME_CARD.getType());
		builder.setChangeNameCardNum(changeNameCardNum);

		FriendsFabulousEntity friendsFabulousEntity = GameGlobals.friendManager.getFabulousEntityByUid(targetUid);
		builder.setFriendsFabulousNum(friendsFabulousEntity.getFabulous());

		RoleBattleEntity battleEntity = GameGlobals.roleManager.getRoleBattleEntity(targetUid);
		if (battleEntity != null) {
			builder.setNormalDupTimes(battleEntity.getNormalDupTimes());
			builder.setHardDupTimes(battleEntity.getHardDupTimes());
		}

		builder.setStatus(ErrorCodeConst.SUCCESS);
	}

	public void insertRoleInfo(long uid) {
		this.dao.insertRoleInfo(uid);
	}

	public void CGRoleHomePageSetUp(MsgBack msgBack, long uid, CGRoleHomePageSetUpProto msg) {
		GCRoleHomePageSetUpProto.Builder builder = GCRoleHomePageSetUpProto.newBuilder();
		msgBack.addBuilder(builder);
		int optType = msg.getOptType();
		builder.setOptType(optType);
		int code = ErrorCodeConst.SUCCESS;
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}
		switch (optType) {
		case 1:// 改名和头像
			code = this.changeRoleNameAndHeader(role, msg.getName(), msg.getHeader());
			break;
//		case 2:// 改头像【暂时没用了】
//			code = this.changeRoleHeader(role, msg.getHeader());
//			break;
		case 3:// 签名
			code = this.changeRoleSign(uid, msg.getSign());
			break;
		case 4:// 好友
			code = this.changeRoleFavouriteFriend(uid, msg.getFriendUid());
			break;
		case 5:// 英雄
			code = this.changeRoleFavouriteHero(uid, msg.getHeroCode());
			break;
		case 6:// 宠物
			code = this.changeRoleFavouritePrts(uid, msg.getPetsCode());
			break;
		}
		builder.setStatus(code);
	}

	private int changeRoleNameAndHeader(RoleEntity role, String name, String header) {
		//先判断名称，如果名称修改不成功直接返回，头像也不能修改成功
		int code = ErrorCodeConst.SUCCESS;
		if (name != null && !Objects.equals("", name)) {
			code = this.changeRoleName(role, name);
			if (code != ErrorCodeConst.SUCCESS) {
				return code;
			}
		}

		//修改头像
		if (header != null && !Objects.equals("", header)) {
			code = this.changeRoleHeader(role, header);
			if (code != ErrorCodeConst.SUCCESS) {
				return code;
			}
		}

		return code;
	}

	/**
	 * 修改最爱宠物
	 * 
	 * @param uid
	 * @param petsCode
	 * @return
	 */
	private int changeRoleFavouritePrts(long uid, int petsCode) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleInfoEntity entity = this.dao.getRoleInfoEntityByUid(uid);
			if (entity == null) {
				return ErrorCodeConst.ERROR_PARAM;
			}

			entity.setFavouritePet(petsCode);
			Globals.getEntityProxy().updateAsync(entity);
		} finally {
			lock.unlock();
		}
		return ErrorCodeConst.SUCCESS;
	}

	/**
	 * 修改最爱英雄
	 * 
	 * @param uid
	 * @param heroCode
	 * @return
	 */
	private int changeRoleFavouriteHero(long uid, int heroCode) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleInfoEntity entity = this.dao.getRoleInfoEntityByUid(uid);
			if (entity == null) {
				return ErrorCodeConst.ERROR_PARAM;
			}

			entity.setFavouriteHero(heroCode);
			Globals.getEntityProxy().updateAsync(entity);
		} finally {
			lock.unlock();
		}

		return ErrorCodeConst.SUCCESS;
	}

	/**
	 * 修改最爱好友
	 * 
	 * @param uid
	 * @param friendUid
	 * @return
	 */
	private int changeRoleFavouriteFriend(long uid, long friendUid) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleInfoEntity entity = this.dao.getRoleInfoEntityByUid(uid);
			if (entity == null) {
				return ErrorCodeConst.ERROR_PARAM;
			}
			entity.setFavouriteFriend(friendUid);
			Globals.getEntityProxy().updateAsync(entity);
		} finally {
			lock.unlock();
		}
		return ErrorCodeConst.SUCCESS;
	}

	/**
	 * 修改签名
	 * 
	 * @param role
	 * @param sign
	 * @return
	 */
	private int changeRoleSign(long uid, String sign) {
		if (sign.length() > 40) {
			return ErrorCodeConst.ERROR_PARAM;
		}

		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleInfoEntity entity = this.dao.getRoleInfoEntityByUid(uid);
			if (entity == null) {
				return ErrorCodeConst.ERROR_PARAM;
			}

			entity.setPersonSign(sign);
			Globals.getEntityProxy().updateAsync(entity);
		} finally {
			lock.unlock();
		}
		return ErrorCodeConst.SUCCESS;
	}

	/**
	 * 修改头像
	 * 
	 * @param role
	 * @param header
	 * @return
	 */
	private int changeRoleHeader(RoleEntity role, String header) {
		ReentrantLock lock = Globals.getLockManager().getLock(role.getUid());
		lock.lock();
		try {
			role.setAvatar(header);
			Globals.getEntityProxy().updateAsync(role);
			return ErrorCodeConst.SUCCESS;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 改名
	 * 
	 * @param role
	 * @param name
	 * @return
	 */
	private int changeRoleName(RoleEntity role, String name) {
		if (name == null || Objects.equals("", name)) {
			return ErrorCodeConst.SUCCESS;
		}
		if (name.length() > 6) {
			return ErrorCodeConst.ERROR_PARAM;
		}
		// 资源
		long uid = role.getUid();
		int changeCardNum = GameGlobals.itemManager.getItemNumByItemId(uid, EItemId.CHANGE_NAME_CARD.getType());
		String cost = GameGlobals.configManager.getChangeNameCost();
		int costType = Integer.parseInt(cost.split(",")[0]);
		int costId = Integer.parseInt(cost.split(",")[1]);
		int costNum = Integer.parseInt(cost.split(",")[2]);
		if (changeCardNum <= 0 && !GameGlobals.unitManager.isEnough(role, costId, costNum)) {
			return ErrorCodeConst.RESOURCE_NOT_ENOUGH;
		}
		// 重名
		boolean check = GameGlobals.userNameCheckManager.isNameExist(name);
		if (!check) {
			return ErrorCodeConst.ROLE_NAME_IS_EXIST;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(role.getUid());
		lock.lock();
		try {
			// 扣除资源
			if (changeCardNum > 0) {
				GameGlobals.itemManager.costItem(uid, EItemId.CHANGE_NAME_CARD.getType(), 1);
			} else {
				GameGlobals.unitManager.cost(uid, costType, costId, costNum, LogfConstants.CHANNEL_CHANGE_NAME, "change_name");
			}

			// 修改
			String oldName = role.getName();
			role.setName(name);
			Globals.getEntityProxy().updateAsync(role);

			GameGlobals.userNameCheckManager.update(uid, oldName, name);
		} finally {
			lock.unlock();
		}

		return ErrorCodeConst.SUCCESS;
	}

	public void buildFavoriteHeroCodeAndPetCode(long uid, Builder builder) {
		RoleInfoEntity entity = this.dao.getRoleInfoEntityByUid(uid);
		if (entity == null) {
			return;
		}
		builder.setHeroCode(entity.getFavouriteHero());
		builder.setPetsCode(entity.getFavouritePet());
	}

}
