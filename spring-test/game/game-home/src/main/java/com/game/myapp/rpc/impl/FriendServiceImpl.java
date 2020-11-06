package com.game.myapp.rpc.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.scheduling.annotation.Async;

import com.game.myapp.GameGlobals;
import com.game.rpc.service.IFriendService;
import com.game.vo.LatyFriendVO;

@DubboService
@Async("gameExecutor")
public class FriendServiceImpl implements IFriendService {
	@Override
	public void insertFriendreqEntity(long targetUid, long requestUid, String avatar, String name, int sex, int step, String declaration) {
		// 插入好友申请数据
		GameGlobals.friendManager.insertFriendreqEntity(targetUid, requestUid, avatar, name, sex, step, declaration);
	}

	@Override
	public void insertFriendsEntity(long uid, long friendUid) {
		// 插入好友数据
		GameGlobals.friendManager.addFriendInfo(uid, friendUid);
	}

	@Override
	public void deleteFriendsEntity(long uid, long friendUid) {
		// 删除好友数据
		GameGlobals.friendManager.deleteFriendsEntity(uid, friendUid);
	}

	@Override
	public void addLatelyFriends(long uid, List<LatyFriendVO> latelyFriendList) {
		// 添加最近好友
		GameGlobals.friendManager.addLatelyFriends(uid, latelyFriendList);
	}

	@Override
	public void addShip(long targetUid, long sendUid) {
		// 给好友送礼物
		GameGlobals.friendManager.addShip(targetUid, sendUid);
	}
}
