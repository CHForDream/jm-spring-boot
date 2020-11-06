package com.game.myapp.module.hero;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.RoleHeroLog;
import com.game.common.user.UserBean;
import com.game.core.handler.MsgBack;
import com.game.core.hibernate.key.LongIntPrimaryKey;
import com.game.db.entity.HeroEntity;
import com.game.db.entity.RoleBattleEntity;
import com.game.db.entity.RoleEntity;
import com.game.generate.bean.HeroBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.achieve.EAchieveBehavior;
import com.game.myapp.module.guide.EGuidePreFinishType;
import com.game.myapp.module.guide.GuideConst;
import com.game.myapp.module.hero.com.comment.HeroCommentManager;
import com.game.myapp.module.redhat.RedHatBehavior;

import buffer.GCGetHeroListMsg;

public class HeroManager {

	/** 评论管理 */
	private HeroCommentManager heroCommentManager = new HeroCommentManager();

	public void CGGetHeroList(MsgBack msgBack, UserBean userBean) {
		GCGetHeroListMsg.GCGetHeroListProto.Builder backMsg = GCGetHeroListMsg.GCGetHeroListProto.newBuilder();
		msgBack.addBuilder(backMsg);
		List<HeroEntity> list = Globals.getEntityProxy().findAllByUid(HeroEntity.class, userBean.getUid());
		for (HeroEntity temp : list) {
			HeroBean bean = Datas.get(HeroBean.class, temp.getHeroId());
			if (bean == null) {
				continue;
			}

			GCGetHeroListMsg.HeroGCGetHeroList.Builder heroMsg = GCGetHeroListMsg.HeroGCGetHeroList.newBuilder();
			heroMsg.setHeroId(temp.getHeroId());
			heroMsg.setHeroCode(temp.getHeroId());
			backMsg.addHeros(heroMsg);
		}
	}

	public HeroCommentManager getHeroCommentManager() {
		return heroCommentManager;
	}

	public int addHero(long uid, int heroCode) {
		return this.addHero(uid, heroCode, false);
	}

	public int addHeroInit(long uid, int heroCode) {
		return this.addHero(uid, heroCode, true);
	}

	public HeroEntity getHeroEntity(long uid, int heroId) {
		return Globals.getEntityProxy().get(HeroEntity.class, new LongIntPrimaryKey(uid, heroId));
	}

	public void deleteHero(long uid, int heroCode) {
		List<HeroEntity> list = getDBList(uid);
		HeroEntity db = null;
		for (HeroEntity temp : list) {
			if (temp.getHeroId() == heroCode) {
				db = temp;
				break;
			}
		}
		if (db != null) {
			Globals.getEntityProxy().delete(db);
		}
	}

	/**
	 * 根据heroId获取hero
	 * 
	 */
	public HeroEntity getHeroEntityByHeroId(List<HeroEntity> entities, int heroId) {
		for (HeroEntity entity : entities) {
			if (entity.getHeroId() == heroId) {
				return entity;
			}
		}
		return null;
	}

	/**
	 * 获取英雄个数
	 * 
	 * @param uid
	 * @return
	 */
	public int getHeroSize(long uid) {
		List<HeroEntity> list = getDBList(uid);
		return list.size();
	}

	public List<HeroEntity> getDBList(long uid) {
		return Globals.getEntityProxy().findAllByUid(HeroEntity.class, uid);
	}

	public List<HeroEntity> getAllHeroByCode(long uid, int heroCode) {
		heroCode = Datas.get(HeroBean.class, heroCode).getId();
		List<HeroEntity> resultList = new ArrayList<HeroEntity>();
		List<HeroEntity> list = getDBList(uid);
		for (HeroEntity temp : list) {
			if (Datas.get(HeroBean.class, temp.getHeroId()).getId() == heroCode) {
				resultList.add(temp);
			}
		}
		return resultList;
	}

	/**
	 * 玩家是否存在这个英雄
	 * 
	 */
	public boolean hasHero(long uid, int heroId) {
		HeroEntity entity = getHeroEntity(uid, heroId);
		return entity != null;
	}

	/**
	 * @param uid
	 */
	public void gmAllHero(long uid) {
		for (HeroBean heroBean : Datas.getDataMap(HeroBean.class).values()) {
			int heroCode = heroBean.getId();
			if (this.hasHero(uid, heroCode)) {
				continue;
			}
			this.addHero(uid, heroCode);
		}
	}

	public boolean addHeroByDupstar(long uid) {
//		boolean changed = false;
//		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
//		if (role == null) {
//			return changed;
//		}
//		for (HeroBean heroBean : Datas.getDataMap(HeroBean.class).values()) {
//			int heroCode = heroBean.getId();
//			if (heroBean.getLockStatus() == 0 || heroBean.getLimitStar() > role.getDupStar() || this.hasHero(uid, heroCode)) {
//				continue;
//			}
//			changed = true;
//			this.addHero(uid, heroCode);
//		}
//		return changed;
		return false;
	}

	private int addHero(long uid, int heroCode, boolean init) {
		int result = this.doAddHero(uid, heroCode);
		if (!init && result > 0) {
			RoleBattleEntity entity = GameGlobals.roleManager.getRoleBattleEntity(uid);
			if (entity != null) {
				entity.setHeroId(heroCode);
				Globals.getEntityProxy().updateAsync(entity);
			}
		}
		return result;
	}

	private int doAddHero(long uid, int code) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			List<HeroEntity> list = getDBList(uid);
			for (HeroEntity temp : list) {
				if (temp.getHeroId() == code) {
					return -1;
				}
			}
			boolean isFristHero = list.isEmpty();

			HeroEntity heroDB = new HeroEntity();
			heroDB.setHeroId(code);
			heroDB.setGetTime(System.currentTimeMillis());
			heroDB.setUid(uid);
			// 存库
			Globals.getEntityProxy().insert(heroDB);

			// 修改玩家体力上限
			list = getDBList(uid);
			int heroPowerLimit = 0;
			for (HeroEntity heroEntity : list) {
				HeroBean heroBean = Datas.get(HeroBean.class, heroEntity.getHeroId());
				if (heroBean == null) {
					continue;
				}
				heroPowerLimit += heroBean.getPowerLimit();
			}
			RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(uid);
			int preHeroPowerLimit = roleEntity.getHeroPowerLimit();
			if (preHeroPowerLimit != heroPowerLimit) {
				// 修改英雄体力上限
				roleEntity.setHeroPowerLimit(heroPowerLimit);
				// 增加上限提升的体力
				int deltaPowerLimit = heroPowerLimit - preHeroPowerLimit;
				if (deltaPowerLimit > 0) {
					roleEntity.setPower(roleEntity.getPower() + deltaPowerLimit);
				}

				// 设置体力变化状态, 通知前端更新体力
				UserBean userBean = Globals.getUserManager().getUserBean(uid);
				if (userBean != null) {
					userBean.setPowerChanged(true);
				}
				Globals.getEntityProxy().updateAsync(roleEntity);
			}

			UserBean user = Globals.getUserManager().getUserBean(uid);
			if (user != null) {
				RoleHeroLog heroLog = new RoleHeroLog(user);
				heroLog.setHeroId(code);
				LogfPrinter.getInstance().push(heroLog);
			}

			GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_COLLEC_HERO, 1);
			//检测引导
			GameGlobals.guideManager.onCheckPreFinish(uid, EGuidePreFinishType.HERO_NUM, list.size() + 1);
			GameGlobals.guideManager.onCheck(uid, GuideConst.OPEN_TYPE_GET_HERO, code);// 获得某个英雄
			GameGlobals.guideManager.onCheck(uid, GuideConst.OPEN_TYPE_GET_HERO_NUM, list.size() + 1);// 获得英雄数量

//			// 检测装饰红点
//			for (HeroDecorateType type : HeroDecorateType.values()) {
//				GameGlobals.redHatManager.checkBehaviorRedHat(uid, type.getRedHatBehavior());
//			}

			// 新伙伴红点, 第一个伙伴不需要红点
			UserBean userBean = Globals.getUserManager().getUserBean(uid);
			if (!isFristHero && userBean != null) {
				userBean.setDirty(RedHatBehavior.HERO_GET_NEW.getBehavior(), true);
				userBean.setDirty(RedHatBehavior.HERO_CHANGE_NEW.getBehavior(), true);
			}

			// 检测宝石红点
			//			GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.ROLE_HERO_GEM);
			return heroDB.getHeroId();
		} finally {
			lock.unlock();
		}
	}
}
