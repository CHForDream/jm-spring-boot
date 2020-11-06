package com.game.rpc.vo;

import java.io.Serializable;

public class RoleStatus implements Serializable {
	private static final long serialVersionUID = -6784990223238500805L;

	private int status = 0;
	private long offMill = 0;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getOffMill() {
		return offMill;
	}

	public void setOffMill(long offMill) {
		this.offMill = offMill;
	}

}
