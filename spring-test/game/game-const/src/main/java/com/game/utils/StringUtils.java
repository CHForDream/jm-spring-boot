package com.game.utils;

public class StringUtils {

	/**
	 * description: 使用 String.format 格式化数字，实现左侧补 0
	 * 
	 * @param num   需要格式化的数字
	 * @param digit 生成字符串长度（保留数字位数）
	 * @return String
	 */
	public static String fillString(int num, int digit) {
		// 0：表示前面补0
		// digit：表示保留数字位数
		// d：表示参数为正数类型
		return String.format("%0" + digit + "d", num);
	}

	/**
	 * 判断一个字符串是否为null或空字符串(不含字符, 或者只包含空格)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static String[] addBefore(String[] arr, String... str) {
		if (arr == null && str == null)
			return new String[] { "0" };
		if (arr == null || arr.length == 0 || arr[0].equals("null") || arr[0].length() == 0) {
			return str;
		}
		if (str == null || str.length == 0) {
			return arr;
		}
		int size = str.length; // 获取追加数组长度
		int newSize = size + arr.length; // 原数组长度加上追加的数据的总长度

		// 新建临时字符串数组
		String[] tmp = new String[newSize];
		// 在头部添加上需要追加的数据
		for (int i = 0; i < size; i++) {
			tmp[i] = str[i];
		}
		// 先遍历将原来的字符串数组数据添加到临时字符串数组
		for (int i = size; i < newSize; i++) {
			tmp[i] = arr[i - size];
		}
		return tmp; // 返回拼接完成的字符串数组
	}

	/**
	 * 是否是数字
	 * 
	 * @return
	 */
	public static boolean isNumeric(final String str) {
		// null or empty
		if (str == null || str.length() == 0) {
			return false;
		}

		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			try {
				Double.parseDouble(str);
				return true;
			} catch (NumberFormatException ex) {
				try {
					Float.parseFloat(str);
					return true;
				} catch (NumberFormatException exx) {
					return false;
				}
			}
		}
	}
}
