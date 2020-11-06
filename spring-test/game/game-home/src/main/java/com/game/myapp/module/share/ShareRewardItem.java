package com.game.myapp.module.share;

public class ShareRewardItem implements Cloneable {
	public static final int STATUS_CAN_NOT_GET = 0;// 不可领取
	public static final int STATUS_CAN_GET = 1;// 可领取
	public static final int STATUS_GOT = 2;// 已领取

	// 第几个奖励
	private int index;
	// 分享到多少次可领取这个奖励
	private int needSharedTimes;
	// 奖励状态. 0:不可领取, 1:可领取, 2:已领取
	private int status;

	public ShareRewardItem(int index, int needSharedTimes, int status) {
		this.index = index;
		this.needSharedTimes = needSharedTimes;
		this.status = status;
	}

	@Override
	protected ShareRewardItem clone() {
		try {
			return (ShareRewardItem) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getNeedSharedTimes() {
		return needSharedTimes;
	}

	public void setNeedSharedTimes(int needSharedTimes) {
		this.needSharedTimes = needSharedTimes;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ShareRewardItem [index=" + index + ", needSharedTimes=" + needSharedTimes + ", status=" + status + "]";
	}
}
