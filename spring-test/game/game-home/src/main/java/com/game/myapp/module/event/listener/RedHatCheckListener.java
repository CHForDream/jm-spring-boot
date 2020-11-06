package com.game.myapp.module.event.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.game.myapp.module.event.RedHatCheckEvent;
import com.game.myapp.module.event.obj.RedHatObj;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.redhat.RedHatManager;
import com.game.myapp.module.redhat.checker.IRedHatChecker;

@Component
@Async("gameExecutor")
public class RedHatCheckListener implements ApplicationListener<RedHatCheckEvent> {

	@Override
	public void onApplicationEvent(RedHatCheckEvent arg0) {
		try {
			Object obj = arg0.getSource();
			if (obj == null) {
				return;
			}

			RedHatObj event = (RedHatObj) obj;
			RedHatBehavior behavior = event.getBehavior();
			if (behavior == null) {
				return;
			}
			IRedHatChecker checker = behavior.getChecker();
			if (checker == null) {
				return;
			}

			checker.check(event.getUid(), event.getBehavior());
		} catch (Exception e) {
			Logger.getLogger(RedHatManager.class).error(e);
			e.printStackTrace();
		}
	}

}
