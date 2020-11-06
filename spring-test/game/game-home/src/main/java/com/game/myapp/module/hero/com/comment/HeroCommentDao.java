package com.game.myapp.module.hero.com.comment;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import com.game.core.hibernate.key.LongIntPrimaryKey;
import com.game.core.redis.RedisConnFactory;
import com.game.db.entity.HeroCommentEntity;
import com.game.db.entity.PetsCommentEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class HeroCommentDao {

	/**
	 * 根据uid和heroCode英雄评论数据
	 * 
	 * @param uid
	 * @param heroCode
	 * @return
	 */
	public HeroCommentEntity findHeroCommentEntityByUidAndHeroCode(long uid, int commentId) {
		return Globals.getEntityProxy().get(HeroCommentEntity.class, new LongIntPrimaryKey(uid, commentId));
	}

	/**
	 * 根据uid和code查找宠物评论数据
	 * 
	 * @param uid
	 * @param commentId
	 * @return
	 */
	public PetsCommentEntity findPetsCommentEntityByUidAndCode(long uid, int commentId) {
		return Globals.getEntityProxy().get(PetsCommentEntity.class, new LongIntPrimaryKey(uid, commentId));
	}

	/***
	 * 插入英雄评论数据
	 * 
	 * @param uid
	 * @param heroCode
	 * @param comment
	 */
	public void insertHeroComment(long uid, int heroCode, String comment) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			// 插入mysql
			long currTime = System.currentTimeMillis() / 1000L;
			HeroCommentEntity heroCommentEntity = new HeroCommentEntity();
			heroCommentEntity.setUid(uid);
			heroCommentEntity.setCreateTime(currTime);
			heroCommentEntity.setHeroCode(heroCode);
			heroCommentEntity.setComment(comment);
			heroCommentEntity.setFabulousNum(0);
			Globals.getEntityProxy().insert(heroCommentEntity);

			// 插入redis
			this.insertRedisCommentTime(EComment.HERO, uid, heroCode, currTime);
		} finally {
			lock.unlock();
		}
	}

	/***
	 * 插入宠物评论数据
	 * 
	 * @param uid
	 * @param heroCode
	 * @param comment
	 */
	public void insertPetsComment(long uid, int heroCode, String comment) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			// 插入mysql
			long currTime = System.currentTimeMillis() / 1000L;
			PetsCommentEntity petsCommentEntity = new PetsCommentEntity();
			petsCommentEntity.setUid(uid);
			petsCommentEntity.setCreateTime(currTime);
			petsCommentEntity.setHeroCode(heroCode);
			petsCommentEntity.setComment(comment);
			petsCommentEntity.setFabulousNum(0);
			Globals.getEntityProxy().insert(petsCommentEntity);
			// 插入redis
			this.insertRedisCommentTime(EComment.PETS, uid, heroCode, currTime);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 插入redis时间榜
	 * 
	 * @param uid
	 * @param heroCode
	 * @param currTime
	 */
	private void insertRedisCommentTime(EComment type, long uid, int code, long currTime) {
		Jedis jedis = RedisConnFactory.getGameJedis();
		try {
			jedis.zadd(EHeroCommentType.valueOf(type, ECommentRankType.TIME).getRedisKey(code), -currTime, uid + "");
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 英雄点赞之后更新redis热度榜
	 * 
	 * @param heroCommentEntity
	 */
	public void afterFabulousUpdateRedis(EComment comment, long uid, int code, int fabulous) {
		Jedis jedis = RedisConnFactory.getGameJedis();
		try {
			String key = EHeroCommentType.valueOf(comment, ECommentRankType.FABULOUS).getRedisKey(code);
			Double score = jedis.zscore(key, uid + "");
			// 存在热度榜
			if (score != null) {
				jedis.zincrby(key, 1d, uid + "");
			} else {
				// 先验证热度榜是否满了
				Long rankCount = jedis.zcard(key);
				// 不满
				if (rankCount.intValue() < GameGlobals.configManager.getCommentUpLimit()) {
					jedis.zadd(key, 1d, uid + "");
				}
				// 热度榜满了
				else {
					Set<Tuple> zrangeWithScores = jedis.zrangeWithScores(key, 0, 0);
					Tuple lastScoreTuple = zrangeWithScores.iterator().next();
					int minScore = (int) lastScoreTuple.getScore();
					// 删除最后一个
					if (fabulous >= minScore) {
						jedis.zrem(key, lastScoreTuple.getElement());
						jedis.zadd(key, fabulous, uid + "");
					}
				}
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 宠物点赞时候操作redis
	 */

	/**
	 * 获取排名信息
	 * 
	 * @param heroCode
	 * @param etype
	 * @return
	 */
	public Set<Tuple> getRankInfoByHeroCodeAndType(int heroCode, EHeroCommentType etype) {
		Jedis jedis = RedisConnFactory.getGameJedis();
		try {
			Set<Tuple> result = jedis.zrangeWithScores(etype.getRedisKey(heroCode), 0, GameGlobals.configManager.getCommentUpLimit());
			return result;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 删除无效数据
	 */
	public void delInvalidComment() {
		Jedis jedis = RedisConnFactory.getGameJedis();
		try {
			long currTime = System.currentTimeMillis() / 1000L;
			long clearMinTime = currTime - (24 * 60 * 60 * 3);
			// 先清理英雄评论
			Set<String> heroTimeKeySet = jedis.keys(EHeroCommentType.HERO_TIME.getRedisKey("*"));
			for (String key : heroTimeKeySet) {
				Set<Tuple> clearData = jedis.zrangeByScoreWithScores(key, -Long.MAX_VALUE, -clearMinTime);
				Iterator<Tuple> it = clearData.iterator();
				int code = Integer.parseInt(key.split("_")[0]);
				while (it.hasNext()) {
					Tuple tuple = it.next();
					long uid = Long.valueOf(tuple.getElement());
					// 删除时间榜数据
					jedis.zrem(key, uid + "");
					Double score = jedis.zscore(EHeroCommentType.HERO_FABULOUS.getRedisKey(code), uid + "");
					if (score == null) {
						// 删除mysql数据
						HeroCommentEntity commentEntity = this.findHeroCommentEntityByUidAndHeroCode(uid, code);
						Globals.getEntityProxy().delete(commentEntity);
					}
				}
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	public void delHeroCommentEntity(HeroCommentEntity heroCommentEntity) {
		Globals.getEntityProxy().delete(heroCommentEntity);
	}

	public void delPetsCommentEntity(PetsCommentEntity petsCommentEntity) {
		Globals.getEntityProxy().delete(petsCommentEntity);
	}

	/**
	 * 删除redis的数据
	 * 
	 * @param comment
	 * @param uid
	 * @param heroCode
	 */
	public void delRedisCommentByCommentType(EComment comment, long uid, int heroCode) {
		Jedis jedis = RedisConnFactory.getGameJedis();
		try {
			// 删除时间榜
			EHeroCommentType commentTimeType = EHeroCommentType.valueOf(comment, ECommentRankType.TIME);
			jedis.zrem(commentTimeType.getRedisKey(heroCode), uid + "");

			// 删除热度榜
			EHeroCommentType commentFabulousType = EHeroCommentType.valueOf(comment, ECommentRankType.FABULOUS);
			jedis.zrem(commentFabulousType.getRedisKey(heroCode), uid + "");
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
