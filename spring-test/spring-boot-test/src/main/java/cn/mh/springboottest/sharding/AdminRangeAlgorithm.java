package cn.mh.springboottest.sharding;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

public class AdminRangeAlgorithm implements RangeShardingAlgorithm<Integer> {

	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames,
			RangeShardingValue<Integer> shardingValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
