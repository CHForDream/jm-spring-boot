package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ScoreconfigBaseBean;

/**
 * 文件名：ScoreconfigBean.java
 * <p>
 * 功能：scoreconfig.xls -> scoreconfigBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "scoreconfig.xls", name = "scoreconfig", sheetFileName = "scoreconfig")
public class ScoreconfigBean extends ScoreconfigBaseBean {
}