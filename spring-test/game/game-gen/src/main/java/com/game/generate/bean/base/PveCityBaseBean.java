package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：PveCityBaseBean.java
 * <p>
 * 功能：pve_city.xls -> pveCityBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class PveCityBaseBean extends BaseBean implements IInitBean {
	/** 城市名 */
	private String name = null;
	/** 圖示檔夾 */
	private String iconFrom = null;
	/** 圖片名稱 */
	private String icon = null;
	/** 座標x */
	private int posX = 0;
	/** 座標y */
	private int posY = 0;
	/** 層級(數字越大越靠前) */
	private int layer = 0;
	/** 大小比例 */
	private float scale = 0.0f;
	/** 水晶球圖片 */
	private String ballIcon = null;
	/** 攝像機拉近大小調整 */
	private float camScale = 0.0f;
	/** 恭喜完成 */
	private String Congratulations = null;
	/** 房間內所有裝飾位置 */
	private String accessories = null;
	/** 房間內所有裝飾位置 */
	private String cityIcon = null;

	/** 城市名 */
	public String getName() {
		return name;
	}

	/** 城市名 */
	public void setName(String name) {
		this.name = name;
	}

	/** 圖示檔夾 */
	public String getIconFrom() {
		return iconFrom;
	}

	/** 圖示檔夾 */
	public void setIconFrom(String iconFrom) {
		this.iconFrom = iconFrom;
	}

	/** 圖片名稱 */
	public String getIcon() {
		return icon;
	}

	/** 圖片名稱 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/** 座標x */
	public int getPosX() {
		return posX;
	}

	/** 座標x */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/** 座標y */
	public int getPosY() {
		return posY;
	}

	/** 座標y */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/** 層級(數字越大越靠前) */
	public int getLayer() {
		return layer;
	}

	/** 層級(數字越大越靠前) */
	public void setLayer(int layer) {
		this.layer = layer;
	}

	/** 大小比例 */
	public float getScale() {
		return scale;
	}

	/** 大小比例 */
	public void setScale(float scale) {
		this.scale = scale;
	}

	/** 水晶球圖片 */
	public String getBallIcon() {
		return ballIcon;
	}

	/** 水晶球圖片 */
	public void setBallIcon(String ballIcon) {
		this.ballIcon = ballIcon;
	}

	/** 攝像機拉近大小調整 */
	public float getCamScale() {
		return camScale;
	}

	/** 攝像機拉近大小調整 */
	public void setCamScale(float camScale) {
		this.camScale = camScale;
	}

	/** 恭喜完成 */
	public String getCongratulations() {
		return Congratulations;
	}

	/** 恭喜完成 */
	public void setCongratulations(String Congratulations) {
		this.Congratulations = Congratulations;
	}

	/** 房間內所有裝飾位置 */
	public String getAccessories() {
		return accessories;
	}

	/** 房間內所有裝飾位置 */
	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	/** 房間內所有裝飾位置 */
	public String getCityIcon() {
		return cityIcon;
	}

	/** 房間內所有裝飾位置 */
	public void setCityIcon(String cityIcon) {
		this.cityIcon = cityIcon;
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

			// 城市名
			if (key.equals("name")) {
				this.name = value;
			}

			// 圖示檔夾
			if (key.equals("iconFrom")) {
				this.iconFrom = value;
			}

			// 圖片名稱
			if (key.equals("icon")) {
				this.icon = value;
			}

			// 座標x
			if (key.equals("posX")) {
				if (value == null || "".equals(value.trim())) {
					this.posX = 0;
				} else {
					this.posX = Integer.parseInt(value);
				}
			}

			// 座標y
			if (key.equals("posY")) {
				if (value == null || "".equals(value.trim())) {
					this.posY = 0;
				} else {
					this.posY = Integer.parseInt(value);
				}
			}

			// 層級(數字越大越靠前)
			if (key.equals("layer")) {
				if (value == null || "".equals(value.trim())) {
					this.layer = 0;
				} else {
					this.layer = Integer.parseInt(value);
				}
			}

			// 大小比例
			if (key.equals("scale")) {
				if (value == null || "".equals(value.trim())) {
					this.scale = 0;
				} else {
					this.scale = Float.parseFloat(value);
				}
			}

			// 水晶球圖片
			if (key.equals("ballIcon")) {
				this.ballIcon = value;
			}

			// 攝像機拉近大小調整
			if (key.equals("camScale")) {
				if (value == null || "".equals(value.trim())) {
					this.camScale = 0;
				} else {
					this.camScale = Float.parseFloat(value);
				}
			}

			// 恭喜完成
			if (key.equals("Congratulations")) {
				this.Congratulations = value;
			}

			// 房間內所有裝飾位置
			if (key.equals("accessories")) {
				this.accessories = value;
			}

			// 房間內所有裝飾位置
			if (key.equals("cityIcon")) {
				this.cityIcon = value;
			}
		}
	}
}