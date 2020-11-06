package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.EvaluationAddBaseBean;

/**
 * 文件名：EvaluationAddBean.java
 * <p>
 * 功能：evaluationAdd.xls -> evaluationAddBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "evaluationAdd.xls", name = "evaluationAdd", sheetFileName = "evaluationAdd")
public class EvaluationAddBean extends EvaluationAddBaseBean {
}