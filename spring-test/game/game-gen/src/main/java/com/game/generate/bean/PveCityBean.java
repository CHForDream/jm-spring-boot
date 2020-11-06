package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.PveCityBaseBean;

/**
 * 文件名：PveCityBean.java
 * <p>
 * 功能：pve_city.xls -> pveCityBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "pve_city.xls", name = "pveCity", sheetFileName = "pveCity")
public class PveCityBean extends PveCityBaseBean {
}