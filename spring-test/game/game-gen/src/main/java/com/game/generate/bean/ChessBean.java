package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.ChessBaseBean;

/**
 * 文件名：ChessBean.java
 * <p>
 * 功能：chess.xls -> chessBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "chess.xls", name = "chess", sheetFileName = "chess")
public class ChessBean extends ChessBaseBean {
}