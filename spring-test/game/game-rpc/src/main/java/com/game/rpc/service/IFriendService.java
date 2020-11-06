package com.game.rpc.service;

import java.util.List;

import com.game.vo.LatyFriendVO;

public interface IFriendService {
	/**
	 * 好友申请
	 */
	void insertFriendreqEntity(long targetUid, long requestUid, String avatar, String name, int sex, int step, String declaration);

	/**
	 * 插入好友的好友关系数据
	 */
	void insertFriendsEntity(long uid, long friendUid);

	/**
	 * 删除好友的好友关系数据
	 */
	void deleteFriendsEntity(long uid, long friendUid);

	/**
	 * 添加最近好友
	 */
	void addLatelyFriends(long uid, List<LatyFriendVO> latelyFriendList);

	/**
	 * 给好友送礼物
	 */
	void addShip(long targetUid, long sendUid);
}
