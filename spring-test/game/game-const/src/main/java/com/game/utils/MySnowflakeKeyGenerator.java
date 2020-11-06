package com.game.utils;

import java.util.Calendar;
import java.util.Properties;

import com.google.common.base.Preconditions;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class MySnowflakeKeyGenerator {
	/** 生成key起始时间戳(静态, 一旦确认不可修改) */
	public static final long EPOCH;
	/** 序列位数 */
	private static final long SEQUENCE_BITS = 10L;
	/** workId位数 */
	private static final long WORKER_ID_BITS = 10L;
	/** 序列位Mask */
	private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;
	/** workId左移位数 */
	private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;
	/** 时间戳左移位数 */
	private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;
	/** workId取值区间最大值(开区间, 不包含临界值) */
	private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;
	/** 默认workId(工作逻辑Id, 取值[0,WORKER_ID_MAX_VALUE)半闭半开区间) */
	private static final long WORKER_ID = 0;
	/** sequence的最大值振幅, sequence达到最大值振幅之后才归零. 默认值为1, 即时间戳变化即归零 */
	private static final int DEFAULT_VIBRATION_VALUE = 1;
	/** 最大容忍时间差毫秒数, 默认为10(没有时间同步服务, 单服不会出现时间差, 当前操作时间一定是在上次操作时间之后) */
	private static final int MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS = 10;

	/**
	 * 配置属性
	 * 
	 * @category worker.id {@link Long} 工作逻辑Id
	 * @category max.vibration.offset {@link Integer} 最大值振幅
	 * @category max.tolerate.time.difference.milliseconds {@link Integer} 最大容忍时间差毫秒数
	 */
	@Getter
	@Setter
	private Properties properties = new Properties();

	private int sequenceOffset = -1;
	private long sequence;
	private long lastMilliseconds;

	static {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2020, Calendar.JULY, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		EPOCH = calendar.getTimeInMillis();
	}

	private static MySnowflakeKeyGenerator mySnowflakeKeyGenerator = new MySnowflakeKeyGenerator();

	public static MySnowflakeKeyGenerator getInstance() {
		return mySnowflakeKeyGenerator;
	}

	private MySnowflakeKeyGenerator() {
	}

	public synchronized long generateKey() {
		long currentMilliseconds = System.currentTimeMillis();
		if (waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
			currentMilliseconds = System.currentTimeMillis();
		}
		if (lastMilliseconds == currentMilliseconds) {
			if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
				currentMilliseconds = waitUntilNextTime(currentMilliseconds);
			}
		} else {
			vibrateSequenceOffset();
			sequence = sequenceOffset;
		}
		lastMilliseconds = currentMilliseconds;
		return ((currentMilliseconds - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (getWorkerId() << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
	}

	@SneakyThrows
	private boolean waitTolerateTimeDifferenceIfNeed(final long currentMilliseconds) {
		if (lastMilliseconds <= currentMilliseconds) {
			return false;
		}
		long timeDifferenceMilliseconds = lastMilliseconds - currentMilliseconds;
		Preconditions.checkState(timeDifferenceMilliseconds < getMaxTolerateTimeDifferenceMilliseconds(),
				"Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastMilliseconds, currentMilliseconds);
		Thread.sleep(timeDifferenceMilliseconds);
		return true;
	}

	private long getWorkerId() {
		long result = Long.valueOf(properties.getProperty("worker.id", String.valueOf(WORKER_ID)));
		Preconditions.checkArgument(result >= 0L && result < WORKER_ID_MAX_VALUE);
		return result;
	}

	private int getMaxVibrationOffset() {
		int result = Integer.parseInt(properties.getProperty("max.vibration.offset", String.valueOf(DEFAULT_VIBRATION_VALUE)));
		Preconditions.checkArgument(result >= 0 && result <= SEQUENCE_MASK, "Illegal max vibration offset");
		return result;
	}

	private int getMaxTolerateTimeDifferenceMilliseconds() {
		return Integer.valueOf(properties.getProperty("max.tolerate.time.difference.milliseconds", String.valueOf(MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS)));
	}

	private long waitUntilNextTime(final long lastTime) {
		long result = System.currentTimeMillis();
		while (result <= lastTime) {
			result = System.currentTimeMillis();
		}
		return result;
	}

	private void vibrateSequenceOffset() {
		sequenceOffset = sequenceOffset >= getMaxVibrationOffset() ? 0 : sequenceOffset + 1;
	}
}
