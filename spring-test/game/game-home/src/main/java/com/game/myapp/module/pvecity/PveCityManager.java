package com.game.myapp.module.pvecity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBack;
import com.game.core.lock.LockManager;
import com.game.db.entity.PveCityEntity;
import com.game.db.entity.PveCityEntity.Duplicate;
import com.game.db.entity.RoleEntity;
import com.game.generate.bean.CityachieveBean;
import com.game.generate.bean.DuplicateBean;
import com.game.generate.bean.PveBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.guide.EGuidePreFinishType;
import com.game.myapp.module.guide.GuideConst;
import com.game.myapp.module.item.EItemType;
import com.game.myapp.module.pvecity.pveenum.EPveStatus;
import com.game.vo.item.ItemVo;

import buffer.CGChangeCityMsg.CGChangeCityProto;
import buffer.CGCityAwardMsg.CGCityAwardProto;
import buffer.CGCityInfoMsg.CGCityInfoProto;
import buffer.CGDuplicateCheckMsg.CGDuplicateCheckProto;
import buffer.CGPveCityAttachMsg.CGPveCityAttachProto;
import buffer.CGPveCityBuyDecMsg.CGPveCityBuyDecProto;
import buffer.CGPveCityInfoMsg.CGPveCityInfoProto;
import buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto;
import buffer.GCChangeCityMsg;
import buffer.GCCityAwardMsg;
import buffer.GCCityInfoMsg;
import buffer.GCDuplicateCheckMsg.GCDuplicateCheckProto;
import buffer.GCPveCityAttachMsg;
import buffer.GCPveCityBuyDecMsg;
import buffer.GCPveCityInfoMsg;
import buffer.GCPveCityInfoMsg.DupInfo;
import buffer.GCPveCityUnlockMsg;

/**
 * 关卡系统
 * 
 * @dec 关卡地图
 * @author mh
 *
 */
public class PveCityManager {
	private Map<Integer, List<Integer>> cityDupMap = new HashMap<Integer, List<Integer>>();

	private Map<Integer, Map<Integer, List<ItemVo>>> cityTypeAwardMap = new HashMap<Integer, Map<Integer, List<ItemVo>>>();

	public PveCityManager() {
		for (PveBean bean : Datas.getDataMap(PveBean.class).values()) {
			if (bean.getDupId() == 0) {
				continue;
			}
			List<Integer> dupIds = cityDupMap.get(bean.getCity());
			if (dupIds == null) {
				dupIds = new ArrayList<Integer>();
				cityDupMap.put(bean.getCity(), dupIds);
			}
			dupIds.add(bean.getDupId());
		}

		for (CityachieveBean bean : Datas.getDataMap(CityachieveBean.class).values()) {
			int type = bean.getType();
			Map<Integer, List<ItemVo>> cityAwardMap = cityTypeAwardMap.get(type);
			if (cityAwardMap == null) {
				cityAwardMap = new HashMap<Integer, List<ItemVo>>();
				cityTypeAwardMap.put(type, cityAwardMap);
			}
			int cityId = bean.getCityId();
			cityAwardMap.put(cityId, bean.getAwardList());
		}
	}

	/**
	 * 客户端获取关卡详细信息
	 */
	public void CGPveCityInfo(MsgBack msgBack, long uid, CGPveCityInfoProto msg) {
		GCPveCityInfoMsg.GCPveCityInfoProto.Builder builder = GCPveCityInfoMsg.GCPveCityInfoProto.newBuilder();
		GCPveCityInfoMsg.CityInfo.Builder cityBuilder = GCPveCityInfoMsg.CityInfo.newBuilder();
		msgBack.addBuilder(builder);
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
				return;
			}
			PveCityEntity pveCityEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
			pveCityEntity = this.checkPveCityEntity(pveCityEntity, uid);
			checkPveEntity(pveCityEntity, uid); // 检测最后一关后是否有新开关卡

			int cityId = msg.getCityId();
			if (msg.getSelCityId() == 0) {
				if (cityId == 0) {
					cityId = pveCityEntity.getCityId();
				}
			} else {
				cityId = msg.getSelCityId();
				pveCityEntity.setCityId(cityId);
				Globals.getEntityProxy().updateAsync(pveCityEntity);
			}

			builder.setPveId(pveCityEntity.getCurPveTarget());
			builder.setPveStatus(pveCityEntity.getCurTargetStatus());
			builder.setCityId(pveCityEntity.getCurCityId());
			// 填充所选城市信息
			cityBuilder.setCityId(cityId);

			// 填充所选城市的副本信息
			Map<Integer, Duplicate> dupByCity = pveCityEntity.getDupByCity(cityId);
			if (dupByCity != null) {
				for (Integer dupId : dupByCity.keySet()) {
					Duplicate dup = dupByCity.get(dupId);
					if (dup == null) {
						continue;
					}
					DupInfo.Builder dupBd = DupInfo.newBuilder();
					dupBd.setDupId(dupId);
					dupBd.setDupNum(dup.getDupNum());
					dupBd.setDupStar(dup.getStar());
					cityBuilder.addDupInfo(dupBd);
				}
			}
			builder.setCityInfo(cityBuilder);
			builder.addAllCityIds(pveCityEntity.getCityIds());
			builder.setStatus(ErrorCodeConst.SUCCESS);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 解锁关卡
	 */
	public void CGPveCityUnlock(MsgBack msgBack, long uid, CGPveCityUnlockProto msg) {
		GCPveCityUnlockMsg.GCPveCityUnlockProto.Builder builder = GCPveCityUnlockMsg.GCPveCityUnlockProto.newBuilder();
		msgBack.addBuilder(builder);

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			PveCityEntity pveCityEntity = this.getPveCityEntityByUid(uid);
			if (pveCityEntity == null) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			EPveStatus status = EPveStatus.getStatusById(pveCityEntity.getCurTargetStatus());
			switch (status) {
			case LOCK:
				builder.setStatus(ErrorCodeConst.PVE_LOCK_STATUS_ERROR);
				return;
			case UNLOCK:
				builder.setStatus(ErrorCodeConst.PVE_UNLOCK_STATUS_ERROR);
				return;
			case PVE_PASS:
				builder.setStatus(ErrorCodeConst.PVE_PVEPASS_STATUS_ERROR);
				return;
			case COMPLETE:
			default:
				if (msg.getPveId() <= pveCityEntity.getCurPveTarget()) {
					builder.setStatus(ErrorCodeConst.PVE_COMPLETE_STATUS_ERROR);
					return;
				}
				GameGlobals.pveCityManager.changPveStatus(msgBack, uid, EPveStatus.UNLOCK);
				Globals.getEntityProxy().updateAsync(pveCityEntity);

				builder.setPveId(pveCityEntity.getCurPveTarget());
				builder.setPveStatus(pveCityEntity.getCurTargetStatus());
				builder.setCityId(pveCityEntity.getCurCityId());
				builder.setStatus(ErrorCodeConst.SUCCESS);
				break;
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 副本资源验证
	 */
	public void CGDuplicateCheck(MsgBack msgBack, long uid, CGDuplicateCheckProto msg) {
		GCDuplicateCheckProto.Builder gcDupCheckBuilder = GCDuplicateCheckProto.newBuilder();
		msgBack.addBuilder(gcDupCheckBuilder);
		gcDupCheckBuilder.setCityId(msg.getCityId());
		gcDupCheckBuilder.setDupId(msg.getDupId());
		gcDupCheckBuilder.setIndex(msg.getIndex());

		RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(uid);
		if (roleEntity == null) {
			Loggers.battleLogger.error("No roleEntity! uid = " + uid);
			gcDupCheckBuilder.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}
		// 获取副本配置
		DuplicateBean dupBean = Datas.get(DuplicateBean.class, msg.getDupId());
		if (dupBean == null) {
			gcDupCheckBuilder.setStatus(ErrorCodeConst.DUPLICATE_ERROR_ID);
			return;
		}
		// 检查副本是否满足开启条件
		if (checkDupOpen(uid, msg.getCityId(), msg.getDupId()) != 1) {
			gcDupCheckBuilder.setStatus(ErrorCodeConst.DUPLICATE_ERROR_OPEN);
			return;
		}
		// 没有配置副本消耗
		if (dupBean.getCostItems() == null || dupBean.getCostItems().size() == 0) {
			gcDupCheckBuilder.setStatus(ErrorCodeConst.SUCCESS);
			return;
		}
		// 验证索引是否越界
		if (msg.getIndex() >= dupBean.getCostItems().size() || msg.getIndex() < 0) {
			gcDupCheckBuilder.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}
		gcDupCheckBuilder.setStatus(ErrorCodeConst.SUCCESS);
		// 副本配置表里的相应消耗品
		ItemVo info = dupBean.getCostItems().get(msg.getIndex());
		// 看视频
		if (info == null || info.getType() == 10 || info.getNum() <= 0) {
			return;
		}
		int costDia = GameGlobals.unitManager.isEnoughCoinAndCash(roleEntity, info.getId(), info.getNum());
		if (costDia < 0) {
			gcDupCheckBuilder.setStatus(ErrorCodeConst.RESOURCE_NOT_ENOUGH);
			return;
		}
		gcDupCheckBuilder.setCoinNum(info.getNum());
		if (costDia > 0) {
			gcDupCheckBuilder.setCoinNum(roleEntity.getCoin());
			gcDupCheckBuilder.setDiamondNum(costDia);
		}
	}

	/**
	 * 客户端获取景点收集城市关卡进度
	 */
	public void CGCityInfo(MsgBack msgBack, long uid, CGCityInfoProto msg) {
		GCCityInfoMsg.GCCityInfoProto.Builder builder = GCCityInfoMsg.GCCityInfoProto.newBuilder();
		msgBack.addBuilder(builder);

		int type = msg.getInfoType();
		builder.setInfoType(type);
		// 目前只有景点收集和完成某一张地图的所有关卡两种方式
		if (type != PveCityConstants.CITY_INFO_DUP_ACHIEVE && type != PveCityConstants.CITY_INFO_PVE_ACHIEVE) {
			builder.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
				return;
			}
			PveCityEntity pveCityEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
			pveCityEntity = this.checkPveCityEntity(pveCityEntity, uid);
			checkPveEntity(pveCityEntity, uid); // 检测最后一关后是否有新开关卡

			for (int i = 0; i < pveCityEntity.getCityIds().size(); i++) {
				int cityId = pveCityEntity.getCityIds().get(i);
				GCCityInfoMsg.CityInfo.Builder cityBuilder = GCCityInfoMsg.CityInfo.newBuilder();
				cityBuilder.setCityId(cityId);

				if (type == PveCityConstants.CITY_INFO_PVE_ACHIEVE) {
					cityBuilder.setStatus(pveCityEntity.getCityAwardMap().getOrDefault(cityId, PveCityConstants.CITY_ACHIEVE_STATUS_PROCESS));
					builder.addCityInfo(cityBuilder);
					continue;
				}

				List<Integer> dupList = cityDupMap.get(cityId);
				if (dupList == null) {
					continue;
				}
				// 为了禁止领取过的地图再次新加副本而改变了客户端的状态
				// 这里以已领取为最终状态传递
				int dupStatus = PveCityConstants.CITY_ACHIEVE_STATUS_COMPLETE;
				for (int j = 0; j < dupList.size(); j++) {
					Duplicate cityDupInfo = pveCityEntity.getCityDupInfo(cityId, dupList.get(j));
					if (cityDupInfo == null || cityDupInfo.getDupNum() == 0) {
						dupStatus = PveCityConstants.CITY_ACHIEVE_STATUS_PROCESS;
						continue;
					}
					GCCityInfoMsg.DupInfo.Builder dupInfo = GCCityInfoMsg.DupInfo.newBuilder();
					dupInfo.setDupId(cityDupInfo.getDupId());
					dupInfo.setDupNum(cityDupInfo.getDupNum());
					dupInfo.setDupStar(cityDupInfo.getStar());
					cityBuilder.addDupInfo(dupInfo);
				}
				dupStatus = pveCityEntity.getDupAwardMap().getOrDefault(cityId, dupStatus);
				cityBuilder.setStatus(dupStatus);
				builder.addCityInfo(cityBuilder);
			}
			builder.setStatus(ErrorCodeConst.SUCCESS);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 客户端获取景点收集城市关卡进度
	 */
	public void CGCityAward(MsgBack msgBack, long uid, CGCityAwardProto msg) {
		GCCityAwardMsg.GCCityAwardProto.Builder builder = GCCityAwardMsg.GCCityAwardProto.newBuilder();
		msgBack.addBuilder(builder);

		int type = msg.getInfoType();
		int cityId = msg.getCityId();
		builder.setInfoType(msg.getInfoType());
		builder.setCityId(cityId);

		// 目前只有景点收集和完成某一张地图的所有关卡两种方式
		if (type != PveCityConstants.CITY_INFO_DUP_ACHIEVE && type != PveCityConstants.CITY_INFO_PVE_ACHIEVE) {
			builder.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
				return;
			}
			PveCityEntity pveCityEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
			if (pveCityEntity == null) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			if (type == PveCityConstants.CITY_INFO_PVE_ACHIEVE) {
				int cityStatus = pveCityEntity.getCityAwardMap().getOrDefault(cityId, PveCityConstants.CITY_ACHIEVE_STATUS_PROCESS);
				if (cityStatus == PveCityConstants.CITY_ACHIEVE_STATUS_AWARD) {
					builder.setStatus(ErrorCodeConst.ALREADY_REWARD);
					return;
				} else if (cityStatus == PveCityConstants.CITY_ACHIEVE_STATUS_PROCESS) {
					builder.setStatus(ErrorCodeConst.NOT_REWARD);
					return;
				}
				pveCityEntity.getCityAwardMap().put(cityId, PveCityConstants.CITY_ACHIEVE_STATUS_AWARD);
			} else {
				int dupStatus = pveCityEntity.getDupAwardMap().getOrDefault(cityId, PveCityConstants.CITY_ACHIEVE_STATUS_PROCESS);
				if (dupStatus == PveCityConstants.CITY_ACHIEVE_STATUS_AWARD) {
					builder.setStatus(ErrorCodeConst.ALREADY_REWARD);
					return;
				}
				if (checkDupComplete(uid, cityId)) {
					pveCityEntity.getDupAwardMap().put(cityId, PveCityConstants.CITY_ACHIEVE_STATUS_AWARD);
				} else {
					builder.setStatus(ErrorCodeConst.NOT_REWARD);
					return;
				}
			}
			Globals.getEntityProxy().updateAsync(pveCityEntity);
			builder.setStatus(ErrorCodeConst.SUCCESS);
			Map<Integer, List<ItemVo>> cityTypeMap = cityTypeAwardMap.get(type);
			if (cityTypeMap == null) {
				return;
			}
			List<ItemVo> items = cityTypeMap.get(cityId);
			if (items == null) {
				return;
			}
			for (ItemVo award : items) {
				if (award == null || award.getNum() == 0) {
					continue;
				}
				GameGlobals.bagManager.addItem(uid, award, LogfConstants.CHANNEL_CITYAWARD, String.valueOf(type), String.valueOf(cityId));
			}
		} finally {
			lock.unlock();
		}
	}

	public void CGChangeCityMsg(MsgBack msgBack, long uid, CGChangeCityProto msg) {
		GCChangeCityMsg.GCChangeCityProto.Builder bd = GCChangeCityMsg.GCChangeCityProto.newBuilder();
		msgBack.addBuilder(bd);

		bd.setCityId(msg.getCityId());
		bd.setStatus(ErrorCodeConst.SUCCESS);
		GameGlobals.guideManager.onCheck(uid, GuideConst.OPEN_TYPE_CHANGE_CITY_FIRST, msg.getCityId());
	}

	/**
	 * 关卡购买装饰
	 */
	@Deprecated
	public void CGPveCityBuyDec(MsgBack msgBack, long uid, CGPveCityBuyDecProto msg) {
		GCPveCityBuyDecMsg.GCPveCityBuyDecProto.Builder builder = GCPveCityBuyDecMsg.GCPveCityBuyDecProto.newBuilder();
		msgBack.addBuilder(builder);
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
				return;
			}

			PveCityEntity pveCityEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
			if (pveCityEntity == null) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			int cityId = msg.getCityId();
			int decId = msg.getDecId();
			int pveId = msg.getPveId();

			if (cityId == 0 || decId == 0 || pveId == 0) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}
//			int status = getCityDec(role, null, decId);
//
//			if (status != ErrorCodeConst.SUCCESS) {
//				builder.setStatus(status);
//				return;
//			}

			Globals.getEntityProxy().updateAsync(pveCityEntity);
			builder.setDecId(decId);
			builder.setPveId(pveId);
			builder.setCityId(cityId);
			builder.setStatus(ErrorCodeConst.SUCCESS);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 关卡镶嵌
	 */
	@Deprecated
	public void CGPveCityAttach(MsgBack msgBack, long uid, CGPveCityAttachProto msg) {
		GCPveCityAttachMsg.GCPveCityAttachProto.Builder builder = GCPveCityAttachMsg.GCPveCityAttachProto.newBuilder();
		GCPveCityAttachMsg.CityInfo.Builder cityBuilder = GCPveCityAttachMsg.CityInfo.newBuilder();
		builder.setCityInfo(cityBuilder);
		msgBack.addBuilder(builder);
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
				return;
			}

			PveCityEntity pveCityEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
			if (pveCityEntity == null) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			int cityId = msg.getCityId();
			int decId = msg.getDecId();
			int pveId = msg.getPveId();

			if (cityId == 0 || decId == 0 || pveId == 0) {
				builder.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			// 检验pveid是否合法
			if ((pveCityEntity.getCurPveTarget() == pveId && pveCityEntity.getCurTargetStatus() == EPveStatus.UNLOCK.getStatus())
					|| pveCityEntity.getCurPveTarget() < pveId) {
				builder.setStatus(ErrorCodeConst.PVE_ATTACH_UNLOCK);
				return;
			}
			// 检验是否为景点关卡
			PveBean pveBean = Datas.get(PveBean.class, pveId);
			if (pveBean == null || pveBean.getType() != PveCityConstants.PVE_CITY_SCENICSPOT) {
				builder.setStatus(ErrorCodeConst.PVE_ATTACH_PVE_TARGET);
				return;
			}
			// 检验装饰是否为合法装饰
//			HomedecorationBean homedecorationBean = Datas.getDataById(HomedecorationBean.class, decId);
//			if (homedecorationBean == null || pveBean.getDecorationGroup() != homedecorationBean.getGroup()) {
//				builder.setStatus(ErrorCodeConst.PVE_ATTACH_GROUP_ERROR);
//				return;
//			}
			// 镶嵌后根据状态判断是否应该完成
			if (pveCityEntity.getCurPveTarget() == pveId && pveCityEntity.getCurTargetStatus() == EPveStatus.PVE_PASS.getStatus()) {
				GameGlobals.pveCityManager.changPveStatus(msgBack, uid, EPveStatus.COMPLETE);
			}

			Globals.getEntityProxy().updateAsync(pveCityEntity);

			builder.setPveId(pveCityEntity.getCurPveTarget());
			builder.setPveStatus(pveCityEntity.getCurTargetStatus());
			builder.setCityId(pveCityEntity.getCurCityId());
			builder.setDecId(decId);
			// 填充所选城市信息
			cityBuilder.setCityId(cityId);
			builder.setCityInfo(cityBuilder);
			builder.addAllCityIds(pveCityEntity.getCityIds());
			// 检测家园道具镶嵌引导
			checkHomeDecorationGuide(uid);
			builder.setStatus(ErrorCodeConst.SUCCESS);
//			// 添加日志
//			HomedecorationBean decBean = Datas.getDataById(HomedecorationBean.class, decId);
//			Logf.log(LogfType.HOME_ATTCH_SLOT, uid, Globals.getUserBeanManager().getUserBean(uid), cityId + "", pveId + "", decId + "", decBean.getName(),
//					"1");
		} finally {
			lock.unlock();
		}
	}

	/**
	 * gm指令 使玩家通关至当前选择的pveid
	 */
	public void gmUpdatePveIdWithStatus(long uid, int pveId, int status) {
		this.updatePveId(uid, pveId, EPveStatus.getStatusById(status));
	}

	/**
	 * gm指令 使玩家通关至当前选择的pveid
	 */
	public void gmUpdatePveId(long uid, int pveId) {
		this.updatePveId(uid, pveId, EPveStatus.PVE_PASS);
	}

	/**
	 * 初始化pve关卡系统
	 */
	public void initPveCity(long uid) {
		PveCityEntity entity = this.getPveCityEntityByUid(uid);
		checkPveCityEntity(entity, uid);
	}

	/**
	 * 关卡状态修改
	 */
	public void changPveStatus(MsgBack msgBack, long uid, EPveStatus status) {
		status.getHandler().process(msgBack, uid);
	}

	/**
	 * pve过关
	 */
	public void completePveTarget(MsgBack msgback, long uid) {
		changPveStatus(msgback, uid, EPveStatus.PVE_PASS);
		PveCityEntity pveCityEntity = this.getPveCityEntityByUid(uid);
		Globals.getEntityProxy().updateAsync(pveCityEntity);
		// 再发一遍pvecityinfo
//		CGPveCityInfo(msgback, uid, CGPveCityInfoProto.getDefaultInstance());
	}

	/**
	 * 获取玩家关卡数据信息
	 */
	public PveCityEntity getPveCityEntityByUid(long uid) {
		return Globals.getEntityProxy().get(PveCityEntity.class, uid);
	}

	/**
	 * 获取玩家的当前的关卡
	 */
	public int getPveCurTarget(long uid) {
		PveCityEntity entity = this.getPveCityEntityByUid(uid);
		if (entity == null) {
			return 0;
		}

		PveBean pveBean = Datas.get(PveBean.class, entity.getCurPveTarget());
		if (pveBean == null) {
			return -1;
		}
		// 战斗结算就不要判断了 因为可能会增加不可预知的结果
		if (entity.getCurTargetStatus() != EPveStatus.UNLOCK.getStatus()) {
			return 0;
		}
		return pveBean.getTargetId();
	}

	/**
	 * 获取副本状态id
	 */
	public int checkDupOpen(long uid, int cityId, int dupId) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			PveCityEntity entity = this.getPveCityEntityByUid(uid);
			if (entity == null) {
				return 0;
			}
			/** 检查副本是否开启 */
			Duplicate dup = entity.getCityDupInfo(cityId, dupId);
			// 副本不存在或者已经获得3星评级 不可再次进入
			if (dup == null || dup.getStar() == 3) {
				return -1;
			}
			return 1;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 获取下一关的关卡id
	 */
	public int getPveNextTarget(long uid) {
		PveCityEntity entity = this.getPveCityEntityByUid(uid);
		if (entity == null) {
			return 0;
		}
		PveBean pveBean = Datas.getCeilingData(PveBean.class, entity.getCurPveTarget() + 1);
		if (pveBean == null) {
			return -1;
		}
		return pveBean.getTargetId();
	}

	public List<ItemVo> recommendItems(int pveId, long uid) {
		PveBean bean = Datas.get(PveBean.class, pveId);
		List<ItemVo> recommendList = new ArrayList<ItemVo>();
		if (bean == null) {
			return recommendList;
		}
		if (bean.getRecommendItemList().isEmpty() && bean.getRecommendHero() == 0) {
			return recommendList;
		}

		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return recommendList;
		}
		if (role.getPveConWinNum() > 0) {
			return recommendList;
		}

		// 连败次数跟设置相等就免费 否则需要花钱购买 推荐优先道具
		int faildNum = Math.abs(role.getPveConWinNum());
		if (faildNum < bean.getFailedNum()) {
			return recommendList;
		}
		for (int i = 0; i < bean.getRecommendItemList().size(); i++) {
			ItemVo info = bean.getRecommendItemList().get(i).clone();
			// 推荐道具没有就要购买
			info.setNum(1);
			// 这里判断下是否需要购买道具 1需要购买 0不需要
			if (GameGlobals.itemManager.getItemEntity(uid, info.getId()) != null) {
				info.setNum(0);
			}
			recommendList.add(info);
		}
		if (recommendList.size() == 0) {
			ItemVo info = new ItemVo();
			info.setType(EItemType.HERO.getType());
			info.setId(bean.getRecommendHero());
			if (faildNum > bean.getFailedNum()) {
				// 这里判断下是否需要购买英雄 1需要购买 0不需要
				if (GameGlobals.heroManager.hasHero(uid, info.getId())) {
					info.setNum(0);
				} else {
					info.setNum(1);
				}
			}
			recommendList.add(info);
		}
		return recommendList;
	}

	private void updatePveId(long uid, int pveId, EPveStatus status) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			Globals.getEntityProxy().delete(this.getPveCityEntityByUid(uid));
			PveCityEntity entity = null;
			entity = this.checkPveCityEntity(entity, uid);

			/** 指定的pveid 需要初始化其副本信息 */
			for (int i = 1; i < pveId; i++) {
				if (i == 0) {
					continue;
				}
				PveBean pveBean = Datas.getCeilingData(PveBean.class, i);
				if (pveBean == null) {
					continue;
				}
				entity.addCityDup(pveBean.getCity(), pveBean.getDupId());
				entity.addCityId(pveBean.getCity());
			}

			PveBean pveBean = Datas.getCeilingData(PveBean.class, pveId);
			if (pveBean != null) {
				entity.setCurPveTarget(pveId);
				entity.setCurCityId(pveBean.getCity());
				entity.setCityId(pveBean.getCity());
				if (!entity.getCityIds().contains(pveBean.getCity())) {
					entity.getCityIds().add(pveBean.getCity());
				}
				changPveStatus(new MsgBack(), uid, status);
				Globals.getEntityProxy().updateAsync(entity);
			}

			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role != null) {
				role.setDupStar(0);
				Globals.getEntityProxy().updateAsync(role);
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 检查是否需要初始化关卡
	 */
	private PveCityEntity checkPveCityEntity(PveCityEntity entity, long uid) {
		if (entity != null && entity.getCurPveTarget() != 0 && entity.getTargetId() != 0)
			return entity;
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			// 这里处理后期增加的玩家字段没有初始化的问题 
			// 这个地方在登录的时候初始化 后面的role信息用的上
			if (entity != null) {
				if (entity.getCurPveTarget() == 0) {
					PveBean pveBean = Datas.getCeilingData(PveBean.class, 1);
					entity.setCurPveTarget(pveBean.getId());
					entity.setCurTargetStatus(EPveStatus.UNLOCK.getStatus());
					entity.setTargetId(pveBean.getTargetId());
					Globals.getEntityProxy().updateAsync(entity);
				} else if (entity.getTargetId() == 0) {
					PveBean pveBean = Datas.getCeilingData(PveBean.class, entity.getCurPveTarget());
					if (pveBean != null) {
						entity.setTargetId(pveBean.getTargetId());
						Globals.getEntityProxy().updateAsync(entity);
					}
				}
				return entity;
			}

			entity = new PveCityEntity();
			entity.setUid(uid);

			PveBean pveBean = Datas.getCeilingData(PveBean.class, 1);
			if (pveBean != null) {
				if (pveBean.getCity() != entity.getCurCityId()) {
					// 开启新地图
					entity.setCurCityId(pveBean.getCity());
					entity.setCityId(pveBean.getCity());
					entity.addCityId(pveBean.getCity());
				}
				entity.setCurPveTarget(pveBean.getId());
				entity.setCurTargetStatus(EPveStatus.UNLOCK.getStatus());
				entity.setTargetId(pveBean.getTargetId());
				Globals.getEntityProxy().insert(entity);

//				RoleEntity role = Globals.getEntityProxy().get(RoleEntity.class, uid);
//				role.setPveTaskId(pveBean.getTargetId());
//				Globals.getEntityProxy().update(role);
			}
			return entity;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 检测最后一关是否已经完成 并且是否有新的关卡加入进来
	 */
	private void checkPveEntity(PveCityEntity entity, long uid) {
		if (entity == null || entity.getCurTargetStatus() != EPveStatus.COMPLETE.getStatus()) {
			return;
		}
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			PveBean pveBean = Datas.getCeilingData(PveBean.class, entity.getCurPveTarget() + 1);
			// 这里只关心切换地图的情况 如果没有切换地图新增了 那么正常给值就行了
			if (pveBean != null && pveBean.getCity() != entity.getCurCityId()) {
				// 开启新地图
				entity.setCurCityId(pveBean.getCity());
				entity.setCityId(pveBean.getCity());
				entity.addCityId(pveBean.getCity());
				entity.setCurTargetStatus(EPveStatus.UNLOCK.getStatus());
				entity.setLastPveTarget(entity.getCurPveTarget());
				entity.setCurPveTarget(pveBean.getId());
				entity.setTargetId(pveBean.getTargetId());
				Globals.getEntityProxy().updateAsync(entity);

//				RoleEntity role = Globals.getEntityProxy().get(RoleEntity.class, uid);
//				if (role == null) {
//					return;
//				}
//				role.setPveTaskId(pveBean.getTargetId());
//				Globals.getEntityProxy().updateAsync(role);
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 装饰槽位个数
	 */
	private void checkHomeDecorationGuide(long uid) {
		int decCount = 0;
		PveCityEntity pveCityEntity = this.getPveCityEntityByUid(uid);
		if (pveCityEntity == null) {
			return;
		}
		// 检测引导
		GameGlobals.guideManager.onCheckPreFinish(uid, EGuidePreFinishType.HOME_DECORATION_NUM, decCount);
		GameGlobals.guideManager.onCheck(uid, GuideConst.OPEN_TYPE_HOME_DECORATION, decCount);
	}

	private boolean checkDupComplete(long uid, int cityId) {
		PveCityEntity entity = this.getPveCityEntityByUid(uid);
		if (entity == null) {
			return false;
		}
		List<Integer> dupList = cityDupMap.get(cityId);
		if (dupList == null) {
			return false;
		}

		for (int i = 0; i < dupList.size(); i++) {
			Duplicate cityDupInfo = entity.getCityDupInfo(cityId, dupList.get(i));
			if (cityDupInfo == null || cityDupInfo.getDupNum() == 0) {
				return false;
			}
		}
		return true;
	}
}
