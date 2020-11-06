package com.game.myapp.module.redhat.checker;

import com.game.myapp.module.redhat.RedHatBehavior;

public interface IRedHatChecker {

	public void check(long uid, RedHatBehavior behavior) throws Exception;
}
