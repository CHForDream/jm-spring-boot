package com.game.gm.handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.CMDConstants;
import com.game.db.entity.ItemEntity;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.vo.ResponseData;

public class CmdSelectRoleItemsHandler extends AbsGmContoller {

	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		ResponseData<String, Object> data = ResponseData.newBuild();
		long uid = getUid(request);
		if (uid == 0) {
			data.put(CMDConstants.KEY_STATE, CMDConstants.REASON_INVALID_UID);
			return data;
		}

		List<ItemEntity> itemsEntities = Globals.getEntityProxy().findAllByUidNotCache(ItemEntity.class, uid);
		List<Item> items = new ArrayList<CmdSelectRoleItemsHandler.Item>();
		for (ItemEntity entity : itemsEntities) {
			Item item = new Item(entity.getItemId(), entity.getNum());
			items.add(item);
		}
		data.put("items", items);
		data.put(CMDConstants.KEY_STATE, CMDConstants.STATE_SUCCESS);
		return data;
	}

	public static class Item implements Serializable {
		private static final long serialVersionUID = 1L;

		private int itemId;
		private int num;

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public Item(int itemId, int num) {
			super();
			this.itemId = itemId;
			this.num = num;
		}
	}
}
