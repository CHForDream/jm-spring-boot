package com.game.myapp.module.chatgm;

import java.util.HashMap;
import java.util.Map;

public enum EChatGmType {
	HELP("help", "帮助[!help]. 指令前缀'!'不区中英文标点"),
	ITEM("item", "添加物品[!item type id num] 例: !item 3 1 100"),
	ITEM_ID("itemid", "所有物品id[!itemid] 例：!itemid"),
	ITEM_CLEAR("itemclear", "清除所有道具[!itemclear] 例：!itemclear"),
	ITEM_CLEAR2("itemc", "清除所有道具[!itemc] 例：!itemc"),
	DELETEROLE("deleterole", "清除账号[!deleteRole]"),
	DELETEROLE2("d", "清除账号[!d]"),
	PAY("pay", "支付[!pay payId] payId详细查询pay.xls, 暂时不支持月卡"),
	MONTHCARD("monthcard", "购买月卡(或月卡加时)[!monthcard]"),
	ALLHERO("allhero", "添加所有的英雄[!allhero]"),
	UID("uid", "查询uid[!uid]"),
	ONLINE("online", "查询在线人数[!online]"),
	PVE("pve", "通过该关卡[!pve pveId] 例：!pve 3"),
	PVE_STATUS("pves", "通过该关卡[!pves pveId status] 例：!pves 3 1"),
	ACTIVITY_PVE_WINNING_STREAK("cowin", "连胜活动开关[!cowin on/off] 例：!cowin on"),
	ELITE_ALL_1("eliteall", "开启所有精英副本[!eliteall] 例：!eliteall"),
	ELITE_ALL_2("ea", "开启所有精英副本[!ea] 例：!ea"),
	ELITE_RESET_1("elitereset", "重置所有精英副本[!elitereset] 例：!elitereset"),
	ELITE_RESET_2("er", "重置所有精英副本[!er] 例：!er"),

	;

	private static Map<String, EChatGmType> valueMap = new HashMap<String, EChatGmType>();

	static {
		for (EChatGmType typeEnum : EChatGmType.values()) {
			valueMap.put(typeEnum.getCmd(), typeEnum);
		}
	}

	/** cmd */
	private final String cmd;
	/** 分享奖励邮件内容 */
	private final String cmdDescription;

	EChatGmType(String cmd, String cmdDescription) {
		this.cmd = cmd;
		this.cmdDescription = cmdDescription;
	}

	public static EChatGmType typeOf(String type) {
		return valueMap.get(type);
	}

	public boolean is(EChatGmType type) {
		return this == type;
	}

	public boolean is(String type) {
		return getCmd().equals(type.toLowerCase());
	}

	public String getCmd() {
		return cmd;
	}

	public String getCmdDescription() {
		return cmdDescription;
	}
}
