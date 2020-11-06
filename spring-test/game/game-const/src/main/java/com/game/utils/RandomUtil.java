package com.game.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.T;

@SuppressWarnings("hiding")
public class RandomUtil {

	public static Random random = new Random();

	/**
	 * 返回随机的数字 1~upLimit
	 * 
	 * @param upLimit
	 * @return
	 */
	public static int getRandomInt(int upLimit) {
		if (upLimit <= 0) {
			return 0;
		} else {
			return random.nextInt(upLimit) + 1;
		}
	}

	/***
	 * 获取区间内数字
	 * 
	 * @param low
	 * @param high
	 * @return
	 */
	public static int random(long low, long high) {
		return (int) (low + (high - low + 0.9) * Math.random());
	}

	/***
	 * 获取权重列表中随机出的位置
	 * 
	 * @param proList
	 * @param totalPro
	 * @return
	 */
	public static int getRandomIndex(List<Integer> proList, long totalPro) {
		int index = 0;
		int end = 0;
		int rnd = random(1, totalPro);
		for (int j = 0; j < proList.size(); j++) {
			end = end + proList.get(j);
			if (rnd <= end) {
				index = j;
				break;
			}
		}
		return index;
	}

	public static <T extends Object> T getRandom(T[] objArr) {
		int length = objArr.length;
		int randomIndex = random(0, length - 1);
		return objArr[randomIndex];
	}

	public static int getRandom(int[] objArr) {
		int length = objArr.length;
		int randomIndex = random(0, length - 1);
		return objArr[randomIndex];
	}

	public static <E extends IWeight> E random(int min, List<E> randomList) {
		if (randomList == null || randomList.size() == 0) {
			return null;
		}
		int total = 0;
		List<Integer> weightList = new ArrayList<Integer>();
		for (int i = min - 1; i < randomList.size(); i++) {
			E temp = randomList.get(i);
			total += temp.getWeight();
			weightList.add(temp.getWeight());
		}
		int index = getRandomIndex(weightList, total);
		E bean = randomList.get(index + min - 1);
		return bean;
	}

	public static <E extends IWeight> E random(List<E> randomList) {
		if (randomList == null || randomList.isEmpty()) {
			return null;
		}

		int total = 0;
		List<Integer> weightList = new ArrayList<Integer>();
		for (int i = 0; i < randomList.size(); i++) {
			E temp = randomList.get(i);
			total += temp.getWeight();
			weightList.add(temp.getWeight());
		}
		int index = getRandomIndex(weightList, total - 1);
		E bean = randomList.get(index);
		return bean;
	}

	public static <E> List<E> random(List<E> randomList, int size) {
		List<E> result = new ArrayList<E>();
		if (randomList == null || randomList.size() == 0) {
			return result;
		}
		for (int i = 0; i < size; i++) {
			if (randomList.size() <= 0) {
				break;
			}
			int index = RandomUtil.random(0, randomList.size() - 1);
			result.add(randomList.get(index));
			randomList.remove(index);
		}

		return result;
	}

	public static <T> T hitObject(T[] randomList) {
		if (randomList == null || randomList.length == 0) {
			return null;
		}
		int index = RandomUtil.random(0, randomList.length - 1);
		return randomList[index];
	}

	public static T hitObject(List<T> randomList) {
		if (randomList == null || randomList.size() == 0) {
			return null;
		}
		int index = RandomUtil.random(0, randomList.size() - 1);
		return randomList.get(index);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			System.out.println(RandomUtil.getRandomInt(3));
		}
	}

	public static int randomNumber(int length) {
		int totalLength = length;
		int code = UUID.randomUUID().toString().hashCode();
		code = Math.abs(code);
		String codeStr = code + "";
		if (codeStr.length() < totalLength) {
			for (int j = 0; j < 100000; j++) {
				code = UUID.randomUUID().toString().hashCode();
				codeStr = code + "";
				if (codeStr.length() >= totalLength) {
					break;
				}
			}
		}
		if (codeStr.length() >= totalLength) {
			codeStr = codeStr.substring(codeStr.length() - length, codeStr.length());
		}
		int sub = length - codeStr.length();
		if (sub > 0) {
			for (int i = 0; i < sub; i++) {
				codeStr = String.valueOf(RandomUtil.random(0, 9)) + codeStr;
			}
		}
		if (codeStr.startsWith("0")) {
			codeStr = codeStr.replaceFirst("0", String.valueOf(RandomUtil.random(1, 9)));
		}
		return Integer.parseInt(codeStr);

	}

}
