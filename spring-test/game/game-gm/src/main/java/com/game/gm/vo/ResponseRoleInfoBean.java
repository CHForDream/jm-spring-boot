package com.game.gm.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseRoleInfoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private long uid;
	private String roleName;
	private int level;
	private long createTime;
	private long lastLoginTime;
}
