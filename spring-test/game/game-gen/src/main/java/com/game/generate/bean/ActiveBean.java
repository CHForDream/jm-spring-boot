package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ActiveBaseBean;

/**
 * 文件名：ActiveBean.java
 * <p>
 * 功能：active.xls -> activeBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "active.xls", name = "active", sheetFileName = "active")
public class ActiveBean extends ActiveBaseBean {
}