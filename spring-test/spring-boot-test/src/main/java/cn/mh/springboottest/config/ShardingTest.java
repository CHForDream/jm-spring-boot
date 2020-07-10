//package cn.mh.springboottest.config;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
//import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
//
//import com.google.common.collect.Range;
//
//
//public class ShardingTest implements PreciseShardingAlgorithm<String>, RangeShardingAlgorithm<String> {
//    /**
//     * in/= 判断标准
//     *
//     * @param collection
//     * @param preciseShardingValue
//     * @return
//     */
//    @Override
//    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
//        String logicTableName = preciseShardingValue.getLogicTableName();
//        // 定义表名
//        String tableName = logicTableName + preciseShardingValue.getColumnName();
//        for (String name : collection) {
//            if (tableName.equals(name)) {
//                return tableName;
//            }
//        }
//        throw new IllegalArgumentException();
//    }
//
//    @Override
//    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
//        System.out.println(rangeShardingValue.getValueRange());
//        Range<String> valueRange = rangeShardingValue.getValueRange();
//        Date right = null;
//        Date left = null;
//        Range<Date> range;
//        if (left == null && right != null) {
//            range = Range.upTo(right, valueRange.upperBoundType());
//        } else if (right == null && left != null) {
//            range = Range.downTo(left, valueRange.lowerBoundType());
//        } else {
//            range = Range.range(right, valueRange.lowerBoundType(), left, valueRange.upperBoundType());
//        }
//        ArrayList<String> list = new ArrayList<>();
//        for (String tableOriginName : collection) {
//            String[] userorders = tableOriginName.split("userorder");
//            String tableTime = userorders[1];
//        }
//        return list;
//    }
//}
//
