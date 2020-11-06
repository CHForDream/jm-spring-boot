package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ActivenessBaseBean;

/**
 * 文件名：ActivenessBean.java
 * <p>
 * 功能：activeness.xls -> activenessBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "activeness.xls", name = "activeness", sheetFileName = "activeness")
public class ActivenessBean extends ActivenessBaseBean {
}