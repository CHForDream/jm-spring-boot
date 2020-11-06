package com.game.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class HttpUtil {
	private static Logger log = Logger.getLogger(HttpUtil.class);

	/**
	 * 根据URL获取平台返回
	 * 
	 * @param url
	 * @return
	 */
	public static String getUrlBack(String url) {
		String s = "";
		try {
			URL urls = new URL(url);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urls.openConnection();
			httpUrlConnection.setConnectTimeout(3000);
			httpUrlConnection.setReadTimeout(3000);
			BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "UTF-8"));

			String line = null;

			while ((line = in.readLine()) != null) {
				s = s + line;
			}
			in.close();
		} catch (Exception e) {
			// log.error("getUrlBack failure", e);
			log.error("url===getUrlBack failure===" + url + e);
		}

		return s;
	}

	public static String getUrlPostBack(String theUrl, String sig, StringBuffer params) {

		String s = "";
		try {
			URL url = new URL(theUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");// 提交模式
			conn.setConnectTimeout(3000);// 连接超时 单位毫秒
			conn.setReadTimeout(2000);// 读取超时 单位毫秒
			conn.setRequestProperty("contentType", "UTF-8");
			conn.setDoOutput(true);// 是否输入参数

			params.append("&sig=").append(sig);

			byte[] bypes = params.toString().getBytes("UTF-8");
			conn.getOutputStream().write(bypes);// 输入参数
			InputStream inStream = conn.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

			String line = null;

			while ((line = in.readLine()) != null) {
				s = s + line;
			}
			in.close();
		} catch (Exception e) {
			// log.error("getUrlBack failure", e);
			log.error("url===getUrlBack failure===" + theUrl + e);
		}

		return s;
	}

	public static String getUrlPostBack(String theUrl, String params) {

		String s = "";
		try {
			URL url = new URL(theUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");// 提交模式
			conn.setConnectTimeout(3000);// 连接超时 单位毫秒
			conn.setReadTimeout(2000);// 读取超时 单位毫秒
			conn.setDoOutput(true);// 是否输入参数

			byte[] bypes = params.getBytes();
			conn.getOutputStream().write(bypes);// 输入参数
			InputStream inStream = conn.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

			String line = null;

			while ((line = in.readLine()) != null) {
				s = s + line;
			}
			in.close();
		} catch (Exception e) {
			// log.error("getUrlBack failure", e);
			log.error("url===getUrlBack failure===" + theUrl + e);
		}

		return s;
	}

	public static String postJson(String theUrl, String params) {
		String s = "";
		try {
			URL url = new URL(theUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("POST");// 提交模式
			conn.setConnectTimeout(3000);// 连接超时 单位毫秒
			conn.setReadTimeout(2000);// 读取超时 单位毫秒
			conn.setDoOutput(true);// 是否输入参数

			byte[] bypes = params.getBytes();
			conn.getOutputStream().write(bypes);// 输入参数
			InputStream inStream = conn.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

			String line = null;

			while ((line = in.readLine()) != null) {
				s = s + line;
			}
			in.close();
		} catch (Exception e) {
			// log.error("getUrlBack failure", e);
			log.error("url===getUrlBack failure===" + theUrl + e);
		}

		return s;
	}

	public static byte[] httpPostByteArray(String theUrl, byte[] bytes) {
		HttpURLConnection conn = null;
		OutputStream out = null;
		InputStream in = null;
		ByteArrayOutputStream output = null;
		try {
			URL url = new URL(theUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setAllowUserInteraction(false);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "multipart/form-data");
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);

			out = conn.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();

			output = new ByteArrayOutputStream();
			in = conn.getInputStream();
			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = in.read(buffer))) {
				output.write(buffer, 0, n);
			}
			byte[] result = output.toByteArray();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void sendDeleteRequest(String url) {
		try {
			URL urls = new URL(url);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urls.openConnection();
			httpUrlConnection.setConnectTimeout(3000);
			httpUrlConnection.setRequestMethod("DELETE");
		} catch (Exception e) {
			// log.error("getUrlBack failure", e);
			log.error("url===sendDeleteRequest===" + url + e);
		}
	}

	/**
	 * 根据URL获取平台返回
	 * 
	 * @param url
	 * @return
	 */
	public static String getUrlHttpsBack(String url) {

		String s = "";
		try {
			URL urls = new URL(url);
			HttpsURLConnection httpsConn = (HttpsURLConnection) urls.openConnection();

			// 取得该连接的输入流，以读取响应内容
			InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());

			// 读取服务器的响应内容并显示
			int respInt = insr.read();
			while (respInt != -1) {
				// System.out.print((char) respInt);
				respInt = insr.read();
			}
		} catch (Exception e) {
			log.error("url===getUrlBack failure===" + url + " ; error message:" + e);
		}

		return s;
	}

	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {

			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");

	}

	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getParm(HttpServletRequest req, String key) {
		Object obj = req.getParameter(key);
		if (null == obj) {
			return "";
		}
		return obj.toString();
	}

	public static String decode(String param) throws UnsupportedEncodingException {
		return new String(param.getBytes("ISO-8859-1"), "UTF-8");
	}
}
