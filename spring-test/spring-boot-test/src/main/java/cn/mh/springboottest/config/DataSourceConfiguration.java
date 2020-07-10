package cn.mh.springboottest.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//@Configuration
public class DataSourceConfiguration {
//	@Value("${database.name}")
//	private String databaseName;
//
//	@Value("${database.user}")
//	private String username;
//
//	@Value("${database.psw}")
//	private String psw;

//	@Bean
	public DataSource getDataSource() throws SQLException {
		return buildDataSource();
	}

	private DataSource buildDataSource() throws SQLException {

		// 配置真实数据源
		Map<String, DataSource> dataSourceMap = new HashMap<>();
		DriverManagerDataSource dataSource1 = new DriverManagerDataSource();
		
		// 配置第一个数据源
//		BasicDataSource dataSource1 = new BasicDataSource();
		dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource1.setUrl("jdbc:mysql://127.0.0.1:3306/game0?useUnicode=true&character_set_server=utf8mb4&useSSL=false&useServerPrepStmts=true");
		dataSource1.setUsername("root");
		dataSource1.setPassword("1234");
		dataSourceMap.put("ds0", dataSource1);

		// 配置第二个数据源
//		BasicDataSource dataSource2 = new BasicDataSource();
		DriverManagerDataSource dataSource2 = new DriverManagerDataSource();
		dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource2.setUrl("jdbc:mysql://127.0.0.1:3306/game1?useUnicode=true&character_set_server=utf8mb4&useSSL=false&useServerPrepStmts=true");
		dataSource2.setUsername("root");
		dataSource2.setPassword("1234");
		dataSourceMap.put("ds1", dataSource2);

		// 配置Order表规则
		TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("student", "ds${0..1}.student");

		// 配置分库 + 分表策略
		orderTableRuleConfig.setDatabaseShardingStrategyConfig(
				new InlineShardingStrategyConfiguration("stu_age", "ds${stu_age % 2}"));
//		orderTableRuleConfig.setTableShardingStrategyConfig(
//				new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 2}"));

		// 配置分片规则
		ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
		shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);


		// 获取数据源对象
		DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig,
				new Properties());
		return dataSource;
	}
}
