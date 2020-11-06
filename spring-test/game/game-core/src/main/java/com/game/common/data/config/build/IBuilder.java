package com.game.common.data.config.build;

import com.game.common.data.define.BaseBean;

public interface IBuilder {
	/**
	 * 第一个构建，用于构建简单的bean
	 * 
	 * @param <B>       需要构建的bean
	 * @param data      原始数据
	 * @param beanClass bean的class
	 * 
	 * @throws Exception 构建出错时抛出错误
	 */
	public <B extends BaseBean> void initBuild(String[][] data, Class<B> beanClass) throws Exception;

	/**
	 * 完全全部第一个构建后，在做第二次关联构建
	 * 
	 * @throws Exception 构建出错时抛出错误
	 */
	public void lastBuild() throws Exception;
}
