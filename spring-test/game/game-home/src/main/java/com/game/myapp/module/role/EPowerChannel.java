/**
 * 
 */
package com.game.myapp.module.role;

/**
 * @author pky
 *
 */
public enum EPowerChannel {
	/** 战斗 */
	BATTLE(false),
	/** 其它 */
	OTHER(true),
	/** 自动 */
	AUTO(false),
	/** 好友点赞 */
	FRIENDFABULOUS(false),
	/** 购买 */
	BUY(false),;

	;
	/** 是否可以超限 */
	private final boolean canOverLimit;

	private EPowerChannel(boolean canOverLimit) {
		this.canOverLimit = canOverLimit;
	}

	public boolean canOverLimit() {
		return canOverLimit;
	}

}
