package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.constants.EliteConstants;
import com.game.generate.bean.base.EliteDuplicateBaseBean;

/**
 * 文件名：EliteDuplicateBean.java
 * <p>
 * 功能：eliteDuplicate.xls -> eliteDuplicateBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
//@DataDefine(configFileName = "eliteDuplicate.xls", name = "eliteDuplicate", sheetFileName = "eliteDuplicate")
public class EliteDuplicateBean extends EliteDuplicateBaseBean {
	public int getTargetIdByState(int state) {
		switch (state) {
		case EliteConstants.STATE_PROGRESS_1:
			return getTarget1();
		case EliteConstants.STATE_PROGRESS_2:
			return getTarget2();
		case EliteConstants.STATE_PROGRESS_3:
			return getTarget3();
		case EliteConstants.STATE_LOCK:
		case EliteConstants.STATE_NOT_START:
		default:
			return 0;
		}
	}
}