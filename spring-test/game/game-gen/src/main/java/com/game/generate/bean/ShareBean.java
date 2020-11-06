package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ShareBaseBean;

/**
 * 文件名：ShareBean.java
 * <p>
 * 功能：share.xls -> shareBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "share.xls", name = "share", sheetFileName = "share")
public class ShareBean extends ShareBaseBean {
}