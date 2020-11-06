package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PayLog extends AbstractLog {

	private String orderid;// 订单号
	private int gssid;// 服务器编号
	private long guid;// 平台账号
	private String gateway;// 支付渠道
	private int itemid;// 充值档位编号
	private int point;// 钻石数量
	private int gift_point;// 赠送的钻石
	private int createtime;// 单据创建时间戳
	private int paytime;// 支付成功时间戳
	private String items;// 道具
	private String cuid;// 货币类型
	private int amount;// 货币数量
	private String itemid2;// 网关itemid
	private int pveId;// pveId
	private int targetId;// targetId

	public PayLog(LogfType logType, UserBean userbean) {
		super(logType, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(orderid).append("|").append(gssid).append("|").append(guid).append("|").append(gateway).append("|")
				.append(itemid).append("|").append(point).append("|").append(gift_point).append("|").append(createtime).append("|").append(paytime).append("|")
				.append(items).append("|").append(cuid).append("|").append(amount).append("|").append(itemid2).append("|").append(pveId).append("|")
				.append(targetId);
		return sb.toString();
	}
}
