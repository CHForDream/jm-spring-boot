package com.game.utils;

public class RandomWeightVO implements IWeight {
	private int key;
	private int weight;

	public RandomWeightVO(int key, int weight) {
		this.key = key;
		this.weight = weight;
	}

	public int getKey() {
		return key;
	}

	@Override
	public int getWeight() {
		return weight;
	}
}
