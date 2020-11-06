package com.game.myapp.module.friends;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGDeleteFriendMsg.CGDeleteFriendProto;
import buffer.CGDoFriendReqMsg.CGDoFriendReqProto;
import buffer.CGFriendFabulousMsg.CGFriendFabulousProto;
import buffer.CGFriendGetInfoByShowIdMsg.CGFriendGetInfoByShowIdProto;
import buffer.CGFriendReqListMsg.CGFriendReqListProto;
import buffer.CGFriendsListMsg.CGFriendsListProto;
import buffer.CGFriendsRankInfoMsg.CGFriendsRankInfoProto;
import buffer.CGReceiveFriendShipMsg.CGReceiveFriendShipProto;
import buffer.CGSendFriendReqMsg.CGSendFriendReqProto;
import buffer.CGSendFriendShipMsg.CGSendFriendShipProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGDeleteFriendProto.class, CGDoFriendReqProto.class, CGFriendReqListProto.class, CGFriendsListProto.class,

		CGReceiveFriendShipProto.class, CGSendFriendReqProto.class, CGSendFriendShipProto.class, CGFriendsRankInfoProto.class, CGFriendFabulousProto.class,
		CGFriendGetInfoByShowIdProto.class,

})
public class FriendMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGDeleteFriendMsg:
			GameGlobals.friendManager.CGDeleteFriendMsg(msgBack, userBean, (CGDeleteFriendProto) msg);
			break;
		case MsgType.CGDoFriendReqMsg:
			GameGlobals.friendManager.CGDoFriendReqMsg(msgBack, userBean, (CGDoFriendReqProto) msg);
			break;
		case MsgType.CGFriendReqListMsg:
			GameGlobals.friendManager.CGFriendReqListMsg(msgBack, userBean, (CGFriendReqListProto) msg);
			break;
		case MsgType.CGFriendsListMsg:
			CGFriendsListProto friendsListProto = (CGFriendsListProto) msg;
			if (friendsListProto.getType() == FriendManager.LIST_TYPE_LATELY) {
				GameGlobals.friendManager.getLatelyList(msgBack, userBean, friendsListProto);
				break;
			}
			GameGlobals.friendManager.CGFriendsListMsg(msgBack, userBean, friendsListProto);
			break;
		case MsgType.CGReceiveFriendShipMsg:
			GameGlobals.friendManager.CGReceiveFriendShipMsg(msgBack, userBean, (CGReceiveFriendShipProto) msg);
			break;
		case MsgType.CGSendFriendReqMsg:
			GameGlobals.friendManager.CGSendFriendReqMsg(msgBack, userBean, (CGSendFriendReqProto) msg);
			break;
		case MsgType.CGSendFriendShipMsg:
			GameGlobals.friendManager.CGSendFriendShipMsg(msgBack, userBean, (CGSendFriendShipProto) msg);
			break;
		case MsgType.CGFriendsRankInfoMsg:
			GameGlobals.friendManager.CGFriendsRankInfo(msgBack, userBean, (CGFriendsRankInfoProto) msg);
			break;
		case MsgType.CGFriendFabulousMsg:
			GameGlobals.friendManager.CGFriendFabulous(msgBack, userBean, (CGFriendFabulousProto) msg);
			break;
		case MsgType.CGFriendGetInfoByShowIdMsg:
			GameGlobals.friendManager.CGFriendGetInfoByShowId(msgBack, userBean, (CGFriendGetInfoByShowIdProto) msg);
			break;
		}
	}
}
