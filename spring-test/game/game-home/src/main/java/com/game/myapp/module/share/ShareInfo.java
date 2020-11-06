package com.game.myapp.module.share;

public class ShareInfo {
	private int shareType;// 分享类型
	private int sharedTimes;// 分享次数
	private int limitTimes;// 次数上限

	public ShareInfo(int shareType, int sharedTimes, int limitTimes) {
		this.shareType = shareType;
		this.sharedTimes = sharedTimes;
		this.limitTimes = limitTimes;
	}

	public void reset() {
		this.sharedTimes = 0;
	}

	public buffer.GCShareInfoMsg.SharedInfo.Builder getShareInfoBuilder() {
		buffer.GCShareInfoMsg.SharedInfo.Builder infoBuilder = buffer.GCShareInfoMsg.SharedInfo.newBuilder().setShareType(shareType).setSharedTimes(sharedTimes)
				.setLimitTimes(limitTimes);
//		for (ShareRewardItem shareRewardItem : rewardItemMap.values()) {
//			buffer.GCShareInfoMsg.RewardItem.Builder rewardItemBuilder = buffer.GCShareInfoMsg.RewardItem.newBuilder();
//			rewardItemBuilder.setIndex(shareRewardItem.getIndex());
//			rewardItemBuilder.setStatus(shareRewardItem.getStatus());
//			rewardItemBuilder.setNeedSharedTimes(shareRewardItem.getNeedSharedTimes());
//			rewardItemBuilder.setRewardStr(ShareManager.buildRewardStr(shareType, shareRewardItem.getIndex(), homeLevel));
//			infoBuilder.addRewardItemList(rewardItemBuilder);
//		}
		return infoBuilder;
	}

	public int getShareType() {
		return shareType;
	}

	public void setShareType(int shareType) {
		this.shareType = shareType;
	}

	public int getSharedTimes() {
		return sharedTimes;
	}

	public void setSharedTimes(int sharedTimes) {
		this.sharedTimes = sharedTimes;
	}

	public int getLimitTimes() {
		return limitTimes;
	}

	public void setLimitTimes(int limitTimes) {
		this.limitTimes = limitTimes;
	}

	@Override
	public String toString() {
		return "ShareInfo [shareType=" + shareType + ", sharedTimes=" + sharedTimes + ", limitTimes=" + limitTimes + "]";
	}
}
