package com.game.rpc.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 好友排行信息
 *
 */
@Data
public class FriendRankInfo implements Serializable {
	private static final long serialVersionUID = 1844570939284624655L;

	/** 昵称 */
	private String name = "";
	/** 头像 */
	private String header = "";
	/** 等级 */
	private int level = 1;
	/** pve关卡id */
	private int pveTaskId = 1;
}
