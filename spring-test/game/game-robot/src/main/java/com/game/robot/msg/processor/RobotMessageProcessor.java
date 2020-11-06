package com.game.robot.msg.processor;

import java.util.ArrayList;
import java.util.List;

import com.game.annotation.ProcessorDefine;
import com.game.common.client.Client;
import com.game.common.client.ClientMessageLogSample;
import com.game.common.client.IClientProcessor;
import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.game.robot.Robot;
import com.game.robot.config.Config;
import com.game.robot.model.CityInfo;
import com.game.robot.model.DupInfo;
import com.game.robot.model.PveCityInfo;
import com.game.robot.strategy.IRobotStrategy;
import com.google.protobuf.GeneratedMessage;

import buffer.CGChatGm.CGChatGmProto;
import buffer.GCBanchChatMsg.GCBanchChatProto;
import buffer.GCBattleChangeChoiceHeroAndHeroDecMsg.GCBattleChangeChoiceHeroAndHeroDecProto;
import buffer.GCBattlePveFailedAddStepMsg.GCBattlePveFailedAddStepProto;
import buffer.GCBattlePveFailedNoticeMsg.GCBattlePveFailedNoticeProto;
import buffer.GCBattlePveResult.GCBattlePveResultProto;
import buffer.GCBattlePveStart.GCBattlePveStartProto;
import buffer.GCChatMsg.GCChatProto;
import buffer.GCCityAwardMsg.GCCityAwardProto;
import buffer.GCCityInfoMsg.GCCityInfoProto;
import buffer.GCCommonResponseMsg.GCCommonResponseProto;
import buffer.GCDeleteFriendMsg.GCDeleteFriendProto;
import buffer.GCDoFriendReqMsg.GCDoFriendReqProto;
import buffer.GCDoLoginMsg.GCDoLoginProto;
import buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto;
import buffer.GCEliteDuplicateBuySteps.GCEliteDuplicateBuyStepsProto;
import buffer.GCEliteDuplicateGameEnd.GCEliteDuplicateGameEndProto;
import buffer.GCEliteDuplicateGameStart.GCEliteDuplicateGameStartProto;
import buffer.GCEliteDuplicateGiveUp.GCEliteDuplicateGiveUpProto;
import buffer.GCEliteDuplicateInfo.GCEliteDuplicateInfoProto;
import buffer.GCEliteDuplicateSyncStep.GCEliteDuplicateSyncStepProto;
import buffer.GCFriendFabulousMsg.GCFriendFabulousProto;
import buffer.GCFriendGetInfoByShowIdMsg.GCFriendGetInfoByShowIdProto;
import buffer.GCFriendReqListMsg.GCFriendReqListProto;
import buffer.GCFriendsListMsg.GCFriendsListProto;
import buffer.GCFriendsRankInfoMsg.GCFriendsRankInfoProto;
import buffer.GCFunctionOpenInfoMsg.GCFunctionOpenInfoProto;
import buffer.GCGetAchieveListMsg.GCGetAchieveListProto;
import buffer.GCGetAchieveRewardMsg.GCGetAchieveRewardProto;
import buffer.GCGetBagListMsg.GCGetBagListProto;
import buffer.GCGetDayTaskListMsg.GCGetDayTaskListProto;
import buffer.GCGetDayTaskRewardMsg.GCGetDayTaskRewardProto;
import buffer.GCGetHeroListMsg.GCGetHeroListProto;
import buffer.GCGetLoginNoticeMsg.GCGetLoginNoticeProto;
import buffer.GCGetLoginSuccessInfoMsg.GCGetLoginSuccessInfoProto;
import buffer.GCGetLuckDrawInfoMsg.GCGetLuckDrawInfoProto;
import buffer.GCGetMonthCardInfoMsg.GCGetMonthCardInfoProto;
import buffer.GCGetPayInfoMsg.GCGetPayInfoProto;
import buffer.GCGetRedHatinfoMsg.GCGetRedHatinfoProto;
import buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto;
import buffer.GCGetSevenSignStatusMsg.GCGetSevenSignStatusProto;
import buffer.GCHeartBeatMsg.GCHeartBeatProto;
import buffer.GCHeroCommentDelMsg.GCHeroCommentDelProto;
import buffer.GCHeroCommentFabulousMsg.GCHeroCommentFabulousProto;
import buffer.GCHeroCommentGetInfoMsg.GCHeroCommentGetInfoProto;
import buffer.GCHeroCommentMsg.GCHeroCommentProto;
import buffer.GCHttpBeatMsg.GCHttpBeatProto;
import buffer.GCKickOfflineMsg.GCKickOfflineProto;
import buffer.GCLampMsg.GCLampMsgProto;
import buffer.GCLoadingRoleMsg.GCLoadingRoleProto;
import buffer.GCLuckDrawMsg.GCLuckDrawMsgProto;
import buffer.GCMailDeleteMsg.GCMailDeleteProto;
import buffer.GCMailReadMsg.GCMailReadProto;
import buffer.GCMailgetListMsg.GCMailgetListProto;
import buffer.GCModifyRoleNameMsg.GCModifyRoleNameProto;
import buffer.GCPowerFullMsg.GCPowerFullProto;
import buffer.GCPowerGetInfoMsg.GCPowerGetInfoProto;
import buffer.GCPushPaySuccessMsg.GCPushPaySuccessProto;
import buffer.GCPveBuyPowerMsg.GCPveBuyPowerProto;
import buffer.GCPveCityAttachMsg.GCPveCityAttachProto;
import buffer.GCPveCityAwardMsg.GCPveCityAwardProto;
import buffer.GCPveCityBuyDecMsg.GCPveCityBuyDecProto;
import buffer.GCPveCityInfoMsg.GCPveCityInfoProto;
import buffer.GCPveCityUnlockMsg.GCPveCityUnlockProto;
import buffer.GCRankInfoMsg.GCRankInfoProto;
import buffer.GCReCeiveFirstPayRewardMsg.GCReCeiveFirstPayRewardProto;
import buffer.GCReceiveFriendShipMsg.GCReceiveFriendShipProto;
import buffer.GCReceiveNoLimitPowerMsg.GCReceiveNoLimitPowerProto;
import buffer.GCReceiveSevenSignRewardMsg.GCReceiveSevenSignRewardProto;
import buffer.GCRedHatCancelMsg.GCRedHatCancelProto;
import buffer.GCRedHatMsg.GCRedHatProto;
import buffer.GCRoleBattleSelect.GCRoleBattleSelectProto;
import buffer.GCRoleHomePageInfoMsg.GCRoleHomePageInfoProto;
import buffer.GCRoleHomePageSetUpMsg.GCRoleHomePageSetUpProto;
import buffer.GCSellBagMsg.GCSellBagProto;
import buffer.GCSendFriendReqMsg.GCSendFriendReqProto;
import buffer.GCSendFriendShipMsg.GCSendFriendShipProto;
import buffer.GCSendGuideMsg.GCSendGuideProto;
import buffer.GCServerBackMsg.GCServerBackProto;
import buffer.GCShareInfoMsg.GCShareInfoProto;
import buffer.GCShareMsg.GCShareProto;
import buffer.GCShopBuyMsg.GCShopBuyProto;
import buffer.GCSignOutBattleMsg.GCSignOutBattleProto;
import buffer.GCSubscribeMonthCardBMsg.GCSubscribeMonthCardBProto;
import buffer.GCUpdateRoleGuideMsg.GCUpdateRoleGuideProto;
import buffer.GCUseItemMsg.GCUseItemProto;
import buffer.GCVisitFriendMsg.GCVisitFriendProto;

@ProcessorDefine(protos = {
		// <HTTP>
		// login
		GCDoLoginProto.class, GCGetLoginSuccessInfoProto.class,

		// gaming
		GCLoadingRoleProto.class, GCCommonResponseProto.class, GCGetRedHatinfoProto.class, GCFunctionOpenInfoProto.class, GCShareInfoProto.class,
		GCGetHeroListProto.class, GCGetSevenSignStatusProto.class, CGChatGmProto.class,

		// ALL
		GCBanchChatProto.class, GCBattleChangeChoiceHeroAndHeroDecProto.class, GCBattlePveFailedAddStepProto.class, GCBattlePveFailedNoticeProto.class,
		GCChatProto.class, GCCommonResponseProto.class, GCDeleteFriendProto.class, GCDoFriendReqProto.class, GCDoLoginProto.class, GCFriendFabulousProto.class,
		GCFriendGetInfoByShowIdProto.class, GCFriendReqListProto.class, GCFriendsListProto.class, GCFriendsRankInfoProto.class, GCFunctionOpenInfoProto.class,
		GCGetAchieveListProto.class, GCGetAchieveRewardProto.class, GCGetBagListProto.class, GCGetDayTaskListProto.class, GCGetDayTaskRewardProto.class,
		GCGetHeroListProto.class, GCGetLoginNoticeProto.class, GCGetLoginSuccessInfoProto.class, GCGetLuckDrawInfoProto.class, GCGetMonthCardInfoProto.class,
		GCGetPayInfoProto.class, GCGetRedHatinfoProto.class, GCGetRoleFirstPayInfoProto.class, GCGetSevenSignStatusProto.class, GCHeartBeatProto.class,
		GCHeroCommentDelProto.class, GCHeroCommentFabulousProto.class, GCHeroCommentGetInfoProto.class, GCHeroCommentProto.class, GCHttpBeatProto.class,
		GCKickOfflineProto.class, GCLampMsgProto.class, GCLoadingRoleProto.class, GCLuckDrawMsgProto.class, GCMailDeleteProto.class, GCMailgetListProto.class,
		GCMailReadProto.class, GCModifyRoleNameProto.class, GCPowerFullProto.class, GCPowerGetInfoProto.class, GCPushPaySuccessProto.class,
		GCPveBuyPowerProto.class, GCRankInfoProto.class, GCReCeiveFirstPayRewardProto.class, GCReceiveFriendShipProto.class, GCReceiveNoLimitPowerProto.class,
		GCReceiveSevenSignRewardProto.class, GCRedHatCancelProto.class, GCRedHatProto.class, GCRoleHomePageInfoProto.class, GCRoleHomePageSetUpProto.class,
		GCSellBagProto.class, GCSendFriendReqProto.class, GCSendFriendShipProto.class, GCSendGuideProto.class, GCServerBackProto.class, GCShareInfoProto.class,
		GCShareProto.class, GCShopBuyProto.class, GCSignOutBattleProto.class, GCSubscribeMonthCardBProto.class, GCUpdateRoleGuideProto.class,
		GCUseItemProto.class, GCVisitFriendProto.class, GCPveCityAttachProto.class, GCPveCityAwardProto.class, GCPveCityUnlockProto.class,
		GCPveCityInfoProto.class, GCPveCityBuyDecProto.class, GCCityInfoProto.class, GCCityAwardProto.class,
		// </HTTP>

		// <Battle>
		GCBattlePveStartProto.class, GCBattlePveResultProto.class, GCRoleBattleSelectProto.class,

		// 精英副本
		GCEliteDuplicateInfoProto.class, GCEliteDuplicateBuyChallengeTimesProto.class, GCEliteDuplicateSyncStepProto.class, GCEliteDuplicateGiveUpProto.class,
		GCEliteDuplicateGameStartProto.class, GCEliteDuplicateGameEndProto.class, GCEliteDuplicateBuyStepsProto.class
		// </Battle>

		// <Chat>
		// </Chat>
})
public class RobotMessageProcessor implements IClientProcessor {

	@Override
	public void process(Client client, int msgType, GeneratedMessage msg) {
		Robot robot = null;
		if (client instanceof Robot) {
			robot = (Robot) client;
		}

		if (Config.IS_PRINT_LOG && ClientMessageLogSample.isPrintMsg(msgType)) {
			Loggers.robotLogger.info("[Receive][" + msgType + "] robotId = " + robot.getId() + ", uid = " + client.getUid());
		}

		switch (msgType) {
		// HTTP
		case MsgType.GCDoLoginMsg: // 登录
			robot.getLoginManager().GCDoLoginMsg((GCDoLoginProto) msg);
			break;
		case MsgType.GCGetLoginSuccessInfoMsg:// 登录成功
			robot.getLoginManager().GCGetLoginSuccessInfoProto((GCGetLoginSuccessInfoProto) msg);
			break;
		case MsgType.GCLoadingRoleMsg:// 角色信息
			robot.getLoginManager().GCLoadingRoleProto((GCLoadingRoleProto) msg);
			break;
		case MsgType.GCCommonResponseMsg:// 货币
			robot.getInfoManager().GCCommonResponse((GCCommonResponseProto) msg);
			break;
		case MsgType.GCGetRedHatInfoMsg:// 红点
			robot.getInfoManager().setGcGetRedHatinfoProto((GCGetRedHatinfoProto) msg);
			break;
		case MsgType.GCFunctionOpenInfoMsg:// 开启功能
			robot.getInfoManager().setGcFunctionOpenInfoProto((GCFunctionOpenInfoProto) msg);
			break;
		case MsgType.GCShareInfoMsg:// 分享&广告
			robot.getInfoManager().setGcShareInfoProto((GCShareInfoProto) msg);
			break;
		case MsgType.GCGetHeroListMsg:// 英雄
			robot.getInfoManager().setGcGetHeroListProto((GCGetHeroListProto) msg);
			break;
		case MsgType.GCGetSevenSignStatusMsg:// 七日签到
			robot.getInfoManager().setGcGetSevenSignStatusProto((GCGetSevenSignStatusProto) msg);
			break;
		case MsgType.GCPveCityInfoMsg:
			PveCityInfo info = robot.getInfoManager().getPveCityInfo();
			if (info == null) {
				info = new PveCityInfo();
				robot.getInfoManager().setPveCityInfo(info);
			}

			GCPveCityInfoProto gcPveCityInfoProto = (GCPveCityInfoProto) msg;
			info.setCityId(gcPveCityInfoProto.getCityId());
			info.setPveId(gcPveCityInfoProto.getPveId());
			info.setPveStatus(gcPveCityInfoProto.getPveStatus());
			info.getCityIds().addAll(gcPveCityInfoProto.getCityIdsList());

			CityInfo city = info.getCityInfo();
			if (city == null) {
				city = new CityInfo();
				info.setCityInfo(city);
			}
			List<DupInfo> dups = city.getDupInfo();
			if (dups == null) {
				dups = new ArrayList<DupInfo>();
				city.setDupInfo(dups);
			}
			city.setCityId(gcPveCityInfoProto.getCityInfo().getCityId());
			dups.clear();
			for (int i = 0; i < gcPveCityInfoProto.getCityInfo().getDupInfoCount(); i++) {
				DupInfo dup = new DupInfo();
				dup.setDupId(gcPveCityInfoProto.getCityInfo().getDupInfo(i).getDupId());
				dup.setDupNum(gcPveCityInfoProto.getCityInfo().getDupInfo(i).getDupNum());
				dup.setStar(gcPveCityInfoProto.getCityInfo().getDupInfo(i).getDupStar());
				dups.add(dup);
			}
			break;

		case MsgType.GCEliteDuplicateInfo:// 精英副本
			robot.getInfoManager().setGcEliteDuplicateInfoProto((GCEliteDuplicateInfoProto) msg);
			break;

		// Battle

		// Chat
		case MsgType.GCServerBackMsg:
			// 长连接已建立
			// 1. 聊天服什么都不需要做
			// 2. 战斗服由对应的测试用例处理
			robot.getChatManager().GCServerBack((GCServerBackProto) msg);
			break;

		default:
			break;
		}

		try {
			for (IRobotStrategy strategy : robot.getStrategyList()) {
				strategy.onResponse(msgType, msg);
			}
		} catch (Exception e) {
			Loggers.robotLogger.error(e);
		}

	}
}
