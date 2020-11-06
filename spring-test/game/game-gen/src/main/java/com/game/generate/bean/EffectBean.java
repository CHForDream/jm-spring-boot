package com.game.generate.bean;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.EffectBaseBean;

/**
 * 文件名：EffectBean.java
 * <p>
 * 功能：effect.xls -> effectBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "effect.xls", name = "effect", sheetFileName = "effect")
public class EffectBean extends EffectBaseBean {
}