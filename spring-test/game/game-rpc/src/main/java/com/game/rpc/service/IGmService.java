package com.game.rpc.service;

public interface IGmService {
	/**
	 * 给玩家发邮件
	 * 
	 */
	void sendMail(int mailType, String title, String content, long toUid, long fromUid, String items, long systemMailId, String params);

	/**
	 * 发系统邮件
	 * 
	 */
	void sendSystemMail(long mailId);

	/**
	 * 聊天禁言
	 * 
	 */
	void banChat(int type, long uid, long startTime, long endTime, String reason);

	/**
	 * 删除评论
	 */
	void deleteComment(long uid, int type, int commentId);

	/**
	 * 踢玩家下线
	 */
	String kickoffUser(long uid);

	/**
	 * 发送跑马灯
	 */
	void sendHorseLamp(String content, long startTime, long endTime, long inverval);
}
