package com.game.myapp.module.role;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGDoLoginMsg.CGDoLoginProto;
import buffer.CGGetLoginSuccessInfoMsg.CGGetLoginSuccessInfoProto;
import buffer.CGLoadingRoleMsg.CGLoadingRoleProto;
import buffer.CGModifyRoleNameMsg.CGModifyRoleNameProto;
import buffer.CGPowerFullMsg.CGPowerFullProto;
import buffer.CGPowerGetInfoMsg.CGPowerGetInfoProto;
import buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto;
import buffer.CGRoleBattleSelect.CGRoleBattleSelectProto;
import buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto;
import buffer.CGRoleHomePageSetUpMsg.CGRoleHomePageSetUpProto;
import buffer.CGSubscribeMonthCardBMsg.CGSubscribeMonthCardBProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGDoLoginProto.class, CGGetLoginSuccessInfoProto.class, CGLoadingRoleProto.class, CGRoleBattleSelectProto.class, CGModifyRoleNameProto.class,
		CGPowerGetInfoProto.class, CGRoleHomePageInfoProto.class, CGRoleHomePageSetUpProto.class, CGPowerFullProto.class, CGReceiveNoLimitPowerProto.class,
		CGSubscribeMonthCardBProto.class,

})
public class RoleMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGDoLoginMsg:
			GameGlobals.loginManager.doLogin(msgBack, userBean.getIp(), (CGDoLoginProto) msg);
			break;
		case MsgType.CGGetLoginSuccessInfoMsg:
			GameGlobals.roleManager.CGGetLoginSuccessInfo(msgBack, userBean.getUid(), (CGGetLoginSuccessInfoProto) msg);
			break;
		case MsgType.CGLoadingRoleMsg:
			GameGlobals.roleManager.CGLoadingRole(msgBack, userBean, (CGLoadingRoleProto) msg);
			break;
		case MsgType.CGModifyRoleNameMsg:
			GameGlobals.roleManager.CGModifyRoleName(msgBack, userBean.getUid(), (CGModifyRoleNameProto) msg);
			break;
		case MsgType.CGPowerGetInfoMsg:
			GameGlobals.roleManager.CGPowerGetInfo(msgBack, userBean.getUid(), (CGPowerGetInfoProto) msg);
			break;
		case MsgType.CGRoleHomePageInfoMsg:
			GameGlobals.rolehHomepageManager.CGRoleHomePageInfo(msgBack, userBean.getUid(), (CGRoleHomePageInfoProto) msg);
			break;
		case MsgType.CGRoleHomePageSetUpMsg:
			GameGlobals.rolehHomepageManager.CGRoleHomePageSetUp(msgBack, userBean.getUid(), (CGRoleHomePageSetUpProto) msg);
			break;
		case MsgType.CGPowerFullMsg:
			GameGlobals.roleManager.CGPowerFull(msgBack, userBean, (CGPowerFullProto) msg);
			break;
		case MsgType.CGReceiveNoLimitPowerMsg:
			GameGlobals.monthCardManager.CGReceiveNoLimitPower(msgBack, userBean, (CGReceiveNoLimitPowerProto) msg);
			break;
		case MsgType.CGSubscribeMonthCardBMsg:
			GameGlobals.monthCardManager.CGSubscribeMonthCardB(msgBack, userBean, (CGSubscribeMonthCardBProto) msg);
			break;
		case MsgType.CGRoleBattleSelect:
			GameGlobals.roleManager.CGRoleBattleSelect(msgBack, userBean, (CGRoleBattleSelectProto) msg);
			break;
		}
	}
}
