package com.game.core.redis;

import java.util.List;
import java.util.Set;

import com.game.constants.Loggers;
import com.game.global.Globals;
import com.game.global.RedisConfigBean;
import com.game.utils.RandomUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

public class RedisConnFactory {
	public static final int REDIS_TYPE_HOME = 1;
	public static final int REDIS_TYPE_SERVICE = 2;

	public static final String GAME_FOLDER_CONFIG = "config";
	public static final String GAME_FOLDER_RANK = "rank";
	public static final String GAME_FOLDER_DB = "db";
	public static final String GAME_FOLDER_COMMENT = "comment";

	public static final String SERVER_FOLDER_SERVER_LOGICAL = "game:%s:server:%s";
	public static final String SERVER_FOLDER_SERVER_UPDATE = "game:%s:updateTime";

	private static JedisPool GAME_JEDIS_POOL = null;
	private static JedisPool SERVER_JEDIS_POOL = null;

	public static void init() {
		initGameJedisPool();
		initServerJedisPool();
	}

	/**
	 * 执行Redis事务
	 * 
	 * @param redisTransaction
	 */
	public static void excuteRedisTransaction(IRedisTransaction redisTransaction) {
		Jedis jedis = GAME_JEDIS_POOL.getResource();// 事务链接
		try {
			// 开始监测key
			jedis.watch(redisTransaction.getKeyArray());

			StringBuilder sb = new StringBuilder();
			for (String key : redisTransaction.getKeyArray()) {
				sb.append(key).append(",");
			}
			String keyStr = sb.toString();
			boolean transactionResult = true;
			do {
				Transaction transaction = jedis.multi();// 开始事务
				if (redisTransaction.isDiscard()) {
					// 取消事务
					transaction.discard();

					transactionResult = true;
					Loggers.redisLogger.info("Discard transaction! key = " + keyStr + " desc = " + redisTransaction.getDescription());
				} else {
					// 执行事务
					redisTransaction.excute(transaction);
					List<Object> resultList = transaction.exec();// 执行
					transactionResult = resultList != null;
					if (transactionResult) {
						// 执行成功
						Loggers.redisLogger.info("Transaction success! Exec. key = " + keyStr + " desc = " + redisTransaction.getDescription());
					} else {
						// 执行失败
						Loggers.redisLogger.info("Transaction failed! Exec. key = " + keyStr + " desc = " + redisTransaction.getDescription());
					}
				}
			} while (!transactionResult && redisTransaction.isRetry());

			// 结束监测key
			jedis.unwatch();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public static Jedis getGameJedis() {
		return GAME_JEDIS_POOL.getResource();
	}

	public static Jedis getServerJedis() {
		return SERVER_JEDIS_POOL.getResource();
	}

	private static void initGameJedisPool() {
		initJedisPool(REDIS_TYPE_HOME);
	}

	private static void initServerJedisPool() {
		initJedisPool(REDIS_TYPE_SERVICE);
	}

	private static void initJedisPool(int redisType) {
		try {
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			/** 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true */
			poolConfig.setBlockWhenExhausted(true);

			/** 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数) */
			poolConfig.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
			/**
			 * 当从池中获取资源或者将资源还回池中时 是否使用java.util.concurrent.locks.ReentrantLock.ReentrantLock
			 * 的公平锁机制,默认为false
			 */
			poolConfig.setFairness(false);

			/** 设置是否启用JMX,默认true */
			poolConfig.setJmxEnabled(true);

			/** 设置连接对象是否后进先出,默认true */
			poolConfig.setLifo(true);

			/*** 控制一个pool最多有多少个状态为idle(空闲)的jedis实例;128 */
			poolConfig.setMaxIdle(512);

			/*** 设置最大连接数,默认18个;1024 */
			poolConfig.setMaxTotal(1024);

			/**
			 * 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间, 默认-1;3000
			 */
			poolConfig.setMaxWaitMillis(1500);

			/** 设置连接最小的逐出间隔时间，默认1800000毫秒 */
			poolConfig.setMinEvictableIdleTimeMillis(60 * 1000);

			/** 设置无连接时池中最小的连接个数,默认连接0 */
			poolConfig.setMinIdle(8);
			/** 每次逐出检查时,逐出连接的个数 */
			poolConfig.setNumTestsPerEvictionRun(32);

			/** 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断 */
			poolConfig.setSoftMinEvictableIdleTimeMillis(180 * 1000);

			/** 从池中获取连接时是否测试连接的有效性,默认false */
			poolConfig.setTestOnBorrow(false);
			/** 在连接对象创建时测试连接对象的有效性,默认false */
			poolConfig.setTestOnCreate(false);
			/** 在连接对象返回时，是否测试对象的有效性,默认false */
			poolConfig.setTestOnReturn(false);
			/** 在连接池空闲时是否测试连接对象的有效性,默认false */
			poolConfig.setTestWhileIdle(true);

			/*** 表示idle object evitor两次扫描之间要sleep的毫秒数； */
			poolConfig.setTimeBetweenEvictionRunsMillis(1000 * 60);

			RedisConfigBean redisConfigBean = Globals.applicationContext.getBean(RedisConfigBean.class);
			switch (redisType) {
			case REDIS_TYPE_HOME:
				GAME_JEDIS_POOL = new JedisPool(poolConfig, redisConfigBean.getHomeRedisHost(), redisConfigBean.getHomeRedisPort(), 3000,
						redisConfigBean.getHomeRedisPassword(), redisConfigBean.getHomeRedisDb());
				break;
			case REDIS_TYPE_SERVICE:
				SERVER_JEDIS_POOL = new JedisPool(poolConfig, redisConfigBean.getServiceRedisHost(), redisConfigBean.getServiceRedisPort(), 3000,
						redisConfigBean.getServiceRedisPassword(), redisConfigBean.getServiceRedisDb());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Loggers.redisLogger.error("Build Connection Pool Failure", e);
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		RedisConnFactory.init();

		// 事务测试
//		testTransaction();

		// 事务测试
		testTransaction2();

//		testZSet();

//		testZSet2();
	}

	private static void testTransaction2() {
		// 初始化数据
		String testKey = "uid";
		Jedis jedis = GAME_JEDIS_POOL.getResource();
		try {
			jedis.set(testKey, "1");
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

		// 执行Redis事务
		IRedisTransaction logoutRedisTransaction = new TestRedisTransaction(testKey);
		excuteRedisTransaction(logoutRedisTransaction);
	}

	@SuppressWarnings("unused")
	private static void testTransaction() {
		Jedis jedis = GAME_JEDIS_POOL.getResource();// 事务链接
		Jedis jedis2 = GAME_JEDIS_POOL.getResource();// 打断事务链接
		try {
			// 初始化数据
			String key = "testKey";
			String value = "testValue";
			jedis.set(key, value);

			// 开始监测key, 返回值为OK
			jedis.watch(key);

			String resultValue = jedis.get(key);

			boolean transactionResult = true;
			do {
				Transaction multi = jedis.multi();// 开始事务

				// 修改目标数据, 打断事务操作
				jedis2.set(key, value + "999");

				System.out.println("get value = " + resultValue);
				if (!value.equals(resultValue)) {
					// 存储的值不同, 取消事务
					System.out.println("Transaction success! Discard.");
					multi.discard();

					transactionResult = true;
				} else {
					// 存储的值相同, 执行事务
					multi.del(key);// 事务执行指令1
//					multi.set(key, value + "1");// 事务执行指令1
					List<Object> resultList = multi.exec();// 执行
					boolean execResult = true;
					if (resultList == null) {
						// 执行失败, 继续while循环
						execResult = false;
						System.out.println("Transaction failed!");
					}

					transactionResult = execResult;
					if (execResult) {
						System.out.println("Transaction success! Exec.");
					}
				}
				if (!transactionResult) {
					// 重新获取数据
					resultValue = jedis.get(key);
				}
			} while (!transactionResult);

			System.out.println("Done!");

			// 结束监测key, 返回值为OK
			jedis.unwatch();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
			if (jedis2 != null) {
				jedis2.close();
			}
		}
	}

	@SuppressWarnings("unused")
	private static void testZSet2() {
		Jedis jedis = GAME_JEDIS_POOL.getResource();
		try {
			String key = "tSet";
			Set<String> zremrangeByLex = jedis.zrangeByLex(key, "[999999", "[9999990");
			for (String ele : zremrangeByLex) {
				System.out.println(ele);
			}

			Set<Tuple> zrangeByScoreWithScores = jedis.zrangeByScoreWithScores(key, 0, Long.MAX_VALUE);
			for (Tuple tuple : zrangeByScoreWithScores) {
				System.out.println("score: " + tuple.getScore());
				System.out.println("Element: " + tuple.getElement());
			}

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@SuppressWarnings("unused")
	private static void testZSet() {
		String key = "pveStageIdRank";
		Jedis jedis = GAME_JEDIS_POOL.getResource();
		int n = 10;
		Long[] uidArray = new Long[n];
		int[] scoreArray = new int[n];
		for (int i = 0; i < n; i++) {
			uidArray[i] = i + 0L;
		}

		for (int i = 0; i < n; i++) {
			scoreArray[i] = -RandomUtil.random.nextInt(10000);
		}

		System.out.println("添加member到zSet, 或修改某个元素的score值");
		for (int i = 0; i < n; i++) {
			int score = scoreArray[i];
			String uid = uidArray[i] + "";
			System.out.println("zadd: uid = " + uid + ", score = " + score);
			jedis.zadd(key, scoreArray[i], uid);
		}

		for (int i = 0; i < n; i++) {
			int score = scoreArray[i];
			String uid = uidArray[i] + "";
			System.out.println("zadd: uid = " + uid + ", score = " + score);
			jedis.zadd(key, scoreArray[i], uid);
		}

		System.out.println("\n-------------------------\n");

		System.out.println("查询zSet的member个数");
		System.out.println("zcard: count = " + jedis.zcard(key));

		System.out.println("\n-------------------------\n");

		System.out.println("查询某个玩家的分数");
		String uid = uidArray[0] + "";
		Double zscoreDouble = jedis.zscore(key, uid);
		Long zrank = jedis.zrank(key, uid);
		if (zscoreDouble != null) {
			int score = zscoreDouble.intValue();
			System.out.println("zscore: uid = " + uid + ", score = " + score + ", rank = " + zrank);
		}

		System.out.println("\n-------------------------\n");

		System.out.println("查询某个玩家的分数2");
		zscoreDouble = jedis.zscore(key, "100");
		zrank = jedis.zrank(key, "100");
		if (zscoreDouble != null) {
			int score = zscoreDouble.intValue();
			System.out.println("zscore: uid = " + 100 + ", score = " + score + ", rank = " + zrank);
		} else {
			System.out.println("zscore: uid = " + 100 + ", No score!" + zscoreDouble + zrank);
		}

		System.out.println("\n-------------------------\n");

		System.out.println("指定member加10分数");
		double increment = 10D;
		double decrement = -10D;
		Double oldScoreDouble = jedis.zscore(key, uid);
		zscoreDouble = jedis.zincrby(key, increment, uid);
		System.out.println("zincrby: uid = " + uid + ", oldScore = " + oldScoreDouble + ", score = " + zscoreDouble);
		oldScoreDouble = zscoreDouble;
		System.out.println("指定member减10分数");
		zscoreDouble = jedis.zincrby(key, decrement, uid);
		System.out.println("zincrby: uid = " + uid + ", oldScore = " + oldScoreDouble + ", score = " + zscoreDouble);

		System.out.println("\n-------------------------\n");

		System.out.println("查询前5名玩家数据");
		Set<Tuple> zrevrangeWithScores = jedis.zrevrangeWithScores(key, 0, 4);
		System.out.println("Top size = " + zrevrangeWithScores.size());
		for (Tuple tuple : zrevrangeWithScores) {
			System.out.println("tuple.element = " + tuple.getElement() + ", tuple.score = " + tuple.getScore());
		}

		System.out.println("\n-------------------------\n");

		System.out.println("查询最后一名玩家的分数");
		Set<Tuple> zrangeWithScores = jedis.zrangeWithScores(key, 0, 0);
		for (Tuple tuple : zrangeWithScores) {
			System.out.println("tuple.element = " + tuple.getElement() + ", tuple.score = " + tuple.getScore());
		}
		System.out.println("\n-------------------------\n");
		System.out.println("查询某一分数段的数据个数. zcount计算的数量包括最小值和最大值两个端点值");
		System.out.println("zcount = " + jedis.zcount(key, Long.MIN_VALUE, -1));

		System.out.println("\n-------------------------\n");
		System.out.println("查询某数据的排行. zrank返回的是index值, 从0开始, 排行值要在zrank的基础上+1");
		System.out.println("zrank = " + (jedis.zrank(key, uid) + 1));

		System.out.println("\n-------------------------\n");

		System.out.println("删除第8名之后的所有数据");
		Long count = jedis.zcard(key);
		Long deleteCount = jedis.zremrangeByRank(key, 8, count - 1);
		Long resultCount = jedis.zcard(key);
		System.out.println("zremrangeByRank: count = " + count + ", deleteCount = " + deleteCount + ", resultCount = " + resultCount);

		System.out.println("\n-------------------------\n");

		System.out.println("删除所有数据");
		count = jedis.zcard(key);
		deleteCount = count;
		jedis.del(key);
		resultCount = jedis.zcard(key);
		System.out.println("zremrangeByRank: count = " + count + ", deleteCount = " + deleteCount + ", resultCount = " + resultCount);

//		System.out.println("删除所有数据");
//		count = jedis.zcard(key);
//		deleteCount = jedis.zremrangeByRank(key, 0, -1);
//		resultCount = jedis.zcard(key);
//		System.out.println("zremrangeByRank: count = " + count + ", deleteCount = " + deleteCount + ", resultCount = " + resultCount);

		jedis.close();
	}
}
