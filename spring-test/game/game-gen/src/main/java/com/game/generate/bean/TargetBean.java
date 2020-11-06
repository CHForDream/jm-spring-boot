package com.game.generate.bean;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.TargetBaseBean;
import com.game.utils.RandomWeightVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 文件名：TargetBean.java
 * <p>
 * 功能：target.xls -> targetBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "target.xls", name = "target", sheetFileName = "target")
public class TargetBean extends TargetBaseBean {
	// 战前随机棋子
	private List<RandomWeightVO> randomChessList = Lists.newArrayList();

	// 战斗目标棋子
	private Map<Integer, Integer> targetMap = Maps.newHashMap();

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		initRandomChess();

		initTargetChess();
	}

	private void initTargetChess() {
		String[] bossTargets = getConditions().split(";");
		for (String temp : bossTargets) {
			if (temp == null || "".equals(temp)) {
				continue;
			}
			String[] chess = temp.split(",");
			if ("1".equals(chess[0])) {
				if (chess.length != 3) {
					continue;
				}
				targetMap.put(Integer.parseInt(chess[1].trim()), Integer.parseInt(chess[2].trim()));
			} else if ("2".equals(chess[0])) {
				if (chess.length != 4) {
					continue;
				}
				if (chess[1] != null) {
					String[] chessId = chess[1].split("#");
					if (chessId.length > 0) {
						targetMap.put(Integer.parseInt(chessId[0].trim()), Integer.parseInt(chess[3].trim()));
					}
				}
			}
		}
	}

	private void initRandomChess() {
		// 解析随机棋子数据
		// 32001,10;32002,10;32003,5;32004,2
		String randomChess = getRandomChess();
		if (randomChess == null || randomChess.equals("")) {
			return;
		}

		String[] split = randomChess.split(";");
		for (String chessStr : split) {
			String[] split2 = chessStr.split(",");
			try {
				randomChessList.add(new RandomWeightVO(Integer.parseInt(split2[0]), Integer.parseInt(split2[1])));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	public List<RandomWeightVO> getRandomChessList() {
		return randomChessList;
	}

	public Map<Integer, Integer> getTargetMap() {
		return targetMap;
	}
}