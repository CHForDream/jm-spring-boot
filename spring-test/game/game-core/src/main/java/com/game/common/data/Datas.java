package com.game.common.data;

import java.util.Map.Entry;
import java.util.TreeMap;

import com.game.common.data.define.BaseBean;

public class Datas {
	public static <T extends BaseBean> TreeMap<Integer, T> getDataMap(Class<T> clazz) {
		return DataContainer.getInstance().getDataMap(clazz);
	}

	public static <T extends BaseBean> T get(Class<T> clazz, int id) {
		if (DataContainer.getInstance().getDataMap(clazz) == null) {
			return null;
		}
		return DataContainer.getInstance().getDataMap(clazz).get(id);
	}

	/**
	 * Returns the greatest id less than or equal to the given id.
	 * 
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return Returns the greatest id less than or equal to the given id,
	 *         or null if there is no such id.
	 */
	public static <T extends BaseBean> Integer getFloorId(Class<T> clazz, int id) {
		if (DataContainer.getInstance().getDataMap(clazz) == null) {
			return null;
		}
		return DataContainer.getInstance().getDataMap(clazz).floorKey(id);
	}

	/**
	 * Returns a BaseBean mapping associated with the greatest id less than or equal to the given id.
	 * 
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return Returns a value mapping associated with the greatest id less than or equal to the given id,
	 *         or null if there is no such id.
	 */
	public static <T extends BaseBean> T getFloorData(Class<T> clazz, int id) {
		if (DataContainer.getInstance().getDataMap(clazz) == null) {
			return null;
		}

		Entry<Integer, T> floorEntry = DataContainer.getInstance().getDataMap(clazz).floorEntry(id);
		if (floorEntry != null) {
			return floorEntry.getValue();
		}
		return null;
	}

	/**
	 * Returns the least key greater than or equal to the given id.
	 * 
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return Returns the least key greater than or equal to the given id,
	 *         or null if there is no such id.
	 */
	public static <T extends BaseBean> Integer getCeilingId(Class<T> clazz, int id) {
		if (DataContainer.getInstance().getDataMap(clazz) == null) {
			return null;
		}
		return DataContainer.getInstance().getDataMap(clazz).ceilingKey(id);
	}

	/**
	 * Returns a BaseBean mapping associated with the least id greater than or equal to the given id.
	 * 
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return Returns a value mapping associated with the least id greater than or equal to the given id,
	 *         or null if there is no such id.
	 */
	public static <T extends BaseBean> T getCeilingData(Class<T> clazz, int id) {
		if (DataContainer.getInstance().getDataMap(clazz) == null) {
			return null;
		}

		Entry<Integer, T> ceilingEntry = DataContainer.getInstance().getDataMap(clazz).ceilingEntry(id);
		if (ceilingEntry != null) {
			return ceilingEntry.getValue();
		}
		return null;
	}
}
