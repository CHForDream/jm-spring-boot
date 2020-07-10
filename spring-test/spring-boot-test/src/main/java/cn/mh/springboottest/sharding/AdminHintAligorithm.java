package cn.mh.springboottest.sharding;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

public class AdminHintAligorithm implements HintShardingAlgorithm<Integer> {

	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames,
			HintShardingValue<Integer> shardingValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
