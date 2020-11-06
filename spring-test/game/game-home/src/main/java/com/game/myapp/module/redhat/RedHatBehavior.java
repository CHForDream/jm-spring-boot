package com.game.myapp.module.redhat;

import java.util.HashMap;
import java.util.Map;

import com.game.myapp.module.redhat.checker.IRedHatChecker;
import com.game.myapp.module.redhat.checker.achievement.AchievementBattleRedChecker;
import com.game.myapp.module.redhat.checker.achievement.AchievementCollectRedChecker;
import com.game.myapp.module.redhat.checker.achievement.AchievementLevelRewardRedChecker;
import com.game.myapp.module.redhat.checker.achievement.AchievementUpgradeRedChecker;
import com.game.myapp.module.redhat.checker.bag.BagAllRedChecker;
import com.game.myapp.module.redhat.checker.friend.FriendInvatationRedChecker;
import com.game.myapp.module.redhat.checker.lobby.LobbySignRedChecker;
import com.game.myapp.module.redhat.checker.mail.MailFriendChecker;
import com.game.myapp.module.redhat.checker.mail.MailSystemChecker;
import com.game.myapp.module.redhat.checker.role.RoleHeroGemGetLuckDrawRedChecker;
import com.game.myapp.module.redhat.checker.task.TaskRedChecker;
import com.game.myapp.module.redhat.checker.task.TaskRewardChecker;
import com.game.myapp.module.redhat.checker.welfare.WelfareActivityMonthCardBuyPanelRedChecker;

public enum RedHatBehavior {

	/** 家园水晶解锁 */
//	HOME_UN_LOCK(1101, new HomeUnLockRedChecker()),

	/** 任务/完成任务 */
	TASK_COMPLETE(104, new TaskRedChecker()),
	/** 任务/任务宝箱 */
	TASK_REWARD(1104, new TaskRewardChecker()),

	/** 签到 */
	LOBBY_SIGN(504, new LobbySignRedChecker()),

	/** 成就/成长 */
	ACHIEVEMENT_UPGRADE(1204, new AchievementUpgradeRedChecker()),
	/** 成就/竞技 */
	ACHIEVEMENT_BATTLE(2204, new AchievementBattleRedChecker()),
	/** 成就/收集 */
	ACHIEVEMENT_COLLECT(3204, new AchievementCollectRedChecker()),
	/** 成就/宝箱 */
	ACHIEVEMENT_LEVEL_REWARD(4204, new AchievementLevelRewardRedChecker()),

	/** 好友邮件 */
	MAIL_FRINED(1604, new MailFriendChecker()),
	/** 系统邮件 */
	MAIL_SYSTEM(2604, new MailSystemChecker()),

	/** 背包/全部 */
	BAG_ALL(1704, new BagAllRedChecker()),

	/** 好友/领取体力 自动领取, 不需要红点 */
//	FRIEND_POWER(108, null),
	/** 好友申请 */
	FRIEND_INVATATION(208, new FriendInvatationRedChecker()),

	/** 伙伴/伙伴分页, 获得伙伴即显示红点, 客户端主动cancel取消红点, 不需要checker */
	HERO_GET_NEW(109, null),
	/** 伙伴/宠物分页, 获得宠物即显示红点, 客户端主动cancel取消红点, 不需要checker */
	PET_GET_NEW(209, null),

	/** 月卡 */
	WELFARE_ACTIVITY_MONTH_CARD_BUY_PANEL(110, new WelfareActivityMonthCardBuyPanelRedChecker()),

	/** 夺宝分页 */
	ROLE_HERO_GEM_GET_LUCK_DRAW(210, new RoleHeroGemGetLuckDrawRedChecker()),

	/** 战斗/更换伙伴, 获得伙伴即显示红点, 客户端主动cancel取消红点, 不需要checker */
	HERO_CHANGE_NEW(111, null),
	/** 战斗/更换宠物, 获得宠物即显示红点, 客户端主动cancel取消红点, 不需要checker */
	PET_CHANGE_NEW(211, null),

	;

	private static Map<Integer, RedHatBehavior> valueMap = new HashMap<Integer, RedHatBehavior>();

	static {
		for (RedHatBehavior typeEnum : RedHatBehavior.values()) {
			valueMap.put(typeEnum.getBehavior(), typeEnum);
		}
	}

	/** 红点行为 */
	private final int behavior;
	/** 红点检测器 */
	private final IRedHatChecker checker;

	RedHatBehavior(int behavior, IRedHatChecker checker) {
		this.behavior = behavior;
		this.checker = checker;
	}

	public static RedHatBehavior valueOf(int type) {
		return valueMap.get(type);
	}

	public int getBehavior() {
		return behavior;
	}

	public IRedHatChecker getChecker() {
		return checker;
	}
}
