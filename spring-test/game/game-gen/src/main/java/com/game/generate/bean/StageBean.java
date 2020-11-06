package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.StageBaseBean;

/**
 * 文件名：StageBean.java
 * <p>
 * 功能：stage.xls -> stageBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "stage.xls", name = "stage", sheetFileName = "stage")
public class StageBean extends StageBaseBean {
}