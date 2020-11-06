package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.RedpointBaseBean;

/**
 * 文件名：RedpointBean.java
 * <p>
 * 功能：redpoint.xls -> redpointBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "redpoint.xls", name = "redpoint", sheetFileName = "redpoint")
public class RedpointBean extends RedpointBaseBean {
}