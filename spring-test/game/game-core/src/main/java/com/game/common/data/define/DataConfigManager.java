package com.game.common.data.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.game.common.data.config.build.DataDefine;
import com.game.common.data.config.build.DefaultBuilder;
import com.game.common.data.config.build.IBuilder;
import com.game.common.data.config.check.IChecker;
import com.game.common.data.config.load.ILoader;
import com.game.constants.Loggers;
import com.game.global.Globals;

@SuppressWarnings("rawtypes")
public class DataConfigManager {
	// 日志
	private Logger log = Loggers.serverLogger;

	private static DataConfigManager dataConfigManager = null;
	private Map<String, HSSFSheet> xlsMap;

	public static DataConfigManager getInstance() {
		if (dataConfigManager == null) {
			synchronized (DataConfigManager.class) {
				if (dataConfigManager == null) {
					dataConfigManager = new DataConfigManager();
				}
			}
		}
		return dataConfigManager;
	}

	public Map<String, HSSFSheet> getXlsMap() {
		return xlsMap;
	}

	public void setXlsMap(Map<String, HSSFSheet> xlsMap) {
		this.xlsMap = xlsMap;
	}

	public void init() {

	}

	private DataConfigManager() {
		this.xlsMap = new HashMap<String, HSSFSheet>();
	}

	public void reload() {
		Set<Class<? extends BaseBean>> beanClasses = Globals.getDataConfigContainer().getAllDataConfigBean();
		Map<Class<? extends BaseBean>, TreeMap<Integer, ? extends BaseBean>> mapDatas = new LinkedHashMap<Class<? extends BaseBean>, TreeMap<Integer, ? extends BaseBean>>();

		for (Class<? extends BaseBean> clazz : beanClasses) {
			reLoadData(clazz, mapDatas);
		}
		Globals.getDataContainer().setMapDatas(mapDatas);
	}

	/**
	 * 加载基础数据配置
	 * 
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public void loadDataConfig(Map<String, Object> map) {
		// 清空容器
		Globals.getDataConfigContainer().clear();

		for (Object temp : map.values()) {
			initFile((Class<? extends BaseBean>) temp.getClass());
		}
	}

	/**
	 * 加载所有基础数据
	 */
	public void loadData() {
		Set<Class<? extends BaseBean>> beanClasses = Globals.getDataConfigContainer().getAllDataConfigBean();
		for (Class<? extends BaseBean> clazz : beanClasses) {
			this.loadData(clazz);
		}
	}

	public void loadData(Class<? extends BaseBean> clazz) {
		DataConfigBean dataConfigBean = Globals.getDataConfigContainer().getDataConfigBean(clazz);
		if (dataConfigBean != null) {
//			log.info(String.format("Load Data:File[%s] sheetName[%s] Bean[%s] Loader[%s] Builder[%s] Checker[%s]", dataConfigBean.getConfigFileName(),
//					dataConfigBean.getSheetFileName(), dataConfigBean.getBeanClass().getName(), dataConfigBean.getLoaderClass().getName(),
//					dataConfigBean.getBuildClass().getName(), dataConfigBean.getCheckClass().getName()));

			try {
				// 读取数据
				ILoader loader = dataConfigBean.getDataDefine().loaderClass().newInstance();
				String[][] dataOriginal = loader.loadConfig(dataConfigBean.getDataDefine().configFileName(), dataConfigBean.getDataDefine().sheetFileName());

				// 校验数据
				IChecker checker = dataConfigBean.getDataDefine().checkClass().newInstance();
				boolean back = checker.checkData(dataOriginal);
				if (!back) {
					throw new IllegalArgumentException(String.format("Check Data Error:File[%s] sheetName[%s] Bean[%s] Loader[%s] Builder[%s] Checker[%s]",
							dataConfigBean.getDataDefine().configFileName(), dataConfigBean.getDataDefine().sheetFileName(),
							dataConfigBean.getBeanClass().getName(), dataConfigBean.getDataDefine().loaderClass().getName(),
							dataConfigBean.getDataDefine().buildClass().getName(), dataConfigBean.getDataDefine().checkClass().getName()));
				}

				// 构建数据
				IBuilder builder = dataConfigBean.getDataDefine().buildClass().newInstance();
				builder.initBuild(dataOriginal, clazz);
			} catch (Exception e) {
				log.error("Load data failure! beanClassName=" + clazz.getName(), e);
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	/**
	 * 加载单个数据，指定要加载bean Class
	 * 
	 * @param beanClassName 要加载bean class
	 */
	public void reLoadData(Class<? extends BaseBean> clazz, Map<Class<? extends BaseBean>, TreeMap<Integer, ? extends BaseBean>> mapDatas) {
		DataConfigBean dataConfigBean = Globals.getDataConfigContainer().getDataConfigBean(clazz);
		if (dataConfigBean != null) {
			log.info(String.format("Load Data:File[%s] sheetName[%s] Bean[%s] Loader[%s] Builder[%s] Checker[%s]",
					dataConfigBean.getDataDefine().configFileName(), dataConfigBean.getDataDefine().sheetFileName(), dataConfigBean.getBeanClass().getName(),
					dataConfigBean.getDataDefine().loaderClass().getName(), dataConfigBean.getDataDefine().buildClass().getName(),
					dataConfigBean.getDataDefine().checkClass().getName()));

			try {
				// 读取数据
				ILoader loader = dataConfigBean.getDataDefine().loaderClass().newInstance();
				String[][] dataOriginal = loader.loadConfig(dataConfigBean.getDataDefine().configFileName(), dataConfigBean.getDataDefine().sheetFileName());

				// 校验数据
				IChecker checker = dataConfigBean.getDataDefine().checkClass().newInstance();
				boolean back = checker.checkData(dataOriginal);
				if (!back) {
					throw new IllegalArgumentException(String.format("Check Data Error:File[%s] sheetName[%s] Bean[%s] Loader[%s] Builder[%s] Checker[%s]",
							dataConfigBean.getDataDefine().configFileName(), dataConfigBean.getDataDefine().sheetFileName(),
							dataConfigBean.getBeanClass().getName(), dataConfigBean.getDataDefine().loaderClass().getName(),
							dataConfigBean.getDataDefine().buildClass().getName(), dataConfigBean.getDataDefine().checkClass().getName()));
				}

				// 构建数据
				new DefaultBuilder().initBuild(dataOriginal, dataConfigBean.getBeanClass(), mapDatas);
			} catch (Exception e) {
				log.error("Load data failure! beanClassName=" + clazz.getName(), e);
				e.printStackTrace();
				throw new IllegalArgumentException(String.format("Check Data Error:File[%s] sheetName[%s] Bean[%s] Loader[%s] Builder[%s] Checker[%s]",
						dataConfigBean.getDataDefine().configFileName(), dataConfigBean.getDataDefine().sheetFileName(),
						dataConfigBean.getBeanClass().getName(), dataConfigBean.getDataDefine().loaderClass().getName(),
						dataConfigBean.getDataDefine().buildClass().getName(), dataConfigBean.getDataDefine().checkClass().getName()));

			}
		}
	}

	/**
	 * 最后校验数据
	 * 
	 * @param beanClassName 要校验bean class
	 */
	public void lastBuild(String beanClassName) {
		// DataConfigBean dataConfigBean = Globals.getDataConfigContainer()
		// .getDataConfigBean(beanClassName);
		// if (dataConfigBean != null) {
		//
		// }
	}

	private void initFile(Class<? extends BaseBean> clazz) {
		try {
			if (clazz.isAnnotationPresent(DataDefine.class)) {
				DataDefine dataDefine = (DataDefine) clazz.getAnnotation(DataDefine.class);
				// 初始化配置bean
				DataConfigBean dataConfigBean = new DataConfigBean();
				dataConfigBean.setBeanClass(clazz);
				dataConfigBean.setDataDefine(dataDefine);
				// 保存配置
				Globals.getDataConfigContainer().addDataConfig(dataConfigBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(String.format("Class[%s] not found", clazz), e);
			System.exit(1);
		}
	}
}
