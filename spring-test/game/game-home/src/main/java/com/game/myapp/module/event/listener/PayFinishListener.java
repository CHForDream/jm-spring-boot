package com.game.myapp.module.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.game.myapp.GameGlobals;
import com.game.myapp.module.event.PayFinishEvent;
import com.game.myapp.module.event.obj.PayFinishObj;

/**
 * 充值完成监听器
 * 
 * @author pky
 *
 */
@Component
@Async("gameExecutor")
public class PayFinishListener implements ApplicationListener<PayFinishEvent> {

	@Override
	public void onApplicationEvent(PayFinishEvent event) {
		try {
			PayFinishObj payFinishObj = (PayFinishObj) event.getSource();
			// 检测首充奖励
			GameGlobals.payManager.onCheckFirstPay(payFinishObj.getUid(), payFinishObj.getPayId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
