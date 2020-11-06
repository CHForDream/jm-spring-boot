package com.game.core.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.game.common.msg.IProcessor;
import com.game.common.msg.MessageLogSample;
import com.game.common.user.UserBean;
import com.game.constants.GameConstants;
import com.game.constants.MsgType;
import com.game.global.Globals;
import com.game.utils.BytesUtil;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.Builder;
import com.google.protobuf.Message;

public class HttpHandler extends HttpServlet {
	private static final long serialVersionUID = 8931940089134995856L;
	// 日志
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HttpHandler() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// PrintWriter writer = response.getWriter();
		response.setHeader("Cache-Control", "no-cache");
		// response.setContentType("text/plain");
		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");

		response.setHeader("Connection", "keep-alive");
		response.setHeader("Keep-Alive", "timeout=30");

		if (!Globals.isSreverStarted()) {
			logger.info("Server is not start!");
			// sendError(response, GameConstants.ERROR_SERVER_STARTING, 0);
			return;
		}

//		if (!Globals.getAppConfigBean().isHomeServer()) {
//			String ip = request.getRemoteAddr();
//			logger.error("not home server:" + ip);
//			sendError(response, GameConstants.ERROR_LOGIN_SESSIONKEY, 0);
//			return;
//		}

		if (!com.game.core.handler.MsgFilter.initMsg(request)) {
			sendError(response, GameConstants.ERROR_NO_MESSAGE_PROCESSOR, 0);
			return;
		}

		try {
			long uid = 0;
			if (request.getAttribute("uid") != null) {
				uid = Long.parseLong(String.valueOf(request.getAttribute("uid")));
			}
			int sessionKey = 0;
			if (request.getAttribute("sessionKey") != null && !"".equals(request.getAttribute("sessionKey"))) {
				sessionKey = Integer.parseInt(String.valueOf(request.getAttribute("sessionKey")));
			}

			GeneratedMessage msg = (GeneratedMessage) request.getAttribute("msg");
			IProcessor logical = (IProcessor) request.getAttribute("logical");
			int msgType = (Integer) request.getAttribute("msgType");
			UserBean userBean = Globals.getUserManager().getUserBean(uid);

			// 打印通讯信息
			MessageLogSample.printMessageInfo(msgType, uid, msg, false);

			if (msgType == MsgType.CGDoLoginMsg) {
				dealMsg(response, msgType, msg, logical, userBean, request.getRemoteAddr());
				return;
			}

			if (userBean == null) {
				String ip = request.getRemoteAddr();
				logger.warn("userBean null uid:" + uid + "  ip:" + ip);
				sendError(response, GameConstants.ERROR_LOGIN_SESSIONKEY, uid);
				return;
			}

			if (userBean.getToken() != sessionKey) {
				sendError(response, GameConstants.ERROR_LOGIN_SESSIONKEY, uid);
				return;
			}

			dealMsg(response, msgType, msg, logical, userBean, request.getRemoteAddr());
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Message Error!", ex);
		}
	}

	private void dealMsg(HttpServletResponse response, int msgType, GeneratedMessage msg, IProcessor logical, UserBean userBean, String reqIp) {
		long timeMill = System.currentTimeMillis();
		if (userBean == null) {
			userBean = new UserBean();
			userBean.setIp(reqIp);
		}
		userBean.setLastOperationTime(timeMill);

		MsgBack msgBack = new MsgBack();
		logical.logical(msgBack, userBean, msgType, msg);

		Globals.getRoleManager().packCommonMessage(msgBack, userBean);
		sendMsgBack(response, msgBack, userBean.getUid());
	}

	private void sendError(HttpServletResponse response, int status, long uid) {
		Builder<?> builder = Globals.getRoleManager().buildErrorMessage(status);
		MsgBack msgBack = new MsgBack();
		msgBack.addBuilder(builder);
		sendMsgBack(response, msgBack, uid);
	}

	private void sendMsgBack(HttpServletResponse response, MsgBack msgBack, long uid) {
		if (msgBack == null) {
			return;
		}
		OutputStream os = null;
		try {
			byte[] respback = new byte[msgBack.getTotalSize()];
			os = response.getOutputStream();
			List<MsgBuilder> builderList = msgBack.getBuilder();
			int index = 0;
			for (MsgBuilder builder : builderList) {
				int msgType = builder.getMsgType();
				Message msg = builder.getBuilder().build();
				byte[] req = msg.toByteArray();
				byte[] lengthArry = BytesUtil.shortToByteArray((short) req.length);
				byte[] typeArry = BytesUtil.shortToByteArray((short) msgType);
				respback[index + 0] = lengthArry[0];
				respback[index + 1] = lengthArry[1];
				respback[index + 2] = typeArry[0];
				respback[index + 3] = typeArry[1];
				System.arraycopy(req, 0, respback, index + 4, req.length);
				index += req.length + 4;

				// 打印通讯信息
				MessageLogSample.printMessageInfo(msgType, uid, msg, true);
			}
			response.setContentLength(respback.length);
			os.write(respback);
			os.flush();
		} catch (Exception ex) {
//			ex.printStackTrace();
//			logger.error("Message Error!", ex);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
//					logger.error("Message Error!", e);
				}
			}
		}
	}
}
