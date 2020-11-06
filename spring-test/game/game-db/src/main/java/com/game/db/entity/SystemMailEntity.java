package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.game.core.cache.CacheType;
import com.game.core.hibernate.orm.BaseEntity;

/**
 * 文件名：SystemMailEntity.java
 * <p>
 * 功能：1
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_system_mail")
@CacheType(type = CacheType.NO_CACHE)
public class SystemMailEntity extends BaseEntity<Long> {

	private String title = null;
	// 邮件内容
	private String content = null;
	// 邮件创建时间
	private long createTime = 0;
	// 邮件失效时间
	private long invalidTime = 0;
	// 附件道具
	private String items = null;
	// 邮件单号
	private String orderId = null;

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 主键自增
	public Long getId() {
		return this.uid;
	}

	@Override
	public void setId(Long id) {
		this.uid = id;
	}

	@Column(columnDefinition = " varchar(255) default ''")
	public String getTitle() {
		return title;
	}

	@Column(columnDefinition = "TEXT")
	public String getContent() {
		return content;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getCreateTime() {
		return createTime;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getInvalidTime() {
		return invalidTime;
	}

	@Column(columnDefinition = " varchar(255) default ''")
	public String getItems() {
		return items;
	}

	@Column(columnDefinition = " varchar(255) default ''")
	public String getOrderId() {
		return orderId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setInvalidTime(long invalidTime) {
		this.invalidTime = invalidTime;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
