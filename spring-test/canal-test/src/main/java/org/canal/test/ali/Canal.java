package org.canal.test.ali;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;

/**
 * 单机模式的测试例子
 * 
 * @author jianghang 2013-4-15 下午04:19:20
 * @version 1.0.4
 */
@Component
public class Canal implements ApplicationRunner {
	protected final static Logger logger = LoggerFactory.getLogger(Canal.class);
	@Autowired
	private CanalClient client;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(client.canalIp, client.canalport),
				client.destination,
				client.username,
				client.password);
		client.setConnector(connector);
		client.start();
		Runtime.getRuntime().addShutdownHook(new Thread() {

			public void run() {
				try {
					logger.info("## stop the canal client");
					client.stop();
				} catch (Throwable e) {
					logger.warn("##something goes wrong when stopping canal:", e);
				} finally {
					logger.info("## canal client is down.");
				}
			}

		});
	}
}
