package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.GradingBaseBean;

/**
 * 文件名：GradingBean.java
 * <p>
 * 功能：grading.xls -> gradingBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "grading.xls", name = "grading", sheetFileName = "grading")
public class GradingBean extends GradingBaseBean {
}