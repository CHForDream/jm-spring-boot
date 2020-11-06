package com.game.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Gm数据返回结构
 * 
 * @author lpf
 * @date june.18.2019
 *
 */
public class ResponseData<K, V> implements Map<K, V>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<K, V> map;

	public ResponseData() {
		super();
		this.map = new HashMap<K, V>();
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return map.entrySet();
	}

	@Override
	public V get(Object key) {
		return map.get(key);
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public V put(K key, V value) {
		return map.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		map.putAll(m);
	}

	@Override
	public V remove(Object key) {

		return map.remove(key);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	@Override
	public Collection<V> values() {
		return map.values();
	}

	public static ResponseData<String, Object> newBuild() {
		return new ResponseData<String, Object>();
	}

}
