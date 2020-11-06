package com.game.core.hibernate.key;

import com.game.core.hibernate.orm.BaseEntity;

@SuppressWarnings("rawtypes")
public class EntityUtils {
	public static <ID> String getEntityUidKey(Class<? extends BaseEntity> clazz, ID id) throws IllegalAccessException {
		String key = null;
		if (id instanceof Long) {
			key = String.valueOf((Long) id);
		} else if (id instanceof Integer) {
			key = String.valueOf((Integer) id);
		} else if (id instanceof String) {
			key = (String) id;
		} else if (id instanceof LongLongPrimaryKey) {
			key = String.valueOf(((LongLongPrimaryKey) id).getUid());
		} else if (id instanceof LongIntPrimaryKey) {
			key = String.valueOf(((LongIntPrimaryKey) id).getUid());
		} else if (id instanceof LongStringPrimaryKey) {
			key = String.valueOf(((LongStringPrimaryKey) id).getUid());
		} else if (id instanceof IntIntPrimaryKey) {
			key = String.valueOf(((IntIntPrimaryKey) id).getUid());
		} else {
			throw new IllegalAccessException("Invalid id: " + id);
		}
		return clazz.getSimpleName() + "-" + key;
	}

	public static <ID> String getEntityDbStringKey(Class<? extends BaseEntity> clazz, ID id) throws IllegalAccessException {
		String key = null;
		if (id instanceof Long) {
			key = String.valueOf((Long) id);
		} else if (id instanceof Integer) {
			key = String.valueOf((Integer) id);
		} else if (id instanceof LongLongPrimaryKey) {
			LongLongPrimaryKey longLongPrimaryKey = (LongLongPrimaryKey) id;
			key = longLongPrimaryKey.getUid() + "-" + longLongPrimaryKey.getXid();
		} else if (id instanceof LongIntPrimaryKey) {
			LongIntPrimaryKey longIntPrimaryKey = (LongIntPrimaryKey) id;
			key = longIntPrimaryKey.getUid() + "-" + longIntPrimaryKey.getXid();
		} else if (id instanceof String) {
			key = (String) id;
		} else if (id instanceof LongStringPrimaryKey) {
			LongStringPrimaryKey longStringPrimaryKey = (LongStringPrimaryKey) id;
			key = longStringPrimaryKey.getUid() + "-" + longStringPrimaryKey.getXid();
		} else if (id instanceof IntIntPrimaryKey) {
			IntIntPrimaryKey intIntPrimaryKey = (IntIntPrimaryKey) id;
			key = intIntPrimaryKey.getUid() + "-" + intIntPrimaryKey.getXid();
		} else {
			throw new IllegalAccessException("Invalid id! class = " + clazz.getSimpleName() + ", id: " + id);
		}
		return clazz.getSimpleName() + "-" + key;
	}
}
