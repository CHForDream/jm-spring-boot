package com.game.myapp.rpc.impl;

import org.apache.dubbo.config.annotation.DubboService;

import com.game.constants.ErrorCodeConst;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.event.PayFinishEvent;
import com.game.myapp.module.event.obj.PayFinishObj;
import com.game.rpc.service.IPayRpcService;
import com.game.rpc.vo.PayInfo;

@DubboService
public class PayRpcServiceImpl implements IPayRpcService {

	@Override
	public int handlePayResult(PayInfo payInfo) {
		try {
			int result = GameGlobals.payManager.handlePay(payInfo);
			if (result == ErrorCodeConst.SUCCESS) {
				// 充值结束检测
				PayFinishObj obj = new PayFinishObj(payInfo.getCharid(), payInfo.getItemid());
				Globals.applicationContext.publishEvent(new PayFinishEvent(obj));
				// 通知客户端充值成功
				GameGlobals.payManager.sendPaySuccessToRole(payInfo.getCharid(), payInfo.getItemid(), payInfo.getPoint(), payInfo.getGift_point(),
						payInfo.getItems());
				// 添加充值成功日志
				GameGlobals.payManager.addLog(payInfo);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorCodeConst.HOME_SERVER_ERROR;
		}
	}
}
