package com.game.utils;

public class EmojiUtil {
	public static boolean containsEmoji(String source) {
		if (source == null || "".equals(source)) {
			return false;
		}
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isEmojiCharacter(codePoint)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	public static String filterEmoji(String source) {
		try {
			if (!containsEmoji(source)) {
				return source;
			}
			StringBuilder buf = null;
			int len = source.length();
			for (int i = 0; i < len; i++) {
				char codePoint = source.charAt(i);
				if (isEmojiCharacter(codePoint)) {
					if (buf == null) {
						buf = new StringBuilder(source.length());
					}
					buf.append(codePoint);
				}
			}
			//
			if (buf == null) {
				return source;
			} else {
				if (buf.length() == len) {
					buf = null;
					return source;
				} else {
					return buf.toString();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			source = "";
		}
		return source;
	}

	public static void main(String[] args) {
		System.out.println(filterEmoji("飞碟?"));
		System.out.println(filterEmoji("👲👳👷👸1123"));
	}

}
