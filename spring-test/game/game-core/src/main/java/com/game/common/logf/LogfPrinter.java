package com.game.common.logf;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import com.game.global.Globals;
import com.game.utils.DateUtil;

public class LogfPrinter implements Runnable {

	private static final LogfPrinter printer = new LogfPrinter();

	private Map<String, Logger> loggerMap = new ConcurrentHashMap<String, Logger>();
	private LinkedBlockingQueue<ILogf> logQueue = new LinkedBlockingQueue<ILogf>();

	private LogfPrinter() {
	}

	public static LogfPrinter getInstance() {
		return printer;
	}

	public void push(ILogf content) {
		if (!Globals.getAppConfigBean().isLogOn()) {
			return;
		}

		if (this.logQueue.size() > 5000) {
			return;
		}

		try {
			logQueue.put(content);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		int num = 0;
		while (true) {
			try {
				ILogf logf = logQueue.take();
				printLog(logf);

				num++;
				if (num > 100) {
					Thread.sleep(5);
					num = 0;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void start() {
		new Thread(this, "Logf-thread").start();
	}

	private void printLog(ILogf logf) {
		String fileName = logf.getLogFile();
		int currDay = DateUtil.getDay(System.currentTimeMillis());
		Logger log = loggerMap.get(currDay + "-" + fileName);
		if (log == null) {
			Iterator<Entry<String, Logger>> it = loggerMap.entrySet().iterator();
			while (it.hasNext()) {
				try {
					Entry<String, Logger> entry = it.next();
					String key = entry.getKey();
					int day = Integer.parseInt(key.split("-")[0]);
					if (day != currDay) {
//						logMap.get(key).shutdown();
						it.remove();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			log = LogfPrinter.getInstance().createLogger(Globals.getAppConfigBean().getLogPath(), Globals.getAppConfigBean().getGssid() + "-" + fileName + "-"
					+ Globals.getAppConfigBean().getSid() + "-" + DateUtil.getDateStrDay(new Date()));

			loggerMap.put(DateUtil.getDay(System.currentTimeMillis()) + "-" + fileName, log);
		}
		log.error(logf.getContent());
	}

	private Logger createLogger(String filePath, String fileName) {
		// 生成新的Logger
		// 如果已经有了一个Logger实例则返回
		Logger logger = Logger.getLogger(fileName);
		// 清空Appender。特別是不想使用现存实例时一定要初期化
		// logger.removeAllAppenders();
		// 设计定Logger级别。
		logger.setLevel(Level.ERROR);
		// 设定是否继承父Logger。
		// 默认为true。继承root输出。
		// 设定false后将不输出root。
		logger.setAdditivity(false);
		// 生成新的Appender
		RollingFileAppender appender = new RollingFileAppender();
		// log的输出形式
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern("%m%n");
		appender.setLayout(layout);
		// log输出路径
		appender.setFile(filePath + fileName + ".log");
		// log的文字码
		appender.setEncoding("UTF-8");
		// true:在已存在log文件后面追加 false:新log覆盖以前的log
		appender.setAppend(true);
		// 适用当前配置
		appender.activateOptions();
		appender.setMaxFileSize("500MB");
		appender.setMaxBackupIndex(1000);

		// 将新的Appender加到Logger中
		logger.addAppender(appender);

		return logger;
	}

	@SuppressWarnings("unused")
	private void printLog2(ILogf logf) {
		String fileName = logf.getLogFile();
		Logger logger = loggerMap.get(fileName);
		if (logger == null) {
			logger = LogfPrinter.getInstance().createLogger2(Globals.getAppConfigBean().getLogPath(),
					Globals.getAppConfigBean().getGssid() + "-" + fileName + "-" + Globals.getAppConfigBean().getSid());
			loggerMap.put(fileName, logger);
		}
		String content = logf.getContent();
		logger.error(content);
	}

	private Logger createLogger2(String filePath, String fileName) {
		// 生成新的Logger
		// 如果已经有了一个Logger实例则返回
		Logger logger = Logger.getLogger(fileName);
		// 清空Appender。特別是不想使用现存实例时一定要初期化
		// logger.removeAllAppenders();
		// 设计定Logger级别。
		logger.setLevel(Level.ERROR);
		// 设定是否继承父Logger。
		// 默认为true。继承root输出。
		// 设定false后将不输出root。
		logger.setAdditivity(false);
		// 生成新的Appender
		DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();
		// log的输出形式
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern("%m%n");
		dailyRollingFileAppender.setLayout(layout);
		// log输出路径
		dailyRollingFileAppender.setFile(filePath + fileName + ".log");
		// log输出文件分隔日期格式
		dailyRollingFileAppender.setDatePattern("'-'yyyy-MM-dd-HH'.log'");
		// log的文字码
		dailyRollingFileAppender.setEncoding("UTF-8");
		// true:在已存在log文件后面追加 false:新log覆盖以前的log
		dailyRollingFileAppender.setAppend(true);
		// 适用当前配置
		dailyRollingFileAppender.activateOptions();
//		appender.setMaxFileSize("500MB");
//		appender.setMaxBackupIndex(1000);

		// 将新的Appender加到Logger中
		logger.addAppender(dailyRollingFileAppender);

		return logger;
	}
}
