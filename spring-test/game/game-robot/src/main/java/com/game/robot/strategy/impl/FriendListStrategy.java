package com.game.robot.strategy.impl;

import java.util.List;

import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.game.robot.Robot;
import com.game.robot.config.Config;
import com.game.robot.strategy.EStrategy;
import com.game.robot.strategy.EStrategyType;
import com.game.robot.strategy.LoopExecuteStrategy;
import com.game.utils.RandomUtil;
import com.google.protobuf.GeneratedMessage;

import buffer.CGFriendFabulousMsg.CGFriendFabulousProto;
import buffer.CGFriendsListMsg.CGFriendsListProto;
import buffer.CGSendFriendReqMsg.CGSendFriendReqProto;
import buffer.GCFriendsListMsg.FriendGCFriendsList;
import buffer.GCFriendsListMsg.GCFriendsListProto;

@EStrategyType(strategy = EStrategy.FRIEND_LIST)
public class FriendListStrategy extends LoopExecuteStrategy {

	public FriendListStrategy(Robot robot, long delay, long minInterval, long maxInterval) {
		super(robot, delay, RandomUtil.random(minInterval, maxInterval));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			Loggers.robotLogger.info("FriendListStrategy start. robotId = " + getRobot().getId());
		}

		CGFriendsListProto.Builder cgFriendsListProto = CGFriendsListProto.newBuilder();
		cgFriendsListProto.setType(0).setResource(0);
		getRobot().sendHttpMessage(cgFriendsListProto.getMsgType(), cgFriendsListProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCFriendsListMsg:
			GCFriendsListProto gcFriendsListProto = (GCFriendsListProto) msg;
			int friendsCount = gcFriendsListProto.getFriendsCount();// 好友数量
			int fabulousFriendsCount = gcFriendsListProto.getFabulousFriendsCount();// 已点赞好友数量
			// 如果有好友, 并且有还没点赞的好友, 则给好友点赞
			if (friendsCount > 0 && friendsCount > fabulousFriendsCount) {
				doFriendFabulous(gcFriendsListProto);
				return;
			}

			// 如果有推荐好友, 加好友
			int recommentFriendsCount = gcFriendsListProto.getRecommentFriendsCount();// 推荐好友数量
			if (recommentFriendsCount > 0) {
				doAddFriend(gcFriendsListProto);
				return;
			}

			// 没有好友, 也没有推荐好友, 结束Case
			if (Config.IS_PRINT_LOG) {
				Loggers.robotLogger.info("FriendListStrategy end 1. robotId = " + getRobot().getId());
			}
			return;
		case MsgType.GCFriendFabulousMsg:
			// 完成好友点赞, 结束Case
			if (Config.IS_PRINT_LOG) {
				Loggers.robotLogger.info("FriendListStrategy end 2. robotId = " + getRobot().getId());
			}
			break;
		case MsgType.GCSendFriendReqMsg:
			// 完成加好友, 结束Case
			if (Config.IS_PRINT_LOG) {
				Loggers.robotLogger.info("FriendListStrategy end 3. robotId = " + getRobot().getId());
			}
			break;
		}
	}

	private void doFriendFabulous(GCFriendsListProto gcFriendsListProto) {
		List<FriendGCFriendsList> friendsList = gcFriendsListProto.getFriendsList();
		List<Long> fabulousFriendsList = gcFriendsListProto.getFabulousFriendsList();
		for (FriendGCFriendsList friend : friendsList) {
			if (fabulousFriendsList.contains(friend.getFriendId())) {
				continue;
			}

			// 给好友点赞
			// [CGFriendFabulousProto]msgType:24019;targetUid:11591682766001;
			CGFriendFabulousProto.Builder cgFriendFabulousProto = CGFriendFabulousProto.newBuilder();
			cgFriendFabulousProto.setTargetUid(friend.getFriendId());
			getRobot().sendHttpMessage(cgFriendFabulousProto.getMsgType(), cgFriendFabulousProto);
			return;
		}
	}

	private void doAddFriend(GCFriendsListProto gcFriendsListProto) {
		FriendGCFriendsList friendGCFriendsList = gcFriendsListProto.getRecommentFriendsList().get(0);
		long friendUid = friendGCFriendsList.getFriendId();

		// 加推荐好友
//		[CGSendFriendReqProto]msgType:2011;uid:11591682766001;declaration:"";
		CGSendFriendReqProto.Builder cgSendFriendReqProto = CGSendFriendReqProto.newBuilder();
		cgSendFriendReqProto.setUid(friendUid).setDeclaration("");
		getRobot().sendHttpMessage(cgSendFriendReqProto.getMsgType(), cgSendFriendReqProto);
	}
}
