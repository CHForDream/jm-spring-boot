package com.game.common.logf;

import java.util.Date;
import java.util.UUID;

import com.game.common.user.UserBean;
import com.game.global.Globals;
import com.game.utils.DateUtil;

import lombok.Data;

@Data
public class AbstractLog implements ILogf {
	private LogfType logfType;// 日志类型
	private long uid;// 角色uid

	private String platformToken;// 平台Token
	private String passportId;// 平台Guid
	private int channel; // 渠道
	private int platform; // 平台

	public AbstractLog(LogfType logfType, UserBean userbean) {
		this.logfType = logfType;
		if (userbean != null) {
			this.uid = userbean.getUid();
			this.platformToken = userbean.getPlatformToken();
			this.passportId = userbean.getPassportId();
			this.channel = userbean.getChannel();
			this.platform = userbean.getPlatform();
		} else {
			this.platformToken = "";
			this.passportId = "";
			this.channel = 0;
			this.platform = 0;
		}
	}

	@Override
	public String getContent() {
		StringBuffer sb = new StringBuffer();
		sb.append(DateUtil.getDateStr(new Date())).append("|");
		sb.append(Globals.getAppConfigBean().getSid()).append("|");
		sb.append(UUID.randomUUID().toString()).append("|");
		sb.append(platformToken).append("|");
		sb.append(passportId).append("|");
		sb.append(uid).append("|");
		sb.append(channel).append("|");
		sb.append(platform).append("|");
		sb.append(logfType.getLogfType());
		return sb.toString();
	}

	@Override
	public String getLogFile() {
		return logfType.getLogfFile();
	}
}
