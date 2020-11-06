package com.game.robot.manager;

import java.util.List;

import com.game.robot.Robot;
import com.game.robot.model.PveCityInfo;
import com.google.common.collect.Lists;

import buffer.GCCommonResponseMsg.GCCommonResponseProto;
import buffer.GCEliteDuplicateInfo.GCEliteDuplicateInfoProto;
import buffer.GCFunctionOpenInfoMsg.GCFunctionOpenInfoProto;
import buffer.GCGetHeroListMsg.GCGetHeroListProto;
import buffer.GCGetRedHatinfoMsg.GCGetRedHatinfoProto;
import buffer.GCGetSevenSignStatusMsg.GCGetSevenSignStatusProto;
import buffer.GCLoadingRoleMsg.GCLoadingRoleProto;
import buffer.GCShareInfoMsg.GCShareInfoProto;
import lombok.Data;

@Data
public class RobotInfoManager {
	private Robot owner;

	private String chatServer;
	private int chatServerPort;

	// 货币信息
	private int gold;//金币
	private int homeMoney;//家园币
	private int blueCash;//蓝钻
	private int rewardBlueCash;//赠送的蓝钻

	private int pveTragerId;// PVE关卡Id
	private int eliteId;// 精英副本Id

	// 红点信息
	private List<Integer> redHatIdsList = Lists.newArrayList();

	// 引导信息
	private int guide;//最后一次上报引导
	private List<Integer> nextGuideIds = Lists.newArrayList();//触发未执行的引导
	private List<Integer> battleGuides = Lists.newArrayList();//战斗中触发过的引导

	private GCGetRedHatinfoProto gcGetRedHatinfoProto;
	private GCFunctionOpenInfoProto gcFunctionOpenInfoProto;
	private GCLoadingRoleProto gcLoadingRoleProto;
	private GCShareInfoProto gcShareInfoProto;
	private GCGetHeroListProto gcGetHeroListProto;
	private GCGetSevenSignStatusProto gcGetSevenSignStatusProto;
	private GCEliteDuplicateInfoProto gcEliteDuplicateInfoProto;

	private PveCityInfo pveCityInfo;

	public RobotInfoManager(Robot robot) {
		this.owner = robot;
	}

	public void GCCommonResponse(GCCommonResponseProto msg) {
		this.gold = msg.getGold();
		this.homeMoney = msg.getHomeMoney();
		this.blueCash = msg.getBlueCash();
		this.rewardBlueCash = msg.getRewardBlueCash();
	}

	public String getName() {
		return gcLoadingRoleProto.getName();
	}
}
