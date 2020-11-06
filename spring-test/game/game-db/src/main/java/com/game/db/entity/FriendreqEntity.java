package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.cache.CacheType;
import com.game.core.hibernate.key.LongLongPrimaryKey;
import com.game.core.hibernate.orm.BaseEntity;

/**
 * 文件名：FriendreqEntity.java
 * <p>
 * 功能：1
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_friendreq")
@CacheType(type = CacheType.ROLE_MULTI_MAPPING)
public class FriendreqEntity extends BaseEntity<LongLongPrimaryKey> {

	// friendUid
	private long friendUid;
	// avatar
	private String avatar = null;
	// createTime
	private long createTime = 0;
	// name
	private String name = null;
	// sex
	private int sex = 0;
	// area
	private String area = null;
	// declaration
	private String declaration = null;
	// areaNO
	private int areaNO = 0;
	// vip
	private int vip = 0;
	// fen
	private int point = 0;

	@Override
	@Id
	public LongLongPrimaryKey getId() {
		return new LongLongPrimaryKey(uid, friendUid);
	}

	@Override
	public void setId(LongLongPrimaryKey id) {
		this.uid = id.getUid();
		this.friendUid = id.getXid();
	}

	@Transient
	public long getFriendUid() {
		return friendUid;
	}

	public void setFriendUid(long friendUid) {
		this.friendUid = friendUid;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(columnDefinition = " int default 0")
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	@Column(columnDefinition = " int default 0")
	public int getAreaNO() {
		return areaNO;
	}

	public void setAreaNO(int areaNO) {
		this.areaNO = areaNO;
	}

	@Column(columnDefinition = " int default 0")
	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	@Column(columnDefinition = " int default 0")
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}
