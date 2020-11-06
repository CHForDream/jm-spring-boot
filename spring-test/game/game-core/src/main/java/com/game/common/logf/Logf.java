package com.game.common.logf;

import java.util.Date;
import java.util.UUID;

import com.game.common.user.UserBean;
import com.game.global.Globals;
import com.game.utils.DateUtil;

public class Logf implements ILogf {
	private String logType;// 日志类型
	private String logPath;// 日志文件夹
	private long uid;// 角色uid

	private String platformToken;// 平台Token
	private String passportId;// 平台Guid
	private int channel; // 渠道
	private int platform; // 平台

	private String[] params;

	private Logf() {
	}

	public static void log(LogfType logfType, long uid, UserBean user, String... params) {
		new Logf().print(logfType, uid, user, params);
	}

	public void print(LogfType logType, long uid, String platformToken, String passportId, int channel, int platform, String... params) {
		this.logType = logType.getLogfType();
		this.logPath = logType.getLogfFile();
		this.uid = uid;

		this.platformToken = platformToken;
		this.passportId = passportId;
		this.channel = channel;
		this.platform = platform;
		this.params = params;

		LogfPrinter.getInstance().push(this);
	}

	public void print(LogfType logType, long uid, UserBean user, String... params) {
		this.logType = logType.getLogfType();
		this.logPath = logType.getLogfFile();
		this.uid = uid;

		boolean online = (user != null);
		this.platformToken = online ? user.getPlatformToken() : "";
		this.passportId = online ? user.getPassportId() : "";
		this.channel = online ? user.getChannel() : 0;
		this.platform = online ? user.getPlatform() : 0;
		this.params = params;

		LogfPrinter.getInstance().push(this);
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
		sb.append(logType);
		if (params != null) {
			for (String param : params) {
				sb.append("|").append(param);
			}
		}

		return sb.toString();
	}

	@Override
	public String getLogFile() {
		return logPath;
	}
}
