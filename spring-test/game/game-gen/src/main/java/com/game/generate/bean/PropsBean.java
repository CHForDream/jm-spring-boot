package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.PropsBaseBean;

/**
 * 文件名：PropsBean.java
 * <p>
 * 功能：props.xls -> propsBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "props.xls", name = "props", sheetFileName = "props")
public class PropsBean extends PropsBaseBean {
}