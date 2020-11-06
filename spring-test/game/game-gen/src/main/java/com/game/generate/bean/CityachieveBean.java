package com.game.generate.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.CityachieveBaseBean;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

/**
 * 文件名：CityachieveBean.java
 * <p>
 * 功能：cityachieve.xls -> cityachieveBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "cityachieve.xls", name = "cityachieve", sheetFileName = "cityachieve")
public class CityachieveBean extends CityachieveBaseBean {
	private List<ItemVo> awardList = new ArrayList<ItemVo>();

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		// 成就奖励
		awardList.addAll(ItemUtil.fromItemArrayString(this.getAward()));
	}

	public List<ItemVo> getAwardList() {
		return awardList;
	}

}