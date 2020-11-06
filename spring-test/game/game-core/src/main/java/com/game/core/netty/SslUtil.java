package com.game.core.netty;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.apache.log4j.Logger;

public class SslUtil {
	public static String type = "JKS";
	public static String path = "D:/serverKeystore.jks";
	public static String password = "123456";

	private static volatile SSLContext sslContext = null;
	private static Logger log = Logger.getLogger(SslUtil.class);

	public static SSLContext createSSLContext(String type, String path, String password) throws Exception {
		if (null == sslContext) {
			synchronized (SslUtil.class) {
				InputStream ksInputStream = null;
				if (null == sslContext) {
					try {
						KeyStore ks = KeyStore.getInstance(type);
						ksInputStream = new FileInputStream(path);
						ks.load(ksInputStream, password.toCharArray());
						KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
						kmf.init(ks, password.toCharArray());
						sslContext = SSLContext.getInstance("TLS");
						sslContext.init(kmf.getKeyManagers(), null, null);
					} catch (Exception ex) {
						ex.printStackTrace();
						log.error(ex);
					} finally {
						if (ksInputStream != null) {
							ksInputStream.close();
						}
					}
				}
			}
		}
		return sslContext;
	}
}
