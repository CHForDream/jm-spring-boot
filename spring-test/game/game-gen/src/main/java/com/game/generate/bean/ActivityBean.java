package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ActivityBaseBean;

/**
 * 文件名：ActivityBean.java
 * <p>
 * 功能：activity.xls -> activityBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "activity.xls", name = "activity", sheetFileName = "activity")
public class ActivityBean extends ActivityBaseBean {
}