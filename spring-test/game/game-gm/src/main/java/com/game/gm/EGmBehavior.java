package com.game.gm;

import java.util.HashMap;
import java.util.Map;

import com.game.gm.handler.CmdAddMailHandler;
import com.game.gm.handler.CmdBanChatHandler;
import com.game.gm.handler.CmdDeleteCommentHandler;
import com.game.gm.handler.CmdGetOnlineCountHandler;
import com.game.gm.handler.CmdKickUserHandler;
import com.game.gm.handler.CmdModifyActivityStatusHandler;
import com.game.gm.handler.CmdModifyGameActivityHandler;
import com.game.gm.handler.CmdSelectActicityHandler;
import com.game.gm.handler.CmdSelectRoleHerosHandler;
import com.game.gm.handler.CmdSelectRoleInfoHandler;
import com.game.gm.handler.CmdSelectRoleItemsHandler;
import com.game.gm.handler.CmdSendHorselightHandler;

/**
 * GM行为枚举
 *
 */
public enum EGmBehavior {
	/** 查询在线人数 */
	CMD_GET_ONLINECOUNT("get_online_count", new CmdGetOnlineCountHandler()),
	/** 查看玩家信息 */
	CMD_SELECT_ROLE_INFO("select_roleinfo", new CmdSelectRoleInfoHandler()),
	/** 查询玩家道具 */
	CMD_SELECT_ROLE_ITEMS("select_role_items", new CmdSelectRoleItemsHandler()),
	/** 查询玩家英雄 */
	CMD_SELECT_ROLE_HEROS("select_role_heros", new CmdSelectRoleHerosHandler()),

	/** 查询活动 */
	CMD_SELECT_ACTIVITY("select_activity", new CmdSelectActicityHandler()),
	/** 修改活动 */
	CMD_MODIFY_ACTIVITY("modify_activity", new CmdModifyGameActivityHandler()),
	/** 修改活动状态 */
	CMD_MODIFY_ACTIVITY_STATUS("modify_activity_status", new CmdModifyActivityStatusHandler()),

	/** 走马灯 */
	CMD_SEND_HORSERACELAMP("send_lamp", new CmdSendHorselightHandler()),
	/** 踢人 */
	CMD_KICK_USER("kick_offline", new CmdKickUserHandler()),
	/** 禁言 */
	CMD_BAN_CHAT("ban_chat", new CmdBanChatHandler()),
	/** 添加邮件 */
	CMD_ADD_MAIL("add_mail", new CmdAddMailHandler()),
	/** 删除评论 */
	CMD_DELETE_COMMENT("delete_comment", new CmdDeleteCommentHandler()),

	;

	private static Map<String, EGmBehavior> values = new HashMap<String, EGmBehavior>();

	static {
		for (EGmBehavior value : EGmBehavior.values()) {
			values.put(value.getType(), value);
		}
	}

	private final String type;
	private final AbsGmContoller controller;

	private EGmBehavior(String type, AbsGmContoller controller) {
		this.type = type;
		this.controller = controller;
	}

	public static EGmBehavior valuesOf(String type) {
		return values.get(type);
	}

	public String getType() {
		return type;
	}

	public AbsGmContoller getController() {
		return controller;
	}
}
