package com.game.db.cache;

import com.game.core.cache.EhcacheUtil;
import com.game.db.entity.AchieveEntity;
import com.game.db.entity.DaytaskEntity;
import com.game.db.entity.GiftEntity;
import com.game.db.entity.HeroEntity;
import com.game.db.entity.ItemEntity;
import com.game.db.entity.PveCityEntity;
import com.game.db.entity.RoleBattleEntity;
import com.game.db.entity.RoleEntity;
import com.game.db.entity.RoleGuideEntity;
import com.game.db.entity.UserNameUidEntity;

public class CacheMapper {

	public static void init() {
		// Role
		EhcacheUtil.register(RoleEntity.class, EhcacheUtil.CACHE_TYPE_ROLE);
		EhcacheUtil.register(UserNameUidEntity.class, EhcacheUtil.CACHE_TYPE_ROLE);
		EhcacheUtil.register(RoleGuideEntity.class, EhcacheUtil.CACHE_TYPE_ROLE);
		EhcacheUtil.register(RoleBattleEntity.class, EhcacheUtil.CACHE_TYPE_ROLE);

		// Hero
		EhcacheUtil.register(HeroEntity.class, EhcacheUtil.CACHE_TYPE_HERO);

		// Item
		EhcacheUtil.register(ItemEntity.class, EhcacheUtil.CACHE_TYPE_ITEM);
		EhcacheUtil.register(GiftEntity.class, EhcacheUtil.CACHE_TYPE_ITEM);

		// Home
//		EhcacheUtil.register(HomeEntity.class, EhcacheUtil.CACHE_TYPE_HOME);
		EhcacheUtil.register(PveCityEntity.class, EhcacheUtil.CACHE_TYPE_HOME);

		// AchTask
		EhcacheUtil.register(AchieveEntity.class, EhcacheUtil.CACHE_TYPE_ACHTASK);
		EhcacheUtil.register(DaytaskEntity.class, EhcacheUtil.CACHE_TYPE_ACHTASK);
	}
}
