package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ShowtimeBaseBean;

/**
 * 文件名：ShowtimeBean.java
 * <p>
 * 功能：showtime.xls -> showtimeBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "showtime.xls", name = "showtime", sheetFileName = "showtime")
public class ShowtimeBean extends ShowtimeBaseBean {
}