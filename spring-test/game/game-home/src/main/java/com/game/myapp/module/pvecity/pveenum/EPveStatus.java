package com.game.myapp.module.pvecity.pveenum;

import java.util.HashMap;
import java.util.Map;

import com.game.myapp.module.pvecity.handler.IPveCityStatusHandler;
import com.game.myapp.module.pvecity.handler.impl.CompleteStatusHandler;
import com.game.myapp.module.pvecity.handler.impl.LockStatusHandler;
import com.game.myapp.module.pvecity.handler.impl.PvePassStatusHandler;
import com.game.myapp.module.pvecity.handler.impl.UnLockStatusHandler;

public enum EPveStatus {
	LOCK(0, LockStatusHandler.getInstance()),
	UNLOCK(1, UnLockStatusHandler.getInstance()),
	PVE_PASS(2, PvePassStatusHandler.getInstance()),
	COMPLETE(3, CompleteStatusHandler.getInstance()),
	;
	private static Map<Integer, EPveStatus> es = new HashMap<Integer, EPveStatus>();
	static {
		for (int i = 0; i < EPveStatus.values().length; i++) {
			EPveStatus pveStatus = EPveStatus.values()[i];
			es.put(pveStatus.getStatus(), pveStatus);
		}
	}

	public static EPveStatus getStatusById(int id) {
		return es.get(id);
	}

	private EPveStatus(int status, IPveCityStatusHandler handler) {
		this.status = status;
		this.handler = handler;
	}

	private int status;
	private IPveCityStatusHandler handler;

	public int getStatus() {
		return status;
	}

	public IPveCityStatusHandler getHandler() {
		return handler;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setHandler(IPveCityStatusHandler handler) {
		this.handler = handler;
	}
}
