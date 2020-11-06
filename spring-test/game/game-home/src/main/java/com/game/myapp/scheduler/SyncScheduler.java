package com.game.myapp.scheduler;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.game.constants.Loggers;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;

@Component
public class SyncScheduler {
	private Logger logger = Logger.getLogger(this.getClass());

	// fixedDelay控制方法执行的间隔时间，是以上一次方法执行完开始算起，如上一次方法执行阻塞住了，那么直到上一次执行完，并间隔给定的时间后，执行下一次。
	// fixedRate是按照一定的速率执行，是从上一次方法执行开始的时间算起，如果上一次方法阻塞住了，下一次也是不会执行，但是在阻塞这段时间内累计应该执行的次数，当不再阻塞时，一下子把这些全部执行掉，而后再按照固定速率继续执行。
	// cron表达式可以定制化执行任务，但是执行的方式是与fixedDelay相近的，也是会按照上一次方法结束时间开始算起。
	// 这个定时器就是在上一个的基础上加了一个initialDelay = 10000
	// 意思就是在容器启动后,延迟10秒后再执行一次定时器,以后每15秒再执行一次该定时器。

	/**
	 * 定期检测活动状态变化
	 */
	@Scheduled(initialDelay = 10000, fixedDelay = 2000)
	public void checkActivity() {
		if (!Globals.isSreverStarted()) {
			return;
		}

		if (!Globals.getAppConfigBean().isMainHome()) {
			return;
		}

		try {
			GameGlobals.activityManager.onCheck();
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
	}

	/**
	 * 定期删除过时评论
	 */
	@Scheduled(initialDelay = 50000, fixedRate = 30000)
	public void checkInvalidComment() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour != 23) {
			return;
		}
		GameGlobals.heroManager.getHeroCommentManager().delInvalidComment();
	}

	/**
	 * 跑马灯
	 */
	@Scheduled(initialDelay = 60 * 1000, fixedDelay = 500)
	public void checkHorseLamp() {
		if (!Globals.isSreverStarted()) {
			return;
		}

		try {
			GameGlobals.horseLampManager.execute();
		} catch (Exception e) {
			Loggers.serverLogger.error(e);
			e.printStackTrace();
		}
	}
}
