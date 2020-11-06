package com.game.generate.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.FirstpayBaseBean;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

/**
 * 文件名：FirstpayBean.java
 * <p>
 * 功能：firstpay.xls -> firstpayBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "firstpay.xls", name = "firstpay", sheetFileName = "firstpay")
public class FirstpayBean extends FirstpayBaseBean {
	private List<ItemVo> rewardList = new ArrayList<ItemVo>();

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		// 首冲奖励
		rewardList.addAll(ItemUtil.fromItemArrayString(this.getReward()));
	}

	public List<ItemVo> getRewardList() {
		return rewardList;
	}

	public void setRewardList(List<ItemVo> rewardList) {
		this.rewardList = rewardList;
	}
}