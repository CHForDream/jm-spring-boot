package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ShopBaseBean.java
 * <p>
 * 功能：shop.xls -> shopBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ShopBaseBean extends BaseBean implements IInitBean {
	/** 道具類型 3：道具 4：禮包 10：英雄 20：寵物 21.靈氣 22.表情 23.寶石 */
	private int itemType = 0;
	/** 道具ID */
	private int itemId = 0;
	/** 獲得數量 */
	private int itemNum = 0;
	/** 貨幣類型道具ID 道具類型，id，數量，折扣（乘以係數）； */
	private String price = null;
	/** 商城類型 1,2 英雄寵物,月卡 3：裝飾 4寶石（不顯示在商城中） 5.道具 */
	private int shopType = 0;
	/** 排序 根據商城類型排列 */
	private int sort = 0;
	/** 商品庫存 單個用戶最多購買數量 */
	private int stock = 0;
	/** 商品開啟條件類型 1、月卡 */
	private int openType = 0;
	/** 商品開啟條件值 */
	private int open = 0;
	/** 是否顯示在商店裡不顯示為1 */
	private int show = 0;

	/** 道具類型 3：道具 4：禮包 10：英雄 20：寵物 21.靈氣 22.表情 23.寶石 */
	public int getItemType() {
		return itemType;
	}

	/** 道具類型 3：道具 4：禮包 10：英雄 20：寵物 21.靈氣 22.表情 23.寶石 */
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	/** 道具ID */
	public int getItemId() {
		return itemId;
	}

	/** 道具ID */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/** 獲得數量 */
	public int getItemNum() {
		return itemNum;
	}

	/** 獲得數量 */
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	/** 貨幣類型道具ID 道具類型，id，數量，折扣（乘以係數）； */
	public String getPrice() {
		return price;
	}

	/** 貨幣類型道具ID 道具類型，id，數量，折扣（乘以係數）； */
	public void setPrice(String price) {
		this.price = price;
	}

	/** 商城類型 1,2 英雄寵物,月卡 3：裝飾 4寶石（不顯示在商城中） 5.道具 */
	public int getShopType() {
		return shopType;
	}

	/** 商城類型 1,2 英雄寵物,月卡 3：裝飾 4寶石（不顯示在商城中） 5.道具 */
	public void setShopType(int shopType) {
		this.shopType = shopType;
	}

	/** 排序 根據商城類型排列 */
	public int getSort() {
		return sort;
	}

	/** 排序 根據商城類型排列 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/** 商品庫存 單個用戶最多購買數量 */
	public int getStock() {
		return stock;
	}

	/** 商品庫存 單個用戶最多購買數量 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/** 商品開啟條件類型 1、月卡 */
	public int getOpenType() {
		return openType;
	}

	/** 商品開啟條件類型 1、月卡 */
	public void setOpenType(int openType) {
		this.openType = openType;
	}

	/** 商品開啟條件值 */
	public int getOpen() {
		return open;
	}

	/** 商品開啟條件值 */
	public void setOpen(int open) {
		this.open = open;
	}

	/** 是否顯示在商店裡不顯示為1 */
	public int getShow() {
		return show;
	}

	/** 是否顯示在商店裡不顯示為1 */
	public void setShow(int show) {
		this.show = show;
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

			// 道具類型 3：道具 4：禮包 10：英雄 20：寵物 21.靈氣 22.表情 23.寶石
			if (key.equals("itemType")) {
				if (value == null || "".equals(value.trim())) {
					this.itemType = 0;
				} else {
					this.itemType = Integer.parseInt(value);
				}
			}

			// 道具ID
			if (key.equals("itemId")) {
				if (value == null || "".equals(value.trim())) {
					this.itemId = 0;
				} else {
					this.itemId = Integer.parseInt(value);
				}
			}

			// 獲得數量
			if (key.equals("itemNum")) {
				if (value == null || "".equals(value.trim())) {
					this.itemNum = 0;
				} else {
					this.itemNum = Integer.parseInt(value);
				}
			}

			// 貨幣類型道具ID 道具類型，id，數量，折扣（乘以係數）；
			if (key.equals("price")) {
				this.price = value;
			}

			// 商城類型 1,2 英雄寵物,月卡 3：裝飾 4寶石（不顯示在商城中） 5.道具
			if (key.equals("shopType")) {
				if (value == null || "".equals(value.trim())) {
					this.shopType = 0;
				} else {
					this.shopType = Integer.parseInt(value);
				}
			}

			// 排序 根據商城類型排列
			if (key.equals("sort")) {
				if (value == null || "".equals(value.trim())) {
					this.sort = 0;
				} else {
					this.sort = Integer.parseInt(value);
				}
			}

			// 商品庫存 單個用戶最多購買數量
			if (key.equals("stock")) {
				if (value == null || "".equals(value.trim())) {
					this.stock = 0;
				} else {
					this.stock = Integer.parseInt(value);
				}
			}

			// 商品開啟條件類型 1、月卡
			if (key.equals("openType")) {
				if (value == null || "".equals(value.trim())) {
					this.openType = 0;
				} else {
					this.openType = Integer.parseInt(value);
				}
			}

			// 商品開啟條件值
			if (key.equals("open")) {
				if (value == null || "".equals(value.trim())) {
					this.open = 0;
				} else {
					this.open = Integer.parseInt(value);
				}
			}

			// 是否顯示在商店裡不顯示為1
			if (key.equals("show")) {
				if (value == null || "".equals(value.trim())) {
					this.show = 0;
				} else {
					this.show = Integer.parseInt(value);
				}
			}
		}
	}
}