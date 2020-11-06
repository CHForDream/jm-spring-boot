package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：RedpointBaseBean.java
 * <p>
 * 功能：redpoint.xls -> redpointBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class RedpointBaseBean extends BaseBean implements IInitBean {
	/** 描述 */
	private String desc = null;
	/** 順序 */
	private String order = null;
	/** projectID */
	private int jumpId = 0;
	/** 對應Project表id */
	private int projectId = 0;

	/** 描述 */
	public String getDesc() {
		return desc;
	}

	/** 描述 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/** 順序 */
	public String getOrder() {
		return order;
	}

	/** 順序 */
	public void setOrder(String order) {
		this.order = order;
	}

	/** projectID */
	public int getJumpId() {
		return jumpId;
	}

	/** projectID */
	public void setJumpId(int jumpId) {
		this.jumpId = jumpId;
	}

	/** 對應Project表id */
	public int getProjectId() {
		return projectId;
	}

	/** 對應Project表id */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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

			// 描述
			if (key.equals("desc")) {
				this.desc = value;
			}

			// 順序
			if (key.equals("order")) {
				this.order = value;
			}

			// projectID
			if (key.equals("jumpId")) {
				if (value == null || "".equals(value.trim())) {
					this.jumpId = 0;
				} else {
					this.jumpId = Integer.parseInt(value);
				}
			}

			// 對應Project表id
			if (key.equals("projectId")) {
				if (value == null || "".equals(value.trim())) {
					this.projectId = 0;
				} else {
					this.projectId = Integer.parseInt(value);
				}
			}
		}
	}
}