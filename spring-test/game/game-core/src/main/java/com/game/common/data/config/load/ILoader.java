package com.game.common.data.config.load;

public interface ILoader<K, V> {

	public String[][] loadConfig(String configPath, String sheetName) throws Exception;
}
