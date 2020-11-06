package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ProjectBaseBean;

/**
 * 文件名：ProjectBean.java
 * <p>
 * 功能：project.xls -> projectBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "project.xls", name = "project", sheetFileName = "project")
public class ProjectBean extends ProjectBaseBean {
}