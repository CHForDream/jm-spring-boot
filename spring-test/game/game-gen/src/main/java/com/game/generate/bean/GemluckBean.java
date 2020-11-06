package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.GemluckBaseBean;

/**
 * 文件名：GemluckBean.java
 * <p>
 * 功能：gemluck.xls -> gemluckBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "gemluck.xls", name = "gemluck", sheetFileName = "gemluck")
public class GemluckBean extends GemluckBaseBean {
}