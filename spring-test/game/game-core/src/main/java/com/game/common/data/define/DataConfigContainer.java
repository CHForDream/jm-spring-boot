package com.game.common.data.define;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataConfigContainer {
	private static DataConfigContainer dataConfigContainer = null;

	// 消息容器
	private Map<Class<? extends BaseBean>, DataConfigBean> beansConfig = new HashMap<Class<? extends BaseBean>, DataConfigBean>();

	private DataConfigContainer() {
	}

	public static DataConfigContainer getInstance() {
		if (dataConfigContainer == null) {
			dataConfigContainer = new DataConfigContainer();
		}
		return dataConfigContainer;
	}

	/**
	 * 添加数据配置
	 * 
	 * @param dataConfigBean 数据配置bean
	 */
	public void addDataConfig(DataConfigBean dataConfigBean) {
		beansConfig.put(dataConfigBean.getBeanClass(), dataConfigBean);
	}

	/**
	 * 根据消息id获取配置bean
	 * 
	 * @param beanClass bean的配置信息
	 * 
	 * @return 数据配置bean
	 */
	public DataConfigBean getDataConfigBean(Class<? extends BaseBean> beanClass) {
		return beansConfig.get(beanClass);
	}

	/**
	 * 获取所有需要加载数据的配置名
	 * 
	 * @return 返回所有数据的配置名
	 */
	public Set<Class<? extends BaseBean>> getAllDataConfigBean() {
		return beansConfig.keySet();
	}

	public void clear() {
		beansConfig.clear();
	}
}
