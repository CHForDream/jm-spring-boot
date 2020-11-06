package com.game.myapp.module.function;

import java.util.Arrays;
import java.util.List;

import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.db.entity.RoleGuideEntity;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.guide.GuideConst;
import com.game.myapp.module.redhat.RedHatBehavior;

import buffer.GCFunctionOpenInfoMsg;

/**
 * 系统开启
 * 
 * @author lpf
 *
 */
public class FunctionOpenManager {
	private FunctionCheckFactory factory = new FunctionCheckFactory();

	public List<Integer> check(long uid, int type, String... params) {
		List<Integer> opens = factory.getFunctionCheckHandler(type).checkFunctionOpen(uid, params);
		for (Integer openId : opens) {
			GameGlobals.guideManager.onCheck(uid, GuideConst.OPEN_TYPE_SYSTEM, openId);

			// 检测签到小红点
			if (openId == ISystemId.SYSTEM_SEVEN_SIGN) {
				GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.LOBBY_SIGN);
			}
		}
		return opens;
	}

	public boolean onCheck(long uid, int systemId) {
		return checkOpenFunction(uid, systemId);
	}

	/**
	 * 获取玩家开启的系统id
	 * 
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public List<?> getRoleOpenFunctionList(long uid) {
		try {
			RoleGuideEntity entity = GameGlobals.guideManager.getRoleGuideEntity(uid);
			if (entity == null) {
				return null;
			}
			List<?> result = Arrays.asList(entity.getSystemId().split(","));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 检测玩家是否开启了此系统
	 * 
	 * @param uid
	 * @param guideId
	 * @return
	 * @throws Exception
	 */
	private boolean checkOpenFunction(long uid, int systemId) {
		List<?> functionList = getRoleOpenFunctionList(uid);
		return functionList.contains(String.valueOf(systemId));
	}

	/**
	 * 返回给客户端开启的系统id集合
	 * 
	 * @param uid
	 * @return
	 */
	public GCFunctionOpenInfoMsg.GCFunctionOpenInfoProto.Builder getOpenFunctionBuilder(UserBean user) {
		GCFunctionOpenInfoMsg.GCFunctionOpenInfoProto.Builder builder = GCFunctionOpenInfoMsg.GCFunctionOpenInfoProto.newBuilder();
		try {
			List<?> functionList = getRoleOpenFunctionList(user.getUid());
			if (functionList == null) {
				return null;
			}
			for (Object id : functionList) {
				builder.addFunctionIds(Integer.parseInt(String.valueOf(id)));
			}
			builder.setStatus(ErrorCodeConst.SUCCESS);
			user.setFunctionOpen(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder;
	}
}
