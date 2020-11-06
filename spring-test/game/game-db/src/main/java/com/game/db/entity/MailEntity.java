package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.cache.CacheType;
import com.game.core.hibernate.key.LongLongPrimaryKey;
import com.game.core.hibernate.orm.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件名：MailEntity.java
 * <p>
 * 功能：1
 * <p>
 * 作者：Builded by AppTools.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "t_mail")
@CacheType(type = CacheType.ROLE_MULTI_MAPPING)
public class MailEntity extends BaseEntity<LongLongPrimaryKey> {

	// 邮件Id
	private long mailId;
	// mailType
	private int mailType;
	// createTime
	private long createTime;
	// readMill
	private long readMill;
	// content
	private String content = null;
	// title
	private String title = null;
	// avatar
	private String avatar = null;
	// sex
	private int sex;
	// name
	private String name = null;
	// 是否已读
	private boolean hasRead;
	// items
	private String items = null;
	// 是否已领取附件物品
	private boolean gotItems;
	// params
	private String params = null;
	// fromUid
	private long fromUid;
	// 系统邮件Id
	private long systemMailId;

	@Override
	@Id
	public LongLongPrimaryKey getId() {
		return new LongLongPrimaryKey(this.uid, this.mailId);
	}

	@Override
	public void setId(LongLongPrimaryKey id) {
		this.uid = id.getUid();
		this.mailId = id.getXid();
	}

	@Transient
	public long getMailId() {
		return mailId;
	}

	@Column(columnDefinition = "BIT default 0")
	public boolean isHasRead() {
		return hasRead;
	}

	@Column(columnDefinition = "BIT default 0")
	public boolean isGotItems() {
		return gotItems;
	}
}
