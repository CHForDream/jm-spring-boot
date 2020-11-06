package com.game.myapp.module.event.obj;

import java.io.Serializable;

import lombok.Data;

@Data
public class PayFinishObj implements Serializable {
	private static final long serialVersionUID = 1L;

	private long uid;
	private int payId;

	public PayFinishObj(long uid, int payId) {
		this.uid = uid;
		this.payId = payId;
	}
}