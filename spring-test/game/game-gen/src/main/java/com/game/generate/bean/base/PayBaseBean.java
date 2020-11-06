package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：PayBaseBean.java
 * <p>
 * 功能：pay.xls -> payBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class PayBaseBean extends BaseBean implements IInitBean {
	/** 名稱 */
	private String Name = null;
	/** 類型 */
	private int Type = 0;
	/** 平臺價 */
	private float price = 0.0f;
	/** 正常獲得 */
	private int reward = 0;
	/** 額外獲得 */
	private int addReward = 0;
	/** 開啟條件 1等級 */
	private String open = null;
	/** 額外獲得關閉條件 */
	private int closeAdd = 0;
	/** 是否在商城正常顯示 */
	private int show = 0;
	/** 圖示 */
	private String Icon = null;
	/** 配圖 */
	private String figures = null;
	/** 平臺標識 */
	private String platform = null;
	/** 額外贈送 */
	private int additionalreward = 0;
	/** googleplay物品名稱 */
	private String googleplayName = null;

	/** 名稱 */
	public String getName() {
		return Name;
	}

	/** 名稱 */
	public void setName(String Name) {
		this.Name = Name;
	}

	/** 類型 */
	public int getType() {
		return Type;
	}

	/** 類型 */
	public void setType(int Type) {
		this.Type = Type;
	}

	/** 平臺價 */
	public float getPrice() {
		return price;
	}

	/** 平臺價 */
	public void setPrice(float price) {
		this.price = price;
	}

	/** 正常獲得 */
	public int getReward() {
		return reward;
	}

	/** 正常獲得 */
	public void setReward(int reward) {
		this.reward = reward;
	}

	/** 額外獲得 */
	public int getAddReward() {
		return addReward;
	}

	/** 額外獲得 */
	public void setAddReward(int addReward) {
		this.addReward = addReward;
	}

	/** 開啟條件 1等級 */
	public String getOpen() {
		return open;
	}

	/** 開啟條件 1等級 */
	public void setOpen(String open) {
		this.open = open;
	}

	/** 額外獲得關閉條件 */
	public int getCloseAdd() {
		return closeAdd;
	}

	/** 額外獲得關閉條件 */
	public void setCloseAdd(int closeAdd) {
		this.closeAdd = closeAdd;
	}

	/** 是否在商城正常顯示 */
	public int getShow() {
		return show;
	}

	/** 是否在商城正常顯示 */
	public void setShow(int show) {
		this.show = show;
	}

	/** 圖示 */
	public String getIcon() {
		return Icon;
	}

	/** 圖示 */
	public void setIcon(String Icon) {
		this.Icon = Icon;
	}

	/** 配圖 */
	public String getFigures() {
		return figures;
	}

	/** 配圖 */
	public void setFigures(String figures) {
		this.figures = figures;
	}

	/** 平臺標識 */
	public String getPlatform() {
		return platform;
	}

	/** 平臺標識 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/** 額外贈送 */
	public int getAdditionalreward() {
		return additionalreward;
	}

	/** 額外贈送 */
	public void setAdditionalreward(int additionalreward) {
		this.additionalreward = additionalreward;
	}

	/** googleplay物品名稱 */
	public String getGoogleplayName() {
		return googleplayName;
	}

	/** googleplay物品名稱 */
	public void setGoogleplayName(String googleplayName) {
		this.googleplayName = googleplayName;
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

			// 名稱
			if (key.equals("Name")) {
				this.Name = value;
			}

			// 類型
			if (key.equals("Type")) {
				if (value == null || "".equals(value.trim())) {
					this.Type = 0;
				} else {
					this.Type = Integer.parseInt(value);
				}
			}

			// 平臺價
			if (key.equals("price")) {
				if (value == null || "".equals(value.trim())) {
					this.price = 0;
				} else {
					this.price = Float.parseFloat(value);
				}
			}

			// 正常獲得
			if (key.equals("reward")) {
				if (value == null || "".equals(value.trim())) {
					this.reward = 0;
				} else {
					this.reward = Integer.parseInt(value);
				}
			}

			// 額外獲得
			if (key.equals("addReward")) {
				if (value == null || "".equals(value.trim())) {
					this.addReward = 0;
				} else {
					this.addReward = Integer.parseInt(value);
				}
			}

			// 開啟條件 1等級
			if (key.equals("open")) {
				this.open = value;
			}

			// 額外獲得關閉條件
			if (key.equals("closeAdd")) {
				if (value == null || "".equals(value.trim())) {
					this.closeAdd = 0;
				} else {
					this.closeAdd = Integer.parseInt(value);
				}
			}

			// 是否在商城正常顯示
			if (key.equals("show")) {
				if (value == null || "".equals(value.trim())) {
					this.show = 0;
				} else {
					this.show = Integer.parseInt(value);
				}
			}

			// 圖示
			if (key.equals("Icon")) {
				this.Icon = value;
			}

			// 配圖
			if (key.equals("figures")) {
				this.figures = value;
			}

			// 平臺標識
			if (key.equals("platform")) {
				this.platform = value;
			}

			// 額外贈送
			if (key.equals("additionalreward")) {
				if (value == null || "".equals(value.trim())) {
					this.additionalreward = 0;
				} else {
					this.additionalreward = Integer.parseInt(value);
				}
			}

			// googleplay物品名稱
			if (key.equals("googleplayName")) {
				this.googleplayName = value;
			}
		}
	}
}