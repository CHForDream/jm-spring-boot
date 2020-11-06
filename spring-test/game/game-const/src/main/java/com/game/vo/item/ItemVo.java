package com.game.vo.item;

import java.io.Serializable;

import lombok.Data;

@Data
public class ItemVo implements Serializable, Cloneable {
	private static final long serialVersionUID = -1961601193647156258L;

	private int type;
	private int id;
	private int num;

	public ItemVo() {
	}

	public ItemVo(int type, int id, int num) {
		this.type = type;
		this.id = id;
		this.num = num;
	}

	public String toItemString() {
		return type + "," + id + "," + num;
	}

	@Override
	public ItemVo clone() {
		ItemVo itemVo = null;
		try {
			itemVo = (ItemVo) super.clone();
		} catch (Exception e) {
		}
		return itemVo;
	}
}
