package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.SteprewardBaseBean;

/**
 * 文件名：SteprewardBean.java
 * <p>
 * 功能：stepreward.xls -> steprewardBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "stepreward.xls", name = "stepreward", sheetFileName = "stepreward")
public class SteprewardBean extends SteprewardBaseBean {
}