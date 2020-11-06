package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.PayBaseBean;

/**
 * 文件名：PayBean.java
 * <p>
 * 功能：pay.xls -> payBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "pay.xls", name = "pay", sheetFileName = "pay")
public class PayBean extends PayBaseBean {
}