package com.game.myapp.module.pvecity.handler.impl;

import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.core.handler.MsgBack;
import com.game.db.entity.PveCityEntity;
import com.game.generate.bean.PveBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.pvecity.handler.IPveCityStatusHandler;
import com.game.myapp.module.pvecity.pveenum.EPveStatus;

public class UnLockStatusHandler implements IPveCityStatusHandler {

	private static UnLockStatusHandler _Instance = new UnLockStatusHandler();

	public static UnLockStatusHandler getInstance() {
		return _Instance;
	}

	@Override
	public void process(MsgBack msgBack, long uid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			PveCityEntity pveCityEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
			if (pveCityEntity == null) {
				return;
			}
			PveBean pveBean = Datas.getCeilingData(PveBean.class, pveCityEntity.getCurPveTarget() + 1);
			if (pveBean != null) {
				pveCityEntity.setLastPveTarget(pveCityEntity.getCurPveTarget());
				pveCityEntity.setCurPveTarget(pveBean.getId());
				pveCityEntity.setTargetId(pveBean.getTargetId());
				pveCityEntity.setCurTargetStatus(EPveStatus.UNLOCK.getStatus());
			} else {
				pveCityEntity.setTargetId(-1);
			}
			// 同步下一关卡数据到role
//			updateRolePveTarget(pveBean, pveCityEntity.getUid());
		} finally {
			lock.unlock();
		}
	}

//	private void updateRolePveTarget(PveBean pveBean, long uid) {
//		RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(uid);
//		if (pveBean == null) {
//			roleEntity.setPveLastTaskId(roleEntity.getPveTaskId());
//			roleEntity.setPveTaskId(-1);
//		} else {
//			roleEntity.setPveTaskId(pveBean.getTargetId());
//		}
//		Globals.getEntityProxy().update(roleEntity);
//	}
}
