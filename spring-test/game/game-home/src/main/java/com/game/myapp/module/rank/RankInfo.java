package com.game.myapp.module.rank;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import com.game.utils.JsonUtil;
import com.google.common.collect.Lists;

public class RankInfo implements Serializable, Cloneable {
	private static final long serialVersionUID = 499426097755420396L;

	/** 排行 */
	private int rank;
	/** 分数 */
	private long score;
	/** 玩家信息 */
	private List<RankPlayerInfo> playerInfoList = Lists.newArrayList();

	public RankInfo() {
	}

	public static RankInfo fromBytes(byte[] bytes) {
		ByteArrayInputStream byteArrayInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(bytes);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			return (RankInfo) objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
				}
			}
			if (byteArrayInputStream != null) {
				try {
					byteArrayInputStream.close();
				} catch (IOException e) {
				}
			}
		}

		return null;
	}

	public static byte[] toBytes(RankInfo rankInfo) {
		ByteArrayOutputStream byteOutStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			byteOutStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteOutStream);
			objectOutputStream.writeObject(rankInfo);
			objectOutputStream.flush();
			return byteOutStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
				}
			}
			if (byteOutStream != null) {
				try {
					byteOutStream.close();
				} catch (IOException e) {
				}
			}
		}

		return null;
	}

	public static RankInfo fromJson(String json) {
		RankInfo obj = JsonUtil.toObj(json, RankInfo.class);
		return obj;
	}

	public static String toJson(RankInfo rankInfo) {
		return JsonUtil.toJson(rankInfo);
	}

	public void addRankPlayerInfo(RankPlayerInfo rankPlayerInfo) {
		playerInfoList.add(rankPlayerInfo);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public List<RankPlayerInfo> getPlayerInfoList() {
		return playerInfoList;
	}

	public void setPlayerInfoList(List<RankPlayerInfo> playerInfoList) {
		this.playerInfoList = playerInfoList;
	}

	@Override
	protected RankInfo clone() {
		try {
			return (RankInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		// 以下代码默认不会执行
		RankInfo rankInfo = new RankInfo();
		rankInfo.setRank(rank);
		rankInfo.setScore(score);
		for (RankPlayerInfo rankPlayerInfo : getPlayerInfoList()) {
			rankInfo.getPlayerInfoList().add(rankPlayerInfo.clone());
		}
		return rankInfo;
	}

	@Override
	public String toString() {
		return "RankInfo [rank=" + rank + ", score=" + score + "]";
	}

//	public static void main(String[] args) {
//		RedisConnFactory.init();
//
//		RankInfo info1 = new RankInfo();
//		info1.setRank(1);
//		info1.setScore(10);
//		RankPlayerInfo player1 = new RankPlayerInfo();
//		player1.setUid(100);
//		player1.setLevel(9);
//		player1.setName("player1");
//		player1.setHeader("header1");
//		RankPlayerInfo player2 = new RankPlayerInfo();
//		player2.setUid(200);
//		player1.setLevel(8);
//		player2.setName("player2");
//		player2.setHeader("header2");
//
//		info1.getPlayerInfoList().add(player1);
//		info1.getPlayerInfoList().add(player2);
//
//		RankInfo info2 = info1.clone();
//		info2.setScore(20);
//
////		String bytes1 = RankInfo.toJson(info1);
////		String bytes2 = RankInfo.toJson(info2);
////		System.out.println(bytes1.equals(bytes2));
////		String key = "testKey";
//
//		byte[] bytes1 = RankInfo.toBytes(info1);
//		byte[] bytes2 = RankInfo.toBytes(info2);
//		System.out.println(bytes1.equals(bytes2));
//		byte[] key = "testKey".getBytes();
//
//		Jedis jedis = RedisConnFactory.getConn();
//		jedis.zadd(key, info1.getScore(), bytes1);
//		jedis.zadd(key, info2.getScore(), bytes2);
//		Long zrevrank1 = jedis.zrank(key, bytes1);
//		Long zrevrank2 = jedis.zrank(key, bytes2);
//		System.out.println("zrevrank1 = " + zrevrank1);
//		System.out.println("zrevrank2 = " + zrevrank2);
//
//		jedis.del(key);
//	}
}
