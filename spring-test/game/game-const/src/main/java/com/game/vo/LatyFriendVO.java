package com.game.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class LatyFriendVO implements Serializable {
	private static final long serialVersionUID = 7367149336716435954L;
	private long uid;
	private long latelyId = 0;
	private String name = "";
	private String avatar = "";
	private int sex = 0;
	private String arena = "";
}
