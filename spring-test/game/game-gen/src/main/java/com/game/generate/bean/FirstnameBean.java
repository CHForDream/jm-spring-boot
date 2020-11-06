package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.FirstnameBaseBean;

/**
 * 文件名：FirstnameBean.java
 * <p>
 * 功能：firstname.xls -> firstnameBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "firstname.xls", name = "firstname", sheetFileName = "firstname")
public class FirstnameBean extends FirstnameBaseBean {
}