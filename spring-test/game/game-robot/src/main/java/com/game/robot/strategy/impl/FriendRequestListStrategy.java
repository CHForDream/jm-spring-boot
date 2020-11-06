package com.game.robot.strategy.impl;

import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.game.robot.Robot;
import com.game.robot.config.Config;
import com.game.robot.strategy.EStrategy;
import com.game.robot.strategy.EStrategyType;
import com.game.robot.strategy.LoopExecuteStrategy;
import com.game.utils.RandomUtil;
import com.google.protobuf.GeneratedMessage;

import buffer.CGDoFriendReqMsg.CGDoFriendReqProto;
import buffer.CGFriendReqListMsg.CGFriendReqListProto;
import buffer.GCFriendReqListMsg.FriendReqGCFriendReqList;
import buffer.GCFriendReqListMsg.GCFriendReqListProto;

@EStrategyType(strategy = EStrategy.FRIEND_REQUEST_LIST)
public class FriendRequestListStrategy extends LoopExecuteStrategy {

	public FriendRequestListStrategy(Robot robot, long delay, long minInterval, long maxInterval) {
		super(robot, delay, RandomUtil.random(minInterval, maxInterval));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			Loggers.robotLogger.info("FriendRequestListStrategy start. robotId = " + getRobot().getId());
		}

//		[CGFriendReqListProto]msgType:2013;
		CGFriendReqListProto.Builder cgFriendReqListProto = CGFriendReqListProto.newBuilder();
		getRobot().sendHttpMessage(cgFriendReqListProto.getMsgType(), cgFriendReqListProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCFriendReqListMsg:
//			[GCFriendReqListProto]friends{;friendId:11591682766001;nickName:"test2";avatar:"";lv:"1";vip:0;sex:1;};
			GCFriendReqListProto gcFriendReqListProto = (GCFriendReqListProto) msg;
			if (gcFriendReqListProto.getFriendsCount() == 0) {
				if (Config.IS_PRINT_LOG) {
					Loggers.robotLogger.info("FriendRequestListStrategy end 1. robotId = " + getRobot().getId());
				}
				return;
			}

			FriendReqGCFriendReqList friendReqGCFriendReqList = gcFriendReqListProto.getFriendsList().get(0);
			long friendUid = friendReqGCFriendReqList.getFriendId();

//			[CGDoFriendReqProto]msgType:2012;uid:11591682766001;type:1;
			CGDoFriendReqProto.Builder cgDoFriendReqProto = CGDoFriendReqProto.newBuilder();
			cgDoFriendReqProto.setUid(friendUid);
			cgDoFriendReqProto.setType(RandomUtil.random.nextInt(2) + 1);// 1同意, 2拒绝
			getRobot().sendHttpMessage(cgDoFriendReqProto.getMsgType(), cgDoFriendReqProto);
			break;
		case MsgType.GCDoFriendReqMsg:
			if (Config.IS_PRINT_LOG) {
				Loggers.robotLogger.info("FriendRequestListStrategy end 2. robotId = " + getRobot().getId());
			}
			break;
		}
	}
}
