package org.canal.test.ali;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.alibaba.otter.canal.protocol.Message;

/**
 * 测试基类
 * 
 * @author jianghang 2013-4-15 下午04:17:12
 * @version 1.0.4
 */
@Component
public class CanalClient extends BaseCanalClient {

	protected void start() {
		Assert.notNull(connector, "connector is null");
		thread = new Thread(new Runnable() {

			public void run() {
				process();
			}
		});

		thread.setUncaughtExceptionHandler(handler);
		running = true;
		thread.start();
	}

	protected void stop() {
		if (!running) {
			return;
		}
		running = false;
		if (thread != null) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// ignore
			}
		}

		MDC.remove("destination");
	}

	protected void process() {
		int batchSize = 5 * 1024;
		MDC.put("destination", destination);
		connector.connect();
		connector.subscribe();
		connector.rollback();
		while (running) {
			try {

				while (running) {
					Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
					long batchId = message.getId();
					int size = message.getEntries().size();
					if (batchId == -1 || size == 0) {
					} else {
						printSummary(message, batchId, size);
						printEntry(message.getEntries());
					}

					if (batchId != -1) {
						connector.ack(batchId); // 提交确认
						// connector.rollback(batchId); // 处理失败, 回滚数据
					}
				}
			} catch (Exception e) {
				logger.error("process error!", e);
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e1) {
				}
			} finally {
				connector.disconnect();
				MDC.remove("destination");
			}
		}
	}

}
