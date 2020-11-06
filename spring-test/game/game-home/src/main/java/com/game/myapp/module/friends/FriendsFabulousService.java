package com.game.myapp.module.friends;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.FriendFabulousLog;
import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.core.handler.MsgBack;
import com.game.db.entity.FriendsFabulousEntity;
import com.game.db.entity.RoleEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.role.EPowerChannel;
import com.game.utils.JsonUtil;
import com.google.common.collect.Lists;

import buffer.CGFriendFabulousMsg.CGFriendFabulousProto;
import buffer.GCFriendFabulousMsg.GCFriendFabulousProto;
import buffer.GCFriendsListMsg.GCFriendsListProto.Builder;

public class FriendsFabulousService {

	private FriendsFabulousDao dao = new FriendsFabulousDao();

	/**
	 * 点赞
	 * 
	 * @param msgBack
	 * 
	 * @param userBean
	 * @param msg
	 */
	public void CGFriendFabulous(MsgBack msgBack, UserBean userBean, CGFriendFabulousProto msg) {
		GCFriendFabulousProto.Builder builder = GCFriendFabulousProto.newBuilder();
		msgBack.addBuilder(builder);
		long uid = userBean.getUid();

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
				return;
			}

			FriendsFabulousEntity entity = this.dao.getFriendsFabulousByUid(uid);
			List<Long> uids = this.getFabulousUidList(entity.getUids());
			long targetUid = msg.getTargetUid();
			if (uids.contains(targetUid)) {
				builder.setStatus(ErrorCodeConst.FRIENDS_FABULOUS_ALREADY);
				return;
			}

			// 添加自己的点赞信息
			uids.add(targetUid);
			entity.setUids(JsonUtil.toJson(uids));
			entity.setFabulousNum(entity.getFabulousNum() + 1);
			entity.setFabulousTime(System.currentTimeMillis());
			Globals.getEntityProxy().updateAsync(entity);

			// 给点赞玩家增加一点点赞数量
			FriendsFabulousEntity target = this.dao.getFriendsFabulousByUid(targetUid);
			target.setFabulous(target.getFabulous() + 1);
			Globals.getEntityProxy().updateAsync(target);

			// 验证是否需要加体力
			int fabulousNum = entity.getFabulousNum();
			if (fabulousNum == 1 || fabulousNum == 3 || fabulousNum == 5) {
				int oldPower = role.getPower();
				GameGlobals.roleManager.addPower(EPowerChannel.FRIENDFABULOUS, role.getUid(), 1);
				int newpower = role.getPower();
				int add = newpower - oldPower;
				builder.setAddPower(add == 0 ? -1 : add);
				if (add > 0) {
					GameGlobals.roleManager.packGCPowerGetInfoProto(msgBack, uid);
				}
			}
			builder.setFabulousNum(fabulousNum);
			builder.setStatus(ErrorCodeConst.SUCCESS);

			//添加点赞日志
			LogfPrinter.getInstance().push(new FriendFabulousLog(Globals.getUserManager().getUserBean(uid), targetUid));
		} finally {
			lock.unlock();
		}
	}

	public List<Long> getFabulousUidList(String s) {
		if (Objects.deepEquals("", s)) {
			return Lists.newArrayList();
		}
		return JsonUtil.toList(s, Long.class);
	}

	/**
	 * 
	 * @param uid
	 */
	public void resetFriendsFabulousData(long uid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			FriendsFabulousEntity entity = this.dao.getFriendsFabulousByUid(uid);
			entity.setFabulousNum(0);
			entity.setUids("");
			Globals.getEntityProxy().updateAsync(entity);
		} finally {
			lock.unlock();
		}
	}

	public int getFabulousNum(long uid) {
		return 0;
	}

	public void fabulous(long uid, Builder gcMsg) {
		FriendsFabulousEntity entity = this.dao.getFriendsFabulousByUid(uid);
		gcMsg.addAllFabulousFriends(this.getFabulousUidList(entity.getUids()));
		gcMsg.setFabulousNum(entity.getFabulousNum());
	}

	public FriendsFabulousEntity getFabulousEntityByUid(long targetUid) {
		return this.dao.getFriendsFabulousByUid(targetUid);
	}
}
