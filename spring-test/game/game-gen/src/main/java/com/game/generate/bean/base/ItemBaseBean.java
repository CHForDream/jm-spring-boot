package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ItemBaseBean.java
 * <p>
 * 功能：item.xls -> itemBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ItemBaseBean extends BaseBean implements IInitBean {
	/** 名字 */
	private String name = null;
	/** 描述 */
	private String display = null;
	/** 背包中是否可使用 */
	private int use = 0;
	/** 分類 */
	private int displaytype = 0;
	/** 是否可疊加 */
	private int isAdd = 0;
	/** 疊加上限 */
	private int addMax = 0;
	/** 物品獲得途徑 */
	private String wap = null;
	/** 小圖示 */
	private String icon = null;
	/** 大圖示 */
	private String bigIcon = null;
	/** 是否可出售 */
	private int issale = 0;
	/** 貨幣類型 3、銀幣 1、鑽石 */
	private int moneyType = 0;
	/** 出售價格 */
	private int cost = 0;
	/** 戰鬥中是否可使用 */
	private int battleuse = 0;
	/** 關卡道具可以攜帶上限 */
	private int limit = 0;
	/** 效果類型 對應props表 */
	private int props = 0;
	/** 道具戰鬥前選擇還是戰鬥內道具 1表示戰鬥中默認攜帶的道具, 2表示戰前選擇的道具 */
	private int arena = 0;
	/** 單機可以攜帶 */
	private int pve = 0;
	/** 副本可以攜帶 */
	private int dup = 0;
	/** 精英副本可以攜帶 */
	private int elite = 0;
	/** 品質【1】白【2】綠【3】藍【4】紫【5】橙 */
	private String color = null;
	/** 道具使用增加其他屬性（時間是分鐘） */
	private int addAttr = 0;
	/** 層級(數字越大越靠前) */
	private int layer = 0;
	/** 戰鬥前道具攜帶物品 id,數量;id,數量 */
	private String itemSelect = null;
	/** 無限打點 無限的時間寫成給的數量就可以比如1就是1分鐘 */
	private int infinite = 0;
	/** 無限時間 戰鬥內是無限1個戰鬥外選擇時無限時間 */
	private int infiniteNum = 0;

	/** 名字 */
	public String getName() {
		return name;
	}

	/** 名字 */
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

	/** 背包中是否可使用 */
	public int getUse() {
		return use;
	}

	/** 背包中是否可使用 */
	public void setUse(int use) {
		this.use = use;
	}

	/** 分類 */
	public int getDisplaytype() {
		return displaytype;
	}

	/** 分類 */
	public void setDisplaytype(int displaytype) {
		this.displaytype = displaytype;
	}

	/** 是否可疊加 */
	public int getIsAdd() {
		return isAdd;
	}

	/** 是否可疊加 */
	public void setIsAdd(int isAdd) {
		this.isAdd = isAdd;
	}

	/** 疊加上限 */
	public int getAddMax() {
		return addMax;
	}

	/** 疊加上限 */
	public void setAddMax(int addMax) {
		this.addMax = addMax;
	}

	/** 物品獲得途徑 */
	public String getWap() {
		return wap;
	}

	/** 物品獲得途徑 */
	public void setWap(String wap) {
		this.wap = wap;
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

	/** 是否可出售 */
	public int getIssale() {
		return issale;
	}

	/** 是否可出售 */
	public void setIssale(int issale) {
		this.issale = issale;
	}

	/** 貨幣類型 3、銀幣 1、鑽石 */
	public int getMoneyType() {
		return moneyType;
	}

	/** 貨幣類型 3、銀幣 1、鑽石 */
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

	/** 戰鬥中是否可使用 */
	public int getBattleuse() {
		return battleuse;
	}

	/** 戰鬥中是否可使用 */
	public void setBattleuse(int battleuse) {
		this.battleuse = battleuse;
	}

	/** 關卡道具可以攜帶上限 */
	public int getLimit() {
		return limit;
	}

	/** 關卡道具可以攜帶上限 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/** 效果類型 對應props表 */
	public int getProps() {
		return props;
	}

	/** 效果類型 對應props表 */
	public void setProps(int props) {
		this.props = props;
	}

	/** 道具戰鬥前選擇還是戰鬥內道具 1表示戰鬥中默認攜帶的道具, 2表示戰前選擇的道具 */
	public int getArena() {
		return arena;
	}

	/** 道具戰鬥前選擇還是戰鬥內道具 1表示戰鬥中默認攜帶的道具, 2表示戰前選擇的道具 */
	public void setArena(int arena) {
		this.arena = arena;
	}

	/** 單機可以攜帶 */
	public int getPve() {
		return pve;
	}

	/** 單機可以攜帶 */
	public void setPve(int pve) {
		this.pve = pve;
	}

	/** 副本可以攜帶 */
	public int getDup() {
		return dup;
	}

	/** 副本可以攜帶 */
	public void setDup(int dup) {
		this.dup = dup;
	}

	/** 精英副本可以攜帶 */
	public int getElite() {
		return elite;
	}

	/** 精英副本可以攜帶 */
	public void setElite(int elite) {
		this.elite = elite;
	}

	/** 品質【1】白【2】綠【3】藍【4】紫【5】橙 */
	public String getColor() {
		return color;
	}

	/** 品質【1】白【2】綠【3】藍【4】紫【5】橙 */
	public void setColor(String color) {
		this.color = color;
	}

	/** 道具使用增加其他屬性（時間是分鐘） */
	public int getAddAttr() {
		return addAttr;
	}

	/** 道具使用增加其他屬性（時間是分鐘） */
	public void setAddAttr(int addAttr) {
		this.addAttr = addAttr;
	}

	/** 層級(數字越大越靠前) */
	public int getLayer() {
		return layer;
	}

	/** 層級(數字越大越靠前) */
	public void setLayer(int layer) {
		this.layer = layer;
	}

	/** 戰鬥前道具攜帶物品 id,數量;id,數量 */
	public String getItemSelect() {
		return itemSelect;
	}

	/** 戰鬥前道具攜帶物品 id,數量;id,數量 */
	public void setItemSelect(String itemSelect) {
		this.itemSelect = itemSelect;
	}

	/** 無限打點 無限的時間寫成給的數量就可以比如1就是1分鐘 */
	public int getInfinite() {
		return infinite;
	}

	/** 無限打點 無限的時間寫成給的數量就可以比如1就是1分鐘 */
	public void setInfinite(int infinite) {
		this.infinite = infinite;
	}

	/** 無限時間 戰鬥內是無限1個戰鬥外選擇時無限時間 */
	public int getInfiniteNum() {
		return infiniteNum;
	}

	/** 無限時間 戰鬥內是無限1個戰鬥外選擇時無限時間 */
	public void setInfiniteNum(int infiniteNum) {
		this.infiniteNum = infiniteNum;
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

			// 名字
			if (key.equals("name")) {
				this.name = value;
			}

			// 描述
			if (key.equals("display")) {
				this.display = value;
			}

			// 背包中是否可使用
			if (key.equals("use")) {
				if (value == null || "".equals(value.trim())) {
					this.use = 0;
				} else {
					this.use = Integer.parseInt(value);
				}
			}

			// 分類
			if (key.equals("displaytype")) {
				if (value == null || "".equals(value.trim())) {
					this.displaytype = 0;
				} else {
					this.displaytype = Integer.parseInt(value);
				}
			}

			// 是否可疊加
			if (key.equals("isAdd")) {
				if (value == null || "".equals(value.trim())) {
					this.isAdd = 0;
				} else {
					this.isAdd = Integer.parseInt(value);
				}
			}

			// 疊加上限
			if (key.equals("addMax")) {
				if (value == null || "".equals(value.trim())) {
					this.addMax = 0;
				} else {
					this.addMax = Integer.parseInt(value);
				}
			}

			// 物品獲得途徑
			if (key.equals("wap")) {
				this.wap = value;
			}

			// 小圖示
			if (key.equals("icon")) {
				this.icon = value;
			}

			// 大圖示
			if (key.equals("bigIcon")) {
				this.bigIcon = value;
			}

			// 是否可出售
			if (key.equals("issale")) {
				if (value == null || "".equals(value.trim())) {
					this.issale = 0;
				} else {
					this.issale = Integer.parseInt(value);
				}
			}

			// 貨幣類型 3、銀幣 1、鑽石
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

			// 戰鬥中是否可使用
			if (key.equals("battleuse")) {
				if (value == null || "".equals(value.trim())) {
					this.battleuse = 0;
				} else {
					this.battleuse = Integer.parseInt(value);
				}
			}

			// 關卡道具可以攜帶上限
			if (key.equals("limit")) {
				if (value == null || "".equals(value.trim())) {
					this.limit = 0;
				} else {
					this.limit = Integer.parseInt(value);
				}
			}

			// 效果類型 對應props表
			if (key.equals("props")) {
				if (value == null || "".equals(value.trim())) {
					this.props = 0;
				} else {
					this.props = Integer.parseInt(value);
				}
			}

			// 道具戰鬥前選擇還是戰鬥內道具 1表示戰鬥中默認攜帶的道具, 2表示戰前選擇的道具
			if (key.equals("arena")) {
				if (value == null || "".equals(value.trim())) {
					this.arena = 0;
				} else {
					this.arena = Integer.parseInt(value);
				}
			}

			// 單機可以攜帶
			if (key.equals("pve")) {
				if (value == null || "".equals(value.trim())) {
					this.pve = 0;
				} else {
					this.pve = Integer.parseInt(value);
				}
			}

			// 副本可以攜帶
			if (key.equals("dup")) {
				if (value == null || "".equals(value.trim())) {
					this.dup = 0;
				} else {
					this.dup = Integer.parseInt(value);
				}
			}

			// 精英副本可以攜帶
			if (key.equals("elite")) {
				if (value == null || "".equals(value.trim())) {
					this.elite = 0;
				} else {
					this.elite = Integer.parseInt(value);
				}
			}

			// 品質【1】白【2】綠【3】藍【4】紫【5】橙
			if (key.equals("color")) {
				this.color = value;
			}

			// 道具使用增加其他屬性（時間是分鐘）
			if (key.equals("addAttr")) {
				if (value == null || "".equals(value.trim())) {
					this.addAttr = 0;
				} else {
					this.addAttr = Integer.parseInt(value);
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

			// 戰鬥前道具攜帶物品 id,數量;id,數量
			if (key.equals("itemSelect")) {
				this.itemSelect = value;
			}

			// 無限打點 無限的時間寫成給的數量就可以比如1就是1分鐘
			if (key.equals("infinite")) {
				if (value == null || "".equals(value.trim())) {
					this.infinite = 0;
				} else {
					this.infinite = Integer.parseInt(value);
				}
			}

			// 無限時間 戰鬥內是無限1個戰鬥外選擇時無限時間
			if (key.equals("infiniteNum")) {
				if (value == null || "".equals(value.trim())) {
					this.infiniteNum = 0;
				} else {
					this.infiniteNum = Integer.parseInt(value);
				}
			}
		}
	}
}