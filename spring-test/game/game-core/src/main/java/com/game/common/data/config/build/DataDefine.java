package com.game.common.data.config.build;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.game.common.data.config.check.DefaultChecker;
import com.game.common.data.config.check.IChecker;
import com.game.common.data.config.load.ExcelLoader;
import com.game.common.data.config.load.ILoader;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataDefine {
	// 配置文件的名字
	String configFileName();

	// 配置文件的sheet，如果使用CVS，留空
	String sheetFileName();

	// 数据名字，即sheet名
	String name();

	// 默认加载器
	@SuppressWarnings("rawtypes")
	Class<? extends ILoader> loaderClass() default ExcelLoader.class;

	// 默认构建器
	Class<? extends IBuilder> buildClass() default DefaultBuilder.class;

	// 默认校验器
	Class<? extends IChecker> checkClass() default DefaultChecker.class;

	// 是否启用
	boolean canUse() default true;
}
