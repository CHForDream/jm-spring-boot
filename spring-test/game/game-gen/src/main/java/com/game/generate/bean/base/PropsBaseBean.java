package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：PropsBaseBean.java
 * <p>
 * 功能：props.xls -> propsBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class PropsBaseBean extends BaseBean implements IInitBean {
	/** 效果類型 */
	private int effectId = 0;
	/** 效果參數 */
	private int param1 = 0;
	/** 效果參數 */
	private String param2 = null;
	/** 使用方式 1、直接使用 2、選擇使用 */
	private int useMode = 0;
	/** 使用方式 1、直接使用 2、選擇使用 */
	private String toolEffect = null;
	/** 延時執行實際方法 */
	private float delayRun = 0.0f;

	/** 效果類型 */
	public int getEffectId() {
		return effectId;
	}

	/** 效果類型 */
	public void setEffectId(int effectId) {
		this.effectId = effectId;
	}

	/** 效果參數 */
	public int getParam1() {
		return param1;
	}

	/** 效果參數 */
	public void setParam1(int param1) {
		this.param1 = param1;
	}

	/** 效果參數 */
	public String getParam2() {
		return param2;
	}

	/** 效果參數 */
	public void setParam2(String param2) {
		this.param2 = param2;
	}

	/** 使用方式 1、直接使用 2、選擇使用 */
	public int getUseMode() {
		return useMode;
	}

	/** 使用方式 1、直接使用 2、選擇使用 */
	public void setUseMode(int useMode) {
		this.useMode = useMode;
	}

	/** 使用方式 1、直接使用 2、選擇使用 */
	public String getToolEffect() {
		return toolEffect;
	}

	/** 使用方式 1、直接使用 2、選擇使用 */
	public void setToolEffect(String toolEffect) {
		this.toolEffect = toolEffect;
	}

	/** 延時執行實際方法 */
	public float getDelayRun() {
		return delayRun;
	}

	/** 延時執行實際方法 */
	public void setDelayRun(float delayRun) {
		this.delayRun = delayRun;
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

			// 效果類型
			if (key.equals("effectId")) {
				if (value == null || "".equals(value.trim())) {
					this.effectId = 0;
				} else {
					this.effectId = Integer.parseInt(value);
				}
			}

			// 效果參數
			if (key.equals("param1")) {
				if (value == null || "".equals(value.trim())) {
					this.param1 = 0;
				} else {
					this.param1 = Integer.parseInt(value);
				}
			}

			// 效果參數
			if (key.equals("param2")) {
				this.param2 = value;
			}

			// 使用方式 1、直接使用 2、選擇使用
			if (key.equals("useMode")) {
				if (value == null || "".equals(value.trim())) {
					this.useMode = 0;
				} else {
					this.useMode = Integer.parseInt(value);
				}
			}

			// 使用方式 1、直接使用 2、選擇使用
			if (key.equals("toolEffect")) {
				this.toolEffect = value;
			}

			// 延時執行實際方法
			if (key.equals("delayRun")) {
				if (value == null || "".equals(value.trim())) {
					this.delayRun = 0;
				} else {
					this.delayRun = Float.parseFloat(value);
				}
			}
		}
	}
}