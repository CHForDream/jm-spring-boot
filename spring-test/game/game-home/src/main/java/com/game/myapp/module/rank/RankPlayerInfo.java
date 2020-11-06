package com.game.myapp.module.rank;

import java.io.Serializable;

public class RankPlayerInfo implements Serializable, Cloneable {
	private static final long serialVersionUID = -5912337630002617271L;

	private long uid;//玩家uid
	private String name;//玩家名字
	private String header;//玩家头像
	private int level;//玩家等级

	public RankPlayerInfo() {
	}

	@Deprecated
	public RankPlayerInfo(long uid, String name, String header) {
		this.uid = uid;
		this.name = name;
		this.header = header;
	}

	public RankPlayerInfo(long uid, String name, String header, int level) {
		super();
		this.uid = uid;
		this.name = name;
		this.header = header;
		this.level = level;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	protected RankPlayerInfo clone() {
		try {
			return (RankPlayerInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		// 以下代码默认不会执行
		RankPlayerInfo rankPlayerInfo = new RankPlayerInfo();
		rankPlayerInfo.setUid(uid);
		rankPlayerInfo.setName(name);
		rankPlayerInfo.setLevel(level);
		rankPlayerInfo.setHeader(header);
		return rankPlayerInfo;
	}

	@Override
	public String toString() {
		return "RankPlayerInfo [uid=" + uid + ", name=" + name + ", header=" + header + ", level=" + level + "]";
	}
}
