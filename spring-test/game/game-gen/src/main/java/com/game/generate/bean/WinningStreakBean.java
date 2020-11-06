package com.game.generate.bean;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.WinningStreakBaseBean;
import com.game.utils.StringUtils;
import com.google.common.collect.Maps;

/**
 * 文件名：WinningStreakBean.java
 * <p>
 * 功能：winningStreak.xls -> winningStreakBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "winningStreak.xls", name = "winningStreak", sheetFileName = "winningStreak")
public class WinningStreakBean extends WinningStreakBaseBean {
	private final Map<Integer, Integer> chessMap = Maps.newHashMap();

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		if (!StringUtils.isEmpty(getCssProps())) {
			String[] cssPropsArr = getCssProps().split(";");
			for (String cssProp : cssPropsArr) {
				String[] chessArr = cssProp.split(",");
				int chessId = Integer.parseInt(chessArr[0]);
				int chessNum = Integer.parseInt(chessArr[1]);
				if (chessMap.containsKey(chessId)) {
					chessMap.put(chessId, chessMap.get(chessId) + chessNum);
				} else {
					chessMap.put(chessId, chessNum);
				}
			}
		}
	}

	public Map<Integer, Integer> getChessMap() {
		return chessMap;
	}
}