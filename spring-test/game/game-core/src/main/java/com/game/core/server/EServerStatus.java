package com.game.core.server;

import java.util.HashMap;
import java.util.Map;

import com.game.core.server.handler.IStatusHandler;
import com.game.core.server.handler.impl.RunningStatusHandler;
import com.game.core.server.handler.impl.ShutDownStatusHandler;
import com.game.core.server.handler.impl.StopStatusHandler;
import com.game.core.server.handler.impl.WaitCloseStatusHandler;
import com.game.core.server.handler.impl.WaitStartStatusHandler;

public enum EServerStatus {
	STOP(-1, "停用", new StopStatusHandler()),
	SHUT_DOWN(0, "已关闭", new ShutDownStatusHandler()),
	WAIT_CLOSE(1, "等待关闭", new WaitCloseStatusHandler()),
	WAIT_START(2, "等待开启", new WaitStartStatusHandler()),
	RUNNING(3, "运行中", new RunningStatusHandler()),
	;

	private static Map<Integer, EServerStatus> es = new HashMap<Integer, EServerStatus>();
	static {
		for (int i = 0; i < EServerStatus.values().length; i++) {
			EServerStatus status = EServerStatus.values()[i];
			es.put(status.code, status);
		}
	}

	public static EServerStatus getServerStatus(int status) {
		return es.get(status);
	}

	private String desc;
	private int code;
	private IStatusHandler handler;

	EServerStatus(int code, String desc, IStatusHandler handler) {
		this.desc = desc;
		this.code = code;
		this.handler = handler;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public IStatusHandler getHandler() {
		return handler;
	}

	public void setHandler(IStatusHandler handler) {
		this.handler = handler;
	}

}
