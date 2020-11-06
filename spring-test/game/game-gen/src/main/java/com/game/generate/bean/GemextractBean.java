package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.GemextractBaseBean;
import com.game.utils.IWeight;

/**
 * 文件名：GemextractBean.java
 * <p>
 * 功能：gemextract.xls -> gemextractBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "gemextract.xls", name = "gemextract", sheetFileName = "gemextract")
public class GemextractBean extends GemextractBaseBean implements IWeight {

	@Override
	public int getWeight() {
		return getRate();
	}
}