package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ChatBaseBean;

/**
 * 文件名：ChatBean.java
 * <p>
 * 功能：chat.xls -> chatBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "chat.xls", name = "chat", sheetFileName = "chat")
public class ChatBean extends ChatBaseBean {
}