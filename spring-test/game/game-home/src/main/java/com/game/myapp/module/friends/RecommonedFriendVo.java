/**
 * 
 */
package com.game.myapp.module.friends;

import java.io.Serializable;

/**
 * 推荐好友Bean
 * 
 * @author LPF
 *
 */
public class RecommonedFriendVo implements Comparable<RecommonedFriendVo>, Serializable {
	private static final long serialVersionUID = 2629853486120798584L;

	/** 角色id */
	private long uid;

	/** 筛选权重 */
	private int weight;

	/** 角色name */
	private String name;

	/** 角色性别 */
	private int sex;

	/** 头像 */
	private String avatar = "";

	/** 段位 */
	private int duan;

	/** showId */
	private long showId;

	public long getShowId() {
		return showId;
	}

	public void setShowId(long showId) {
		this.showId = showId;
	}

	public int getDuan() {
		return duan;
	}

	public void setDuan(int duan) {
		this.duan = duan;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Override
	public int compareTo(RecommonedFriendVo o) {
		return this.getWeight() - o.getWeight();
	}

	@Override
	public String toString() {
		return "RecommonedFriendVo [uid=" + uid + ", weight=" + weight + ", name=" + name + ", sex=" + sex + ", avatar=" + avatar + ", duan=" + duan + "]";
	}
}
