package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.AchlevelBaseBean;

/**
 * 文件名：AchlevelBean.java
 * <p>
 * 功能：achlevel.xls -> achlevelBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "achlevel.xls", name = "achlevel", sheetFileName = "achlevel")
public class AchlevelBean extends AchlevelBaseBean {
	private int[][] itemsArray;

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		// 3,2,100;3,3,100;3,6,1
		if (this.getItem() != null) {
			String[] items = this.getItem().split(";");
			this.itemsArray = new int[items.length][3];
			for (int i = 0; i < items.length; i++) {
				String temp = items[i];
				String[] paraArray = temp.split(",");
				if (paraArray.length == 3) {
					itemsArray[i][0] = Integer.parseInt(paraArray[0]);
					itemsArray[i][1] = Integer.parseInt(paraArray[1]);
					itemsArray[i][2] = Integer.parseInt(paraArray[2]);
				}
			}
		} else {
			itemsArray = new int[0][0];
		}
	}

	public int[][] getItemsArray() {
		return itemsArray;
	}
}