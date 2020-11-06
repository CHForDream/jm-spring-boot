
package com.game.gm.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.CMDConstants;
import com.game.db.entity.RoleEntity;
import com.game.db.entity.ShowIdEntity;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.gm.vo.ResponseRoleInfoBean;
import com.game.utils.StringUtils;
import com.game.vo.ResponseData;

/**
 * 查询玩家信息
 * 
 * @author lpf
 *
 */
public class CmdSelectRoleInfoHandler extends AbsGmContoller {

	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		ResponseData<String, Object> data = ResponseData.newBuild();
		String uidStr = request.getParameter("uid");
		String showidStr = request.getParameter("showid");
		boolean hasUid = !StringUtils.isEmpty(uidStr);
		boolean hasShowid = !StringUtils.isEmpty(showidStr);
		if (!hasUid && !hasShowid) {
			data.put(CMDConstants.KEY_STATE, "No uid or showid.");
			return data;
		}

		RoleEntity roleEntity = null;
		if (hasUid) {
			try {
				long uid = Long.parseLong(uidStr);
				roleEntity = Globals.getEntityProxy().getNotCache(RoleEntity.class, uid);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			if (roleEntity == null) {
				data.put(CMDConstants.KEY_STATE, CMDConstants.REASON_INVALID_UID);
				return data;
			}
		} else {
			try {
				long showId = Long.parseLong(showidStr);
				ShowIdEntity showIdEntity = Globals.getEntityProxy().getNotCache(ShowIdEntity.class, showId);
				if (showIdEntity == null) {
					data.put(CMDConstants.KEY_STATE, "Invalid showid: " + showidStr);
					return data;
				}

				roleEntity = Globals.getEntityProxy().getNotCache(RoleEntity.class, showIdEntity.getTargetUid());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			if (roleEntity == null) {
				data.put(CMDConstants.KEY_STATE, "No roleEntity for showid: " + showidStr);
				return data;
			}
		}

		ResponseRoleInfoBean roleInfo = new ResponseRoleInfoBean();
		roleInfo.setUid(roleEntity.getUid());
		roleInfo.setRoleName(roleEntity.getName());
		roleInfo.setLevel(1);
		roleInfo.setCreateTime(roleEntity.getCreateTime());
		roleInfo.setLastLoginTime(roleEntity.getLastLoginTime());

		data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_SUCCESS);
		data.put("roleInfo", roleInfo);
		return data;
	}
}
