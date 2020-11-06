package com.game.vo.item;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BattleItemVo extends ItemVo implements Serializable {
	private static final long serialVersionUID = -7888946987030247019L;

	private int freeNum;

	public BattleItemVo(int type, int id, int num) {
		super(type, id, num);
	}
}
