package com.game.myapp.scheduler;

import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.game.common.user.UserBean;
import com.game.core.cache.EhcacheUtil;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;

@Async
@Component
public class AsyncScheduler {

	// fixedDelay控制方法执行的间隔时间，是以上一次方法执行完开始算起，如上一次方法执行阻塞住了，那么直到上一次执行完，并间隔给定的时间后，执行下一次。
	// fixedRate是按照一定的速率执行，是从上一次方法执行开始的时间算起，如果上一次方法阻塞住了，下一次也是不会执行，但是在阻塞这段时间内累计应该执行的次数，当不再阻塞时，一下子把这些全部执行掉，而后再按照固定速率继续执行。
	// cron表达式可以定制化执行任务，但是执行的方式是与fixedDelay相近的，也是会按照上一次方法结束时间开始算起。
	// 这个定时器就是在上一个的基础上加了一个initialDelay = 10000
	// 意思就是在容器启动后,延迟10秒后再执行一次定时器,以后每15秒再执行一次该定时器。

	/**
	 * Http会话超时检测
	 */
	@Scheduled(initialDelay = 50000, fixedRate = 30000)
	public void checkHttpSession() {
		try {
			if (!Globals.isSreverStarted()) {
				return;
			}

			Globals.getUserManager().checkUserTimeout();

			// 打印缓存数据信息
			EhcacheUtil.printCacheStatus();

			// 同步排行榜数据到本地
			GameGlobals.rankService.syncRankInfoData();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 超时会话监测
	 */
	@Scheduled(initialDelay = 30000, fixedRate = 60000)
	public void checkNettySession() {
		if (!Globals.isSreverStarted()) {
			return;
		}

//		if (Globals.appConfigBean.isBattleServer()) {
//			try {
//				Globals.getGameSessionManager().onRun();
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}

//		if (Globals.appConfigBean.isChatServer()) {
		try {
			Globals.getChatSessionManager().onRun();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
//		}
	}

	/**
	 * 体力变化检测
	 */
	@Scheduled(initialDelay = 50000, fixedRate = 30000)
	public void checkPower() {
		try {
			Iterator<Entry<Long, UserBean>> it = Globals.getUserManager().getAll().entrySet().iterator();
			while (it.hasNext()) {
				Entry<Long, UserBean> entry = it.next();
				UserBean bean = entry.getValue();
				GameGlobals.roleManager.updateRolePower(bean.getUid(), System.currentTimeMillis() / 1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
