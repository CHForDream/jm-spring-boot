package com.game.gm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.utils.JsonUtil;

public abstract class AbsGmContoller {

	public abstract Object handler(HttpServletRequest request, HttpServletResponse response);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Object object = handler(request, response);
			String result = JsonUtil.toJson(object);

			out.write(result);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public long getUid(HttpServletRequest request) {
		long uid = 0;
		if (request.getParameter("uid") != null) {
			uid = Long.parseLong(request.getParameter("uid"));
		}
		return uid;
	}
}
