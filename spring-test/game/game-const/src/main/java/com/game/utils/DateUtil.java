package com.game.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat DATE_FORMATTER_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

	public static String formatDateYYYYMMDD(long time) {
		return DATE_FORMATTER_YYYY_MM_DD.format(new Date(time));
	}

	public static long parseDate(String dateStr, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		long result = 0;
		try {
			Date date = df.parse(dateStr);
			result = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static long parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	public static long getTodayTime(int hour) {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR_OF_DAY, hour);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		return ca.getTimeInMillis();
	}

	public static int getDay(long dateMill) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateMill);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		return day;
	}

	public static boolean canRefresh(int oldDay, int refreshHour) {
		long now = System.currentTimeMillis();
		int nowDay = getDay(now);
		if (nowDay == oldDay) {
			return false;
		}
		if (nowDay - oldDay > 1) {
			return true;
		}

		if (nowDay - oldDay < 0) {
			int hour = getHour(now);
			if (hour >= refreshHour) {
				return true;
			}
			return false;
		}

		if (nowDay - oldDay == 1) {
			int hour = getHour(now);
			if (hour >= refreshHour) {
				return true;
			}
			return false;
		}
		return false;
	}

	public static int getHour(long dateMill) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateMill);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static String getDateStr(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String dateStr = df.format(date);
		return dateStr;
	}

	public static String getDateStrDay(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(date);
		return dateStr;
	}

	public static boolean isSameDay(long dateLong, String time) {
		boolean result = false;
		Calendar ca = getCalendarByTime(time);
		long now = System.currentTimeMillis();
		long end = ca.getTimeInMillis();
		long begin = 0;
		if (now > end) {
			begin = end;
			end += 24 * 60 * 60 * 1000;
		} else {
			begin = end - 24 * 60 * 60 * 1000;
		}
		if (dateLong >= begin && dateLong < end) {
			result = true;
		}
		return result;

	}

	public static boolean isSameDay(long m1, long m2) {
		int d1 = getDay(m1);
		int d2 = getDay(m2);
		return d1 == d2;
	}

	public static boolean isSameMonth(long m1, long m2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(m1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(m2);

		if (calendar.get(Calendar.YEAR) != calendar2.get(Calendar.YEAR)) {
			return false;
		}

		return calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
	}

	private static Calendar getCalendarByTime(String time) {
		String[] params = time.split(":");
		Calendar ca = Calendar.getInstance();
		if (params.length >= 1) {
			ca.set(Calendar.HOUR_OF_DAY, Integer.valueOf(params[0]));
		} else {
			ca.set(Calendar.HOUR_OF_DAY, 0);
		}
		if (params.length >= 2) {
			ca.set(Calendar.MINUTE, Integer.valueOf(params[1]));
		} else {
			ca.set(Calendar.MINUTE, 0);
		}
		if (params.length >= 3) {
			ca.set(Calendar.SECOND, Integer.valueOf(params[2]));
		} else {
			ca.set(Calendar.SECOND, 0);
		}
		return ca;
	}

	public static long getSurplusTime(String time) {
		long now = System.currentTimeMillis();
		Calendar ca = getCalendarByTime(time);
		long surplusTime = ca.getTimeInMillis() - now;
		if (surplusTime < 0) {
			surplusTime += 24 * 60 * 60 * 1000;
		}
		return surplusTime / 1000;
	}

	public static boolean isToday(long dateLong) {
		String time = "24:00:00";
		return isSameDay(dateLong, time);
	}

	public static int getRangeDays(long beginTime, long endTime) {
		int rangeDays = 0;
		Calendar ca1 = Calendar.getInstance();
		ca1.setTimeInMillis(beginTime);

		Calendar ca2 = Calendar.getInstance();
		ca2.setTimeInMillis(endTime);

		int year1 = ca1.get(Calendar.YEAR);
		int year2 = ca2.get(Calendar.YEAR);
		// 判断是否是同一年
		if (year1 == year2) {
			rangeDays = ca2.get(Calendar.DAY_OF_YEAR) - ca1.get(Calendar.DAY_OF_YEAR);
		} else if (year2 > year1) {
			int max = ca1.getActualMaximum(Calendar.DAY_OF_YEAR);
			int firstDay = max - ca1.get(Calendar.DAY_OF_YEAR);
			rangeDays = firstDay + ca2.get(Calendar.DAY_OF_YEAR);
			// 假如超过一年，则加上中间这些年的天数
			if (year2 - year1 > 1) {
				for (int i = year1 + 1; i < year2; i++) {
					Calendar ca = Calendar.getInstance();
					ca.set(Calendar.YEAR, i);
					int maxday = ca.getActualMaximum(Calendar.DAY_OF_YEAR);
					rangeDays += maxday;
				}
			}
		}

		return rangeDays;
	}

	/**
	 * 获取当天的零点时间戳
	 *
	 * @return 当天的零点时间戳
	 */
	public static int getTodayStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return (int) (calendar.getTime().getTime() / 1000);
	}

	/**
	 * 获取下次刷新任务的时间戳
	 */
	public static Date getDayRefreshTime(int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (hour > 0) {
			calendar.add(Calendar.HOUR_OF_DAY, -hour);
		}
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static boolean isTwoEndOfWeek() {
		Calendar ca1 = Calendar.getInstance();
		int c = ca1.get(Calendar.DAY_OF_WEEK);
		System.out.println(c);
		if (c == 7 || c == 1) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isTwoEndOfWeek());
	}
}
