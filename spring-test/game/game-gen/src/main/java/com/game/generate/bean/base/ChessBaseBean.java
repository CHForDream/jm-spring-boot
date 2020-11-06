package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ChessBaseBean.java
 * <p>
 * 功能：chess.xls -> chessBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ChessBaseBean extends BaseBean implements IInitBean {
	/** 棋子說明策劃用前後端不用 */
	private String chessExplain = null;
	/** 是否可以交換0，不可操作不可交換不可拖拽；1，可交換；2，可拖拽 */
	private int optType = 0;
	/** 是否重力下落 */
	private int canFall = 0;
	/** 是否飛向目標 */
	private int canFlyTarget = 0;
	/** 是否任意交換消除 */
	private int anySwap = 0;
	/** 是否按兩下消除 */
	private int DoubleClick = 0;
	/** 是否蔓延（地板層） */
	private int canSpread = 0;
	/** 是否阻擋，火箭 1，是 0，否 */
	private int blockRocket = 0;
	/** 棋子類型 1 普通5色棋子 2 花朵 3 氣球產生3*3效果 4 氣泡 5 相機 6 火箭 7 可按兩下炸彈 8 彩虹 9 可按兩下小飛機 10 蜂蜜餅乾棋子 11 餅乾棋子，會隨著block移動，配合34002 12 雨傘，最高3層 13 果醬罐 14 甜甜圈紙袋 15 5色蝴... */
	private int chessType = 0;
	/** 直接影響 */
	private int takeDirect = 0;
	/** 棋子顏色 */
	private int directColor = 0;
	/** 間接影響 */
	private int takeIndirect = 0;
	/** 接受顏色 0，接受全部顏色；1，紅；2，綠；3，藍；4，黃；5，紫。 */
	private int indirectColor = 0;
	/** 接受特殊影響 */
	private int takeSpecial = 0;
	/** block層沒有就死 */
	private int sunshineDie = 0;
	/** 是否對地板造成傷害 */
	private int isAttackFloor = 0;
	/** 觸發後是否積攢技能能量數值 */
	private float energy = 0.0f;
	/** 功能類型引用effect表id */
	private int effectId = 0;
	/** 層數 */
	private int maxFloor = 0;
	/** 層級迴圈 */
	private int floorLoop = 0;
	/** floor改變方式(-1,1) */
	private int floorChange = 0;
	/** 預設 */
	private String baseIconName = null;
	/** 生成動畫 */
	private String createAnimation = null;
	/** 生成特效 */
	private String createParticle = null;
	/** 生成音效 */
	private String createSound = null;
	/** 消除特效 */
	private String removeParticle = null;
	/** 消除音效 */
	private String removeSound = null;
	/** 銷毀、動畫 */
	private String destroyAnimation = null;
	/** 銷毀特效 */
	private String destroyParticle = null;
	/** 銷毀音效 */
	private String destroySound = null;
	/** Idle動畫 */
	private String idleAnimation = null;
	/** Idle特效 */
	private String chessEffect = null;
	/** 觸發動畫 */
	private String triggerAnimation = null;
	/** 觸發特效 */
	private String triggerParticle = null;
	/** 飛向目標前的特效 */
	private String flyToTargetAnimation = null;
	/** 新棋子產生的拖尾 */
	private String addChessFly = null;
	/** 新棋子到達的特效 */
	private String addChessReach = null;

	/** 棋子說明策劃用前後端不用 */
	public String getChessExplain() {
		return chessExplain;
	}

	/** 棋子說明策劃用前後端不用 */
	public void setChessExplain(String chessExplain) {
		this.chessExplain = chessExplain;
	}

	/** 是否可以交換0，不可操作不可交換不可拖拽；1，可交換；2，可拖拽 */
	public int getOptType() {
		return optType;
	}

	/** 是否可以交換0，不可操作不可交換不可拖拽；1，可交換；2，可拖拽 */
	public void setOptType(int optType) {
		this.optType = optType;
	}

	/** 是否重力下落 */
	public int getCanFall() {
		return canFall;
	}

	/** 是否重力下落 */
	public void setCanFall(int canFall) {
		this.canFall = canFall;
	}

	/** 是否飛向目標 */
	public int getCanFlyTarget() {
		return canFlyTarget;
	}

	/** 是否飛向目標 */
	public void setCanFlyTarget(int canFlyTarget) {
		this.canFlyTarget = canFlyTarget;
	}

	/** 是否任意交換消除 */
	public int getAnySwap() {
		return anySwap;
	}

	/** 是否任意交換消除 */
	public void setAnySwap(int anySwap) {
		this.anySwap = anySwap;
	}

	/** 是否按兩下消除 */
	public int getDoubleClick() {
		return DoubleClick;
	}

	/** 是否按兩下消除 */
	public void setDoubleClick(int DoubleClick) {
		this.DoubleClick = DoubleClick;
	}

	/** 是否蔓延（地板層） */
	public int getCanSpread() {
		return canSpread;
	}

	/** 是否蔓延（地板層） */
	public void setCanSpread(int canSpread) {
		this.canSpread = canSpread;
	}

	/** 是否阻擋，火箭 1，是 0，否 */
	public int getBlockRocket() {
		return blockRocket;
	}

	/** 是否阻擋，火箭 1，是 0，否 */
	public void setBlockRocket(int blockRocket) {
		this.blockRocket = blockRocket;
	}

	/** 棋子類型 1 普通5色棋子 2 花朵 3 氣球產生3*3效果 4 氣泡 5 相機 6 火箭 7 可按兩下炸彈 8 彩虹 9 可按兩下小飛機 10 蜂蜜餅乾棋子 11 餅乾棋子，會隨著block移動，配合34002 12 雨傘，最高3層 13 果醬罐 14 甜甜圈紙袋 15 5色蝴... */
	public int getChessType() {
		return chessType;
	}

	/** 棋子類型 1 普通5色棋子 2 花朵 3 氣球產生3*3效果 4 氣泡 5 相機 6 火箭 7 可按兩下炸彈 8 彩虹 9 可按兩下小飛機 10 蜂蜜餅乾棋子 11 餅乾棋子，會隨著block移動，配合34002 12 雨傘，最高3層 13 果醬罐 14 甜甜圈紙袋 15 5色蝴... */
	public void setChessType(int chessType) {
		this.chessType = chessType;
	}

	/** 直接影響 */
	public int getTakeDirect() {
		return takeDirect;
	}

	/** 直接影響 */
	public void setTakeDirect(int takeDirect) {
		this.takeDirect = takeDirect;
	}

	/** 棋子顏色 */
	public int getDirectColor() {
		return directColor;
	}

	/** 棋子顏色 */
	public void setDirectColor(int directColor) {
		this.directColor = directColor;
	}

	/** 間接影響 */
	public int getTakeIndirect() {
		return takeIndirect;
	}

	/** 間接影響 */
	public void setTakeIndirect(int takeIndirect) {
		this.takeIndirect = takeIndirect;
	}

	/** 接受顏色 0，接受全部顏色；1，紅；2，綠；3，藍；4，黃；5，紫。 */
	public int getIndirectColor() {
		return indirectColor;
	}

	/** 接受顏色 0，接受全部顏色；1，紅；2，綠；3，藍；4，黃；5，紫。 */
	public void setIndirectColor(int indirectColor) {
		this.indirectColor = indirectColor;
	}

	/** 接受特殊影響 */
	public int getTakeSpecial() {
		return takeSpecial;
	}

	/** 接受特殊影響 */
	public void setTakeSpecial(int takeSpecial) {
		this.takeSpecial = takeSpecial;
	}

	/** block層沒有就死 */
	public int getSunshineDie() {
		return sunshineDie;
	}

	/** block層沒有就死 */
	public void setSunshineDie(int sunshineDie) {
		this.sunshineDie = sunshineDie;
	}

	/** 是否對地板造成傷害 */
	public int getIsAttackFloor() {
		return isAttackFloor;
	}

	/** 是否對地板造成傷害 */
	public void setIsAttackFloor(int isAttackFloor) {
		this.isAttackFloor = isAttackFloor;
	}

	/** 觸發後是否積攢技能能量數值 */
	public float getEnergy() {
		return energy;
	}

	/** 觸發後是否積攢技能能量數值 */
	public void setEnergy(float energy) {
		this.energy = energy;
	}

	/** 功能類型引用effect表id */
	public int getEffectId() {
		return effectId;
	}

	/** 功能類型引用effect表id */
	public void setEffectId(int effectId) {
		this.effectId = effectId;
	}

	/** 層數 */
	public int getMaxFloor() {
		return maxFloor;
	}

	/** 層數 */
	public void setMaxFloor(int maxFloor) {
		this.maxFloor = maxFloor;
	}

	/** 層級迴圈 */
	public int getFloorLoop() {
		return floorLoop;
	}

	/** 層級迴圈 */
	public void setFloorLoop(int floorLoop) {
		this.floorLoop = floorLoop;
	}

	/** floor改變方式(-1,1) */
	public int getFloorChange() {
		return floorChange;
	}

	/** floor改變方式(-1,1) */
	public void setFloorChange(int floorChange) {
		this.floorChange = floorChange;
	}

	/** 預設 */
	public String getBaseIconName() {
		return baseIconName;
	}

	/** 預設 */
	public void setBaseIconName(String baseIconName) {
		this.baseIconName = baseIconName;
	}

	/** 生成動畫 */
	public String getCreateAnimation() {
		return createAnimation;
	}

	/** 生成動畫 */
	public void setCreateAnimation(String createAnimation) {
		this.createAnimation = createAnimation;
	}

	/** 生成特效 */
	public String getCreateParticle() {
		return createParticle;
	}

	/** 生成特效 */
	public void setCreateParticle(String createParticle) {
		this.createParticle = createParticle;
	}

	/** 生成音效 */
	public String getCreateSound() {
		return createSound;
	}

	/** 生成音效 */
	public void setCreateSound(String createSound) {
		this.createSound = createSound;
	}

	/** 消除特效 */
	public String getRemoveParticle() {
		return removeParticle;
	}

	/** 消除特效 */
	public void setRemoveParticle(String removeParticle) {
		this.removeParticle = removeParticle;
	}

	/** 消除音效 */
	public String getRemoveSound() {
		return removeSound;
	}

	/** 消除音效 */
	public void setRemoveSound(String removeSound) {
		this.removeSound = removeSound;
	}

	/** 銷毀、動畫 */
	public String getDestroyAnimation() {
		return destroyAnimation;
	}

	/** 銷毀、動畫 */
	public void setDestroyAnimation(String destroyAnimation) {
		this.destroyAnimation = destroyAnimation;
	}

	/** 銷毀特效 */
	public String getDestroyParticle() {
		return destroyParticle;
	}

	/** 銷毀特效 */
	public void setDestroyParticle(String destroyParticle) {
		this.destroyParticle = destroyParticle;
	}

	/** 銷毀音效 */
	public String getDestroySound() {
		return destroySound;
	}

	/** 銷毀音效 */
	public void setDestroySound(String destroySound) {
		this.destroySound = destroySound;
	}

	/** Idle動畫 */
	public String getIdleAnimation() {
		return idleAnimation;
	}

	/** Idle動畫 */
	public void setIdleAnimation(String idleAnimation) {
		this.idleAnimation = idleAnimation;
	}

	/** Idle特效 */
	public String getChessEffect() {
		return chessEffect;
	}

	/** Idle特效 */
	public void setChessEffect(String chessEffect) {
		this.chessEffect = chessEffect;
	}

	/** 觸發動畫 */
	public String getTriggerAnimation() {
		return triggerAnimation;
	}

	/** 觸發動畫 */
	public void setTriggerAnimation(String triggerAnimation) {
		this.triggerAnimation = triggerAnimation;
	}

	/** 觸發特效 */
	public String getTriggerParticle() {
		return triggerParticle;
	}

	/** 觸發特效 */
	public void setTriggerParticle(String triggerParticle) {
		this.triggerParticle = triggerParticle;
	}

	/** 飛向目標前的特效 */
	public String getFlyToTargetAnimation() {
		return flyToTargetAnimation;
	}

	/** 飛向目標前的特效 */
	public void setFlyToTargetAnimation(String flyToTargetAnimation) {
		this.flyToTargetAnimation = flyToTargetAnimation;
	}

	/** 新棋子產生的拖尾 */
	public String getAddChessFly() {
		return addChessFly;
	}

	/** 新棋子產生的拖尾 */
	public void setAddChessFly(String addChessFly) {
		this.addChessFly = addChessFly;
	}

	/** 新棋子到達的特效 */
	public String getAddChessReach() {
		return addChessReach;
	}

	/** 新棋子到達的特效 */
	public void setAddChessReach(String addChessReach) {
		this.addChessReach = addChessReach;
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

			// 棋子說明策劃用前後端不用
			if (key.equals("chessExplain")) {
				this.chessExplain = value;
			}

			// 是否可以交換0，不可操作不可交換不可拖拽；1，可交換；2，可拖拽
			if (key.equals("optType")) {
				if (value == null || "".equals(value.trim())) {
					this.optType = 0;
				} else {
					this.optType = Integer.parseInt(value);
				}
			}

			// 是否重力下落
			if (key.equals("canFall")) {
				if (value == null || "".equals(value.trim())) {
					this.canFall = 0;
				} else {
					this.canFall = Integer.parseInt(value);
				}
			}

			// 是否飛向目標
			if (key.equals("canFlyTarget")) {
				if (value == null || "".equals(value.trim())) {
					this.canFlyTarget = 0;
				} else {
					this.canFlyTarget = Integer.parseInt(value);
				}
			}

			// 是否任意交換消除
			if (key.equals("anySwap")) {
				if (value == null || "".equals(value.trim())) {
					this.anySwap = 0;
				} else {
					this.anySwap = Integer.parseInt(value);
				}
			}

			// 是否按兩下消除
			if (key.equals("DoubleClick")) {
				if (value == null || "".equals(value.trim())) {
					this.DoubleClick = 0;
				} else {
					this.DoubleClick = Integer.parseInt(value);
				}
			}

			// 是否蔓延（地板層）
			if (key.equals("canSpread")) {
				if (value == null || "".equals(value.trim())) {
					this.canSpread = 0;
				} else {
					this.canSpread = Integer.parseInt(value);
				}
			}

			// 是否阻擋，火箭 1，是 0，否
			if (key.equals("blockRocket")) {
				if (value == null || "".equals(value.trim())) {
					this.blockRocket = 0;
				} else {
					this.blockRocket = Integer.parseInt(value);
				}
			}

			// 棋子類型 1 普通5色棋子 2 花朵 3 氣球產生3*3效果 4 氣泡 5 相機 6 火箭 7 可按兩下炸彈 8 彩虹 9 可按兩下小飛機 10 蜂蜜餅乾棋子 11 餅乾棋子，會隨著block移動，配合34002 12 雨傘，最高3層 13 果醬罐 14 甜甜圈紙袋 15 5色蝴...
			if (key.equals("chessType")) {
				if (value == null || "".equals(value.trim())) {
					this.chessType = 0;
				} else {
					this.chessType = Integer.parseInt(value);
				}
			}

			// 直接影響
			if (key.equals("takeDirect")) {
				if (value == null || "".equals(value.trim())) {
					this.takeDirect = 0;
				} else {
					this.takeDirect = Integer.parseInt(value);
				}
			}

			// 棋子顏色
			if (key.equals("directColor")) {
				if (value == null || "".equals(value.trim())) {
					this.directColor = 0;
				} else {
					this.directColor = Integer.parseInt(value);
				}
			}

			// 間接影響
			if (key.equals("takeIndirect")) {
				if (value == null || "".equals(value.trim())) {
					this.takeIndirect = 0;
				} else {
					this.takeIndirect = Integer.parseInt(value);
				}
			}

			// 接受顏色 0，接受全部顏色；1，紅；2，綠；3，藍；4，黃；5，紫。
			if (key.equals("indirectColor")) {
				if (value == null || "".equals(value.trim())) {
					this.indirectColor = 0;
				} else {
					this.indirectColor = Integer.parseInt(value);
				}
			}

			// 接受特殊影響
			if (key.equals("takeSpecial")) {
				if (value == null || "".equals(value.trim())) {
					this.takeSpecial = 0;
				} else {
					this.takeSpecial = Integer.parseInt(value);
				}
			}

			// block層沒有就死
			if (key.equals("sunshineDie")) {
				if (value == null || "".equals(value.trim())) {
					this.sunshineDie = 0;
				} else {
					this.sunshineDie = Integer.parseInt(value);
				}
			}

			// 是否對地板造成傷害
			if (key.equals("isAttackFloor")) {
				if (value == null || "".equals(value.trim())) {
					this.isAttackFloor = 0;
				} else {
					this.isAttackFloor = Integer.parseInt(value);
				}
			}

			// 觸發後是否積攢技能能量數值
			if (key.equals("energy")) {
				if (value == null || "".equals(value.trim())) {
					this.energy = 0;
				} else {
					this.energy = Float.parseFloat(value);
				}
			}

			// 功能類型引用effect表id
			if (key.equals("effectId")) {
				if (value == null || "".equals(value.trim())) {
					this.effectId = 0;
				} else {
					this.effectId = Integer.parseInt(value);
				}
			}

			// 層數
			if (key.equals("maxFloor")) {
				if (value == null || "".equals(value.trim())) {
					this.maxFloor = 0;
				} else {
					this.maxFloor = Integer.parseInt(value);
				}
			}

			// 層級迴圈
			if (key.equals("floorLoop")) {
				if (value == null || "".equals(value.trim())) {
					this.floorLoop = 0;
				} else {
					this.floorLoop = Integer.parseInt(value);
				}
			}

			// floor改變方式(-1,1)
			if (key.equals("floorChange")) {
				if (value == null || "".equals(value.trim())) {
					this.floorChange = 0;
				} else {
					this.floorChange = Integer.parseInt(value);
				}
			}

			// 預設
			if (key.equals("baseIconName")) {
				this.baseIconName = value;
			}

			// 生成動畫
			if (key.equals("createAnimation")) {
				this.createAnimation = value;
			}

			// 生成特效
			if (key.equals("createParticle")) {
				this.createParticle = value;
			}

			// 生成音效
			if (key.equals("createSound")) {
				this.createSound = value;
			}

			// 消除特效
			if (key.equals("removeParticle")) {
				this.removeParticle = value;
			}

			// 消除音效
			if (key.equals("removeSound")) {
				this.removeSound = value;
			}

			// 銷毀、動畫
			if (key.equals("destroyAnimation")) {
				this.destroyAnimation = value;
			}

			// 銷毀特效
			if (key.equals("destroyParticle")) {
				this.destroyParticle = value;
			}

			// 銷毀音效
			if (key.equals("destroySound")) {
				this.destroySound = value;
			}

			// Idle動畫
			if (key.equals("idleAnimation")) {
				this.idleAnimation = value;
			}

			// Idle特效
			if (key.equals("chessEffect")) {
				this.chessEffect = value;
			}

			// 觸發動畫
			if (key.equals("triggerAnimation")) {
				this.triggerAnimation = value;
			}

			// 觸發特效
			if (key.equals("triggerParticle")) {
				this.triggerParticle = value;
			}

			// 飛向目標前的特效
			if (key.equals("flyToTargetAnimation")) {
				this.flyToTargetAnimation = value;
			}

			// 新棋子產生的拖尾
			if (key.equals("addChessFly")) {
				this.addChessFly = value;
			}

			// 新棋子到達的特效
			if (key.equals("addChessReach")) {
				this.addChessReach = value;
			}
		}
	}
}