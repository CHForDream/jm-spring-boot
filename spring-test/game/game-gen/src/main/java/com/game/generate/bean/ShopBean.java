package com.game.generate.bean;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ShopBaseBean;
import com.game.utils.StringUtils;
import com.google.common.collect.Maps;

/**
 * 文件名：ShopBean.java
 * <p>
 * 功能：shop.xls -> shopBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "shop.xls", name = "shop", sheetFileName = "shop")
public class ShopBean extends ShopBaseBean {
	// 使用道具(货币)Id作为key
	private Map<Integer, Integer[]> costMap = Maps.newHashMap();

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		if (!StringUtils.isEmpty(getPrice())) {
			String[] costSplitArray = getPrice().split(";");
			for (String costItem : costSplitArray) {
				String[] split = costItem.split(",");
				if (split.length == 4) {
					Integer[] item = new Integer[3];
					item[0] = Integer.parseInt(split[0]);
					item[1] = Integer.parseInt(split[1]);
					item[2] = (int) (Integer.parseInt(split[2]) * Float.parseFloat(split[3]));
					// 使用道具(货币)Id作为key
					costMap.put(item[1], item);
				}
			}
		}
	}

	public Map<Integer, Integer[]> getCostMap() {
		return costMap;
	}
}
