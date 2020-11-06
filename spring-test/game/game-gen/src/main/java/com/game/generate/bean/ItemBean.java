package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ItemBaseBean;

/**
 * 文件名：ItemBean.java
 * <p>
 * 功能：item.xls -> itemBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "item.xls", name = "item", sheetFileName = "item")
public class ItemBean extends ItemBaseBean {
}