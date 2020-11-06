package com.game.generate.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.AchieveBaseBean;
import com.game.utils.StringUtils;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

/**
 * 文件名：AchieveBean.java
 * <p>
 * 功能：achieve.xls -> achieveBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "achieve.xls", name = "achieve", sheetFileName = "achieve")
public class AchieveBean extends AchieveBaseBean {
	private List<Integer> targetList = new ArrayList<Integer>();
	private List<ItemVo> awardList = new ArrayList<ItemVo>();

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		// 目标类型 目前主要指收集类型的成就的目标
		if (!StringUtils.isEmpty(this.getParam1())) {
			String[] target = this.getParam1().split(",");
			if (target != null) {
				for (int i = 0; i < target.length; i++) {
					targetList.add(Integer.parseInt(target[i]));
				}
			}
		}

		// 成就奖励
		awardList.addAll(ItemUtil.fromItemArrayString(this.getItem()));
	}

	public List<Integer> getTargetList() {
		return targetList;
	}

	public List<ItemVo> getAwardList() {
		return awardList;
	}
}