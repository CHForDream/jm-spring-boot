package com.game.gm.handler;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.CMDConstants;
import com.game.db.entity.HeroEntity;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.vo.ResponseData;
import com.google.common.collect.Lists;

public class CmdSelectRoleHerosHandler extends AbsGmContoller {

	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		ResponseData<String, Object> data = ResponseData.newBuild();
		long uid = getUid(request);
		if (uid == 0) {
			data.put(CMDConstants.KEY_STATE, CMDConstants.REASON_INVALID_UID);
			return data;
		}

		List<HeroEntity> heroEntities = Globals.getEntityProxy().findAllByUidNotCache(HeroEntity.class, uid);
		List<Integer> heros = Lists.newArrayList();
		for (HeroEntity entity : heroEntities) {
			heros.add(entity.getHeroId());
		}
		data.put("heroIds", heros);
		data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_SUCCESS);
		return data;
	}

	public static class Hero implements Serializable {

		/**
		 *  	
		 */
		private static final long serialVersionUID = 1L;

		private int heroId;

		public int getHeroId() {
			return heroId;
		}

		public void setHeroId(int heroId) {
			this.heroId = heroId;
		}

		public Hero() {
		}

		public Hero(int heroId, int exp, int level, int proficiency) {
			this.heroId = heroId;
		}
	}
}
