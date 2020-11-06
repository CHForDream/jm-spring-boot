package com.game.rpc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;

import com.game.core.lock.LockManager;
import com.game.global.Globals;

public class RpcManager {
	private static Map<String, Object> refMap = new ConcurrentHashMap<String, Object>();

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <E> E getRpcImplBySid(int sid, Class<E> clazz) {
		String url = getUrlBySid(sid);
		String key = url + clazz.getName();
		if (refMap.containsKey(key)) {
			return (E) refMap.get(key);
		}

		ReentrantLock serverLock = LockManager.getInstance().getServerLock();
		serverLock.lock();
		try {
			if (refMap.containsKey(key)) {
				return (E) refMap.get(key);
			}

			StringBuffer sb = new StringBuffer();
			sb.append(url.replaceFirst("http:", "dubbo:"));
			sb.append("/");
			sb.append(clazz.getName());
			ApplicationConfig ac = new ApplicationConfig();
			ac.setName("game-home-dubbo");
			ReferenceConfig<E> ref = new ReferenceConfig<E>();
			ref.setInterface(clazz);
			ref.setUrl(sb.toString());
			ref.setApplication(ac);
			ref.setTimeout(3000);
			E result = ref.get();
			refMap.put(key, result);
			return result;
		} finally {
			serverLock.unlock();
		}
	}

	private static String getUrlBySid(int sid) {
		String serverIp = Globals.getAppConfigBean().getHomeServerMap().get(sid);
		int port = Globals.getAppConfigBean().getDubboPort();
		return serverIp + ":" + port;
	}
}
