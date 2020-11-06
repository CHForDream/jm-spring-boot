package com.game.common.data.config.build;

import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.game.common.data.define.BaseBean;
import com.game.common.data.define.DataConfigBean;
import com.game.global.Globals;

public class DefaultBuilder implements IBuilder {
	public <B extends BaseBean> void initBuild(String[][] data, Class<B> beanClass,
			Map<Class<? extends BaseBean>, TreeMap<Integer, ? extends BaseBean>> mapData) throws Exception {
		List<B> list = new ArrayList<B>();
		String[] row;
		B b;
		for (int i = 4; i < data.length; i++) {
			row = data[i];
			b = beanClass.newInstance();
			if (b instanceof IInitBean) {
				((IInitBean) b).initBean(data[0], row);
				list.add(b);
			} else {
				throw new IllegalClassFormatException(String.format("[%s] must implement IInitBean", beanClass.getName()));
			}
		}

		// 添加数据到容器中
		DataConfigBean dataConfigBean = Globals.getDataConfigContainer().getDataConfigBean(beanClass);
		if (dataConfigBean != null) {
			// 查找设置键值方法
			String getMethod = "getId";
			Method method = null;
			Method[] methods = beanClass.getMethods();
			for (Method _tmp : methods) {
				if (_tmp.getName().equalsIgnoreCase(getMethod)) {
					method = _tmp;
					break;
				}
			}

			// 获取键值
			if (method != null) {
				// 循环获取键值，并设置
				int key;
				TreeMap<Integer, B> beans = new TreeMap<Integer, B>();
				for (B bean : list) {
					key = (int) method.invoke(bean, new Object[0]);
					if (beans.containsKey(key)) {
						throw new IllegalArgumentException(String.format("Bean[%s] ->row's key[%s] exsit!", beanClass.getName(), key));
					} else {
						beans.put(key, bean);
					}
				}

				mapData.put(beanClass, beans);
			} else {
				throw new IllegalArgumentException(String.format("Method[%s]'s not fount in class[%s]", getMethod, beanClass.getName()));
			}
		}

	}

	@Override
	public void lastBuild() throws Exception {

	}

	public <B extends BaseBean> void initBuild(String[][] data, Class<B> beanClass) throws Exception {
		List<B> list = new ArrayList<B>();
		String[] row;
		B b;
		for (int i = 4; i < data.length; i++) {
			row = data[i];
			b = beanClass.newInstance();
			if (b instanceof IInitBean) {
				((IInitBean) b).initBean(data[0], row);
				list.add(b);
			} else {
				throw new IllegalClassFormatException(String.format("[%s] must implement IInitBean", beanClass.getName()));
			}
		}

		// 添加数据到容器中
		DataConfigBean dataConfigBean = Globals.getDataConfigContainer().getDataConfigBean(beanClass);
		if (dataConfigBean != null) {
			// 查找设置键值方法
			String getMethod = "getId";
			Method method = null;
			Method[] methods = beanClass.getMethods();
			for (Method _tmp : methods) {
				if (_tmp.getName().equalsIgnoreCase(getMethod)) {
					method = _tmp;
					break;
				}
			}

			// 获取键值
			if (method != null) {
				// 循环获取键值，并设置
				int key;
				TreeMap<Integer, B> beans = new TreeMap<Integer, B>();
				for (B bean : list) {
					key = (int) method.invoke(bean, new Object[0]);
					if (beans.containsKey(key)) {
						throw new IllegalArgumentException(String.format("Bean[%s] ->row's key[%s] exsit!", beanClass.getName(), key));
					} else {
						beans.put(key, bean);
					}
				}

				Globals.getDataContainer().addMapData(beanClass, beans);
			} else {
				throw new IllegalArgumentException(String.format("Method[%s]'s not fount in class[%s]", getMethod, beanClass.getName()));
			}
		}
	}
}
