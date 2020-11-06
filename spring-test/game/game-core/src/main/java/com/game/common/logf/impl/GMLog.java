package com.game.common.logf.impl;

import java.util.ArrayList;
import java.util.List;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GMLog extends AbstractLog {
	private List<String> gmArray = new ArrayList<String>(); // gm命令组

	public GMLog(UserBean userbean) {
		super(LogfType.GM_TYPE, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(getGmContent());
		return sb.toString();
	}

	private String getGmContent() {
		String str = "";
		if (this.gmArray == null || gmArray.isEmpty()) {
			return str;
		}
		str = gmArray.toString();
		// 掐掉数组的左右括号
		return str.substring(1, str.length() - 1);
	}
}
