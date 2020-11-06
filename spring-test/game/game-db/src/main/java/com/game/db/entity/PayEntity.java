package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.cache.CacheType;
import com.game.core.hibernate.key.LongStringPrimaryKey;
import com.game.core.hibernate.orm.BaseEntity;

@Entity
@Table(name = "t_pay")
@CacheType(type = CacheType.ROLE_MULTI_MAPPING)
public class PayEntity extends BaseEntity<LongStringPrimaryKey> {

	private String orderId = "";// 订单号
	private int serverId;// 服务器id
	private long guid;// 平台id
	private int payId;// 充值挡位
	private int cash;// 充值钻石
	private int rewardCash;// 赠送的钻石
	private int createTime;// 单据创建时间
	private int payTime;// 支付成功时间
	private String rewardItem = "";// 赠送的道具
	private String channel = "";// 支付渠道

	@Override
	@Id
	public LongStringPrimaryKey getId() {
		return new LongStringPrimaryKey(uid, orderId);
	}

	@Override
	public void setId(LongStringPrimaryKey id) {
		this.uid = id.getUid();
		this.orderId = id.getXid();
	}

	@Transient
	public String getOrderId() {
		return orderId;
	}

	@Column(columnDefinition = " int default 0")
	public int getServerId() {
		return serverId;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getGuid() {
		return guid;
	}

	@Column(columnDefinition = " int default 0")
	public int getPayId() {
		return payId;
	}

	@Column(columnDefinition = " int default 0")
	public int getCash() {
		return cash;
	}

	@Column(columnDefinition = " int default 0")
	public int getRewardCash() {
		return rewardCash;
	}

	@Column(columnDefinition = " int default 0")
	public int getCreateTime() {
		return createTime;
	}

	@Column(columnDefinition = " int default 0")
	public int getPayTime() {
		return payTime;
	}

	@Column(columnDefinition = " varchar(255) default ''")
	public String getRewardItem() {
		return rewardItem;
	}

	@Column(columnDefinition = " varchar(255) default ''")
	public String getChannel() {
		return channel;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public void setGuid(long guid) {
		this.guid = guid;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public void setRewardCash(int rewardCash) {
		this.rewardCash = rewardCash;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public void setPayTime(int payTime) {
		this.payTime = payTime;
	}

	public void setRewardItem(String rewardItem) {
		this.rewardItem = rewardItem;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
