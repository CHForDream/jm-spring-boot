package cn.mh.springboottest.sharding;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

public class AdminPreciseAlgorithm implements PreciseShardingAlgorithm<Integer> {

	
	
	
	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingValue) {
		String tmp = "";
		System.out.println(availableTargetNames);
		System.out.println(shardingValue);
		if (!shardingValue.getLogicTableName().equalsIgnoreCase("student")) {
			return "ds0";
		}
		
		for (String tableName : availableTargetNames) {
			if (shardingValue.getValue() < 5) {
				if (tableName.endsWith("0")) {
					tmp = tableName;
					break;
				}
			} else {
				if (tableName.endsWith("1")) {
					tmp = tableName;
					break;
				}
			}
		}

		System.out.println(tmp);
		return tmp;
	}

}
