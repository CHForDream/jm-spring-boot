package com.game.common.data.define;

import com.game.common.data.config.build.DataDefine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataConfigBean {
	// Bean
	private Class<? extends BaseBean> beanClass = null;

	private DataDefine dataDefine;
}
