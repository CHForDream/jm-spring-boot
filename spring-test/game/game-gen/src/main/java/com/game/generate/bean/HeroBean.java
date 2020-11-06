package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.HeroBaseBean;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

/**
 * 文件名：HeroBean.java
 * <p>
 * 功能：hero.xls -> heroBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "hero.xls", name = "hero", sheetFileName = "hero")
public class HeroBean extends HeroBaseBean {
	private ItemVo item = new ItemVo();

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		ItemVo itemVo = ItemUtil.fromItemString(this.getExchange());
		if (itemVo != null) {
			this.item = itemVo;
		}
	}

	public ItemVo getItem() {
		return item;
	}
}