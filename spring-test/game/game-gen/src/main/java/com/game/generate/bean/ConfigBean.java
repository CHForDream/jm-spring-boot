package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ConfigBaseBean;

/**
 * 文件名：ConfigBean.java
 * <p>
 * 功能：config.xls -> configBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "config.xls", name = "config", sheetFileName = "config")
public class ConfigBean extends ConfigBaseBean {
}