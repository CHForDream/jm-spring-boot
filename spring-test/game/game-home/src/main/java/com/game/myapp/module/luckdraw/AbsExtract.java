package com.game.myapp.module.luckdraw;

import com.game.core.handler.MsgBack;

public abstract class AbsExtract {

	public static final int NORMAL_DATA_POOL_TYPE = 1;// 普通库
	public static final int SENIOR_DAYA_POOL_TYPE = 2;// 高级库
	public static final int MUST_DROP_POOL_TYPE = 3;// 单抽必掉库
	public static final int DOUBLE_MUST_POOL_TYPE = 4;// double必掉

	public abstract void extractReward(MsgBack msgBack, long uid);

}
