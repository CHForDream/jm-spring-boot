package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ChangeTypeBaseBean;

/**
 * 文件名：ChangeTypeBean.java
 * <p>
 * 功能：changeType.xls -> changeTypeBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "changeType.xls", name = "changeType", sheetFileName = "changeType")
public class ChangeTypeBean extends ChangeTypeBaseBean {
}