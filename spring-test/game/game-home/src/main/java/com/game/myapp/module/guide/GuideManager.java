package com.game.myapp.module.guide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.game.common.data.Datas;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.LogfType;
import com.game.common.logf.impl.RoleGuideLog;
import com.game.common.session.GameSession;
import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBack;
import com.game.db.entity.RoleEntity;
import com.game.db.entity.RoleGuideEntity;
import com.game.generate.bean.GuideBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.utils.Utils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import buffer.CGUpdateRoleGuideMsg.CGUpdateRoleGuideProto;
import buffer.GCSendGuideMsg;
import buffer.GCUpdateRoleGuideMsg;

public class GuideManager {
	// 将引导按类型进行分类<引导类型, <引导组Bean>>
	private Map<Integer, List<GuideBeanVo>> guideDataMap = Maps.newHashMap();
	/** 服务器可以提前完成的引导 */
	private Map<EGuidePreFinishType, List<GuideBeanVo>> serverPrefinishGuideMap = Maps.newHashMap();

	private Logger log = Loggers.guideLogger;

	public GuideManager() {
		init();
	}

	public void initGuideDataMap() {
		synchronized (GuideManager.class) {
			guideDataMap.clear();
			init();
		}
	}

	/**
	 * 初始化服务器用到的引导数据
	 */
	private void init() {
		for (GuideBean guideBean : Datas.getDataMap(GuideBean.class).values()) {
			int type = guideBean.getOpenType();
			// 只存储每组数据的第一个,给客户端推送也是发送第一个
			if (type == 0) {
				continue;
			}
			//不需要服务器主动触发
			if (guideBean.getServer() == 0) {
				continue;
			}
			if (!guideDataMap.containsKey(type)) {
				List<GuideBeanVo> list = new ArrayList<GuideBeanVo>();
				guideDataMap.put(type, list);
			}
			List<GuideBeanVo> addList = guideDataMap.get(type);
			GuideBeanVo vo = new GuideBeanVo();
			vo.setId(guideBean.getId());
			vo.setType(type);
			vo.setParam(guideBean.getOpenNum());
			vo.setNextId(guideBean.getNextId());
			vo.setEndImmediately(guideBean.getEndImmediately() == 1);
			vo.setServerPrefinish(guideBean.getServerFinish() == 1);
			vo.setServerConditionType(guideBean.getServerConditionType());
			vo.setServerConditionNum(guideBean.getServerConditionNum());
			if (!guideBean.getMutex().equals("")) {
				String[] mutex = guideBean.getMutex().split(",");
				for (String metexId : mutex) {
					if (Objects.equals(metexId, "0")) {
						continue;
					}
					vo.addmutexList(Integer.parseInt(metexId));
				}
			}
			addList.add(vo);

			if (vo.isServerPrefinish()) {
				EGuidePreFinishType finishType = EGuidePreFinishType.valueOf(vo.getServerConditionType());
				if (finishType != null) {
					if (!serverPrefinishGuideMap.containsKey(finishType)) {
						List<GuideBeanVo> prefinishList = Lists.newArrayList();
						serverPrefinishGuideMap.put(finishType, prefinishList);
					}
					serverPrefinishGuideMap.get(finishType).add(vo);
				}
			}
		}
	}

	/**
	 * 收到客户都端上报引导进度
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 */
	public void CGUpdateRoleGuide(MsgBack msgBack, long uid, CGUpdateRoleGuideProto msg) {
		GCUpdateRoleGuideMsg.GCUpdateRoleGuideProto.Builder builder = GCUpdateRoleGuideMsg.GCUpdateRoleGuideProto.newBuilder();
		msgBack.addBuilder(builder);
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}
		int status = ErrorCodeConst.SUCCESS;
		int guide = msg.getStep();
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			if (msg.getType() == 1 || msg.getType() == 0) {
				status = updateRoleGuideInfo(uid, guide);
			} else if (msg.getType() == 2) {
				status = updateRoleBattleGuideInfo(uid, guide);
			}
			builder.setStatus(status);
		} finally {
			lock.unlock();
		}
		if (status == ErrorCodeConst.SUCCESS) {
			GameGlobals.guideManager.onCheck(uid, GuideConst.OPEN_TYPE_GUIDE, msg.getStep());
		}
	}

	/**
	 * 更新战斗引导
	 * 
	 * @param uid
	 * @param guide
	 * @return
	 * @throws Exception
	 */
	private int updateRoleBattleGuideInfo(long uid, int guide) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleGuideEntity guideEntity = getRoleGuideEntity(uid);
			guideEntity.setBattleGuides(guideEntity.getBattleGuides() + "," + guide);
			log.info("========>>>>收到客户端上报战斗引导进度,uid:" + uid + "--->id:" + guide);
			Globals.getEntityProxy().updateAsync(guideEntity);
			addGuideLog(uid, guide, LogfType.GUIDE_COMPLETE);
			return ErrorCodeConst.SUCCESS;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 更新战斗外引导
	 * 
	 * @param uid
	 * @param guide
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int updateRoleGuideInfo(long uid, int guide) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleGuideEntity guideEntity = getRoleGuideEntity(uid);
			String nextIds = guideEntity.getNextGuide();
			List<String> list = Arrays.asList(nextIds.split(","));
			int index = list.indexOf(String.valueOf(guide));
			// 已经上报过了
			if (Arrays.asList(guideEntity.getGuide().split(",")).contains(String.valueOf(guide))) {
				if (index != -1) {
					List<String> roleGuides = new ArrayList<String>(list);
					roleGuides.remove(index);
					guideEntity.setNextGuide(Utils.listToString(roleGuides));
					Globals.getEntityProxy().updateAsync(guideEntity);
				}
				return ErrorCodeConst.ERROR_PARAM;
			}

			if (guide != -1 && index == -1) {// -1是游戏外的战斗引导
				return ErrorCodeConst.ERROR_PARAM;
			}
			guideEntity.setGuide(guideEntity.getGuide() + "," + guide);
			if (index != -1) {
				List<String> roleGuides = new ArrayList(list);
				roleGuides.remove(index);
				guideEntity.setNextGuide(Utils.listToString(roleGuides));
			}
			log.info("Finish guide, uid = " + uid + ", finishGuide = " + guide + ", nextGuide = " + guideEntity.getNextGuide());
			Globals.getEntityProxy().updateAsync(guideEntity);
			addGuideLog(uid, guide, LogfType.GUIDE_COMPLETE);
			return ErrorCodeConst.SUCCESS;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 获取玩家引导信息
	 * 
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public RoleGuideEntity getRoleGuideEntity(long uid) {
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return null;
		}
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleGuideEntity entity = Globals.getEntityProxy().get(RoleGuideEntity.class, uid);
			if (entity == null) {
				entity = new RoleGuideEntity();
				entity.setUid(uid);
				entity.setGuide("0");
				entity.setSystemId("0");
				entity.setNextGuide("0");
				entity.setBattleGuides("0");
				Globals.getEntityProxy().insert(entity);
				return entity;
			}

			// 兼容下旧账号
			if (entity.getNextGuide() == null) {
				entity.setNextGuide("0");
				Globals.getEntityProxy().updateAsync(entity);
			}
			if (entity.getBattleGuides() == null) {
				entity.setBattleGuides("0");
				Globals.getEntityProxy().updateAsync(entity);
			}
			return entity;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 删除角色触发的引导新信息
	 * 
	 * @throws Exception
	 */
	public void removeRoleNextGuideIds(long uid, int guideId) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleGuideEntity guideEntity = getRoleGuideEntity(uid);
			List<String> list = Arrays.asList(guideEntity.getNextGuide().split(","));
			int index = list.indexOf(String.valueOf(guideId));
			if (index == -1) {
				return;
			}
			List<String> roleGuides = new ArrayList<String>(list);
			roleGuides.remove(index);
			guideEntity.setNextGuide(Utils.listToString(roleGuides));
			Globals.getEntityProxy().updateAsync(guideEntity);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 将玩家触发的引导信息存一下,下次玩家登陆时需要发给玩家继续执行
	 */
	public void addRoleNextGuideIds(long uid, int guideId) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleGuideEntity guideEntity = getRoleGuideEntity(uid);
			List<String> list = Arrays.asList(guideEntity.getNextGuide().split(","));
			if (list.contains(String.valueOf(guideId))) {
				return;
			}
			int index = list.indexOf(String.valueOf(guideId));
			if (index != -1) {
				return;
			}
			guideEntity.setNextGuide(guideEntity.getNextGuide() + "," + guideId);
			Globals.getEntityProxy().updateAsync(guideEntity);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 获取玩家执行过的引导list
	 * 
	 * @param uid
	 * @return
	 */
	public List<String> getRoleGuideList(long uid) {
		RoleGuideEntity guideEntity = getRoleGuideEntity(uid);
		List<String> result = Arrays.asList(guideEntity.getGuide().split(","));
		return result;
	}

	/**
	 * 获取玩家执行过的引导list
	 * 
	 * @param uid
	 * @return
	 */
	public List<String> getRoleNextList(long uid) {
		RoleGuideEntity guideEntity = getRoleGuideEntity(uid);
		List<String> result = Arrays.asList(guideEntity.getNextGuide().split(","));
		return result;
	}

	/**
	 * 玩家上次退出未执行过的引导
	 * 
	 * @param uid
	 * @return
	 */
	public List<Integer> getRoleNextGuideIds(long uid) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			RoleGuideEntity guideEntity = getRoleGuideEntity(uid);

			String nextIds = guideEntity.getNextGuide();
			if (nextIds == null || nextIds.equals("0")) {
				return result;
			}
			String[] nextIdArr = nextIds.split(",");
			for (String id : nextIdArr) {
				if (id.equals("0")) {
					continue;
				}
				result.add(Integer.parseInt(id));
//				log.info("=====》》》》角色登陆有触发未完成的引导,UID:" + uid + "-->guideId:" + id);
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 玩家触发过的战斗引导集合
	 */
	public List<Integer> getRoleBattleGuideList(long uid) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			RoleGuideEntity guideEntity = getRoleGuideEntity(uid);
			String battleGuides = guideEntity.getBattleGuides();
			if (null == battleGuides || "".equals(battleGuides) || "0".equals(battleGuides)) {
				return result;
			}
			String[] battleGuidesArr = battleGuides.split(",");
			for (String id : battleGuidesArr) {
				if (id.equals("0")) {
					continue;
				}
				result.add(Integer.parseInt(id));
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取玩家最后一次引导id
	 * 
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public int getLastGuideStep(long uid) {
		try {
			List<?> guideList = getRoleGuideList(uid);
			return Integer.parseInt((String) guideList.get(guideList.size() - 1));
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 检测玩家是否执行过本次引导
	 * 
	 * @param uid
	 * @param guideId
	 * @return
	 * @throws Exception
	 */
	public boolean checkExeGuideId(long uid, int guideId) {
		List<?> guideList = getRoleGuideList(uid);
		return guideList.contains(String.valueOf(guideId));
	}

	public void sendToClientGuides(long uid, List<Integer> guides) {
		GameSession session = Globals.getChatSessionManager().getSession(uid);
		if (session == null) {
			return;
		}
		GCSendGuideMsg.GCSendGuideProto.Builder builder = GCSendGuideMsg.GCSendGuideProto.newBuilder();
		builder.setStatus(ErrorCodeConst.SUCCESS);
		builder.addAllGuides(guides);
		session.sendMsg(builder);
	}

	/**
	 * 检测可提前完成的引导
	 * 
	 * @param uid
	 * @param type
	 * @param val
	 * @throws Exception
	 */
	public void onCheckPreFinish(long uid, EGuidePreFinishType type, int val) {
		if (!serverPrefinishGuideMap.containsKey(type)) {
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleGuideEntity guideEntity = getRoleGuideEntity(uid);
			List<String> roleGuideList = Arrays.asList(guideEntity.getGuide().split(","));
			List<String> roleNextGuideList = Arrays.asList(guideEntity.getNextGuide().split(","));
			for (GuideBeanVo guideBeanVo : serverPrefinishGuideMap.get(type)) {
				if (roleGuideList.contains(String.valueOf(guideBeanVo.getId()))) {
					return;
				}
				if (roleNextGuideList.contains(String.valueOf(guideBeanVo.getId()))) {
					return;
				}

				if (val >= guideBeanVo.getServerConditionNum()) {
					guideEntity.setGuide(guideEntity.getGuide() + "," + guideBeanVo.getId());
					Globals.getEntityProxy().updateAsync(guideEntity);
				}
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 引导检测
	 * 
	 * @param uid
	 * @param type
	 * @param val
	 * @throws Exception
	 */
	public void onCheck(long uid, int type, int val) {
		try {
			if (!guideDataMap.containsKey(type)) {
				return;
			}
			List<GuideBeanVo> guideList = guideDataMap.get(type);
			if (guideList == null || guideList.size() == 0) {
				return;
			}
			List<Integer> guides = new ArrayList<Integer>();
			for (GuideBeanVo temp : guideList) {
				if (check(uid, val, temp)) {
					log.info("New guide, uid = " + uid + ", guideId =" + temp.getId());
					guides.add(temp.getId());
					addRoleNextGuideIds(uid, temp.getId());
					//触发引导日志
					addGuideLog(uid, temp.getId(), LogfType.GUIDE_TRIGGER);
					if (temp.isEndImmediately()) {
						log.info("Finish guide immediately! uid = " + uid + ", guideId =" + temp.getId());
						int status = updateRoleGuideInfo(uid, temp.getId());
						if (status == ErrorCodeConst.SUCCESS) {
							GameGlobals.guideManager.onCheck(uid, GuideConst.OPEN_TYPE_GUIDE, temp.getId());
						}
					}
				}
			}
			if (guides.size() > 0) {
				sendToClientGuides(uid, guides);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检测某个引导是否需要发送给客户端
	 * 
	 * @param uid
	 * @param val
	 * @param temp
	 * @return
	 */
	private boolean check(long uid, int val, GuideBeanVo temp) {
		if (val != temp.getParam()) {
			return false;
		}
		int id = temp.getId();
		List<?> excList = getRoleGuideList(uid);
		if (excList.contains(String.valueOf(id))) {
			return false;
		}
		List<Integer> mutexIdList = temp.getMutexList();
		List<?> nextList = getRoleNextList(uid);
		for (Integer mutexId : mutexIdList) {
			if (excList.contains(String.valueOf(mutexId))) {
				return false;
			}
			//已经触发未执行的引导包含互斥也不需要触发引导
			if (nextList.contains(String.valueOf(mutexId))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 战斗引导日志
	 * 
	 * @param type
	 */
	private void addGuideLog(long uid, int guideId, LogfType type) {
		try {
			UserBean user = Globals.getUserManager().getUserBean(uid);
			RoleGuideLog log = new RoleGuideLog(type, user);
			log.setGuideId(guideId);
			LogfPrinter.getInstance().push(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
