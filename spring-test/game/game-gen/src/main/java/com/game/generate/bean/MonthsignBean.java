package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.MonthsignBaseBean;

/**
 * 文件名：MonthsignBean.java
 * <p>
 * 功能：monthsign.xls -> monthsignBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "monthsign.xls", name = "monthsign", sheetFileName = "monthsign")
public class MonthsignBean extends MonthsignBaseBean {
}