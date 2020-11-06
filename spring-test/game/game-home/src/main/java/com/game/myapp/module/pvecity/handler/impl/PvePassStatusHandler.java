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
import com.game.vo.item.ItemVo;

import buffer.GCPveCityAwardMsg;

public class PvePassStatusHandler implements IPveCityStatusHandler {

	private static PvePassStatusHandler _Instance = new PvePassStatusHandler();

	public static PvePassStatusHandler getInstance() {
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
			pveCityEntity.setCurTargetStatus(EPveStatus.PVE_PASS.getStatus());
			PveBean pveBean = Datas.get(PveBean.class, pveCityEntity.getCurPveTarget());
			if (pveBean == null) {
				return;
			}
			addAward(msgBack, pveBean, pveCityEntity);

			if (pveBean.getDupId() != 0) {
				pveCityEntity.addCityDup(pveBean.getCity(), pveBean.getDupId());
			}
			GameGlobals.pveCityManager.changPveStatus(msgBack, uid, EPveStatus.COMPLETE);
		} finally {
			lock.unlock();
		}
	}

	private void addAward(MsgBack msgBack, PveBean pveBean, PveCityEntity pveCityEntity) {
		if (pveCityEntity.getCurTargetStatus() == EPveStatus.COMPLETE.getStatus()) {
			return;
		}
		GCPveCityAwardMsg.GCPveCityAwardProto.Builder builder = GCPveCityAwardMsg.GCPveCityAwardProto.newBuilder();
		boolean flag = false;
		for (int i = 0; i < pveBean.getHardRewardList().size(); i++) {
			ItemVo info = pveBean.getHardRewardList().get(i);
			GCPveCityAwardMsg.RewardInfo.Builder rewardBuilder = GCPveCityAwardMsg.RewardInfo.newBuilder();
			rewardBuilder.setItemId(info.getId());
			rewardBuilder.setItemType(info.getType());
			rewardBuilder.setItemNum(info.getNum());
			builder.addHardAwards(rewardBuilder);
			flag = true;
		}
		for (int i = 0; i < pveBean.getChessRewardList().size(); i++) {
			ItemVo info = pveBean.getChessRewardList().get(i);
			GCPveCityAwardMsg.RewardInfo.Builder rewardBuilder = GCPveCityAwardMsg.RewardInfo.newBuilder();
			rewardBuilder.setItemId(info.getId());
			rewardBuilder.setItemType(info.getType());
			rewardBuilder.setItemNum(info.getNum());
			builder.addChessAwards(rewardBuilder);
			flag = true;
		}
		if (flag) {
			msgBack.addBuilder(builder);
		}
	}

}
