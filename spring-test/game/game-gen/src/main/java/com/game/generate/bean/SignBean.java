package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.SignBaseBean;
import com.game.vo.item.ItemVo;

/**
 * 文件名：SignBean.java
 * <p>
 * 功能：sign.xls -> signBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "sign.xls", name = "sign", sheetFileName = "sign")
public class SignBean extends SignBaseBean {
	private ItemVo itemVo;

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		this.itemVo = new ItemVo(getItemtype(), getItem(), getNum());
	}

	public ItemVo getItemVo() {
		return itemVo;
	}
}