package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：GiftBaseBean.java
 * <p>
 * 功能：gift.xls -> giftBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class GiftBaseBean extends BaseBean implements IInitBean {
	/** 禮包名稱 */
	private String name = null;
	/** 描述 */
	private String display = null;
	/** 小圖示 */
	private String icon = null;
	/** 大圖示 */
	private String bigIcon = null;
	/** 開啟類型 */
	private int openType = 0;
	/** 開啟條件 */
	private int openFactor = 0;
	/** 顯示類型 */
	private int showType = 0;
	/** 是否能全部打開 */
	private int openAll = 0;
	/** 道具類型1 */
	private int itemType1 = 0;
	/** 道具Id */
	private int itemId1 = 0;
	/** 道具數量1 */
	private int itemNum1 = 0;
	/** 道具類型2 */
	private int itemType2 = 0;
	/** 道具Id */
	private int itemId2 = 0;
	/** 道具數量2 */
	private int itemNum2 = 0;
	/** 道具類型3 */
	private int itemType3 = 0;
	/** 道具Id */
	private int itemId3 = 0;
	/** 道具數量3 */
	private int itemNum3 = 0;
	/** 道具類型4 */
	private int itemType4 = 0;
	/** 道具Id */
	private int itemId4 = 0;
	/** 道具數量4 */
	private int itemNum4 = 0;
	/** 道具類型5 */
	private int itemType5 = 0;
	/** 道具Id */
	private int itemId5 = 0;
	/** 道具數量5 */
	private int itemNum5 = 0;
	/** 小喇叭 */
	private int trumpet = 0;
	/** 喇叭管道 */
	private String trumpetCanal = null;
	/** 背包中是否可使用 */
	private int use = 0;
	/** 是否可出售 */
	private int issale = 0;
	/** 貨幣類型 3.銀幣 4.鑽石 */
	private int moneyType = 0;
	/** 出售價格 */
	private int cost = 0;
	/** 折扣 */
	private int discount = 0;
	/** 品質【1】白【2】綠【3】藍【4】紫【5】橙 */
	private String color = null;

	/** 禮包名稱 */
	public String getName() {
		return name;
	}

	/** 禮包名稱 */
	public void setName(String name) {
		this.name = name;
	}

	/** 描述 */
	public String getDisplay() {
		return display;
	}

	/** 描述 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/** 小圖示 */
	public String getIcon() {
		return icon;
	}

	/** 小圖示 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/** 大圖示 */
	public String getBigIcon() {
		return bigIcon;
	}

	/** 大圖示 */
	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
	}

	/** 開啟類型 */
	public int getOpenType() {
		return openType;
	}

	/** 開啟類型 */
	public void setOpenType(int openType) {
		this.openType = openType;
	}

	/** 開啟條件 */
	public int getOpenFactor() {
		return openFactor;
	}

	/** 開啟條件 */
	public void setOpenFactor(int openFactor) {
		this.openFactor = openFactor;
	}

	/** 顯示類型 */
	public int getShowType() {
		return showType;
	}

	/** 顯示類型 */
	public void setShowType(int showType) {
		this.showType = showType;
	}

	/** 是否能全部打開 */
	public int getOpenAll() {
		return openAll;
	}

	/** 是否能全部打開 */
	public void setOpenAll(int openAll) {
		this.openAll = openAll;
	}

	/** 道具類型1 */
	public int getItemType1() {
		return itemType1;
	}

	/** 道具類型1 */
	public void setItemType1(int itemType1) {
		this.itemType1 = itemType1;
	}

	/** 道具Id */
	public int getItemId1() {
		return itemId1;
	}

	/** 道具Id */
	public void setItemId1(int itemId1) {
		this.itemId1 = itemId1;
	}

	/** 道具數量1 */
	public int getItemNum1() {
		return itemNum1;
	}

	/** 道具數量1 */
	public void setItemNum1(int itemNum1) {
		this.itemNum1 = itemNum1;
	}

	/** 道具類型2 */
	public int getItemType2() {
		return itemType2;
	}

	/** 道具類型2 */
	public void setItemType2(int itemType2) {
		this.itemType2 = itemType2;
	}

	/** 道具Id */
	public int getItemId2() {
		return itemId2;
	}

	/** 道具Id */
	public void setItemId2(int itemId2) {
		this.itemId2 = itemId2;
	}

	/** 道具數量2 */
	public int getItemNum2() {
		return itemNum2;
	}

	/** 道具數量2 */
	public void setItemNum2(int itemNum2) {
		this.itemNum2 = itemNum2;
	}

	/** 道具類型3 */
	public int getItemType3() {
		return itemType3;
	}

	/** 道具類型3 */
	public void setItemType3(int itemType3) {
		this.itemType3 = itemType3;
	}

	/** 道具Id */
	public int getItemId3() {
		return itemId3;
	}

	/** 道具Id */
	public void setItemId3(int itemId3) {
		this.itemId3 = itemId3;
	}

	/** 道具數量3 */
	public int getItemNum3() {
		return itemNum3;
	}

	/** 道具數量3 */
	public void setItemNum3(int itemNum3) {
		this.itemNum3 = itemNum3;
	}

	/** 道具類型4 */
	public int getItemType4() {
		return itemType4;
	}

	/** 道具類型4 */
	public void setItemType4(int itemType4) {
		this.itemType4 = itemType4;
	}

	/** 道具Id */
	public int getItemId4() {
		return itemId4;
	}

	/** 道具Id */
	public void setItemId4(int itemId4) {
		this.itemId4 = itemId4;
	}

	/** 道具數量4 */
	public int getItemNum4() {
		return itemNum4;
	}

	/** 道具數量4 */
	public void setItemNum4(int itemNum4) {
		this.itemNum4 = itemNum4;
	}

	/** 道具類型5 */
	public int getItemType5() {
		return itemType5;
	}

	/** 道具類型5 */
	public void setItemType5(int itemType5) {
		this.itemType5 = itemType5;
	}

	/** 道具Id */
	public int getItemId5() {
		return itemId5;
	}

	/** 道具Id */
	public void setItemId5(int itemId5) {
		this.itemId5 = itemId5;
	}

	/** 道具數量5 */
	public int getItemNum5() {
		return itemNum5;
	}

	/** 道具數量5 */
	public void setItemNum5(int itemNum5) {
		this.itemNum5 = itemNum5;
	}

	/** 小喇叭 */
	public int getTrumpet() {
		return trumpet;
	}

	/** 小喇叭 */
	public void setTrumpet(int trumpet) {
		this.trumpet = trumpet;
	}

	/** 喇叭管道 */
	public String getTrumpetCanal() {
		return trumpetCanal;
	}

	/** 喇叭管道 */
	public void setTrumpetCanal(String trumpetCanal) {
		this.trumpetCanal = trumpetCanal;
	}

	/** 背包中是否可使用 */
	public int getUse() {
		return use;
	}

	/** 背包中是否可使用 */
	public void setUse(int use) {
		this.use = use;
	}

	/** 是否可出售 */
	public int getIssale() {
		return issale;
	}

	/** 是否可出售 */
	public void setIssale(int issale) {
		this.issale = issale;
	}

	/** 貨幣類型 3.銀幣 4.鑽石 */
	public int getMoneyType() {
		return moneyType;
	}

	/** 貨幣類型 3.銀幣 4.鑽石 */
	public void setMoneyType(int moneyType) {
		this.moneyType = moneyType;
	}

	/** 出售價格 */
	public int getCost() {
		return cost;
	}

	/** 出售價格 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/** 折扣 */
	public int getDiscount() {
		return discount;
	}

	/** 折扣 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}

	/** 品質【1】白【2】綠【3】藍【4】紫【5】橙 */
	public String getColor() {
		return color;
	}

	/** 品質【1】白【2】綠【3】藍【4】紫【5】橙 */
	public void setColor(String color) {
		this.color = color;
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

			// 禮包名稱
			if (key.equals("name")) {
				this.name = value;
			}

			// 描述
			if (key.equals("display")) {
				this.display = value;
			}

			// 小圖示
			if (key.equals("icon")) {
				this.icon = value;
			}

			// 大圖示
			if (key.equals("bigIcon")) {
				this.bigIcon = value;
			}

			// 開啟類型
			if (key.equals("openType")) {
				if (value == null || "".equals(value.trim())) {
					this.openType = 0;
				} else {
					this.openType = Integer.parseInt(value);
				}
			}

			// 開啟條件
			if (key.equals("openFactor")) {
				if (value == null || "".equals(value.trim())) {
					this.openFactor = 0;
				} else {
					this.openFactor = Integer.parseInt(value);
				}
			}

			// 顯示類型
			if (key.equals("showType")) {
				if (value == null || "".equals(value.trim())) {
					this.showType = 0;
				} else {
					this.showType = Integer.parseInt(value);
				}
			}

			// 是否能全部打開
			if (key.equals("openAll")) {
				if (value == null || "".equals(value.trim())) {
					this.openAll = 0;
				} else {
					this.openAll = Integer.parseInt(value);
				}
			}

			// 道具類型1
			if (key.equals("itemType1")) {
				if (value == null || "".equals(value.trim())) {
					this.itemType1 = 0;
				} else {
					this.itemType1 = Integer.parseInt(value);
				}
			}

			// 道具Id
			if (key.equals("itemId1")) {
				if (value == null || "".equals(value.trim())) {
					this.itemId1 = 0;
				} else {
					this.itemId1 = Integer.parseInt(value);
				}
			}

			// 道具數量1
			if (key.equals("itemNum1")) {
				if (value == null || "".equals(value.trim())) {
					this.itemNum1 = 0;
				} else {
					this.itemNum1 = Integer.parseInt(value);
				}
			}

			// 道具類型2
			if (key.equals("itemType2")) {
				if (value == null || "".equals(value.trim())) {
					this.itemType2 = 0;
				} else {
					this.itemType2 = Integer.parseInt(value);
				}
			}

			// 道具Id
			if (key.equals("itemId2")) {
				if (value == null || "".equals(value.trim())) {
					this.itemId2 = 0;
				} else {
					this.itemId2 = Integer.parseInt(value);
				}
			}

			// 道具數量2
			if (key.equals("itemNum2")) {
				if (value == null || "".equals(value.trim())) {
					this.itemNum2 = 0;
				} else {
					this.itemNum2 = Integer.parseInt(value);
				}
			}

			// 道具類型3
			if (key.equals("itemType3")) {
				if (value == null || "".equals(value.trim())) {
					this.itemType3 = 0;
				} else {
					this.itemType3 = Integer.parseInt(value);
				}
			}

			// 道具Id
			if (key.equals("itemId3")) {
				if (value == null || "".equals(value.trim())) {
					this.itemId3 = 0;
				} else {
					this.itemId3 = Integer.parseInt(value);
				}
			}

			// 道具數量3
			if (key.equals("itemNum3")) {
				if (value == null || "".equals(value.trim())) {
					this.itemNum3 = 0;
				} else {
					this.itemNum3 = Integer.parseInt(value);
				}
			}

			// 道具類型4
			if (key.equals("itemType4")) {
				if (value == null || "".equals(value.trim())) {
					this.itemType4 = 0;
				} else {
					this.itemType4 = Integer.parseInt(value);
				}
			}

			// 道具Id
			if (key.equals("itemId4")) {
				if (value == null || "".equals(value.trim())) {
					this.itemId4 = 0;
				} else {
					this.itemId4 = Integer.parseInt(value);
				}
			}

			// 道具數量4
			if (key.equals("itemNum4")) {
				if (value == null || "".equals(value.trim())) {
					this.itemNum4 = 0;
				} else {
					this.itemNum4 = Integer.parseInt(value);
				}
			}

			// 道具類型5
			if (key.equals("itemType5")) {
				if (value == null || "".equals(value.trim())) {
					this.itemType5 = 0;
				} else {
					this.itemType5 = Integer.parseInt(value);
				}
			}

			// 道具Id
			if (key.equals("itemId5")) {
				if (value == null || "".equals(value.trim())) {
					this.itemId5 = 0;
				} else {
					this.itemId5 = Integer.parseInt(value);
				}
			}

			// 道具數量5
			if (key.equals("itemNum5")) {
				if (value == null || "".equals(value.trim())) {
					this.itemNum5 = 0;
				} else {
					this.itemNum5 = Integer.parseInt(value);
				}
			}

			// 小喇叭
			if (key.equals("trumpet")) {
				if (value == null || "".equals(value.trim())) {
					this.trumpet = 0;
				} else {
					this.trumpet = Integer.parseInt(value);
				}
			}

			// 喇叭管道
			if (key.equals("trumpetCanal")) {
				this.trumpetCanal = value;
			}

			// 背包中是否可使用
			if (key.equals("use")) {
				if (value == null || "".equals(value.trim())) {
					this.use = 0;
				} else {
					this.use = Integer.parseInt(value);
				}
			}

			// 是否可出售
			if (key.equals("issale")) {
				if (value == null || "".equals(value.trim())) {
					this.issale = 0;
				} else {
					this.issale = Integer.parseInt(value);
				}
			}

			// 貨幣類型 3.銀幣 4.鑽石
			if (key.equals("moneyType")) {
				if (value == null || "".equals(value.trim())) {
					this.moneyType = 0;
				} else {
					this.moneyType = Integer.parseInt(value);
				}
			}

			// 出售價格
			if (key.equals("cost")) {
				if (value == null || "".equals(value.trim())) {
					this.cost = 0;
				} else {
					this.cost = Integer.parseInt(value);
				}
			}

			// 折扣
			if (key.equals("discount")) {
				if (value == null || "".equals(value.trim())) {
					this.discount = 0;
				} else {
					this.discount = Integer.parseInt(value);
				}
			}

			// 品質【1】白【2】綠【3】藍【4】紫【5】橙
			if (key.equals("color")) {
				this.color = value;
			}
		}
	}
}