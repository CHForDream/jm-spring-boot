package com.game.myapp.module.activity;

public interface IActivityHandler {

	void onStart(int activityId);

	void onEnd(int activityId);

	boolean resetRoleActivityInfo(long uid, RoleActivityInfo roleActivityInfo);

//	/**
//	 * 检测玩家是否完成了活动
//	 * 
//	 * @param uid
//	 * @param completeNum
//	 * @return true:达成新的目标<br>
//	 *         false:其他
//	 */
//	public boolean onCheck(int completeNum, long uid);
//
//	/**
//	 * 更新玩家活动数据
//	 * 
//	 * @param uid
//	 */
//	public void updateRoleActivityDB(List<Long> rewardIds, long uid, int num);
//
//	/**
//	 * 领取奖励
//	 * 
//	 * @param msgBack
//	 * 
//	 * @param uid
//	 */
//	public void receiveRewards(MsgBack msgBack, long rewardId, long uid);
//
//	/**
//	 * 返回玩家的活动完成度
//	 * 
//	 * @param uid
//	 * @return
//	 */
//	public String getActivityRewardsState(long uid);
//
//	/**
//	 * 清空活动状态
//	 */
//	public void clearActivityStatus(long uid);
//
//	/**
//	 * 获取奖励状态
//	 */
//	public int getRewardsState(long rewardId, long uid);
//
//	/**
//	 * 判断奖励是不是存在
//	 */
//	public boolean isHaveRewardsId(String ids, long target);
}
