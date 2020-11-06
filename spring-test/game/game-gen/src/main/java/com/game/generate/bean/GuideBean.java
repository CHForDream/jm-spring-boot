package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.GuideBaseBean;

/**
 * 文件名：GuideBean.java
 * <p>
 * 功能：guide.xls -> guideBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "guide.xls", name = "guide", sheetFileName = "guide")
public class GuideBean extends GuideBaseBean {
	private int serverConditionType;
	private int serverConditionNum;

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		String serverCondition = getServerCondition();
		String[] split = serverCondition.split(",");
		if (split.length == 2) {
			serverConditionType = Integer.parseInt(split[0]);
			serverConditionNum = Integer.parseInt(split[1]);
		}
	}

	public int getServerConditionType() {
		return serverConditionType;
	}

	public int getServerConditionNum() {
		return serverConditionNum;
	}
}