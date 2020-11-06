package com.game.common.user;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import com.game.constants.RedisConstants;
import com.game.constants.UserConstants;
import com.game.core.lock.LockManager;
import com.game.core.redis.RedisConnFactory;
import com.game.global.Globals;
import com.game.utils.JsonUtil;
import com.game.utils.StringUtils;
import com.game.utils.TimeUtils;
import com.game.vo.UserInfoVo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class UserManager {
	private Map<Long, UserBean> userBeanMap = new ConcurrentHashMap<Long, UserBean>();
	private static UserManager userManager = new UserManager();

	public static UserManager getInstance() {
		return userManager;
	}

	public Map<Long, UserBean> getAll() {
		return userBeanMap;
	}

	public UserBean getUserBean(long uid) {
		return userBeanMap.get(uid);
	}

	public void addUser(UserBean bean) {
		bean.setLastOperationTime(System.currentTimeMillis());
		this.userBeanMap.put(bean.getUid(), bean);
	}

	public void remove(long uid) {
		userBeanMap.remove(uid);
	}

	public int getOnlinePlayerNum() {
		if (Globals.isSreverStarted()) {
			return userBeanMap.size();
		}

		return 0;
	}

	public boolean isOnline(long uid) {
		return userBeanMap.get(uid) != null;
	}

	public void offline(long uid) {
		UserBean userBean = userBeanMap.remove(uid);
		if (userBean != null) {
			Globals.getRoleManager().onLogout(userBean);
		}
	}

	public void checkUserTimeout() {
		Iterator<Entry<Long, UserBean>> it = userBeanMap.entrySet().iterator();
		long nowMill = System.currentTimeMillis();
		while (it.hasNext()) {
			try {
				Entry<Long, UserBean> entry = it.next();
				UserBean userBean = entry.getValue();
				long deltaTime = nowMill - userBean.getLastOperationTime();
				if (deltaTime > UserConstants.DB_SAVE_TIME) {
					// 检测到30秒没有心跳, 主动存储玩家未更新数据
					Globals.getRoleManager().saveRoleData(userBean.getUid());
				}

				if (deltaTime > UserConstants.ONLINE_TIME) {
					// 90秒超时
					it.remove();// 删除userBean缓存

					Globals.getRoleManager().onLogout(userBean);
					continue;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void onLogin(UserBean userBean) {
		Jedis jedis = RedisConnFactory.getGameJedis();
		ReentrantLock lock = LockManager.getInstance().getLock(userBean.getUid());
		lock.lock();
		try {
			// 更新玩家定位信息(sid)
			handleSidOnLogin(jedis, userBean.getUid());
			// 加载离线数据
			handleInfoOnLogin(jedis, userBean);
		} finally {
			lock.unlock();
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public void onLogout(UserBean userBean) {
		Jedis jedis = RedisConnFactory.getGameJedis();
		ReentrantLock lock = LockManager.getInstance().getLock(userBean.getUid());
		lock.lock();
		try {
			// 更新玩家定位信息(sid)
			handleSidOnLogout(jedis, userBean.getUid());
			// 保存离线数据
			handleInfoOnLogout(jedis, userBean);
		} finally {
			lock.unlock();
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public void onShutdown() {
		// 保存所有在线玩家的离线数据
		for (UserBean userBean : userBeanMap.values()) {
			onLogout(userBean);
		}
	}

	public int getSid(long uid) {
		String userSidKey = getUserSidKey(uid);
		Jedis jedis = RedisConnFactory.getGameJedis();
		try {
			return Integer.parseInt(jedis.get(userSidKey));
		} catch (NumberFormatException e) {
			// 更新为当前逻辑服
			jedis.set(userSidKey, Globals.getAppConfigBean().getSid() + "");
			return Globals.getAppConfigBean().getSid();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public boolean isThisServer(long uid) {
		return getSid(uid) == Globals.getAppConfigBean().getSid();
	}

	private void handleSidOnLogin(Jedis jedis, long uid) {
		String userSidKey = getUserSidKey(uid);
		Long result = jedis.setnx(userSidKey, Globals.getAppConfigBean().getSid() + "");
		if (result == 1L) {
			// 设置成功
			return;
		}

		// 查询玩家定位数据, 若没有, 则直接更新
		String sidStr = jedis.get(userSidKey);
		if (sidStr == null) {
			// 更新为当前服
			jedis.set(userSidKey, Globals.getAppConfigBean().getSid() + "");
			return;
		}

		try {
			// 比对存储的sid
			int preSid = Integer.parseInt(sidStr);
			if (preSid == Globals.getAppConfigBean().getSid()) {
				// 定位的sid与当前sid相同, 不做任何修改
				return;
			}

			// 远程调用, 保存玩家游戏数据
			Globals.getRoleManager().remoteSaveRoleData(preSid, uid);
		} catch (NumberFormatException e) {
			// 存储的数据异常, 直接更新为当前服
		} finally {
			// 更新为当前服
			jedis.set(userSidKey, Globals.getAppConfigBean().getSid() + "");
		}
	}

	private void handleInfoOnLogin(Jedis jedis, UserBean userBean) {
		String userInfoKey = getUserInfoKey(userBean.getUid());
		String jsonInfo = jedis.get(userInfoKey);
		if (jsonInfo == null) {
			return;
		}

		jedis.del(userInfoKey);
		if (StringUtils.isEmpty(jsonInfo)) {
			// 没有离线数据
			return;
		}

		try {
			// 离线数据json转UserInfoVo, 赋值给当前的UserBean
			UserInfoVo obj = JsonUtil.toObj(jsonInfo, UserInfoVo.class);
			userBean.setUserInfoVo(obj);
		} catch (Exception e) {
		}
	}

	private void handleSidOnLogout(Jedis jedis, long uid) {
		String userSidKey = getUserSidKey(uid);
		String sidStr = jedis.get(userSidKey);
		if (sidStr == null) {
			return;
		}

		try {
			int sid = Integer.parseInt(sidStr);
			if (sid == Globals.getAppConfigBean().getSid()) {
				// sid相同, 开启redis事务删除key
				LogoutRedisTransaction logoutRedisTransaction = new LogoutRedisTransaction(userSidKey);
				RedisConnFactory.excuteRedisTransaction(logoutRedisTransaction);
			}
		} catch (NumberFormatException e) {
			// 数据异常, 直接删除
			jedis.del(userSidKey);
		}
	}

	private void handleInfoOnLogout(Jedis jedis, UserBean userBean) {
		if (userBean.getUserInfoVo().getBattleType() == -1) {
			// 没有战斗数据, 不需要保存
			return;
		}

		// 保存离线战斗数据
		String userInfoJson = JsonUtil.toJson(userBean.getUserInfoVo());
		String userInfoKey = getUserInfoKey(userBean.getUid());
		// 有效时间1天
		SetParams setParams = new SetParams();
		setParams.px(TimeUtils.DAY);
		jedis.set(userInfoKey, userInfoJson, setParams);
	}

	private String getUserSidKey(long uid) {
		return RedisConstants.GAME_KEY_DB_SID + ":" + uid;
	}

	private String getUserInfoKey(long uid) {
		return RedisConstants.GAME_KEY_DB_INFO + ":" + uid;
	}
}
