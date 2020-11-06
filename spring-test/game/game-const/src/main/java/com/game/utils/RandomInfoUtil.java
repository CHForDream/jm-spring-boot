package com.game.utils;

import java.util.Random;

public class RandomInfoUtil {

	public static final int MAN = 1;
	public static final int WOMAN = 2;

	public static Random random = new Random();

	public static final String[] surnameList = { "司馬", "歐陽", "端木", "上官", "獨孤", "夏侯", "尉遲", "赫連", "皇甫", "公孫", "慕容", "長孫", "宇文", "司徒", "軒轅", "百里", "呼延", "令狐",
			"諸葛", "南宮", "東方", "西門", "李", "王", "張", "劉", "陳", "楊", "趙", "黃", "周", "胡", "林", "梁", "宋", "鄭", "唐", "馮", "董", "程", "曹", "袁", "許", "沈", "曾", "彭", "呂",
			"蔣", "蔡", "魏", "葉", "杜", "夏", "汪", "田", "方", "石", "熊", "白", "秦", "江", "孟", "龍", "萬", "段", "雷", "武", "喬", "洪", "魯", "葛", "柳", "嶽", "梅", "辛", "耿",
			"關", "苗", "童", "項", "裴", "鮑", "霍", "甘", "景", "包", "柯", "阮", "華", "滕", "穆", "燕", "敖", "冷", "卓", "花", "藍", "楚", "荊" };

	public static final String[] forenameList_man_0 = { "峰", "不", "近", "小", "千", "萬", "百", "一", "求", "笑", "雙", "淩", "伯", "仲", "叔", "震", "飛", "曉", "昌", "霸", "沖",
			"志", "留", "九", "子", "立", "小", "雲", "文", "安", "博", "才", "光", "弘", "華", "清", "燦", "俊", "凱", "樂", "良", "明", "健", "輝", "天", "星", "永", "玉", "英", "真",
			"修", "義", "雪", "嘉", "成", "傲", "欣", "逸", "飄", "淩", "青", "火", "森", "傑", "思", "智", "辰", "元", "夕", "蒼", "勁", "巨", "瀟", "紫", "邪", "塵" };
	public static final String[] forenameList_man_1 = { "敗", "悔", "南", "寶", "仞", "刀", "斐", "德", "雲", "天", "仁", "嶽", "宵", "忌", "爵", "權", "敏", "陽", "狂", "冠", "康",
			"平", "香", "剛", "強", "凡", "邦", "福", "歌", "國", "和", "康", "瀾", "民", "寧", "然", "順", "翔", "晏", "宜", "怡", "易", "志", "雄", "佑", "斌", "河", "元", "墨", "松",
			"林", "之", "翔", "竹", "宇", "軒", "榮", "哲", "風", "霜", "山", "炎", "罡", "盛", "睿", "達", "洪", "武", "耀", "磊", "寒", "冰", "瀟", "痕", "嵐", "空" };
	public static final String[] forenameList_woman_0 = { "思", "冰", "夜", "癡", "依", "小", "香", "綠", "向", "映", "含", "曼", "春", "醉", "之", "新", "雨", "天", "如", "若",
			"涵", "亦", "采", "冬", "安", "芷", "綺", "雅", "飛", "又", "寒", "憶", "曉", "樂", "笑", "妙", "元", "碧", "翠", "初", "懷", "幻", "慕", "秋", "語", "覓", "幼", "靈", "傲",
			"冷", "沛", "念", "尋", "水", "紫", "易", "惜", "詩", "青", "雁", "盼", "爾", "以", "雪", "夏", "凝", "丹", "迎", "問", "宛", "夢", "憐", "聽", "巧", "凡", "靜" };
	public static final String[] forenameList_woman_1 = { "煙", "琴", "藍", "夢", "丹", "柳", "冬", "萍", "菱", "寒", "陽", "霜", "白", "絲", "南", "真", "露", "雲", "芙", "筠",
			"容", "香", "荷", "風", "兒", "雪", "巧", "蕾", "芹", "柔", "靈", "卉", "夏", "嵐", "蓉", "萱", "珍", "彤", "蕊", "曼", "凡", "蘭", "晴", "珊", "易", "青", "春", "玉", "瑤",
			"文", "雙", "竹", "凝", "桃", "菡", "綠", "楓", "梅", "旋", "山", "松", "之", "亦", "蝶", "蓮", "柏", "波", "安", "天", "薇", "海", "翠", "槐", "秋", "雁", "夜" };

	public static final String[] avatar_man_Array = { "1001", "1002", "1003", "1004", "1005" };
	public static final String[] avatar_woman_Array = { "1006", "1007", "1008", "1009", "1010" };

	public static String getName() {
		String name = "";
		int sex = random.nextInt(2) + 1;
		if (sex == MAN) {
			name = getMamName();
		} else {
			name = getWomanName();
		}
		return name;
	}

	public static String getMamName() {
		StringBuffer sb = new StringBuffer();
		sb.append(getWordByRandom(surnameList));
		int num = random.nextInt(4);
		switch (num) {
		case 0:// 第二个名字从forenameList_woman_0取
			sb.append(getWordByRandom(forenameList_woman_0));
			break;
		case 1:// 第二个名字从forenameList_woman_1取
			sb.append(getWordByRandom(forenameList_woman_1));
			break;
		case 2:// 第二个名字从forenameList_man_0取,第三个名字从forenameList_woman_1取
			sb.append(getWordByRandom(forenameList_woman_0));
			sb.append(getWordByRandom(forenameList_woman_1));
			break;
		default:// 第二个名字从forenameList_man_1取,第三个名字从forenameList_woman_2取
			sb.append(getWordByRandom(forenameList_woman_1));
			sb.append(getWordByRandom(forenameList_woman_0));
			break;
		}
		return sb.toString();
	}

	public static String getWomanName() {
		StringBuffer sb = new StringBuffer();
		sb.append(getWordByRandom(surnameList));
		int num = random.nextInt(4);
		switch (num) {
		case 0:// 第二个名字从forenameList_man_0取
			sb.append(getWordByRandom(forenameList_man_0));
			break;
		case 1:// 第二个名字从forenameList_man_1取
			sb.append(getWordByRandom(forenameList_man_1));
			break;
		case 2:// 第二个名字从forenameList_man_0取,第三个名字从forenameList_man_1取
			sb.append(getWordByRandom(forenameList_man_0));
			sb.append(getWordByRandom(forenameList_man_1));
			break;
		default:// 第二个名字从forenameList_man_1取,第三个名字从forenameList_man_2取
			sb.append(getWordByRandom(forenameList_man_1));
			sb.append(getWordByRandom(forenameList_man_0));
			break;
		}
		return sb.toString();
	}

	public static String getManAvatar() {
		return getWordByRandom(avatar_man_Array);
	}

	public static String getWomanAvatar() {
		return getWordByRandom(avatar_woman_Array);
	}

	public static String getWordByRandom(String[] wordArray) {
		int size = wordArray.length;
		int surnameIndex = random.nextInt(size);
		return wordArray[surnameIndex];
	}

	public static void main(String[] args) {
		String name = "";
		for (int i = 0; i < 10000; i++) {
			name = getName();
			if (name.contains("司馬")) {
			}

		}

	}

}
