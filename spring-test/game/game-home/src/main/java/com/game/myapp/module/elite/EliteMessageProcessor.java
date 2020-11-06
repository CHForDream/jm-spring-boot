package com.game.myapp.module.elite;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGEliteDuplicateBuyChallengeTimes.CGEliteDuplicateBuyChallengeTimesProto;
import buffer.CGEliteDuplicateBuySteps.CGEliteDuplicateBuyStepsProto;
import buffer.CGEliteDuplicateGameEnd.CGEliteDuplicateGameEndProto;
import buffer.CGEliteDuplicateGameStart.CGEliteDuplicateGameStartProto;
import buffer.CGEliteDuplicateGiveUp.CGEliteDuplicateGiveUpProto;
import buffer.CGEliteDuplicateInfo.CGEliteDuplicateInfoProto;
import buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto;

@Component
@ProcessorDefine(protos = {

		CGEliteDuplicateInfoProto.class, CGEliteDuplicateBuyChallengeTimesProto.class, CGEliteDuplicateSyncStepProto.class,

		CGEliteDuplicateGiveUpProto.class, CGEliteDuplicateGameStartProto.class, CGEliteDuplicateGameEndProto.class,

		CGEliteDuplicateBuyStepsProto.class

})
public class EliteMessageProcessor extends HttpMessageProcessor {
	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGEliteDuplicateInfo:// 精英副本.信息列表
			GameGlobals.eliteManager.CGEliteDuplicateInfo(msgBack, userBean);
			break;
		case MsgType.CGEliteDuplicateBuyChallengeTimes:// 精英副本.购买次数
			GameGlobals.eliteManager.CGEliteDuplicateBuyChallengeTimes(msgBack, userBean, (CGEliteDuplicateBuyChallengeTimesProto) msg);
			break;
		case MsgType.CGEliteDuplicateSyncStep:// 精英副本.同步步数
			GameGlobals.eliteManager.CGEliteDuplicateSyncStep(msgBack, userBean, (CGEliteDuplicateSyncStepProto) msg);
			break;
		case MsgType.CGEliteDuplicateGiveUp:// 精英副本.放弃挑战
			GameGlobals.eliteManager.CGEliteDuplicateGiveUp(msgBack, userBean, (CGEliteDuplicateGiveUpProto) msg);
			break;
		case MsgType.CGEliteDuplicateGameStart:// 精英副本.游戏开始
			GameGlobals.eliteManager.CGEliteDuplicateGameStart(msgBack, userBean, (CGEliteDuplicateGameStartProto) msg);
			break;
		case MsgType.CGEliteDuplicateGameEnd:// 精英副本.游戏结束
			GameGlobals.eliteManager.CGEliteDuplicateGameEnd(msgBack, userBean, (CGEliteDuplicateGameEndProto) msg);
			break;
		case MsgType.CGEliteDuplicateBuySteps:// 精英副本.购买步数
			GameGlobals.eliteManager.CGEliteDuplicateBuySteps(msgBack, userBean, (CGEliteDuplicateBuyStepsProto) msg);
			break;
		}
	}
}
