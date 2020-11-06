package com.game.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.common.logf.LogfPrinter;
import com.game.common.logf.LogfType;
import com.game.common.logf.impl.PayLog;
import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.global.Globals;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IPayRpcService;
import com.game.rpc.vo.PayInfo;
import com.game.utils.JsonUtil;

public class PayHttpHandler extends HttpServlet {
	private static final long serialVersionUID = -2137355649658317008L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String param = req.getParameter("data");
			String json = new String(Base64.getDecoder().decode(param));
			json = URLDecoder.decode(json, "utf-8");
			Loggers.payLogger.info("pay call back. ip = " + req.getRemoteAddr() + ", json = " + json);
			PayInfo payInfo = JsonUtil.toObj(json, PayInfo.class);
			Loggers.payLogger.info(payInfo);

			IPayRpcService payService = RpcManager.getRpcImplBySid(Globals.getUserManager().getSid(payInfo.getCharid()), IPayRpcService.class);
			int result = payService.handlePayResult(payInfo);
			String reason = "";
			switch (result) {
			case ErrorCodeConst.SUCCESS:// 10000 成功
				reason = "SUCCESS";

				addLog(payInfo);
				break;
			case ErrorCodeConst.ROLE_NOT_EXIST:// 10001 角色不存在
				reason = "ROLE_NOT_EXIST";
				break;
			case ErrorCodeConst.PAY_ORDER_IS_EXIST:// 10008 订单已存在
				reason = "PAY_ORDER_IS_EXIST";
				break;
			case ErrorCodeConst.PAY_CONFIG_NOT_EXIST:// 10009 挡位不存在
				reason = "PAY_CONFIG_NOT_EXIST";
				break;
			case ErrorCodeConst.HOME_SERVER_ERROR:// 10020 服务器处理异常
				reason = "HOME_SERVER_ERROR";
				break;
			default:
				break;
			}
			Loggers.payLogger.info("uid = " + payInfo.getCharid() + ", payid = " + payInfo.getItemid() + ", orderId = " + payInfo.getOrderid() + ", result = "
					+ result + ", reason = " + reason);

			PrintWriter out = resp.getWriter();
			out.write(String.valueOf(result));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();

			PrintWriter out = resp.getWriter();
			out.write(String.valueOf(ErrorCodeConst.ERROR_PARAM));// 10002 参数错误
			out.flush();
		}
	}

	private void addLog(PayInfo payInfo) {
		UserBean userBean = new UserBean();
		userBean.setUid(payInfo.getCharid());
		userBean.setPassportId(payInfo.getGuid() + "");

		PayLog payLog = new PayLog(LogfType.PAY_RECEIVE, userBean);
		payLog.setOrderid(payInfo.getOrderid());
		payLog.setGssid(payInfo.getGssid());
		payLog.setGuid(payInfo.getGuid());
		payLog.setGateway(payInfo.getGateway());
		payLog.setItemid(payInfo.getItemid());
		payLog.setPoint(payInfo.getPoint());
		payLog.setGift_point(payInfo.getGift_point());
		payLog.setCreatetime(payInfo.getCreatetime());
		payLog.setPaytime(payInfo.getPaytime());
		payLog.setItems(payInfo.getItems());
		payLog.setCuid(payInfo.getCuid());
		payLog.setAmount(payInfo.getAmount());
		payLog.setItemid2(payInfo.getItemid2());
		payLog.setPveId(0);// 这里是支付服, 拿不到pveId
		payLog.setTargetId(0);// 这里是支付服, 拿不到targetId
		LogfPrinter.getInstance().push(payLog);
	}

	/**
	 * 创建假订单
	 */
	@SuppressWarnings("unused")
	private PayInfo createTestPayInfo(long uid) {
		PayInfo pay = new PayInfo();
		pay.setOrderid(String.valueOf(System.nanoTime()));
		pay.setGssid(Integer.parseInt(Globals.getAppConfigBean().getGssid()));
		pay.setCharid(uid);
		pay.setGateway("2098161931");
		pay.setItemid(2);
		pay.setPoint(1000);
		pay.setGift_point(60);
		pay.setCreatetime((int) (System.currentTimeMillis() / 1000l));
		pay.setPaytime((int) (System.currentTimeMillis() / 1000l));
		pay.setItems("");
		return pay;
	}
}
