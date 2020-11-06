package com.game.myapp.module.event.obj;

import java.io.Serializable;

import com.game.myapp.module.redhat.RedHatBehavior;

public class RedHatObj implements Serializable {

	private static final long serialVersionUID = 1L;

	private long uid;// 角色id
	private RedHatBehavior behavior;// 红点行为类型

	public RedHatObj(long uid, RedHatBehavior behavior) {
		this.uid = uid;
		this.behavior = behavior;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public RedHatBehavior getBehavior() {
		return behavior;
	}

	public void setBehavior(RedHatBehavior behavior) {
		this.behavior = behavior;
	}
}
