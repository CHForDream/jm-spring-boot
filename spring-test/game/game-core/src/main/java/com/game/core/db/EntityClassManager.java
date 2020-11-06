package com.game.core.db;

import java.util.List;

import javax.persistence.Table;

import com.game.utils.AnnotationUtils;
import com.google.common.collect.Lists;

public class EntityClassManager {
	private static final List<Class<?>> ENTITY_CLASS_LIST = Lists.newArrayList();

	public static List<Class<?>> getEntityClassList() {
		if (ENTITY_CLASS_LIST.isEmpty()) {
			synchronized (EntityClassManager.class) {
				if (ENTITY_CLASS_LIST.isEmpty()) {
					try {
						List<Class<?>> classList = AnnotationUtils.findBeanClasses("com.game.db.entity", Table.class);
						ENTITY_CLASS_LIST.addAll(classList);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return ENTITY_CLASS_LIST;
	}
}
