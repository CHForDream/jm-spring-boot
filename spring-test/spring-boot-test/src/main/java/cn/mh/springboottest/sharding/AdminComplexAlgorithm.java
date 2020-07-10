package cn.mh.springboottest.sharding;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

public class AdminComplexAlgorithm implements ComplexKeysShardingAlgorithm<Integer> {

	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Integer> shardingValue) {
		
		System.out.println(availableTargetNames);
		
		System.out.println(shardingValue);
		
		shardingValue.getColumnNameAndShardingValuesMap().values();
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
		for (String tableName : availableTargetNames) {
			result.add(tableName);
			break;
		}
		return result;
	}

}
