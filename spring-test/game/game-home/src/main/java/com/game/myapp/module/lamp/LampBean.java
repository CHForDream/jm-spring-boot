package com.game.myapp.module.lamp;

import lombok.Data;

@Data
public class LampBean {
	private long id;
	private String content;
	private long startTime;// 开始时间
	private long endTime;// 结束时间
	private long interval;// 循环间隔(毫秒)
	private long lastTime;// 上次执行时间
}
