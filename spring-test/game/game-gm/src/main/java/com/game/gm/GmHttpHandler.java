package com.game.gm;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.Loggers;

public class GmHttpHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 打印请求的参数
		printParams(request.getParameterMap());

		String code = request.getParameter("code");
		EGmBehavior gmBehavior = EGmBehavior.valuesOf(code);
		if (gmBehavior == null || gmBehavior.getController() == null) {
			return;
		}

		gmBehavior.getController().execute(request, response);
	}

	private void printParams(Map<String, String[]> parameterMap) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> iterator = parameterMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			sb.append(key).append("=");
			boolean isfirstValue = true;
			for (String value : parameterMap.get(key)) {
				if (isfirstValue) {
					isfirstValue = false;
					sb.append(value);
				} else {
					sb.append(",").append(value);
				}
			}
			sb.append(";");
		}
		Loggers.gmLogger.info(sb.toString());
	}
}
