package com.game.rpc.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PayInfo implements Serializable {
	private static final long serialVersionUID = 6864122244953700590L;

	private String orderid;// 订单号
	private int gssid;// 服务器编号
	private long guid;// 平台账号
	private long charid;// 玩家uid
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
}
