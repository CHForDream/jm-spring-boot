/**
 * 
 */
package com.game.myapp.module.achieve;

import java.util.HashMap;
import java.util.Map;

import com.game.myapp.module.achieve.impl.ActiveDegreeTaskImpl;
import com.game.myapp.module.achieve.impl.CommTaskEqualImpl;
import com.game.myapp.module.achieve.impl.CommTaskImpl;
import com.game.myapp.module.achieve.impl.CommTaskMapImpl;

/**
 * @author pky
 *         成就枚举
 */

public enum EAchieveBehavior {
	/** 累计登陆 */
	TYPE_LOGIN(1, false, CommTaskImpl.getInstance()),
	/** 通关副本个数 */
	TYPE_DUP_NUM(2, false, CommTaskImpl.getInstance()),
	/** 通关副本次数 */
	TYPE_DUP_TIMES(3, false, CommTaskImpl.getInstance()),
	/** 累计x天活跃度到达xx */
	TYPE_ACTIVEDEGREE(4, false, ActiveDegreeTaskImpl.getInstance()),
	/** 收集棋子 */
	TYPE_COLLEC_CHESS(5, false, CommTaskMapImpl.getInstance()),
	/** 累计获得金币 */
	TYPE_COLLEC_COIN(6, false, CommTaskImpl.getInstance()),
	/** 累计获得钻石 */
	TYPE_COLLEC_CASH(7, false, CommTaskImpl.getInstance()),
	/** 关卡内触发连击x次（1-2连击） */
	TYPE_COMBO_1(8, false, CommTaskImpl.getInstance()),
	/** 关卡内触发连击x次（3-4连击） */
	TYPE_COMBO_2(9, false, CommTaskImpl.getInstance()),
	/** 使用战斗内道具 */
	TYPE_BATTLE_IN_ITEM(10, false, CommTaskImpl.getInstance()),
	/** 使用战斗外道具 */
	TYPE_BATTLE_OUT_ITEM(11, false, CommTaskMapImpl.getInstance()),
	/** 合成道具 */
	TYPE_BATTLE_COMPOSE(12, false, CommTaskMapImpl.getInstance()),
	/** 连续战斗胜利 (只针对pve) */
	TYPE_BATTLE_CONTINUITY_WIN(13, false, CommTaskEqualImpl.getInstance()),
	/** 拥有X个英雄 */
	TYPE_COLLEC_HERO(14, false, CommTaskImpl.getInstance()),
	/** 观看视频x次 */
	TYPE_VIEW_VIDEO(15, false, CommTaskImpl.getInstance()),
	/** 连续签到 暂时不要了 */
//	TYPE_CONTINUITY_SIGN(16, false, SignContinuityTaskImpl.getInstance()),
	/** pve通关 */
	TYPE_PVE_NUM(17, false, CommTaskEqualImpl.getInstance()),
	/** 关卡内触发连击x次（5-6连击） */
	TYPE_COMBO_3(18, false, CommTaskImpl.getInstance()),
	/** 关卡内触发连击x次（6连击以上） */
	TYPE_COMBO_4(19, false, CommTaskImpl.getInstance()),

//	/** 天梯次数 */
//	LADDER_BATTLE_NUM(18, false, CommTaskImpl.getInstance()),
//	/** 与好友1v1次数 */
//	PVP_1V1_FRIEND(19, false, CommTaskImpl.getInstance()),
//	/** 战斗 */
//	TYPE_BATTLE(2, true, CommTaskImpl.getInstance()),
//	/** 组队战斗 */
//	TYPE_TEAM_BATTLE(3, true, CommTaskImpl.getInstance()),
//	/** 战斗胜利 */
//	TYPE_WIM(4, true, CommTaskImpl.getInstance()),
	;

	/** 成就类型 */
	private final int type;
	/** 是否需要系统开启才检测 */
	private final boolean systemOpen;
	/** 检测类 */
	private final IAchieveTask doChecker;

	private static Map<Integer, EAchieveBehavior> valueMap = new HashMap<Integer, EAchieveBehavior>();

	static {
		for (EAchieveBehavior typeEnum : EAchieveBehavior.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	private EAchieveBehavior(int type, boolean systemOpen, IAchieveTask doChecker) {
		this.type = type;
		this.systemOpen = systemOpen;
		this.doChecker = doChecker;
	}

	public static EAchieveBehavior valueOf(int type) {
		return valueMap.get(type);
	}

	public int getType() {
		return type;
	}

	public boolean isSystemOpen() {
		return systemOpen;
	}

	public IAchieveTask getDoChecker() {
		return doChecker;
	}

}
