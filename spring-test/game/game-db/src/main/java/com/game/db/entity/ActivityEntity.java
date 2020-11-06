package com.game.db.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.game.core.cache.CacheType;
import com.game.core.hibernate.orm.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "t_activity")
@CacheType(type = CacheType.NO_CACHE)
public class ActivityEntity extends BaseEntity<Integer> {
	// 活动Id
	@Id
	private int id;
	// 活动名
	private String name = null;
	// 活动描述
	private String description = null;
	// 活动是否开放。true开放；false关闭
	private boolean isOpen;
	// 活动状态。0未开始，1进行中，2已结束
	private int state;
	// 活动显示开始时间
	private Timestamp showTimeStart;
	// 活动显示结束时间
	private Timestamp showTimeEnd;
	// 活动开始时间
	private Timestamp timeStart;
	// 活动结束时间
	private Timestamp timeEnd;
	// 活动版本（时间戳）
	private long version;
	// 活动排序（由大到小）
	private int priority;
	// 活动目标奖励
	@Column(columnDefinition = "TEXT")
	private String rewards;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
}
