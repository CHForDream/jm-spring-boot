package com.game.myapp.module.hero;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGGetHeroListMsg.CGGetHeroListProto;
import buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto;
import buffer.CGHeroCommentFabulousMsg.CGHeroCommentFabulousProto;
import buffer.CGHeroCommentGetInfoMsg.CGHeroCommentGetInfoProto;
import buffer.CGHeroCommentMsg.CGHeroCommentProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGGetHeroListProto.class, CGHeroCommentProto.class, CGHeroCommentFabulousProto.class, CGHeroCommentGetInfoProto.class, CGHeroCommentDelProto.class,

})
public class HeroMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGGetHeroListMsg:
			GameGlobals.heroManager.CGGetHeroList(msgBack, userBean);
			break;
		// 评论相关
		case MsgType.CGHeroCommentMsg:
			GameGlobals.heroManager.getHeroCommentManager().CGHeroComment(msgBack, userBean.getUid(), (CGHeroCommentProto) msg);
			break;
		case MsgType.CGHeroCommentFabulousMsg:
			GameGlobals.heroManager.getHeroCommentManager().CGHeroCommentFabulous(msgBack, userBean.getUid(), (CGHeroCommentFabulousProto) msg);
			break;
		case MsgType.CGHeroCommentGetInfoMsg:
			GameGlobals.heroManager.getHeroCommentManager().CGHeroCommentGetInfo(msgBack, userBean.getUid(), (CGHeroCommentGetInfoProto) msg);
			break;
		case MsgType.CGHeroCommentDelMsg:
			GameGlobals.heroManager.getHeroCommentManager().CGHeroCommentDel(msgBack, userBean.getUid(), (CGHeroCommentDelProto) msg);
			break;
		}
	}
}
