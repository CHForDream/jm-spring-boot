package com.game.generate.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import com.game.generate.bean.base.GiftBaseBean;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;
import com.google.common.collect.Lists;

import lombok.Getter;

/**
 * 文件名：GiftBean.java
 * <p>
 * 功能：gift.xls -> giftBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Getter
@Component
@DataDefine(configFileName = "gift.xls", name = "gift", sheetFileName = "gift")
public class GiftBean extends GiftBaseBean {
	private final List<ItemVo> rewardList = Lists.newArrayList();

	@Override
	public void initBean(String[] keyArray, String[] data) {
		super.initBean(keyArray, data);

		List<ItemVo> itemList = Lists.newArrayList();
		if (getItemType1() > 0 && getItemId1() > 0 && getItemNum1() > 0) {
			itemList.add(new ItemVo(getItemType1(), getItemId1(), getItemNum1()));
		}
		if (getItemType2() > 0 && getItemId2() > 0 && getItemNum2() > 0) {
			itemList.add(new ItemVo(getItemType2(), getItemId2(), getItemNum2()));
		}
		if (getItemType3() > 0 && getItemId3() > 0 && getItemNum3() > 0) {
			itemList.add(new ItemVo(getItemType3(), getItemId3(), getItemNum3()));
		}
		if (getItemType4() > 0 && getItemId4() > 0 && getItemNum4() > 0) {
			itemList.add(new ItemVo(getItemType4(), getItemId4(), getItemNum4()));
		}
		if (getItemType5() > 0 && getItemId5() > 0 && getItemNum5() > 0) {
			itemList.add(new ItemVo(getItemType5(), getItemId5(), getItemNum5()));
		}
		rewardList.addAll(ItemUtil.merge(itemList));
	}
}