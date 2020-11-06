package com.game.core.handler;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.game.common.msg.IProcessor;
import com.game.constants.Loggers;
import com.game.global.Globals;
import com.google.protobuf.GeneratedMessage;

public class MsgFilter implements Filter {
	// 日志
	private static Logger logger = Logger.getLogger(MsgFilter.class);

	/**
	 * Default constructor.
	 */
	public MsgFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("static-access")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			// 启动异步线程，不阻塞主线程
			// AsyncContext ctx = request.startAsync(request, response);
			if (this.initMsg(request)) {// 合法
				// 向下传递
				chain.doFilter(request, response);
				// chain.doFilter(ctx.getRequest(), ctx.getResponse());
				// ctx.complete();
			}
		} catch (Exception e) {
			logger.error("Read data error!", e);
		}
	}

	/**
	 * 初始化消息
	 * 
	 * @param is 消息流
	 * @return 消息bean
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static boolean initMsg(ServletRequest request) {
		InputStream is = null;
		DataInputStream dis = null;

		try {
			is = request.getInputStream();
			dis = new DataInputStream(is);
			// 消息解码
			long uid = dis.readLong();
			int key = dis.readInt();

			request.setAttribute("uid", uid);
			request.setAttribute("sessionKey", key);

			short length = dis.readShort();
			short msgType = dis.readShort();

			// 读取数据
			byte[] data = new byte[length];
			dis.read(data);

			// 获取消息
			byte[] proto_bytes = data;
			IProcessor processor = Globals.getMessageManager().getProcessor(msgType);
			Class<? extends GeneratedMessage> messageClass = Globals.getMessageManager().getProtoClass(msgType);
			if (processor == null || messageClass == null) {
				Loggers.serverLogger.error("MsgFilter.initMsg(): Erorr msgType: " + msgType);
				return false;
			}

			Method method = messageClass.getMethod("parseFrom", byte[].class);
			GeneratedMessage msg = (GeneratedMessage) method.invoke(null, proto_bytes);
//			Object msg = processor.toMsg(proto_bytes);

			request.setAttribute("msg", msg);
			request.setAttribute("logical", processor);
			request.setAttribute("msgType", (int) msgType);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("decoder error", ex);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(e);
				}
			}
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(e);
				}
			}

		}

		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
