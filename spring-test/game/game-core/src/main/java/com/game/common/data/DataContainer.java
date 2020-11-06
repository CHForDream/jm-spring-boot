package com.game.common.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.game.common.data.define.BaseBean;

public class DataContainer {
	/**
	 * 统一的数据Map容器
	 */
	private Map<Class<? extends BaseBean>, TreeMap<Integer, ? extends BaseBean>> mapDatas = new LinkedHashMap<Class<? extends BaseBean>, TreeMap<Integer, ? extends BaseBean>>();

	private static DataContainer dataContainer = null;

	public static DataContainer getInstance() {
		if (dataContainer == null) {
			dataContainer = new DataContainer();
		}
		return dataContainer;
	}

	private DataContainer() {
	}

	public Map<Class<? extends BaseBean>, TreeMap<Integer, ? extends BaseBean>> getMapDatas() {
		return mapDatas;
	}

	public void setMapDatas(Map<Class<? extends BaseBean>, TreeMap<Integer, ? extends BaseBean>> mapDatas) {
		this.mapDatas = mapDatas;
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseBean> TreeMap<Integer, T> getDataMap(Class<T> clazz) {
		if (!this.mapDatas.containsKey(clazz)) {
			return null;
		}
		return (TreeMap<Integer, T>) this.mapDatas.get(clazz);
	}

	public void addMapData(Class<? extends BaseBean> clazz, TreeMap<Integer, ? extends BaseBean> data) {
		if (this.mapDatas.containsKey(clazz)) {
			throw new IllegalArgumentException(String.format("Same name:[%s]", clazz.getName()));
		}
		this.mapDatas.put(clazz, data);
	}
}
