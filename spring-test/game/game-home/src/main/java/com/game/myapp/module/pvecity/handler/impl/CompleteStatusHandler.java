package com.game.myapp.module.pvecity.handler.impl;

import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.core.handler.MsgBack;
import com.game.db.entity.PveCityEntity;
import com.game.db.entity.RoleEntity;
import com.game.generate.bean.PveBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.pvecity.handler.IPveCityStatusHandler;
import com.game.myapp.module.pvecity.pveenum.EPveStatus;

public class CompleteStatusHandler implements IPveCityStatusHandler {

	private static CompleteStatusHandler _Instance = new CompleteStatusHandler();

	public static CompleteStatusHandler getInstance() {
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
			int curTarget = pveCityEntity.getCurPveTarget();
			pveCityEntity.setCurTargetStatus(EPveStatus.COMPLETE.getStatus());

			PveBean pveBean = Datas.getCeilingData(PveBean.class, curTarget + 1);
			if (pveBean != null && pveBean.getCity() != pveCityEntity.getCurCityId()) {
				// 开启新地图
				int lastCity = pveCityEntity.getCurCityId();
				pveCityEntity.getCityAwardMap().put(lastCity, 1);
				pveCityEntity.setCurCityId(pveBean.getCity());
				pveCityEntity.setCityId(pveBean.getCity());
				pveCityEntity.addCityId(pveBean.getCity());

				GameGlobals.eliteManager.onCityClear(uid, lastCity);
				GameGlobals.pveCityManager.changPveStatus(msgBack, uid, EPveStatus.UNLOCK);

				RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
				// 体力加满
				GameGlobals.roleManager.addPowerFull(role);
				GameGlobals.roleManager.packGCPowerGetInfoProto(msgBack, uid);
			}
		} finally {
			lock.unlock();
		}
	}
}
