package com.game.utils;

public class BitMap {
	private static final int BIT_OFFSET = 3;
	private static final int BIT_COUNT = 8;

	private byte[] bits; //如果是byte那就一个只能存8个数
	private int min; //表示最小的那个数
	private int max; //表示最大的那个数

	public BitMap(int max) {
		this.min = 0;
		this.max = max;
		bits = new byte[(max >> BIT_OFFSET) + 1]; // max/8 + 1
	}

	public BitMap(int min, int max) {
		this.min = min;
		this.max = max;
		bits = new byte[((max - min) >> BIT_OFFSET) + 1]; // (max - min)/8 + 1
	}

	/**
	 * 添加数字
	 * 
	 * @param number
	 * @return true 添加成功; false, 其他.
	 */
	public boolean add(int number) {
		if (number < min || number > max) {
			return false;
		}

		int index = getIndex(number);
		int loc = getLocation(number);
		//接下来就是要把bit数组里面的 bisIndex这个下标的byte里面的 第loc 个bit位置为1
		bits[index] |= 1 << loc;
		return true;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public boolean delete(int number) { //从bitmap里面删除数字
		if (number < min || number > max) {
			return false;
		}

		int index = getIndex(number);
		int loc = getLocation(number);
		//接下来就是要把bit数组里面的 bisIndex这个下标的byte里面的 第loc 个bit位置为0
		bits[index] &= ~(1 << loc);
		return true;
	}

	public boolean has(int number) {
		if (number < min || number > max) {
			return false;
		}

		int index = getIndex(number);
		int loc = getLocation(number);
		int flag = bits[index] & (1 << loc); // 与对应bit位置做且运算.
		return flag != 0; // 若值为0, 则不存在; 不为0, 则存在
	}

	private int getIndex(int number) {
		return (number - min) >> BIT_OFFSET; // 除以8(右移3位), 获得byte所在数组的index
	}

	private int getLocation(int number) {
		return (number - min) % BIT_COUNT; // 对8取模, 获得byte中的那个bit
	}

	public static void main(String[] args) {
		BitMap bitMap = new BitMap(1000, 200000001); //8000 - 10亿
		System.out.println(bitMap.add(2));
		System.out.println(bitMap.add(3));
		System.out.println(bitMap.add(1064));
		System.out.println(bitMap.add(1065));

		System.out.println("----------------");

		System.out.println(bitMap.has(3));
		System.out.println(bitMap.has(1063));
		System.out.println(bitMap.has(1064));

		System.out.println("----------------");

		System.out.println(bitMap.delete(1064));
		System.out.println(bitMap.has(1064));
	}
}
