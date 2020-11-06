package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：CityachieveBaseBean.java
 * <p>
 * 功能：cityachieve.xls -> cityachieveBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class CityachieveBaseBean extends BaseBean implements IInitBean {
	/** 任務類型 1：每個城市收集星星 2：每個城市完成關卡 */
	private int type = 0;
	/** 城市id (對應pvecity中的cityid) */
	private int cityId = 0;
	/** 獎勵(itemtype,itemid,itemnum;...) */
	private String award = null;

	/** 任務類型 1：每個城市收集星星 2：每個城市完成關卡 */
	public int getType() {
		return type;
	}

	/** 任務類型 1：每個城市收集星星 2：每個城市完成關卡 */
	public void setType(int type) {
		this.type = type;
	}

	/** 城市id (對應pvecity中的cityid) */
	public int getCityId() {
		return cityId;
	}

	/** 城市id (對應pvecity中的cityid) */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/** 獎勵(itemtype,itemid,itemnum;...) */
	public String getAward() {
		return award;
	}

	/** 獎勵(itemtype,itemid,itemnum;...) */
	public void setAward(String award) {
		this.award = award;
	}

	@Override
	public void initBean(String[] keyArray, String[] data) {
		// id
		if (data[0] == null || "".equals(data[0].trim())) {
			this.id = 0;
		} else {
			this.id = Integer.parseInt(data[0]);
		}

		for (int i = 1; i < keyArray.length; i++) {
			String key = keyArray[i];
			String value = data[i];

			// id
			if (key.equals("id")) {
				if (value == null || "".equals(value.trim())) {
					this.id = 0;
				} else {
					this.id = Integer.parseInt(value);
				}
			}

			// 任務類型 1：每個城市收集星星 2：每個城市完成關卡
			if (key.equals("type")) {
				if (value == null || "".equals(value.trim())) {
					this.type = 0;
				} else {
					this.type = Integer.parseInt(value);
				}
			}

			// 城市id (對應pvecity中的cityid)
			if (key.equals("cityId")) {
				if (value == null || "".equals(value.trim())) {
					this.cityId = 0;
				} else {
					this.cityId = Integer.parseInt(value);
				}
			}

			// 獎勵(itemtype,itemid,itemnum;...)
			if (key.equals("award")) {
				this.award = value;
			}
		}
	}
}