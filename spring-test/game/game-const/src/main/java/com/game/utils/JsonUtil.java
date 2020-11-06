package com.game.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
	private static Gson _json = new Gson();

	public static String toJson(Object obj) {
		return _json.toJson(obj);
	}

	public static <T> T toObj(String json, Class<T> classType) {
		return _json.fromJson(json, classType);
	}

	public static <T> List<T> toList(String json, Class<T> classType) {
		List<T> listJson = _json.fromJson(json, new TypeToken<List<T>>() {
		}.getType());
		List<T> instanceList = new ArrayList<T>();
		for (T temp : listJson) {
			String str = _json.toJson(temp);
			T tempIns = _json.fromJson(str, classType);
			instanceList.add(tempIns);
		}
		return instanceList;
	}

	public static <T> Map<Integer, T> toMapIntegerKey(String json, Class<T> classValue) {
		Map<Integer, T> map = _json.fromJson(json, new TypeToken<Map<Integer, T>>() {
		}.getType());
		Map<Integer, T> resultMap = new TreeMap<Integer, T>();
		for (Map.Entry<Integer, T> entry : map.entrySet()) {
			Integer key = entry.getKey();
			String str = _json.toJson(entry.getValue());
			T value = _json.fromJson(str, classValue);
			resultMap.put(key, value);
		}
		return resultMap;
	}

	public static <T> Map<Integer, Map<Integer, T>> toMapMapKey(String json, Class<T> classValue) {
		Map<Integer, Map<Integer, T>> map = _json.fromJson(json, new TypeToken<Map<Integer, Map<Integer, T>>>() {
		}.getType());
		Map<Integer, Map<Integer, T>> resultMap = new TreeMap<Integer, Map<Integer, T>>();
		for (Map.Entry<Integer, Map<Integer, T>> entry : map.entrySet()) {
			Integer key = entry.getKey();
			String str = _json.toJson(entry.getValue());
			Map<Integer, T> subMap = _json.fromJson(str, new TypeToken<Map<Integer, T>>() {
			}.getType());
			Map<Integer, T> resultSubMap = new TreeMap<Integer, T>();
			for (Map.Entry<Integer, T> subEntry : subMap.entrySet()) {
				Integer subKey = subEntry.getKey();
				String str2 = _json.toJson(subEntry.getValue());
				T value = _json.fromJson(str2, classValue);
				resultSubMap.put(subKey, value);
			}
			resultMap.put(key, resultSubMap);
		}
		return resultMap;
	}

	public static <T> Map<Integer, List<T>> toMapListKey(String json, Class<T> classValue) {
		Map<Integer, List<T>> map = _json.fromJson(json, new TypeToken<Map<Integer, List<T>>>() {
		}.getType());
		Map<Integer, List<T>> resultMap = new TreeMap<Integer, List<T>>();
		for (Map.Entry<Integer, List<T>> entry : map.entrySet()) {
			Integer key = entry.getKey();
			String str = _json.toJson(entry.getValue());
			List<T> listJson = _json.fromJson(str, new TypeToken<List<T>>() {
			}.getType());
			List<T> instanceList = new ArrayList<T>();
			for (T temp : listJson) {
				String str2 = _json.toJson(temp);
				T tempIns = _json.fromJson(str2, classValue);
				instanceList.add(tempIns);
			}
			resultMap.put(key, instanceList);
		}
		return resultMap;
	}

	public static <T> Map<Long, T> toMapLongKey(String json, Class<T> classValue) {
		Map<Long, T> map = _json.fromJson(json, new TypeToken<Map<Long, T>>() {
		}.getType());
		Map<Long, T> resultMap = new TreeMap<Long, T>();
		for (Map.Entry<Long, T> entry : map.entrySet()) {
			Long key = entry.getKey();
			String str = _json.toJson(entry.getValue());
			T value = _json.fromJson(str, classValue);
			resultMap.put(key, value);
		}
		return resultMap;
	}

	public static <T> Map<String, T> toMapStringKey(String json, Class<T> classValue) {
		Map<String, T> map = _json.fromJson(json, new TypeToken<Map<String, T>>() {
		}.getType());
		Map<String, T> resultMap = new TreeMap<String, T>();
		for (Map.Entry<String, T> entry : map.entrySet()) {
			String key = entry.getKey();
			String str = _json.toJson(entry.getValue());
			T value = _json.fromJson(str, classValue);
			resultMap.put(key, value);
		}
		return resultMap;
	}

	public static void main(String[] args) {
//		Map<Integer, Map<Integer, Integer>> mmap = new HashMap<Integer, Map<Integer, Integer>>();
//		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//		map.put(1, 1);
//		map.put(2, 2);
//		mmap.put(1, map);
//		String str = toJson(mmap);
//		System.err.println(str);
//
//		Map<Integer, Map<Integer, Integer>> mmap1 = JsonUtil.toMapMapIntegerKey(str, Integer.class);
//
//		System.err.println(mmap1.toString());
//
//		Map<Integer, List<Integer>> lmap = new HashMap<Integer, List<Integer>>();
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(2);
//		lmap.put(1, list);
//		str = toJson(lmap);
//		System.err.println(str);
//
//		Map<Integer, List<Integer>> lmap1 = JsonUtil.toMapListIntegerKey(str, Integer.class);
//		System.err.println(lmap1.toString());

//		List<Integer> list = Lists.newArrayList();
//		list.add(1);
//		String json = toJson(list);
//		System.out.println(json);
//
//		List<Integer> list2 = toList(json, Integer.class);
//		System.out.println(list2);

		test1();
	}

	private static void test1() {
		Map<Integer, Map<Integer, Integer>> map1 = Maps.newHashMap();
		map1.put(1, Maps.newHashMap());
		map1.put(2, Maps.newHashMap());
		map1.put(3, Maps.newHashMap());
		map1.get(1).put(1, 1);
		map1.get(1).put(2, 2);
		map1.get(2).put(3, 3);
		map1.get(2).put(4, 4);
		map1.get(3).put(5, 5);
		map1.get(3).put(5, 5);
		System.out.println(map1);

		String json = toJson(map1);
		System.out.println("json = " + json);

		Map<Integer, Map<Integer, Integer>> map2 = toMapMapKey(json, Integer.class);
		System.out.println(map2);
	}
}
