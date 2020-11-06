package com.game.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class UuidGenerator {
	private static long _SECOND = 0;
	private static long _SECOND2 = 0;
	private static AtomicInteger SEEK_KEY = new AtomicInteger(0);
	private static AtomicInteger SEEK_KEY2 = new AtomicInteger(0);

	private UuidGenerator() {
	}

//	public static void main(String[] args) {
//		for (int i = 0; i < 1000; i++) {
//			System.out.println(generateUid(20999));
//		}
//	}

	public synchronized static long generateUid(int sid) {
		int seconds = (int) (System.currentTimeMillis() / 1000);
		int add = 0;
		if (seconds != _SECOND) {
			_SECOND = seconds;
			SEEK_KEY.set(0);
		} else {
			add = SEEK_KEY.addAndGet(1);
		}
		if (add > 999) {
			return generateUid(sid);
		}

		StringBuffer sb = new StringBuffer();
		sb.append(seconds);
		sb.append(StringUtils.fillString(sid, 3));
		sb.append(StringUtils.fillString(add, 3));
		return Long.parseLong(sb.toString());
	}

	public synchronized static long generateLongUuid() {
		int seconds = (int) (System.currentTimeMillis() / 1000);
		if (seconds != _SECOND2) {
			_SECOND2 = seconds;
			SEEK_KEY2.set(0);
		}
		int add = SEEK_KEY2.addAndGet(1);
		if (add > 999) {
			return generateLongUuid();
		}
		StringBuffer sb = new StringBuffer();
		sb.append(seconds);
		sb.append(StringUtils.fillString(add, 3));
		return Long.parseLong(sb.toString());
	}
}
